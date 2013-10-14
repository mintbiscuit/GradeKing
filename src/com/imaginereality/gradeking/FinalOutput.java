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

public class FinalOutput extends Activity {
	
	// Return Button
	public Button returnButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_final_output);
		// Only want this App to display without screen rotation (for now)
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		// Set new window animation
		getWindow().setWindowAnimations(android.R.anim.slide_in_left);
		
		addFinalOutput();
		addListenerOnReturnButton();
	}
	
	public void addFinalOutput() {
		TextView output = (TextView) findViewById(R.id.textFinalOutput);
		String conclusion = FinalExam.finalOutput;
		
		TextView comment = (TextView) findViewById(R.id.textComments);
		String comments = FinalExam.comments;
		
		// Set strings to display in output
		output.setText(conclusion);
		comment.setText(comments);
	}
	
	public void addListenerOnReturnButton() {
		
		returnButton = (Button) findViewById(R.id.buttonReturn); 	// set the button to the variable
		returnButton.setOnClickListener(new OnClickListener() { 	// make it listen to the button click!
		
			@Override
			public void onClick (View v) {
			
				// Move to Output screen
				Intent i = new Intent(FinalOutput.this, GradeKing.class);
				startActivity(i);
				}
			});
	}


}
