package application;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.*;

public class GPACalcController {	
	
	
	//MainMenuController mainMenuObject = new MainMenuController();
	
	// List of values for letter grade dropdown menu
	private ObservableList<String> gradeList = FXCollections.observableArrayList("A+", "A", "A-", "B+", "B", "B-", 
																		         "C+", "C", "C-", "D+", "D", "D-", "F");
	
	// Global variables accessible across GUI
	public static double currentGPA;
	public static ObservableList<Course> courseList;

	// Reference to variables declared in FXML doc
	@FXML private ChoiceBox gradeDropDown1;
	@FXML private ChoiceBox gradeDropDown2;
	@FXML private ChoiceBox gradeDropDown3;
	@FXML private ChoiceBox gradeDropDown4;
	@FXML private ChoiceBox gradeDropDown5;
	
	@FXML private TextField classText1;
	@FXML private TextField classText2;
	@FXML private TextField classText3;
	@FXML private TextField classText4;
	@FXML private TextField classText5;
	
	@FXML private TextField creditTextField1;
	@FXML private TextField creditTextField2;
	@FXML private TextField creditTextField3;
	@FXML private TextField creditTextField4;
	@FXML private TextField creditTextField5;
	
	@FXML private Label gradeLabel;
	@FXML private Label creditLabel;
	
	
	static ArrayList<TextField> classFieldList = new ArrayList<>();	
	static ArrayList<TextField> creditFieldList = new ArrayList<>();	
	static ArrayList<ChoiceBox> gradeSelectionList = new ArrayList<>();	
	static ArrayList<Double>    weightedGradeList = new ArrayList<>();
	
	private static DecimalFormat df = new DecimalFormat("0.00");
	

	
	
	// Initializes any drop-down menus, TableViews, etc... with a list and/or a default value
	@FXML
	private void initialize() {
		
		currentGPA = SceneReferences.gpa;
				
		classFieldList.clear();
		creditFieldList.clear();
		
		gradeLabel.setVisible(false);
		creditLabel.setVisible(false);
		
		gradeDropDown1.setValue("A");
		gradeDropDown1.setItems(gradeList);
		
		gradeDropDown2.setValue("A");
		gradeDropDown2.setItems(gradeList);
		
		gradeDropDown3.setValue("A");
		gradeDropDown3.setItems(gradeList);
		
		gradeDropDown4.setValue("A");
		gradeDropDown4.setItems(gradeList);
		
		gradeDropDown5.setValue("A");
		gradeDropDown5.setItems(gradeList);
		
		classFieldList.add(classText1);
		classFieldList.add(classText2);
		classFieldList.add(classText3);
		classFieldList.add(classText4);
		classFieldList.add(classText5);
		
		creditFieldList.add(creditTextField1);
		creditFieldList.add(creditTextField2);
		creditFieldList.add(creditTextField3);
		creditFieldList.add(creditTextField4);
		creditFieldList.add(creditTextField5);


		
	}
	
	
	public void calculateGPAButton(ActionEvent event) throws IOException {		
		System.out.println("Calculate GPA Button Clicked...");		
		
		creditFieldList.add(creditTextField1);
		creditFieldList.add(creditTextField2);
		creditFieldList.add(creditTextField3);
		creditFieldList.add(creditTextField4);
		creditFieldList.add(creditTextField5);
		
		gradeSelectionList.add(gradeDropDown1);
		gradeSelectionList.add(gradeDropDown2);
		gradeSelectionList.add(gradeDropDown3);
		gradeSelectionList.add(gradeDropDown4);
		gradeSelectionList.add(gradeDropDown5);
				
		// Letter grade as a String from drop-down box
		String gradeSelectionValue;
		
		double credit, gradeGPA, 
			   finalGrade = 0, 
			   totalCredits = 0;
		
		int effectiveCredits = 0;
				
		for (int i = 0; i < 5; i++) {
			
			if ( !(creditFieldList.get(i).getText().isEmpty() )) {
							
				try {
					
					credit = Double.parseDouble(creditFieldList.get(i).getText());
					System.out.println("\nThe credit for class " + (i+1) + " is " + credit);
					totalCredits += credit;
					
					gradeSelectionValue = (String) gradeSelectionList.get(i).getValue();
					
					// Calculate effective credits (grade higher than F)
					if ((gradeSelectionList.get(i).getValue()) != "F") {
						effectiveCredits += credit;
					}
					
					//Call method to convert letter grade into 4.0 scale double
					gradeGPA = convertLetterGrade(gradeSelectionValue);				
					
					weightedGradeList.add(credit * gradeGPA); 
					
					System.out.println("Grade for class " + (i+1) + " is " + gradeGPA);
				} 
				catch (NumberFormatException e) {
					System.out.println("Error: Credit for class " + i + " is invalid");
				}
				
			}
			
		}
		
		double weightedGradeSum = 0;
		
		for (int i = 0; i < weightedGradeList.size(); i++) {
			weightedGradeSum += weightedGradeList.get(i);
		}
	
		if (totalCredits == 0) {
			gradeLabel.setText("No grades entered");
			gradeLabel.setVisible(true);
		}
		
		else {
			finalGrade = (weightedGradeSum / totalCredits);
			
			gradeLabel.setText(df.format(finalGrade));
			gradeLabel.setVisible(true);
			
			System.out.println("\nFinal weighted grade is: " + df.format(finalGrade));
			
			int totalCreditsInt = (int) totalCredits;
			creditLabel.setText(totalCreditsInt + " (" + effectiveCredits + " effective)");
			creditLabel.setVisible(true);
		}
		
		
		currentGPA = finalGrade;
		SceneReferences.gpa = currentGPA;
		
		//creditFieldList.clear();
		weightedGradeList.clear();
		gradeSelectionList.clear();
		
	}
	
	
	
	
	public double convertLetterGrade(String gradeSelectionValue) {
		
		double gradeGPA;
		switch (gradeSelectionValue) 
		{
		case "A+":
		case "A":
			gradeGPA = 4.0;
			break;
			
		case "A-":
			gradeGPA = 3.7;
			break;
			
		case "B+":
			gradeGPA = 3.3;
			break;
			
		case "B":
			gradeGPA = 2.7;
			break;
			
		case "B-":
			gradeGPA = 2.7;
			break;
			
		case "C+":
			gradeGPA = 2.3;
			break;
			
		case "C":
			gradeGPA = 2.0;
			break;
			
		case "C-":
			gradeGPA = 1.7;
			break;
			
		case "D+":
			gradeGPA = 1.3;
			break;
			
		case "D":
		case "D-":
			gradeGPA = 1.0;
			break;
			
		case "F":
			gradeGPA = 0.0;
			break;

		default:
			gradeGPA = 0;
		}

		
		return gradeGPA;
		
	}
	
