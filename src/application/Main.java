/**
 *  COMI-2510-601: Advanced Java - Final Project
 *  
 *  The 'Ordio' Student Organizer
 *  
 *  Authors:
 *  Aram Elmayan, Arlen Dumas, Callum Sharkey, 
 *    
 */

package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	

	 
	public static void main(String[] args) {
		launch(args);
	}
	
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Main Menu.fxml"));
			Parent root = loader.load();
			SceneReferences.mainMenuScene = new Scene(root);
			SceneReferences.mainMenuController = loader.getController();	
			
			//primaryStage.initStyle(StageStyle.UNDECORATED);
			referenceControllers();
	
			
			primaryStage.setScene(SceneReferences.mainMenuScene);
			primaryStage.setTitle("Main Menu");
			primaryStage.show();
		} 
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void referenceControllers() throws IOException {

		
		FXMLLoader addCourseLoader = new FXMLLoader();
		addCourseLoader.setLocation(getClass().getResource("Add Courses.fxml"));
		Parent addCourseRoot = addCourseLoader.load();
		SceneReferences.addCourseScene = new Scene(addCourseRoot);
		SceneReferences.addCourseController = addCourseLoader.getController();
		
		
		FXMLLoader organizerLoader = new FXMLLoader();
		organizerLoader.setLocation(getClass().getResource("Organizer.fxml"));
		Parent organizerRoot = organizerLoader.load();
		SceneReferences.organizerScene = new Scene(organizerRoot);
		SceneReferences.organizerController = organizerLoader.getController();
		
		
		FXMLLoader gpaLoader = new FXMLLoader();
		gpaLoader.setLocation(getClass().getResource("GPA Calculator.fxml"));
		Parent gpaCalcRoot = gpaLoader.load();
		SceneReferences.gpaCalcScene = new Scene(gpaCalcRoot);
		SceneReferences.gpaCalcController = gpaLoader.getController();
			
		
		FXMLLoader calendarLoader = new FXMLLoader();
		calendarLoader.setLocation(getClass().getResource("Calendar.fxml"));
		Parent calendarRoot = calendarLoader.load();
		SceneReferences.calendarScene = new Scene(calendarRoot);
		SceneReferences.calendarController = calendarLoader.getController();
		
	}
	
}
