package application;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CalendarController {
	
	public static ObservableList<Course> courseList;
	public static ObservableList<TaskItem> taskList;
	
	// Reference FXML variables
	@FXML private VBox vbox1;
	@FXML private VBox vbox2;
	@FXML private VBox vbox3;
	@FXML private VBox vbox4;
	@FXML private VBox vbox5;
	@FXML private VBox vbox6;
	@FXML private VBox vbox7;
	@FXML private VBox vbox8;
	@FXML private VBox vbox9;
	@FXML private VBox vbox10;
	
	@FXML private VBox vbox11;
	@FXML private VBox vbox12;
	@FXML private VBox vbox13;
	@FXML private VBox vbox14;
	@FXML private VBox vbox15;
	@FXML private VBox vbox16;
	@FXML private VBox vbox17;
	@FXML private VBox vbox18;
	@FXML private VBox vbox19;
	@FXML private VBox vbox20;
	
	@FXML private VBox vbox21;
	@FXML private VBox vbox22;
	@FXML private VBox vbox23;
	@FXML private VBox vbox24;
	@FXML private VBox vbox25;	
	@FXML private VBox vbox26;
	@FXML private VBox vbox27;
	@FXML private VBox vbox28;
	@FXML private VBox vbox29;
	@FXML private VBox vbox30;
	@FXML private VBox vbox31;
	@FXML private VBox vbox32;
	@FXML private VBox vbox33;
	@FXML private VBox vbox34;
	@FXML private VBox vbox35;
	
	@FXML private GridPane gridMonthView;
	

	
	static ArrayList<Label> dateLabelList = new ArrayList<>();
	static ArrayList<DayOfWeek> dayList = new ArrayList<>();
	static ArrayList<VBox> vBoxList = new ArrayList<>();
	
	@FXML
	private void initialize() {
		
		// Style GridPane
		gridMonthView.setStyle("-fx-padding: 10;" + 
	             "-fx-border-width: 2;" +
	             "-fx-border-insets: 5;" + 
	             "-fx-border-radius: 5;" + 
	             "-fx-border-color: #60b4f0;");
		
		taskList = SceneReferences.listOfTasks;
		

		dayList.add(DayOfWeek.SUNDAY);
		dayList.add(DayOfWeek.MONDAY);
		dayList.add(DayOfWeek.TUESDAY);
		dayList.add(DayOfWeek.WEDNESDAY);
		dayList.add(DayOfWeek.THURSDAY);
		dayList.add(DayOfWeek.FRIDAY);
		dayList.add(DayOfWeek.SATURDAY);
		
		vBoxList.add(vbox1);
		vBoxList.add(vbox2);
		vBoxList.add(vbox3);
		vBoxList.add(vbox4);
		vBoxList.add(vbox5);
		vBoxList.add(vbox6);
		vBoxList.add(vbox7);
		vBoxList.add(vbox8);
		vBoxList.add(vbox9);
		vBoxList.add(vbox10);
		vBoxList.add(vbox11);
		vBoxList.add(vbox12);
		vBoxList.add(vbox13);
		vBoxList.add(vbox14);
		vBoxList.add(vbox15);
		vBoxList.add(vbox16);
		vBoxList.add(vbox17);
		vBoxList.add(vbox18);
		vBoxList.add(vbox19);
		vBoxList.add(vbox20);
		vBoxList.add(vbox21);
		vBoxList.add(vbox22);
		vBoxList.add(vbox23);
		vBoxList.add(vbox24);
		vBoxList.add(vbox25);
		vBoxList.add(vbox26);
		vBoxList.add(vbox27);
		vBoxList.add(vbox28);
		vBoxList.add(vbox29);
		vBoxList.add(vbox30);
		vBoxList.add(vbox31);
		vBoxList.add(vbox32);
		vBoxList.add(vbox33);
		vBoxList.add(vbox34);
		vBoxList.add(vbox35);
		
		
		gridMonthView.setGridLinesVisible(true);
				
	}
	
	
	public void updateTaskList (ObservableList<TaskItem> tasks) {
		taskList = tasks;
		
		System.out.println("Current Tasks: ");
		for (TaskItem t : taskList) {
			System.out.println(t.getName());
		}
	}
	
	
	/**
	 * Updates courseList and then adds courses + to-do items to GridPane (calendar)
	 * @param classes
	 */
	public void updateCourseList (ObservableList<Course> classes) {
		
		LocalDate today;
		today = LocalDate.now();
		
		// Update courseList with new value
		courseList = classes;

		
		// Clear VBox's in grid 
		for (int i = 0; i < vBoxList.size(); i++) {
			vBoxList.get(i).getChildren().clear();
		}

		
		// Find starting index day of week (current day)
		int startIndex = 0;		
		for (int i = 0; i < 7; i++) {	
			
			if (today.getDayOfWeek() == dayList.get(i)) {
				startIndex = i;				
			}
		}
		
		
		// Add dates to GridPane
		for (int i = startIndex; i < 35; i++) {
			Label dateLabel = new Label(today.toString());
			
			dateLabel.setStyle("-fx-font-weight: bold");
			
			vBoxList.get(i).getChildren().add(dateLabel);
			today = today.plusDays(1);
		}
		
		
		// Month projection is based off of the current day of the week
		// Used to fill in GridPane with the appropriate number of items
		int monthProjection = 35;
		
		if (startIndex == 1)
			monthProjection = 34;
	
		else if (startIndex == 2)
			monthProjection = 33;
		
		else if (startIndex == 3)
			monthProjection = 32;
		
		else if (startIndex == 4)
			monthProjection = 31;
		
		else if (startIndex == 5)
			monthProjection = 30;
		
		else if (startIndex == 6)
			monthProjection = 29;
			

		// Enter course schedule into GridPane calendar
		// Warning: Loop Nightmare ahead - Turn back now while you still can!
		for (int j = 0; j < courseList.size(); j++) {
									
			for (int k = 0; k < courseList.get(j).getDayList().size(); k++) {
				
				today = LocalDate.now();
				System.out.println("Class meets on: " + (courseList.get(j).getDayList().get(k)));
				
				
				int l = startIndex;
				for (int m = 0; m < monthProjection; m++) {
										
					System.out.println("l counter is: " + l);
					
					if ( today.getDayOfWeek() == (courseList.get(j).getDayList().get(k)) ) {

						Label classLabel = new Label(courseList.get(j).getCourseName());
						vBoxList.get(l).getChildren().add(classLabel);

						System.out.println("Added Class to Calendar on " + today.toString());	
						
					}
					l++;
					today = today.plusDays(1);				
					
				}								
				
			}
						
		}
		System.out.println("End of Course Loop");
		
		
		// Enter To-Do Items into GridPane Calendar
		
		System.out.println("Task List Size: " + taskList.size());		
		today = LocalDate.now();
		
		for (int m = 0; m < taskList.size(); m++) {
			
			today = LocalDate.now();
			int n = startIndex;
			for (int l = 0; l < monthProjection; l++) {
								
				if (today.equals(taskList.get(m).getDateDue())) {
					
					System.out.println("TEQUILA!");	
					Label toDoLabel = new Label(taskList.get(m).getName());
					
					// Set label style for to-do item priorities
					// High = red, Medium = orange, Low = green
					if (taskList.get(m).getPriority() == Priority.High) {
						toDoLabel.setStyle("-fx-background-color: #e34245;"
										 + "-fx-text-fill: white;"
										 + "-fx-background-radius: .3em,.3em,.3em,.3em;"
										 + "-fx-padding :0 .4em 0 .4em;");
						
					}
					
					else if (taskList.get(m).getPriority() == Priority.Medium) {
						toDoLabel.setStyle("-fx-background-color: #e3ae6d;"
								 + "-fx-text-fill: white;"
								 + "-fx-background-radius: .3em,.3em,.3em,.3em;"
								 + "-fx-padding :0 .4em 0 .4em;");
					}
					
					else {
						toDoLabel.setStyle("-fx-background-color: #55d466;"
								 + "-fx-text-fill: white;"
								 + "-fx-background-radius: .3em,.3em,.3em,.3em;"
								 + "-fx-padding :0 .4em 0 .4em;");
					}
					//toDoLabel.setWrapText( true );
					vBoxList.get(n).getChildren().add(toDoLabel);
				}
				
				n++;
				today = today.plusDays(1);
				
			}					
			
		}

	}
	
	
	// Scene Switching
	public void openToDoScene(ActionEvent event) throws IOException {
		
		System.out.println("To-Do Button was clicked.");
		Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
		window.setScene(SceneReferences.organizerScene);
		window.setTitle("To-Do List");
	}
	
	public void addCourseButton (ActionEvent event) throws IOException {
		
		System.out.println("Changed scene to Add courses");
		Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
		
		SceneReferences.mainMenuController.updateWeekCalendar(taskList, courseList);
		
		window.setScene(SceneReferences.addCourseScene);
		window.setTitle("Add Courses");
	}
	
	public void gpaCalc(ActionEvent event) {
		System.out.println("Changed scene to GPA calculator");
		SceneReferences.gpaCalcController.updateCourseList(courseList);
		Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
		
		SceneReferences.mainMenuController.updateWeekCalendar(taskList, courseList);
		
		window.setScene(SceneReferences.gpaCalcScene);
		window.setTitle("GPA Calculator");
	}
	
	public void mainMenu(ActionEvent event) {
		System.out.println("Returned to main menu");
		Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
		SceneReferences.mainMenuController.updateGPA(SceneReferences.gpa);
		SceneReferences.mainMenuController.updateTaskList(taskList);
		SceneReferences.mainMenuController.updateWeekCalendar(taskList, courseList);
		
		window.setScene(SceneReferences.mainMenuScene);
		window.setTitle("Main Menu");
	}

	

	
		
	

}