package com.example.broadcastbestpractice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {

	private EditText accountEdit;
	private EditText passwordEdit;
	private Button login;

	// add something for shared perferences supporting
	private SharedPreferences pref;
	private SharedPreferences.Editor editor;
	private CheckBox rememberPass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		// add something for shared perferences supporting
		pref = PreferenceManager.getDefaultSharedPreferences(this);

		accountEdit = (EditText) findViewById(R.id.account);
		passwordEdit = (EditText) findViewById(R.id.password);

		// added for remembering password
		rememberPass = (CheckBox) findViewById(R.id.remember_pass);
		boolean isRemember = pref.getBoolean("remember_password", false);

		if (isRemember) {
			// set account and pswd info to textview
			String account = pref.getString("account", "");
			String password = pref.getString("password", "");
			accountEdit.setText(account);
			passwordEdit.setText(password);
			rememberPass.setChecked(true);
		}

		login = (Button) findViewById(R.id.login);
		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String account = accountEdit.getText().toString();
				String password = passwordEdit.getText().toString();

				// if account is admin and pswd is 12345, then authentication
				// succeed
				if (account.equals("admin")) {
				} else {
					Toast.makeText(LoginActivity.this,
							"account " + account + " does NOT exist",
							Toast.LENGTH_SHORT).show();
					return;
				}

				if (password.equals("12345")) {

					editor = pref.edit();
					if (rememberPass.isChecked()) {
						editor.putBoolean("remember_password", true);
						editor.putString("account", account);
						editor.putString("password", password);
					} else {
						editor.clear();
					}
					editor.commit();

					Intent intent = new Intent(LoginActivity.this,
							MainActivity.class);
					startActivity(intent);
					finish();
				} else {
					Toast.makeText(LoginActivity.this,
							"password " + password + " is NOT correct",
							Toast.LENGTH_SHORT).show();
				}

			}
		});

	}
}
