package application;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class NewTaskController implements Initializable {
	
	public AnchorPane layout;
	public Label nameLabel;
	public Label dateLabel;
	public Label priorityLabel;
	public TextField nameField;
	public DatePicker dateField;
	public ComboBox<Priority> priorityField;
	public Button addButton;
	public OrganizerController parentController;
	
	public void setParentController(OrganizerController controller) {
		parentController = controller;
	}
	
	public void addTask() {
		String name = nameField.getText();
		LocalDate date = dateField.getValue();
		Priority priority = priorityField.getValue();
		parentController.addToTaskList(name, date, priority);
		Stage window = (Stage)((Node)layout).getScene().getWindow();
		window.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		priorityField.getItems().addAll(Priority.Low, Priority.Medium, Priority.High);
	}
}