package com.imaginereality.gradeking;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;

public class About extends Activity {
	
	// Button variable
	public Button menuButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_about);
		// Only want this App to display without screen rotation (for now)
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		// Set new window animation
		getWindow().setWindowAnimations(android.R.anim.slide_in_left);
		
		addListenerOnMenuButton();
	}
	
	public void addListenerOnMenuButton() {
		menuButton = (Button) findViewById(R.id.menuButton);
		menuButton.setOnClickListener(new OnClickListener() {
		
			@Override
		public void onClick (View v) {
			
			// Move to Output screen
			Intent i = new Intent(About.this, GradeKing.class);
			startActivity(i);
			}
			
		});
	}

}
