package com.example.activitytest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.os.Process;

public class ThirdActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.third_layout);
		
		Log.d("qzb", "3rd Act==instance: " + this.toString().split("@")[1] + " onCreate! "  + "PID: " + Process.myPid() +  " Task ID: " + getTaskId());	
		
		Button button3 = (Button) findViewById(R.id.button_3);
		button3.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				ActivityCollector.finishAll();
				Log.d("qzb", "kill process id: " + Process.myPid());
				android.os.Process.killProcess(Process.myPid());
			}
		});
		
	}	
	
	@Override
	public void onRestart()
	{
		super.onRestart();
		Log.d("qzb", "3rd Act==instance: "+ this.toString().split("@")[1] + " onRestart");
		
	}
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		Log.d("qzb", "3rd Act==instance: "+ this.toString().split("@")[1] + " onDestroy");
		
	}
	
}
