package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;

public class SceneReferences {
	
	static Scene mainMenuScene;
	static Scene addCourseScene;
	static Scene organizerScene;
	static Scene gpaCalcScene;
	static Scene calendarScene;
	
	static MainMenuController mainMenuController;
	static GPACalcController gpaCalcController;
	static OrganizerController organizerController;
	static AddCourseController addCourseController;
	static CalendarController calendarController;	
	
	static double gpa;
	static ObservableList<Course> courseList = FXCollections.observableArrayList();
	static ObservableList<TaskItem> listOfTasks = FXCollections.observableArrayList();
	
}
