package com.imaginereality.gradeking;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GradeKing extends Activity {
	
	// add button variables
	public Button buttonCalc;
	public Button buttonFinal;
	public Button buttonAbout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);		
		setContentView(R.layout.activity_main_page);
		// Only want this App to display without screen rotation (for now)
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		// Set new window animation
		getWindow().setWindowAnimations(android.R.anim.slide_in_left);


		addListenerOnCalcButton(); // call listeners for all buttons!
		addListenerOnFinalButton();
		addListenerOnAboutButton();
	}
	
	public void addListenerOnCalcButton() {
		
		buttonCalc = (Button) findViewById(R.id.buttonSemesterGPA); // set the button to the variable
		buttonCalc.setOnClickListener(new OnClickListener() { // makes listen to the button click!
	 
			@Override
			public void onClick(View v) {
				    
				    
			Intent i = new Intent(GradeKing.this,CalculateGPA.class); // Move to GPA page
			startActivity (i);
	 
			}
	 
		});
	}
	
	public void addListenerOnFinalButton() {
		
		buttonFinal = (Button) findViewById(R.id.buttonFinal); // set the button to the variable
		buttonFinal.setOnClickListener(new OnClickListener() { // makes listen to the button click!
	 
			@Override
			public void onClick(View v) {
				    
				    
			Intent i = new Intent(GradeKing.this,FinalExam.class); // Move to Final Grades page
			startActivity (i);
	 
			}
	 
		});
	}
	
	public void addListenerOnAboutButton() {
		
		buttonAbout = (Button) findViewById(R.id.buttonAbout);
		buttonAbout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent i = new Intent(GradeKing.this,About.class); // Move to About page
				startActivity(i);
			}
		});
	}

}
