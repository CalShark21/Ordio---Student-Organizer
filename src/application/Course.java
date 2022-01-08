package application;

import java.time.DayOfWeek;
import java.util.ArrayList; 

public class Course {
	
	private String courseName;
	ArrayList<DayOfWeek> dayList;
	ArrayList<String> dayListString;
	int credit;
	
	
	public Course(String name, ArrayList<DayOfWeek> days, ArrayList<String> daysString, int cred) {
		
		courseName = name;
		dayList = days;
		dayListString = daysString;
		credit = cred;
			
	}
	
	public String getCourseName() {
		return courseName;
	}
	
	public ArrayList<DayOfWeek> getDayList(){
		return dayList;
	}
	
	public ArrayList<String> getDayListString(){
		return dayListString;
	}
	
	public int getCredit() {
		return credit;
	}
	
	
	

}

