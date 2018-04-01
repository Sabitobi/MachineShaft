package pl.machineshaft.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.control.Button;
import javafx.stage.Stage;
import pl.machineshaft.main.Gradation;
import pl.machineshaft.main.InitialData;
import pl.machineshaft.main.OutputShaftEnd;
import pl.machineshaft.main.ShaftUnderTheBearing;
import pl.machineshaft.main.ShaftUnderTheSealant;

public class GradationController implements Initializable{

    @FXML
    private Canvas canvas;

    @FXML
    private Button buttonMain;

    @FXML
    private Button buttonCalculations;
    
    @FXML
    private InitialData id;
    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		configureButtons();
		configureCanvas();
		
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
		buttonCalculations.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				try {
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("/pl/machineshaft/view/CalculationsPane.fxml"));
					Parent loginViewParent = loader.load();
					Scene loginViewScene = new Scene(loginViewParent);
					
					CalculationsController controller = loader.getController();
					
				
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
	
	private void configureCanvas() {
			GraphicsContext gc = canvas.getGraphicsContext2D();
			gc.setLineDashes(10);
			gc.strokeLine(10, 200, 690, 200);
			
			
		}
	
	public void drawShaft(double[] cucumber) {
		drawOutputShaftEnd(cucumber);
		drawShaftUnderTheSealant(cucumber);
		drawShaftUnderTheBearing(cucumber);
	}
	
	private void drawShaftScheme(double[] cucumber) {
		OutputShaftEnd outputShaftEnd = Gradation.chooseOutputShaftEnd(cucumber);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		int scale = 650/cucumber.length;
		
	}
	
	private void drawOutputShaftEnd(double[] cucumber) {
		OutputShaftEnd outputShaftEnd = Gradation.chooseOutputShaftEnd(cucumber);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		int scale = 650/cucumber.length;
		gc.setLineDashes(null);
		gc.strokeRect(650-outputShaftEnd.getShortEnd()*scale, 200-(outputShaftEnd.getDiameter()/2)*scale, outputShaftEnd.getShortEnd()*scale, outputShaftEnd.getDiameter()*scale);
	}
	
	private void drawShaftUnderTheSealant(double[] cucumber) {
		OutputShaftEnd outputShaftEnd = Gradation.chooseOutputShaftEnd(cucumber);
		ShaftUnderTheSealant shaftUnderTheSealant = Gradation.chooseShaftUnderTheSealant(cucumber, outputShaftEnd);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		int scale = 650/cucumber.length;
		gc.setLineDashes(null);
		gc.strokeRect(650-(outputShaftEnd.getShortEnd()+3*shaftUnderTheSealant.getWidth())*scale, 200-shaftUnderTheSealant.getDiameter()/2*scale, 3*shaftUnderTheSealant.getWidth()*scale ,shaftUnderTheSealant.getDiameter()*scale);
	}
	
	private void drawShaftUnderTheBearing(double[] cucumber) {
		OutputShaftEnd outputShaftEnd = Gradation.chooseOutputShaftEnd(cucumber);
		ShaftUnderTheSealant shaftUnderTheSealant = Gradation.chooseShaftUnderTheSealant(cucumber, outputShaftEnd);
		ShaftUnderTheBearing shaftUnderTheBearing = Gradation.chooseShaftUnderTheBearing(cucumber, outputShaftEnd, shaftUnderTheSealant);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		int scale = 650/cucumber.length;
		gc.setLineDashes(null);
		gc.strokeRect(650-(outputShaftEnd.getShortEnd()+3*shaftUnderTheSealant.getWidth()+shaftUnderTheBearing.getWidth())*scale, 200-shaftUnderTheBearing.getDiameter()/2*scale, shaftUnderTheBearing.getWidth()*scale ,shaftUnderTheBearing.getDiameter()*scale);
	}
    
	public void setId(InitialData id) {
		this.id = id;
	}

}
