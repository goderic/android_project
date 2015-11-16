package com.example.activitylifecycletest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends Activity {

	public static final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Log.d(TAG, this.toString().split("@")[1] + ":" +"onCreate");

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		// restore saved data before current activity be destroyed
		if (savedInstanceState != null) {
			String tempData = savedInstanceState.getString("data_key");
			Log.d(TAG, this.toString().split("@")[1] + ":" + "read something when create activity:" + tempData);
		} else {
			Log.d(TAG,
					this.toString().split("@")[1] + ":" + "there is no saved infomation when activity been created");
		}

		Button startNormalActivity = (Button) findViewById(R.id.start_normal_activity);
		Button startDialoglActivity = (Button) findViewById(R.id.start_dialog_activity);

		startNormalActivity.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						NormalActivity.class);
				startActivity(intent);
			}
		});

		startDialoglActivity.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						DialogActivity.class);
				startActivity(intent);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onStart() {
		super.onStart();
		Log.d(TAG, this.toString().split("@")[1] + ":" + "onStart()");
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.d(TAG, this.toString().split("@")[1] + ":" + "onResume()");
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.d(TAG, this.toString().split("@")[1] + ":" + "onPause()");
	}

	@Override
	public void onStop() {
		super.onStop();
		Log.d(TAG, this.toString().split("@")[1] + ":" + "onStop()");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(TAG, this.toString().split("@")[1] + ":" +"onDestroy()");
	}

	@Override
	public void onRestart() {
		super.onRestart();
		Log.d(TAG, this.toString().split("@")[1] + ":" + "onRestart()");
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		String tempData = "Something you just typed";
		outState.putString("data_key", tempData);
		Log.d(TAG, this.toString().split("@")[1] + ":" + "save some String: "+ tempData);
	}

}
