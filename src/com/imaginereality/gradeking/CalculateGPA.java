package com.imaginereality.gradeking;


import java.util.ArrayList;
import java.util.regex.Pattern;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;

public class CalculateGPA extends Activity {
	
	// add button variable
	public Button buttonCalc;
	
	// public ArrayList to use
	public ArrayList<String> gradeList = new ArrayList<String>();
	public ArrayList<String> hourList = new ArrayList<String>();
	
	// Save output String
	public static String output;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_calculate_gp);
		// Only want this App to display without screen rotation (for now)
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		// Set new window animation
		getWindow().setWindowAnimations(android.R.anim.slide_in_left);		
	
		addListenerOnCalcButton(); // call the listener for the calc button									
					
	}
	
	public void addListenerOnCalcButton() {
		
		buttonCalc = (Button) findViewById(R.id.buttonCalc); 	// set the button to the variable
		buttonCalc.setOnClickListener(new OnClickListener() { 	// make it listen to the button click!
			
			
				 
			@Override
			public void onClick(View v) {
			
			// Grab all the Grades!
			String g1 = ((EditText) findViewById(R.id.editGrade1)).getText().toString(); // grab Grade 1
			String g2 = ((EditText) findViewById(R.id.editGrade2)).getText().toString(); // grab Grade 2
			String g3 = ((EditText) findViewById(R.id.editGrade3)).getText().toString(); // grab Grade 3
			String g4 = ((EditText) findViewById(R.id.editGrade4)).getText().toString(); // grab Grade 4
			String g5 = ((EditText) findViewById(R.id.editGrade5)).getText().toString(); // grab Grade 5
			String g6 = ((EditText) findViewById(R.id.editGrade6)).getText().toString(); // grab Grade 6
			String g7 = ((EditText) findViewById(R.id.editGrade7)).getText().toString(); // grab Grade 7
		
			// Grab all the course hours!
			String h1 = ((EditText) findViewById(R.id.editHours1)).getText().toString(); // grab Hour 1
			String h2 = ((EditText) findViewById(R.id.editHours2)).getText().toString(); // grab Hour 2
			String h3 = ((EditText) findViewById(R.id.editHours3)).getText().toString(); // grab Hour 3
			String h4 = ((EditText) findViewById(R.id.editHours4)).getText().toString(); // grab Hour 4
			String h5 = ((EditText) findViewById(R.id.editHours5)).getText().toString(); // grab Hour 5
			String h6 = ((EditText) findViewById(R.id.editHours6)).getText().toString(); // grab Hour 6
			String h7 = ((EditText) findViewById(R.id.editHours7)).getText().toString(); // grab Hour 7		
			
			// Add grades to an ArrayList
			gradeList.add(0, g1); // add grade 1
			gradeList.add(1, g2); // add grade 2
			gradeList.add(2, g3); // add grade 3
			gradeList.add(3, g4); // add grade 4
			gradeList.add(4, g5); // add grade 5
			gradeList.add(5, g6); // add grade 6
			gradeList.add(6, g7); // add grade 7
			
			// Add credit hours to an ArrayList
			hourList.add(0, h1); // add hour 1
			hourList.add(1, h2); // add hour 2
			hourList.add(2, h3); // add hour 3
			hourList.add(3, h4); // add hour 4
			hourList.add(4, h5); // add hour 5
			hourList.add(5, h6); // add hour 6
			hourList.add(6, h7); // add hour 7
			
			// String to store problem
			String problem = "";
			
			// Boolean to use in checking validity
			boolean valid = false;
			
			
			// Loop to see how many strings are empty in GL
			for (int i = 0; i < 7; i++) {
				// if grade/hour isn't blank or BOTH are blank
				if (!gradeList.get(i).toString().matches("")) {		// we have a grade
					if (hourList.get(i).toString().matches("")) {	// no hour
						problem = "Please enter credit hour amount for each grade.";
						
						// Because you input one thing, make valid true (to get other message)
						valid = true;
					} else {
						// do nothing because everything is OK (you want grade and hour)
						
						// All valid is true!
						valid = true;
					}
				}
				else if (gradeList.get(i).toString().matches("")) {	// no grade
					if (!hourList.get(i).toString().matches(""))	// we have an hour
					{
						problem = "Please enter a grade for every credit hour.";
						// Because you input one thing, make valid true (to get other message)
						valid = true;
					} else {
						// do nothing because it is OK to have a blank grade AND hour
					}
				}
			}	
			
			
			// Did code include at least ONE valid row?
			if (!valid) {
				problem = "You need to include at least one grade and credit hour.";
			} else { 
				// input included at least ONE valid row
			}
			
			
			// Do an if-check to make sure grades match up with hours
			if (problem != "") {		// if percent OR grade are empty
				// be prompted to enter information
				Toast.makeText(CalculateGPA.this, problem, Toast.LENGTH_LONG).show();
			} else {
			
				
			// Calculate the GPA (returns an output String)
			output = calculateGpa(gradeList, hourList);
			
			
			// Move to the Output page
			Intent i = new Intent(CalculateGPA.this,SemesterGPA.class);
			startActivity (i);
	 
				}
			}
			
		});
	}
		
		// This method calculates the GPA and returns a String
		public String calculateGpa(ArrayList<String> grade, ArrayList<String> hour) {
			String output2 = "";
			Double total = 0.0;		// Total number of grade points
			Double h_total = 0.0;	// Total number of hours
			Double[] grades = new Double[grade.size()];
			Double[] hours = new Double[hour.size()];

			// Make patterns to find grade letter (including +/-)
			Pattern p1 = Pattern.compile(" a|a.?", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
			Pattern p2 = Pattern.compile(" b|b.?", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
			Pattern p3 = Pattern.compile(" c|c.?", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
			Pattern p4 = Pattern.compile(" d|d.?", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
			Pattern p5 = Pattern.compile("");

			// Change grades to number
			for (int i = 0; i < grade.size(); i++) {
				if (p1.matcher(grade.get(i)).matches()) {	// If that point in grade is an A
					grades[i] = 4.0;	// Add a 4.0 grade to grades
				}
				if (p2.matcher(grade.get(i)).matches()) {	// If that point in grade is a B
					grades[i] = 3.0;	// Add a 3.5 grade to grades
				}
				if (p3.matcher(grade.get(i)).matches()) {	// If that point in grade is a C
					grades[i] = 2.0;	// Add a 3.0 grade to grades
				}
				if (p4.matcher(grade.get(i)).matches()) {	// If that point in grade is a D
					grades[i] = 1.0;	// Add a 25. grade to grades
				}
				if (p5.matcher(grade.get(i)).matches()) {	// If that point in grade is a D
					grades[i] = 0.0;	// Add a 25. grade to grades
				}

			}
			
			// Change hours to double, then fill in array
			for (int a = 0; a < hour.size(); a++) {
				if (p5.matcher(hour.get(a)).matches()) { // If `Hour` is empty
					hours[a] = 0.0;
				}
				else {
					hours[a] = Double.parseDouble(hour.get(a));
				}
			}
			
			// Find total hour grade points
			for (int g = 0; g < grades.length; g++) {	// For every grade..
					total += (grades[g] * hours[g]);	// Multiply the hour and grade and add it to total
			}
			
			// Find out how many hours total
			for (double h : hours) {
				h_total += h;
			}
			
			// Average out the total
			
			Double avg = total / h_total;
			avg = Math.round(avg * 100) / 100D;

			output2 =  String.valueOf(avg);

			return output2;
	}

}
