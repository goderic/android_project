package com.example.activitytest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.first_layout);
		
	    Log.d("qzb", "1st Act==instance: "+ this.toString().split("@")[1] + " onCreate! " + "PID: " + Process.myPid() + 
	    " Task ID: " + getTaskId());
		Button button1 = (Button) findViewById(R.id.button_1);
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
//				Toast.makeText(FirstActivity.this, "You clicked button 1",
//						Toast.LENGTH_SHORT).show();

				// below is explicit intent calling
				// Intent intent = new Intent(FirstActivity.this,
				// SecondActivity.class);
				// startActivity(intent);

				// below is implicit intent calling
				// Intent intent = new Intent(
				// "com.example.activitytest.ACTION_START");

				// Intent intent = new Intent(Intent.ACTION_DIAL);
				// intent.setData(Uri.parse("tel:122"));
				// intent.addCategory("com.example.activitytest.MY_CATEGORY");

				Intent intent = new Intent(FirstActivity.this,
						SecondActivity.class);
				startActivity(intent);
				
				//start an activity with request code
//				startActivityForResult(intent, 1);
//				Log.d("FirstActivity", "1st->2nd with code 1");
				
				// startActivity(intent);
				// Log.d("FirstActivity", "1st->2nd directly start");

				// test1();
				// finish();
			}

		});

	}

	// private void test1() {
	// Log.w("rsj", "private test1 called");
	// }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.add_item:
			Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT).show();
			break;

		case R.id.remove_item:
			Toast.makeText(this, "You clicked Remove", Toast.LENGTH_SHORT)
					.show();
			break;

		default:
		}
		return true;

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		switch (requestCode) {
		case 1:
			if (RESULT_OK == resultCode) {
				String returnedData = data.getStringExtra("data_return");
//				Log.d("FirstActivity", "1st <- " + returnedData);
			}
			break;

		default:
			Log.d("FirstActivity", "shall not be here!");
			break;
		}
	}
	
	@Override
	public void onRestart()
	{
		super.onRestart();
		Log.d("qzb", "1st Act==instance: "+ this.toString().split("@")[1] + " onRestart");
		
	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();
		Log.d("qzb", "1st Act==instance: "+ this.toString().split("@")[1] + " onDestroy");
		
	}

}


