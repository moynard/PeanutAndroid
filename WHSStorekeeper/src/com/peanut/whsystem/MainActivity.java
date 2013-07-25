package com.peanut.whsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.SyncStateContract.Constants;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.peanut.model.User;



public class MainActivity extends Activity {
protected EditText email;
protected EditText password;
protected Button login;
protected String emailText;
protected String passwordText;
protected User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        email = (EditText)findViewById(R.id.email);
        password =(EditText)findViewById(R.id.password);
       
        login=(Button)findViewById(R.id.loginButton);
        
        login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				passwordText =  password.getText().toString();
				emailText = email.getText().toString();
				JSONObject reqBody = new JSONObject();
				try {
					reqBody.put("usuario", emailText);
					reqBody.put("password", passwordText);

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				RequestQueue req= Volley.newRequestQueue(getApplication());
				 String url = "http://peanut.16mb.com/ws/index.php/login";
			        
			        JsonObjectRequest jr =new JsonObjectRequest(Request.Method.POST, url, reqBody, new Response.Listener<JSONObject>() {

						@Override
						public void onResponse(JSONObject response) {
							// TODO Auto-generated method stub
						 
								parseJSONRespone(response);
								
								
							        
								Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
							
								startActivity(intent);
							
					        Log.w("myApp1", "busco");

							
							
						}
					}, new ErrorListener() {

						@Override
						public void onErrorResponse(VolleyError error) {
							// TODO Auto-generated method stub
						//	Log.v("app",error.getMessage());
					        Log.w("myApp1", "busco2");
					        
					        AlertDialog alert = new AlertDialog.Builder(MainActivity.this).create();
					       alert.setTitle("Error");
					        alert.setMessage("Usuario y/o Contrasenaa Invalidos");
					        alert.setCancelable(true);
					        alert.show();


							
						}
					});
			        
			        if(!emailText.isEmpty() && !passwordText.isEmpty()){
				        req.add(jr);

			        }else{
			        	Log.w("prueba","mensaje de que falta password");
			        }

				
				
			}
		});
        
        
    }
    
    private void parseJSONRespone(JSONObject response) {
		// TODO Auto-generated method stub
		try {
		//	JSONArray responseData = response.optJSONArray();
			//JSONArray entries = response.getJSONArray("usuario");
			//JSONObject Jarray = new JSONObject();

			

				JSONObject anEntry = response.getJSONObject("usuario");
				user = new User(anEntry.optInt("IdUsuario"), anEntry.optInt("perfil"), anEntry.optString("nombre"), anEntry.optInt("estatus"));
				SharedPreferences settings = getSharedPreferences("Datos", 0);
				SharedPreferences.Editor editor = settings.edit();
				editor.putInt("IdUsuario", user.getIdUsuario());
				editor.putString("email", user.getEmail());
				editor.commit();
				
		        Log.w("myApp1", settings.getString("email", null));

				
				//Directivo directivo = new Directivo(null, anEntry.optString("nombre"),
		//				anEntry.optString("apellido"),anEntry.optString("id"));
		//		arraydir.add(directivo);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
   
}