	public void updateCourseList (ObservableList<Course> list) {
		courseList = list;
		
		System.out.println("Credit field array is: " + creditFieldList.size());
		System.out.println("Class field array is: " + classFieldList.size());
		
		System.out.println("Inside GPA Calc method: Course list size is: " + courseList.size());
		
		
		if  (courseList.size() > 0) {
			
			for(int i = 0; i < courseList.size(); i++) {
				
				classFieldList.get(i).setText( courseList.get(i).getCourseName() );
				creditFieldList.get(i).setText( Integer.toString(courseList.get(i).getCredit()) );
					
			}
			
		}	
		
	}
	
	// Switching Scenes
	
	public void mainMenu(ActionEvent event) {
		System.out.println("Returned to main menu");
		Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
		SceneReferences.mainMenuController.updateGPA(currentGPA);
		window.setScene(SceneReferences.mainMenuScene);
		window.setTitle("Main Menu");
	}
	
	public void addCourseButton (ActionEvent event) throws IOException {
		
		System.out.println("Changed scene to Add courses");
		Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
		SceneReferences.mainMenuController.updateGPA(currentGPA);
		window.setScene(SceneReferences.addCourseScene);
		window.setTitle("Add Courses");
	}
	
	public void openToDoScene(ActionEvent event) throws IOException {
		
		System.out.println("To-Do Button was clicked.");
		Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
		SceneReferences.mainMenuController.updateGPA(currentGPA);
		window.setScene(SceneReferences.organizerScene);
		window.setTitle("To-Do List");
	}
	
	public void openCalendar(ActionEvent event) throws IOException {
		
		System.out.println("Changed scene to Calendar");
		Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
		
		SceneReferences.calendarController.updateCourseList(courseList);
		
		
		window.setScene(SceneReferences.calendarScene);
		window.setTitle("Upcoming Events");
		
	}
	
}
