package application;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ToDoItemController {
	
	
	// Reference to TextField variable in To-Do List pop-up - For text input
    @FXML private TextField addItemTextField;
	
    // To-Do List - Update with 'TaskItem' objects later
	static ObservableList<String> taskList = FXCollections.observableArrayList();


	// Action for "Create Item" button in To-Do List pop-up
	// The code in the 'else' statement also closes the pop-up window
	public void createItem (ActionEvent event) throws IOException {
		
		String name = addItemTextField.getText();
		
		// Output error if any of the prompts are empty
		if (addItemTextField.getText().isEmpty())
			System.out.println("Error: invalid input");
		
		else {
			System.out.println(name);
					
			// Load Main Menu FXML and close To-Do pop-up window
			Parent thisParent = FXMLLoader.load(getClass().getResource("Add Todo Item.fxml"));
			Scene thisScene = new Scene(thisParent);		
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();	
			window.setScene(thisScene);
			window.close();
			System.out.println("Creating to-do item and exiting pop-up window");		
		}

		
		
			

		
	}
	
	public static void createTask(String name) {
		
		String newTask = name;
		
		taskList.add(newTask);
	}
	


}
