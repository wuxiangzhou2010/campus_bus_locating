package com.example.campus_bus_locating;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RouteoneDemo extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_and_locate);
	}
	
	public void startSearchAndLocateDemo(View view){
		Intent intent = new Intent();
		intent.setClass(this, RouteoneActivity.class);
		startActivity(intent);
		
		
	}
}
