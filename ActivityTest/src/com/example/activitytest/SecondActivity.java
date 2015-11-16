package com.example.activitytest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.second_layout);

		Log.d("qzb", "2nd Act==instance: " + this.toString().split("@")[1] + " onCreate! " + "PID: " + Process.myPid() +  " Task ID: " + getTaskId());
		// Intent intent = getIntent();
		// String data = intent.getStringExtra("extra_data");
		// Log.d("SecondActivity", data);

		Button button2 = (Button) findViewById(R.id.button_2);
		button2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				Intent intent = new Intent();
//				intent.putExtra("data_return", "^-^||| Hello 1st");
//				Log.d("SecondActivity", "2nd->1st Hello 1st");
//				setResult(RESULT_OK, intent);

//				finish();
				Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
				
				startActivity(intent);
			}
		});
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent();
		intent.putExtra("data_return", "^-^||| Hello 1st");
		setResult(RESULT_OK, intent);
		finish();
	}
	
	@Override
	public void onRestart()
	{
		super.onRestart();
		Log.d("qzb", "2nd Act==instance: "+ this.toString().split("@")[1] + " onRestart");
		
	}	
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		Log.d("qzb", "2nd Act==instance: "+ this.toString().split("@")[1] + " onDestroy");
		
	}	
}