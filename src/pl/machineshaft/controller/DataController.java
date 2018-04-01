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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import pl.machineshaft.main.InitialData;
import pl.machineshaft.main.Material;

public class DataController implements Initializable {

	@FXML
	private TextField textFieldF1;

	@FXML
	private TextField textFieldFw1;

	@FXML
	private TextField textFieldFp1;

	@FXML
	private TextField textFieldF2;

	@FXML
	private TextField textFieldFp2;

	@FXML
	private TextField textFieldL;

	@FXML
	private TextField textFieldL1;

	@FXML
	private TextField textFieldL2;

	@FXML
	private TextField textFieldD1;

	@FXML
	private TextField textFieldD2;

	@FXML
	private Button buttonMenu;

	@FXML
	private Button buttonCharts;

	@FXML
	private Label label1;

	@FXML
	private ChoiceBox<Material> choiceBoxMaterial;

	@FXML
	ChartsController chartsController;

	@FXML
	MainController mainController;

	@FXML
	InitialData id;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		configureButtons();
		configureChoiceBox();
	}

	private void configureButtons() {

		buttonMenu.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("/pl/machineshaft/view/MainPane.fxml"));
					Parent loginViewParent = loader.load();
					Scene loginViewScene = new Scene(loginViewParent);

					id = new InitialData(Integer.valueOf(textFieldF1.getText()),
							Integer.valueOf(textFieldFw1.getText()), Integer.valueOf(textFieldFp1.getText()),
							Integer.valueOf(textFieldF2.getText()), Integer.valueOf(textFieldFp2.getText()),
							Integer.valueOf(textFieldL.getText()), Integer.valueOf(textFieldL1.getText()),
							Integer.valueOf(textFieldL2.getText()), Integer.valueOf(textFieldD1.getText()),
							Integer.valueOf(textFieldD2.getText()), choiceBoxMaterial.getValue());

					MainController controller = loader.getController();

					if (chackDataCorrectness(id)) {
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

		buttonCharts.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				InitialData id = new InitialData(Integer.valueOf(textFieldF1.getText()),
						Integer.valueOf(textFieldFw1.getText()), Integer.valueOf(textFieldFp1.getText()),
						Integer.valueOf(textFieldF2.getText()), Integer.valueOf(textFieldFp2.getText()),
						Integer.valueOf(textFieldL.getText()), Integer.valueOf(textFieldL1.getText()),
						Integer.valueOf(textFieldL2.getText()), Integer.valueOf(textFieldD1.getText()),
						Integer.valueOf(textFieldD2.getText()), choiceBoxMaterial.getValue());

				if (chackDataCorrectness(id)) {

					

					

					try {
						FXMLLoader loader = new FXMLLoader();
						loader.setLocation(getClass().getResource("/pl/machineshaft/view/ChartsPane.fxml"));
						Parent loginViewParent = loader.load();
						Scene loginViewScene = new Scene(loginViewParent);
						ChartsController controller = loader.getController();
						controller.configureCharts(id);

						controller.setId(id);

						Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
						window.setScene(loginViewScene);
						window.show();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		});
	}

	private boolean chackDataCorrectness(InitialData id) {
		boolean result = true;
		StringBuilder sb = new StringBuilder();
		sb.append("Wykryto b³¹d danych wejœciowych:");
		sb.append("\n");
		if (!(id.getLenght() > id.getLenght1() && id.getLenght1() > id.getLenght2())) {
			result = false;
			sb.append("Odleg³oœci musz¹ spe³niaæ zale¿noœæ: l > l1 > l2");
			sb.append("\n");
			label1.resize(label1.getWidth(), 200);
		}
		if (id.getForce1() * id.getDiameter1() != id.getForce2() * id.getDiameter2()) {
			result = false;
			sb.append("Dane wejœciowe musz¹ spe³niaæ warunek F1*d1=F2*d2");
			sb.append("\n");
		}

		if (id.getDiameter1() == 0 || id.getDiameter2() == 0 || id.getLenght() == 0 || id.getLenght1() == 0
				|| id.getLenght2() == 0) {
			result = false;
			sb.append("Dane geometryczne nie mog¹ byæ zerowe");

		}

		label1.setTextFill(Paint.valueOf("C42025"));
		label1.setText(sb.toString());
		return result;
	}
	
	private void configureChoiceBox() {
		choiceBoxMaterial.getItems().setAll(Material.values());
		choiceBoxMaterial.setValue(Material.st5);
		
	}

	public void setChoiceBox(InitialData id) {
		choiceBoxMaterial.getItems().setAll(Material.values());
		if (id.getMaterial() != null) {
			choiceBoxMaterial.setValue(id.getMaterial());
		} else {
			choiceBoxMaterial.setValue(Material.st5);
		}
	}

	public void configureTextFields(InitialData id) {
		textFieldD1.setText(String.valueOf(id.getDiameter1()));
		textFieldD2.setText(String.valueOf(id.getDiameter2()));
		textFieldF1.setText(String.valueOf(id.getForce1()));
		textFieldF2.setText(String.valueOf(id.getForce2()));
		textFieldFp1.setText(String.valueOf(id.getTransverseForce1()));
		textFieldFp2.setText(String.valueOf(id.getTransverseForce2()));
		textFieldFw1.setText(String.valueOf(id.getLongitudinalForce1()));
		textFieldL.setText(String.valueOf(id.getLenght()));
		textFieldL1.setText(String.valueOf(id.getLenght1()));
		textFieldL2.setText(String.valueOf(id.getLenght2()));
	}

}
