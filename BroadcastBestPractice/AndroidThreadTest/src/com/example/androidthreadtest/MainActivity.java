package com.example.androidthreadtest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Process;

public class MainActivity extends Activity implements OnClickListener {

	public static final int UPDATE_TEXT = 1;

	private TextView text;
	private Button changeText;

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {

			Toast.makeText(MainActivity.this,
					"get something in handleMessage()", Toast.LENGTH_SHORT)
					.show();

			switch (msg.what) {
			case UPDATE_TEXT:
				text.setText("Nice to meet you!");
				break;
			default:
				break;

			}
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		text = (TextView) findViewById(R.id.text);
		changeText = (Button) findViewById(R.id.change_text);
		changeText.setOnClickListener(this);
		Log.d("qzb", "father taskid: " + getTaskId() + " main thread in java scope: "
				+ Thread.currentThread().getId());
		Log.d("qzb", "OS pid: " + Process.myPid() + " main os thread id: " + Process.myTid() );
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.change_text:
			new Thread(new Runnable() {
				@Override
				public void run() {
					// text.setText("Nice to meet you!");

					Message message = new Message();
					message.what = UPDATE_TEXT;
					handler.sendMessage(message);
					Log.d("qzb", "child taskid: " + getTaskId() + " thread in java scope "
							+ Thread.currentThread().getId());
					Log.d("qzb", "OS pid: " + Process.myPid() + " OS Tid: " + Process.myTid() );

				}
			}).start();

			break;

		default:
			break;
		}
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
}
