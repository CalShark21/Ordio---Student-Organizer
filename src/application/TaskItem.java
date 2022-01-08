/**
 * This class is the data type for the tasks that display in the GUI.
 * It has properties for the name, due date, and priority.
 */
package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskItem {
	private String name;
	private LocalDate dateDue;
	private Priority priority;
	
	public TaskItem(String name, LocalDate dateDue, Priority priority) {
		this.name = name;
		this.dateDue = dateDue;
		this.priority = priority;
		
	}
	
	public String getName() {
		return name;
	}
	
	public Priority getPriority() {
		return priority;
	}
	
	public LocalDate getDateDue() {
		return dateDue;
	}
	
	/**
	 * This method takes the name of a month and turns it into the corresponding number.
	 * @param monthString the English name of the month
	 * @return the number corresponding to the name of the month
	 */
	public static int convertMonthInt(String monthString) {
		int monthInt = 0;
		
		if (monthString.equals("January"))
			monthInt = 1;
		
		else if (monthString.equals("February"))
			monthInt = 2;
		
		else if (monthString.equals("March"))
			monthInt = 3;
		
		else if (monthString.equals("April"))
			monthInt = 4;
		
		else if (monthString.equals("May"))
			monthInt = 5;
		
		else if (monthString.equals("June"))
			monthInt = 6;
		
		else if (monthString.equals("July"))
			monthInt = 7;
		
		else if (monthString.equals("August"))
			monthInt = 8;
		
		else if (monthString.equals("September"))
			monthInt = 9;
		
		else if (monthString.equals("October"))
			monthInt = 10;
		
		else if (monthString.equals("November"))
			monthInt = 11;
		
		else if (monthString.equals("December"))
			monthInt = 12;
		
		
		return monthInt;
	}
}
