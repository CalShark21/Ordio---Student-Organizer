package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OrganizerController implements Initializable {
	
	@FXML private VBox tableLayout;
	@FXML private HBox buttons;
	@FXML private TableView<TaskItem> table;
	@FXML private TableColumn<TaskItem, String> nameColumn;
	@FXML private TableColumn<TaskItem, LocalDate> dateColumn;
	@FXML private TableColumn<TaskItem, Priority> priorityColumn;
	@FXML private Button newButton;
	@FXML private Button deleteButton;
	
	
	//public ObservableList<TaskItem> listOfTasks = FXCollections.observableArrayList();
	public ObservableList<TaskItem> listOfTasks;
		
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		listOfTasks = SceneReferences.listOfTasks;
		
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateDue"));
		priorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));
		table.setItems(listOfTasks);
	}
	
	
	public void newTaskWindow() throws IOException {
		System.out.println("New Task button pressed");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("NewTaskWindow.fxml"));
		Parent root = loader.load();
		
		NewTaskController controller = loader.getController();
		controller.setParentController(this);
		
		Scene scene = new Scene(root);
		Stage popUp = new Stage();
		popUp.setScene(scene);
		popUp.setTitle("New Task...");
		popUp.show();
	}
	
	public void deleteTask() {
		System.out.println("Task deleted!");
		table.getItems().removeAll(table.getSelectionModel().getSelectedItem());
	}
	
	public void addToTaskList(String name, LocalDate date, Priority priority) {
		System.out.println("Task added!");
		TaskItem task = new TaskItem(name, date, priority);
		listOfTasks.add(task);
	}
	
	
	// Switching Scenes
	
	public void mainMenu(ActionEvent event) {
		System.out.println("Returned to main menu");
		Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
	
		SceneReferences.mainMenuController.updateTaskList(listOfTasks);
		SceneReferences.mainMenuController.updateWeekCalendar(listOfTasks, SceneReferences.courseList);
		
		window.setScene(SceneReferences.mainMenuScene);
		window.setTitle("Main Menu");
	}
	
	public void addCourseButton (ActionEvent event) throws IOException {
		
		System.out.println("\nChanged scene to Add courses");
		Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
		window.setScene(SceneReferences.addCourseScene);
		window.setTitle("Add Courses");
	}
	
	public void gpaCalc(ActionEvent event) {
		System.out.println("\nChanged scene to GPA calculator");
		Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
		SceneReferences.gpaCalcController.updateCourseList(SceneReferences.courseList);
		window.setScene(SceneReferences.gpaCalcScene);
		window.setTitle("GPA Calculator");
				
	}
	
	public void openCalendar(ActionEvent event) throws IOException {
		
		System.out.println("\nChanged scene to Calendar");
		Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
		
		SceneReferences.calendarController.updateTaskList(listOfTasks);
		SceneReferences.calendarController.updateCourseList(SceneReferences.courseList);
		
		
		window.setScene(SceneReferences.calendarScene);
		window.setTitle("Upcoming Events");
		
	}

}