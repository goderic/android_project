package com.example.databasetest;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private MyDatabaseHelper dbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		dbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 1);

		// button for creating database
		Button createDatabase = (Button) findViewById(R.id.create_database);
		createDatabase.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dbHelper.getWritableDatabase();
				Toast.makeText(MainActivity.this, "create databases!",
						Toast.LENGTH_SHORT).show();
			}

		});

		// button for adding new entries
		Button addData = (Button) findViewById(R.id.add_data);
		addData.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				SQLiteDatabase db = dbHelper.getWritableDatabase();

				try {
					// 1st record
					ContentValues values = new ContentValues();
					values.put("name", "The Da Vinci Code");
					values.put("author", "Dan Brown");
					values.put("pages", 454);
					values.put("price", 16.96);

					db.insert("Book", null, values);
					values.clear();

					// 2nd record
					values.put("name", "The Lost Symbol");
					values.put("author", "Dan Brown");
					values.put("pages", 510);
					values.put("price", 19.95);

					db.insert("Book", null, values);
				} catch (Exception e) {
					Toast.makeText(MainActivity.this,
							"fail to insert 2 records into Book!",
							Toast.LENGTH_SHORT).show();
					e.printStackTrace();
				}

				Toast.makeText(MainActivity.this,
						"insert 2 records into Book!", Toast.LENGTH_SHORT)
						.show();
			}

		});

		// button for updating table
		Button updateData = (Button) findViewById(R.id.update_data);
		updateData.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				SQLiteDatabase db = dbHelper.getWritableDatabase();

				try {

					ContentValues values = new ContentValues();
					values.put("price", 10.99);
					db.update("Book", values, "name = ?",
							new String[] { "The Da Vinci Code" });

				} catch (Exception e) {
					Toast.makeText(MainActivity.this,
							"fail to update table Book!", Toast.LENGTH_SHORT)
							.show();
					e.printStackTrace();
				}

				Toast.makeText(MainActivity.this, "update Book!",
						Toast.LENGTH_SHORT).show();
			}

		});

		// button for deleting table
		Button deleteData = (Button) findViewById(R.id.delete_data);
		deleteData.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				SQLiteDatabase db = dbHelper.getWritableDatabase();

				db.delete("Book", "pages > ?", new String[] { "500" });

				Toast.makeText(MainActivity.this, "delete Book!",
						Toast.LENGTH_SHORT).show();
			}

		});

		// button for query table
		Button queryData = (Button) findViewById(R.id.query_data);
		queryData.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				SQLiteDatabase db = dbHelper.getWritableDatabase();

				Cursor cursor = db.query("Book", null, null, null, null, null,
						null);

				if (cursor.moveToNext()) {

					do {
						String name = cursor.getString(cursor
								.getColumnIndex("name"));
						String author = cursor.getString(cursor
								.getColumnIndex("author"));
						int pages = cursor.getInt(cursor
								.getColumnIndex("pages"));
						double price = cursor.getDouble(cursor
								.getColumnIndex("price"));

						Log.d("qzb", "book name is " + name);
						Log.d("qzb", "book author is " + author);
						Log.d("qzb", "book pages is " + pages);
						Log.d("qzb", "book price is " + price);
					} while (cursor.moveToNext());

				}
				cursor.close();

				Toast.makeText(MainActivity.this, "query Book!",
						Toast.LENGTH_SHORT).show();
			}

		});

		// button for replace table
		Button replaceData = (Button) findViewById(R.id.replace_data);
		replaceData.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				SQLiteDatabase db = dbHelper.getWritableDatabase();
				db.beginTransaction();

				try {
					db.delete("Book", null, null);

					// manually throw a exception
//					if (true) {
//						throw new NullPointerException();
//					}

					ContentValues values = new ContentValues();
					values.put("name", "Game of Thrones");
					values.put("author", "George Martin");
					values.put("pages", 720);
					values.put("price", 20.85);
					db.insert("Book", null, values);

					// succeed to commit a transaction
					db.setTransactionSuccessful();
					Toast.makeText(MainActivity.this, "transaction succceeded!",
							Toast.LENGTH_SHORT).show();
					
				} catch (Exception e) {
					Toast.makeText(MainActivity.this, "transaction failed!",
							Toast.LENGTH_SHORT).show();
					e.printStackTrace();

				} finally {
					db.endTransaction();
				}


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
