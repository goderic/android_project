package com.example.servicebestpractice;

import java.util.Date;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

public class LongRunningService extends Service {

	public static final int FIVE_SEC = 5 * 1000;
	
	@Override
	public void onCreate() {
		Log.d("qzb", "LongRunning.onCreate () executed at " + new Date().toString());
		super.onCreate();
	}

	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		new Thread(new Runnable() {

			@Override
			public void run() {
				Log.d("qzb", "LongRunning.run() executed at " + new Date().toString());
//				stopSelf();
			}

		}).start();

		AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);

		long triggerAtTime = SystemClock.elapsedRealtime() + FIVE_SEC;

		Intent i = new Intent(this, AlarmReceiver.class);

		PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);

		manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		Log.d("qzb", "LongRunning.onDestroy() executed at " + new Date().toString());
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
