package application;

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ErrorMessageController {

	//public static String message;
	
	
	@FXML private Label errorMessageLabel;
	

	
	
	public void setMessage(String message) {
		errorMessageLabel.setText(message);
	}
	
	public void closeMessageButton (ActionEvent event) throws IOException{
		// Load Main Menu FXML and close To-Do pop-up window
		Parent thisParent = FXMLLoader.load(getClass().getResource("Error Message Popup.fxml"));
		Scene thisScene = new Scene(thisParent);		
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();	
		window.setScene(thisScene);
		window.close();
		System.out.println("Exiting Error Message Popup");	
	}



	
}
