package com.example.activitytest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.View.OnClickListener;
public class BaseActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
        Log.d("qzb", getClass().getSimpleName());
        ActivityCollector.addActivity(this);
	}	
	
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		ActivityCollector.removeActivity(this);
		
	}
}
