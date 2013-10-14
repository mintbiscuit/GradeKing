package com.imaginereality.gradeking;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class FinalExam extends Activity {
	
	// Button and RadioGroup variables
	public Button buttonFind;
	public RadioGroup gradeGroup;
	
	// String to pass to final output page
	static String finalOutput = "";
	static String comments = "";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_final_exam);
		// Only want this App to display without screen rotation (for now)
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		// Set new window animation
		getWindow().setWindowAnimations(android.R.anim.slide_in_left);		
	
		addListenerOnFindButton(); // call the listener for the button
	}

	public void addListenerOnFindButton() {
		
		buttonFind = (Button) findViewById(R.id.buttonFinalExam); 	// set the button to the variable
		buttonFind.setOnClickListener(new OnClickListener() { 	// make it listen to the button click!
			
				 
			@Override
			public void onClick(View v) {
							
			// Set grade percent and current grade to variables
			String gradePercent = ((EditText) findViewById(R.id.editPercent)).getText().toString();
			String currentGrade = ((EditText) findViewById(R.id.editGrade)).getText().toString();
			
			// Do an if-check to make sure percent/grade are not empty, if not empty then move on
			if (gradePercent.matches("") || currentGrade.matches("")) {		// if percent OR grade are empty
				// be prompted to enter information
				Toast.makeText(FinalExam.this, "It looks like you didn't enter all the information. Please go back and make sure you included an exam worth and current grade.", Toast.LENGTH_LONG).show();
			} else {
			
			// Grab RadioGroup
			gradeGroup = (RadioGroup) findViewById(R.id.gradeGroup);
			
			// Double to store the desired grade
			Double desiredGrade = 0.0;
			
			// Find selected desired grade
			switch (gradeGroup.getCheckedRadioButtonId()) {
				case R.id.grade0:
					// Add 90.0%
					desiredGrade = 0.90;
					break;
				case R.id.grade1:
					// Add 80.0%
					desiredGrade = 0.80;
					break;
				case R.id.grade2:
					// Add 70.0%
					desiredGrade = 0.70;
					break;
				case R.id.grade3:
					// Add 60.0%
					desiredGrade = 0.60;
					break;
				default:
					// Add nothing
					desiredGrade = 0.0;
				}
			
			// Calculate Final Grade then set it to output
			String output = calculateFinal(gradePercent, currentGrade, desiredGrade);
			comments = getComments(output);
			finalOutput = (output + "%");
				
			// Move to Output screen
			Intent i = new Intent(FinalExam.this, FinalOutput.class);
			startActivity(i);
				}
			}
		});
	}
	
		public String calculateFinal(String percent, String current, Double desired) {
			
			// String to return
			String finalGrade = "";
			
			// Double to use for calculations
			Double total = 0.0;
			
			// Change Strings to numbers - make them into decimals
			Double d_percent = Double.parseDouble(percent)/100;
			Double d_current = Double.parseDouble(current)/100;
			
			// Do some calculations - then round total
			total = Math.round((((desired - (d_current*(1-d_percent))) / d_percent)*100)*10)/10D;
			int grade = total.intValue();
			
			finalGrade = String.valueOf(grade);
			
			return finalGrade;
			
		}
		
		// Use this to get comments depending on grade
		public static String getComments(String grades) {
			
			// String to return
			String comment = "";
			String c = "";
			int grade = Integer.parseInt(grades);
			
			// Depending on required final grade, give different outputs
			if (grade >= 100) {
				comment = "Well. You're pretty much screwed. Sorry! Unless your professor is very kind or likes bribes, you're out of luck on this one.";
			}
			if (grade >= 90 && grade < 100) {
				comment = "This will be very hard, but if you study you can make it! Don't slack off.";
			}
			if (grade >= 80 && grade < 90) {
				comment = "This is do-able, I promise! Put your mind to it, study up, and you can make it!";
			}
			if (grade >= 60 && grade < 80) {
				comment = "Not too shabby, but I'd still make sure to spend time studying.";
			}
			if (grade <= 60) {
				comment = "You got this! No need for studying. ;)";
			}
			if (grade < 0) {
				comment = "Well look at that! You could even skip and still be fine. I still wouldn't skip though. ;)";
			}
			
			c = comment;
			
			return c;
		}

}
