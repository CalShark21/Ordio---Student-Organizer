package application;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.DayOfWeek;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AddCourseController {
	
	public static ObservableList<Course> courseList;
	
	// Reference FXML variables
	@FXML private RadioButton mondayRadio;
	@FXML private RadioButton tuesdayRadio;
	@FXML private RadioButton wednesdayRadio;
	@FXML private RadioButton thursdayRadio;
	@FXML private RadioButton fridayRadio;
	@FXML private RadioButton saturdayRadio;
	@FXML private RadioButton sundayRadio;
	
	@FXML private TextField courseNameTextField;
	@FXML private TextField creditTextField;
	
	@FXML private TableView<Course> courseTable;
	
	ArrayList<RadioButton> radioButtonList = new ArrayList<>();
	
	@FXML
	private void initialize() {
		
		radioButtonList.add(mondayRadio);
		radioButtonList.add(tuesdayRadio);
		radioButtonList.add(wednesdayRadio);
		radioButtonList.add(thursdayRadio);
		radioButtonList.add(fridayRadio);
		radioButtonList.add(saturdayRadio);
		radioButtonList.add(sundayRadio);
		
		// Course name column
		TableColumn<Course, String> nameColumn = new TableColumn<>("Class Name");
		nameColumn.setMinWidth(175);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
		
		// Days meeting column
		TableColumn<Course, ArrayList<String>> daysColumn = new TableColumn<>("Days Meeting");
		daysColumn.setMinWidth(190);
		daysColumn.setCellValueFactory(new PropertyValueFactory<>("dayListString"));
		
		// Credit Hour column
		TableColumn<Course, Integer> creditColumn = new TableColumn<>("Credit Hours");
		creditColumn.setMinWidth(85);
		creditColumn.setCellValueFactory(new PropertyValueFactory<>("credit"));
		
		// Set course list to global
		courseList = SceneReferences.courseList;
		
		courseTable.setItems(courseList);
		
		courseTable.getColumns().addAll(nameColumn, daysColumn, creditColumn);
		
	}
	
	public void handleCreateCourseButton (ActionEvent event) throws IOException{
		
		System.out.println("\nCreate Course button was clicked");
		
		ArrayList<DayOfWeek> dayList = new ArrayList<>();
		ArrayList<String> dayListString = new ArrayList<>();
		String courseName;
		int credit;
				
		courseName = courseNameTextField.getText();		
		
		if (courseName.isEmpty() ) {
			System.out.println("Error: Course must have a name.");
			showErrorMessage("Please enter a course name");
		}

		else {
			
			try {
			
				credit = Integer.parseInt(creditTextField.getText());
				
				System.out.println("Course name: " + courseName);
				System.out.println("Number of credit hours: " + credit);
				
				// Get DayOfWeek enumeration as abbreviated String for display
				if (mondayRadio.isSelected()) { 
					dayList.add(DayOfWeek.MONDAY);
					dayListString.add("Mon.");
				}		
				if (tuesdayRadio.isSelected()) {
					dayList.add(DayOfWeek.TUESDAY);
					dayListString.add("Tue.");					
				}
				if (wednesdayRadio.isSelected()) {
					dayList.add(DayOfWeek.WEDNESDAY);
					dayListString.add("Wed.");
				}
				
				if (thursdayRadio.isSelected()) {
					dayList.add(DayOfWeek.THURSDAY);
					dayListString.add("Thu.");
				}
				
				if (fridayRadio.isSelected()) {
					dayList.add(DayOfWeek.FRIDAY);
					dayListString.add("Fri.");
				}
				
				if (saturdayRadio.isSelected()) {
					dayList.add(DayOfWeek.SATURDAY);
					dayListString.add("Sat.");
				}
				
				if (sundayRadio.isSelected()) {
					dayList.add(DayOfWeek.SUNDAY);
					dayListString.add("Sun.");
				}
				

				System.out.println("Meets on: ");
				for (DayOfWeek d : dayList) 
					System.out.println(d);
				
				
				// Create new Course object and add to List
				Course course = new Course(courseName, dayList, dayListString, credit);
				courseList.add(course);	
				
				
				// Clear All Fields
				courseNameTextField.clear();
				
				creditTextField.clear();
				
				for (RadioButton r : radioButtonList)
					r.setSelected(false);
				
				
				
			} 
			catch (NumberFormatException e) {
				System.out.println("Error: Invalid value for credit hours.");
				showErrorMessage("Invalid value for credit hours.");
			}
			
		}

		SceneReferences.courseList = courseList;
		
		
	}
	
	
	
	/**
	 * Displays a pop-up error window with message defined by parameter
	 * @param message The text to display on the error message
	 * @throws IOException
	 */
	public void showErrorMessage (String message) throws IOException {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("Error Message Popup.fxml"));
		Parent errorMessageParent = loader.load();
		
		Scene errorScene = new Scene(errorMessageParent);
		
		ErrorMessageController controller = loader.getController();
		
		controller.setMessage(message);
		
		Stage window = new Stage();		
		window.setScene(errorScene);
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Oops!");
		window.showAndWait();

	}

	
	
	
	// Switching Scenes
	
	public void mainMenu(ActionEvent event) {
		System.out.println("\nReturned to main menu");
		Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
		
		SceneReferences.mainMenuController.updateWeekCalendar(SceneReferences.listOfTasks, courseList);
		
		window.setScene(SceneReferences.mainMenuScene);
		window.setTitle("Main Menu");
	}
	
	public void gpaCalc(ActionEvent event) throws IOException {
		
		System.out.println("\nChanged scene to GPA calculator");
		Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
		SceneReferences.gpaCalcController.updateCourseList(courseList);
		window.setScene(SceneReferences.gpaCalcScene);
		window.setTitle("GPA Calculator");
	}
	
	public void openToDoScene(ActionEvent event) throws IOException {
		
		System.out.println("\nTo-Do Button was clicked.");
		Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
		window.setScene(SceneReferences.organizerScene);
		window.setTitle("To-Do List");
	}
	
	public void openCalendar(ActionEvent event) throws IOException {
		
		System.out.println("\nChanged scene to Calendar");
		Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
		
		SceneReferences.calendarController.updateCourseList(courseList);
		
		window.setScene(SceneReferences.calendarScene);
		window.setTitle("Upcoming Events");
		
	}
	
	
}
