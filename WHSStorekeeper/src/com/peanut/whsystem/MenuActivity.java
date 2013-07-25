package com.peanut.whsystem;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.SyncStateContract.Constants;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends Activity{
public TextView txtMail;
public String email;
public Button btnarrivado;
public Button btnPicklist;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_activity);
		txtMail = (TextView)findViewById(R.id.usermail);
		btnPicklist =(Button)findViewById(R.id.picklistBtn);
		btnarrivado =(Button)findViewById(R.id.ArrivdadosBtn);
		SharedPreferences settings = getSharedPreferences("Datos", 0);

	
		txtMail.setText(settings.getString("email", null));
		
		btnarrivado.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), ArrivadosActivty.class);
				
				startActivity(intent);
			}
		});
		
		btnPicklist.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
	Intent intent = new Intent(getApplicationContext(), PickListActivity.class);
				
				startActivity(intent);
			}
		});
        
	}
	
	
	 @Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

	}


	
}
