package com.example.servicebestpractice;

import java.util.Date;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d("qzb", "AlarmReceiver get broadcast and running at " + new Date().toString());
		Intent i = new Intent(context, LongRunningService.class);
		context.startService(i);
		
	}

}