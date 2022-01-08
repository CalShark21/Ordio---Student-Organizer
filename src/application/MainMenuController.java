package application;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainMenuController {

	// Global/Static Variables
	public static double currentGPA;
	public static ObservableList<Course> courseList;
	public static ObservableList<TaskItem> taskList;
		
	private static DecimalFormat df = new DecimalFormat("0.00");
	
	static ArrayList<Label>     dayLabelList = new ArrayList<>();
	static ArrayList<DayOfWeek> dayList = new ArrayList<>();
	static ArrayList<VBox>      vBoxList = new ArrayList<>();
	
	// FXML Variable Declarations
	@FXML private Label semesterGPA;
	
	@FXML private GridPane weekGrid;
	@FXML private Label dayLabel0;
	@FXML private Label dayLabel1;
	@FXML private Label dayLabel2;
	@FXML private Label dayLabel3;
	@FXML private Label dayLabel4;
	@FXML private Label dayLabel5;
	@FXML private Label dayLabel6;
	
	@FXML private VBox vbox0;
	@FXML private VBox vbox1;
	@FXML private VBox vbox2;
	@FXML private VBox vbox3;
	@FXML private VBox vbox4;
	@FXML private VBox vbox5;
	@FXML private VBox vbox6;
	
	@FXML private TableView<Course> courseSchedule;
	
	
	FadeTransition fadeInMessage = new FadeTransition();
	

    

	
	

	@FXML
	private void initialize() {
		
		fadeInMessage.setDuration(Duration.seconds(1));
		fadeInMessage.setFromValue(0.0);
		fadeInMessage.setToValue(1.0);
		fadeInMessage.setNode(semesterGPA);
		
		
		weekGrid.setStyle("-fx-padding: 2;" + 
	             "-fx-border-style: solid inside;" + 
	             "-fx-border-width: 1;" +
	             "-fx-border-insets: 0;" + 
	             "-fx-border-radius: 0;" + 
	             "-fx-border-color: #60b4f0;");
		
		weekGrid.setGridLinesVisible(true);
		
		dayLabelList.add(dayLabel0);
		dayLabelList.add(dayLabel1);
		dayLabelList.add(dayLabel2);
		dayLabelList.add(dayLabel3);
		dayLabelList.add(dayLabel4);
		dayLabelList.add(dayLabel5);
		dayLabelList.add(dayLabel6);
		
		vBoxList.add(vbox0);
		vBoxList.add(vbox1);
		vBoxList.add(vbox2);
		vBoxList.add(vbox3);
		vBoxList.add(vbox4);
		vBoxList.add(vbox5);
		vBoxList.add(vbox6);
		
		
		dayList.add(DayOfWeek.SUNDAY);
		dayList.add(DayOfWeek.MONDAY);
		dayList.add(DayOfWeek.TUESDAY);
		dayList.add(DayOfWeek.WEDNESDAY);
		dayList.add(DayOfWeek.THURSDAY);
		dayList.add(DayOfWeek.FRIDAY);
		dayList.add(DayOfWeek.SATURDAY);
		
		taskList = SceneReferences.listOfTasks;
		
						
		/** Define columns and TableView for course schedule **/
		
		// Course name column
		TableColumn<Course, String> nameColumn = new TableColumn<>("Class Name");
		nameColumn.setMinWidth(200);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
		
		// Days meeting column
		TableColumn<Course, ArrayList<String>> daysColumn = new TableColumn<>("Days Meeting");
		daysColumn.setMinWidth(180);
		daysColumn.setCellValueFactory(new PropertyValueFactory<>("dayListString"));
		
		// Credit Hour column
		TableColumn<Course, Integer> creditColumn = new TableColumn<>("Credit Hours");
		creditColumn.setMinWidth(90);
		creditColumn.setCellValueFactory(new PropertyValueFactory<>("credit"));
		
		// Set course list to global
		courseList = SceneReferences.courseList;
		
		courseSchedule.setItems(courseList);		
		courseSchedule.getColumns().addAll(nameColumn, daysColumn, creditColumn);
		
		
		
		// Initialize GPA Values
		semesterGPA.setVisible(true);
		semesterGPA.setText(df.format(currentGPA));
		

		
		fadeInMessage.play();
		
		
		// Set day labels for week calendar
		LocalDate today;
		today = LocalDate.now();
		
		for (int i = 0; i < dayLabelList.size(); i++) {
			
			String day = getDayString((today.getDayOfWeek()));
			dayLabelList.get(i).setText(day);
			
			today = today.plusDays(1);
			
		}
		
	}

	/**
	 * Converts DayOfWeek enumeration to String for display on calendar
	 * @param day
	 * @return
	 */
	public String getDayString(DayOfWeek day) {
		
		String dayAsString;
		
		if (day == DayOfWeek.SUNDAY)
			dayAsString = "Sunday";
		
		else if (day == DayOfWeek.MONDAY)
			dayAsString = "Monday";
		
		else if (day == DayOfWeek.TUESDAY)
			dayAsString = "Tuesday";
		
		else if (day == DayOfWeek.WEDNESDAY)
			dayAsString = "Wednesday";
		
		else if (day == DayOfWeek.THURSDAY)
			dayAsString = "Thursday";
		
		else if (day == DayOfWeek.FRIDAY)
			dayAsString = "Friday";
		
		else
			dayAsString = "Saturday";
		
		return dayAsString;
		
		
		
	}
	
	public void updateGPA(double newGPA) {
		currentGPA = newGPA;
		semesterGPA.setText(df.format(currentGPA));
		fadeInMessage.play();
	}
	
	public void updateTaskList(ObservableList<TaskItem> tasks) {
		taskList = tasks;
	}
	
	
	public void updateWeekCalendar(ObservableList<TaskItem> tasks, ObservableList<Course> classes) {

		LocalDate today;
		
		courseList = classes;
		taskList = tasks;
				
		// Clear GridPane VBox' of old values
		for (int i = 0; i < vBoxList.size(); i++) {
			vBoxList.get(i).getChildren().clear();
		}
		
		
		
		// Add Classes to Week GridPane
		for (int j = 0; j < courseList.size(); j++) {
			
			for (int k = 0; k < courseList.get(j).getDayList().size(); k++) {
				
				today = LocalDate.now();
				System.out.println("Today: " + today.getDayOfWeek());
				System.out.println("Class: " + (courseList.get(j).getDayList().get(k)));
				
				for (int l = 0; l < 7; l++) {
				 					
					System.out.println("Date: " + today);
					
					if ( today.getDayOfWeek() == (courseList.get(j).getDayList().get(k)) ) {

						Label classLabel = new Label(courseList.get(j).getCourseName());
						
						vBoxList.get(l).getChildren().add(classLabel);

						
						System.out.println("Current day is: " + today.toString());
						
						
						//System.out.println("j is: " + j);
						System.out.println("TEQUILA!");	
						
					}
	
					today = today.plusDays(1);				
					
				}								
				
			}
						
		}
		
		// Add to-do items to Week GridPane
		today = LocalDate.now();
		for (int m = 0; m < taskList.size(); m++) {
			
			today = LocalDate.now();
			for (int l = 0; l < 7; l++) {
				
				// Set label styles for to-do item priorities
				// High = red, Medium = Orange, Low = Green
				if (today.equals(taskList.get(m).getDateDue())) {
					
					System.out.println("TEQUILA!");	
					Label toDoLabel = new Label(taskList.get(m).getName());
					
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
					
					vBoxList.get(l).getChildren().add(toDoLabel);
				}
				
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
		window.setScene(SceneReferences.addCourseScene);
		window.setTitle("Add Courses");
	}
	
	public void gpaCalc(ActionEvent event) {
		System.out.println("Changed scene to GPA calculator");
		SceneReferences.gpaCalcController.updateCourseList(courseList);
		Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
		window.setScene(SceneReferences.gpaCalcScene);
		window.setTitle("GPA Calculator");
	}
	
	public void openCalendar(ActionEvent event) {
		System.out.println("Changed scene to Calendar");
		Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
		
		SceneReferences.calendarController.updateCourseList(courseList);
		SceneReferences.calendarController.updateTaskList(taskList);
		
		window.setScene(SceneReferences.calendarScene);
		window.setTitle("Calendar");
		
	}
	
}
