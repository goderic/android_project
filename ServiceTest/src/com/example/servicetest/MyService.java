package com.example.servicetest;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

	private DownloadBinder mBinder = new DownloadBinder();

	class DownloadBinder extends Binder {

		public void startDownload() {

			Log.d("qzb", "startDownload executed");

		}

		public int getProgress() {
			Log.d("qzb", "getProgress executed");
			return 0;
		}

	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.d("qzb", "onBind( " + intent.toString() + " executed");
		return mBinder;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.d("qzb", "onCreate executed");

		Notification notification = new Notification(R.drawable.ic_launcher,
				"Notification comes", System.currentTimeMillis());
		Intent notificationIntent = new Intent(this, MainActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
				notificationIntent, 0);
		notification.setLatestEventInfo(this, "This is title",
				"This is content", pendingIntent);

		startForeground(1, notification);

	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.d("qzb", "onStartCommand executed");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub

		super.onDestroy();
		Log.d("qzb", "onDestroy executed");
	}

}
