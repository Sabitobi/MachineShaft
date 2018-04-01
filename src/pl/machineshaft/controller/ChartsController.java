package pl.machineshaft.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.resources.JFreeChartResources;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.TriangleMesh;
import javafx.stage.Stage;
import pl.machineshaft.main.BendingMoments;
import pl.machineshaft.main.Gradation;
import pl.machineshaft.main.InitialData;

public class ChartsController implements Initializable{

    @FXML
    private Tab tab1;

    @FXML
    private AreaChart<Integer, Double> areaChart1;

    @FXML
    private Tab tab2;

    @FXML
    private AreaChart<Integer, Double> areaChart2;

    @FXML
    private Tab tab3;

    @FXML
    private AreaChart<Integer, Double> areaChart3;

    @FXML
    private Tab tab4;

    @FXML
    private AreaChart<Integer, Double> areaChart4;

    @FXML
    private Tab tab5;

    @FXML
    private AreaChart<Integer, Double> areaChart5;

    @FXML
    private Tab tab6;

    @FXML
    private AreaChart<Integer, Double> areaChart6;
    
    @FXML
    private Tab tab7;

    @FXML
    private AreaChart<Integer, Double> areaChart7;
    
    @FXML
    private NumberAxis xAxis7;

    @FXML
    private NumberAxis yAxis7;

    @FXML
    private Button buttonMain;

    @FXML
    private Button buttonGradation;
    
    @FXML
    private ImageView imageArrowRight;

    @FXML
    private ImageView imageArrowTop;
    
    @FXML
    public InitialData id;
    
    @FXML
    public double [] cucumber;
    
    @FXML
    MainController mainController;
    
    
    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		configureButtons();

	}
	
	private void configureButtons() {
		buttonMain.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				try {
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("/pl/machineshaft/view/MainPane.fxml"));
					Parent loginViewParent = loader.load();
					Scene loginViewScene = new Scene(loginViewParent);
					
					MainController controller = loader.getController();
					controller.setId(id);
					

					Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
					window.setScene(loginViewScene);
					window.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		buttonGradation.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				try {
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("/pl/machineshaft/view/GradationPane.fxml"));
					Parent loginViewParent = loader.load();
					Scene loginViewScene = new Scene(loginViewParent);
					
					GradationController controller = loader.getController();
					controller.drawShaft(cucumber);
					
					if (id != null) {
						controller.setId(id);
					}
					Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
					window.setScene(loginViewScene);
					window.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
	}
	
	public void configureCharts(InitialData id) {
		
		BendingMoments bm = new BendingMoments();
		
		double[] planeZY = bm.calculateZY(id);
		double[] planeXY = bm.calculateXY(id);
		double[] resultantBendingMoment = bm.calculateResultantBendingMoment(planeZY, planeXY);
		double[] torque = bm.calculateTorque(id);
		double[] reducedTorque = bm.calculateReducedTorque(torque, id.getMaterial());
		double[] replacementMoment = bm.calculateReplacementMoment(resultantBendingMoment, reducedTorque);
		double[] calculationMoment = bm.calculateCalculationMoment(replacementMoment, id);
		double[] cucumber  = bm.calculateCucumber(calculationMoment, id);
		
		setCucumber(cucumber);
		
		areaChart1.getData().clear();
		XYChart.Series<Integer, Double> series = new XYChart.Series<>();
			for (int i = 0; i < planeZY.length; i++) {
				series.getData().add(new XYChart.Data<>(i, planeZY[i]));
			}
		areaChart1.getData().add(series);
		areaChart1.createSymbolsProperty().set(false);
		
		areaChart2.getData().clear();
		XYChart.Series<Integer, Double> series2 = new XYChart.Series<>();
			for (int i = 0; i < planeXY.length; i++) {
				series2.getData().add(new XYChart.Data<>(i, planeXY[i]));
			}
		areaChart2.getData().add(series2);
		areaChart2.createSymbolsProperty().set(false);
		
		
		areaChart3.getData().clear();
		XYChart.Series<Integer, Double> series3 = new XYChart.Series<>();
			for (int i = 0; i < planeXY.length; i++) {
				series3.getData().add(new XYChart.Data<>(i, resultantBendingMoment[i]));
			}
		areaChart3.getData().add(series3);
		areaChart3.createSymbolsProperty().set(false);
		
		areaChart4.getData().clear();
		XYChart.Series<Integer, Double> series4 = new XYChart.Series<>();
			for (int i = 0; i < planeXY.length; i++) {
				series4.getData().add(new XYChart.Data<>(i, torque[i]));
			}
		areaChart4.getData().add(series4);
		areaChart4.createSymbolsProperty().set(false);
		
		areaChart5.getData().clear();
		XYChart.Series<Integer, Double> series5 = new XYChart.Series<>();
			for (int i = 0; i < planeXY.length; i++) {
				series5.getData().add(new XYChart.Data<>(i, reducedTorque[i]));
			}
		areaChart5.getData().add(series5);
		areaChart5.createSymbolsProperty().set(false);
		
		areaChart6.getData().clear();
		XYChart.Series<Integer, Double> series6 = new XYChart.Series<>();
		XYChart.Series<Integer, Double> series7 = new XYChart.Series<>();
		
				for (int i = 0; i < reducedTorque.length; i++) {
					series6.getData().add(new XYChart.Data<>(i, replacementMoment[i]));
					series7.getData().add(new XYChart.Data<>(i, calculationMoment[i]));
				}
		areaChart6.getData().add(series6);
		areaChart6.getData().add(series7);
		areaChart6.createSymbolsProperty().set(false);
		
		areaChart7.getData().clear();
		XYChart.Series<Integer, Double> series8 = new XYChart.Series<>();
		XYChart.Series<Integer, Double> series9 = new XYChart.Series<>();
			for (int i = 0; i < cucumber.length; i++) {
				series8.getData().add(new XYChart.Data<>(i, cucumber[i]));
			}
			for (int i = 0; i < cucumber.length; i++) {
				series9.getData().add(new XYChart.Data<>(i, - cucumber[i]));
			}
		areaChart7.getData().add(series8);
		areaChart7.getData().add(series9);
		
		xAxis7.setAutoRanging(false);
	    xAxis7.setLowerBound(-10);
	    xAxis7.setUpperBound(cucumber.length+10);
	    xAxis7.setTickUnit(10);
	    
	    
	    yAxis7.setAutoRanging(false);
	    yAxis7.setLowerBound(-cucumber.length/2);
	    yAxis7.setUpperBound(cucumber.length/2);
	    yAxis7.setTickUnit(10);
	    areaChart7.createSymbolsProperty().set(false);
	    	    
	    
	    imageArrowTop.setTranslateX(3);
	    imageArrowRight.setTranslateY(-27);
	    
	    
	}
	
	public void setId(InitialData id) {
		this.id = id;
		
	}
	
	public void setCucumber(double[] cucumber) {
		this.cucumber = cucumber;
	}
	
	
	
	

}


