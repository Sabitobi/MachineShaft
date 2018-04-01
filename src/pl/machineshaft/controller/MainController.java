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
import javafx.scene.control.Button;
import javafx.stage.Stage;
import pl.machineshaft.main.Gradation;
import pl.machineshaft.main.InitialData;



public class MainController implements Initializable{

    @FXML
    private Button buttonData;

    @FXML
    private Button buttonCharts;

    @FXML
    private Button buttonGradation;

    @FXML
    private Button buttonCalculations;

    @FXML
    private Button buttonNormalizedElements;

    @FXML
    private Button buttonStandards;
    
    @FXML
    public InitialData id;
    
    @FXML
    public DataController dataController;
    


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		configureDataButton();
		
	}
	
	private void configureDataButton() {
		buttonData.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				try {
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("/pl/machineshaft/view/DataPane.fxml"));
					Parent loginViewParent = loader.load();
					Scene loginViewScene = new Scene(loginViewParent);
					DataController controller = loader.getController();
					
					if (id != null) {
						controller.configureTextFields(id);
						controller.setChoiceBox(id);
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
		buttonCharts.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				try {
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("/pl/machineshaft/view/ChartsPane.fxml"));
					Parent loginViewParent = loader.load();
					Scene loginViewScene = new Scene(loginViewParent);
					ChartsController controller = loader.getController();
					
					if (id != null) {
						controller.configureCharts(id);
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
		buttonGradation.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				try {
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("/pl/machineshaft/view/GradationPane.fxml"));
					Parent loginViewParent = loader.load();
					Scene loginViewScene = new Scene(loginViewParent);
					
					GradationController controller = loader.getController();
					
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

	
	public void setId(InitialData id) {
		this.id = id;
	}

}
