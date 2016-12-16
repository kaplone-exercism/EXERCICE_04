package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Settings;

public class ControlleurSettings implements Initializable {
	
	@FXML
	ColorPicker colorPicker;
	
	@FXML
	CheckBox moveOnContact;
	
	@FXML
	CheckBox changeOnContact;
	
	@FXML
	CheckBox affCoordSouris;
	
	@FXML
	CheckBox AffInfosMur;
	
	Stage stage;
	Main_Exercice_04 main;
	
	
	
	public Scene init(AnchorPane root, Main_Exercice_04 main_Exercice_04) {
		
		main = main_Exercice_04;
		stage = (Stage) root.getScene().getWindow();
		
		Scene scene = new Scene((Parent) JfxUtils.loadFxml("settings.fxml"), 800, 500);
		
		Button versMenu = new Button("Menu principal");
		versMenu.setLayoutX(450);
		versMenu.setLayoutY(350);
		versMenu.setOnAction(a -> main.retourMenu());
		
		((AnchorPane) scene.getRoot()).getChildren().add(versMenu);

		return scene;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		colorPicker.setValue(Settings.getCouleurMurs());
		colorPicker.setOnAction( a -> Settings.setCouleurMurs(colorPicker.getValue()));
		
		moveOnContact.setSelected(Settings.isRetourContact());
		moveOnContact.setOnAction(a -> Settings.setRetourContact(moveOnContact.isSelected()));
		
		affCoordSouris.setSelected(Settings.isAffPositionSouris());
		affCoordSouris.setOnAction(a -> Settings.setAffPositionSouris(affCoordSouris.isSelected()));
		
		AffInfosMur.setSelected(Settings.isAffInfosMurs());
		AffInfosMur.setOnAction(a -> Settings.setAffInfosMurs(AffInfosMur.isSelected()));
		
	}

}
