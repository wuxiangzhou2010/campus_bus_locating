package com.example.campus_bus_locating;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;


public class Splash extends Activity{
	private final int SPLASH_DISPLAY_LENGTH=1000;  //—”≥Ÿ1√Î
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash);
		new Handler().postDelayed(new Runnable(){
			
			@Override
			public void run(){
				Intent mainIntent=new Intent(Splash.this,AppMain.class);
				Splash.this.startActivity(mainIntent);
				   Splash.this.finish();
			}
		},SPLASH_DISPLAY_LENGTH);
	}

}
