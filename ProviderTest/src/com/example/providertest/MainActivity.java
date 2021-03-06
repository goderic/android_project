package com.example.providertest;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private String newId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// add button
		Button addData = (Button) findViewById(R.id.add_data);
		addData.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Uri uri = Uri
						.parse("content://com.example.databasetest.provider/Book");

				ContentValues values = new ContentValues();
				values.put("name", "A clash of Kings");
				values.put("author", "George Martin");
				values.put("pages", 1040);
				values.put("price", 22.85);

				Uri newUri = getContentResolver().insert(uri, values);
				newId = newUri.getPathSegments().get(1);
				Log.d("qzb", "add() " + "uri=" + uri.toString() + " newId is "
						+ newId);
			}
		});

		// query button
		Button queryData = (Button) findViewById(R.id.query_data);
		queryData.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Uri uri = Uri
						.parse("content://com.example.databasetest.provider/Book");

				Cursor cursor = getContentResolver().query(uri, null, null,
						null, null);

				if (cursor != null) {
					while (cursor.moveToNext()) {
						int id = cursor.getInt(cursor.getColumnIndex("id"));

						String name = cursor.getString(cursor
								.getColumnIndex("name"));
						String author = cursor.getString(cursor
								.getColumnIndex("author"));
						int pages = cursor.getInt(cursor
								.getColumnIndex("pages"));
						double price = cursor.getDouble(cursor
								.getColumnIndex("price"));
						
                        Log.d("qzb", "primary key is " + id);
						Log.d("qzb", "book name is " + name);
						Log.d("qzb", "book author is " + author);
						Log.d("qzb", "book pages is " + pages);
						Log.d("qzb", "book price is " + price);
					}
					cursor.close();
				}

			}
		});

		// update button
		Button updateData = (Button) findViewById(R.id.update_data);
		updateData.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Uri uri = Uri
						.parse("content://com.example.databasetest.provider/Book/"
								+ newId);

				ContentValues values = new ContentValues();
				values.put("name", "A Storm of Swords");
				values.put("author", "George Martin");
				values.put("pages", 1216);
				values.put("price", 24.055);

				getContentResolver().update(uri, values, null, null);
				Log.d("qzb", "update() " + "uri=" + uri.toString() + " newId is " + newId);
			}
		});

		// delete button
		Button deleteData = (Button) findViewById(R.id.delete_data);
		deleteData.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Uri uri = Uri
						.parse("content://com.example.databasetest.provider/Book/"
								+ newId);
				getContentResolver().delete(uri, null, null);
				Log.d("qzb", "delete()" + "uri=" + uri.toString() + " newId is " + newId);
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
}
