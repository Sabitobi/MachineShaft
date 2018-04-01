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

public class CalculationsController implements Initializable{

    @FXML
    private Button buttonMain;

    @FXML
    private Button buttonNormalizedElements;

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

}