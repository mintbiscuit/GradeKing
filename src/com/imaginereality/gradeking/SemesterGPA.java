package com.imaginereality.gradeking;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SemesterGPA extends Activity {
	
	// Menu button
	public Button goBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);		
		setContentView(R.layout.activity_semester_gp);
		// Only want this App to display without screen rotation (for now)
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		// Set new window animation
		getWindow().setWindowAnimations(android.R.anim.slide_in_left);
		
		addTextToOutput();
		addListenerOnMenuButton();
	}

public void addTextToOutput() {
		
		TextView output = (TextView) findViewById(R.id.textSGPA_output); //set up the TextView
		String conclusion = CalculateGPA.output;
				
		// Set strings to display in output
		output.setText(conclusion);
		
	}

public void addListenerOnMenuButton() {
	
	goBack = (Button) findViewById(R.id.buttonMain); // set the button to the variable
	goBack.setOnClickListener(new OnClickListener() { // make 'back' listen to the button click!

		@Override
		public void onClick(View v) {
			    
			    
		Intent i = new Intent(SemesterGPA.this,GradeKing.class); // Move back to the main page
		startActivity (i);
 
		}

});

}

}
