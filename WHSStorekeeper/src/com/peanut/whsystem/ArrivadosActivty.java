package com.peanut.whsystem;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.peanut.adapters.EntradasAdapter;
import com.peanut.model.Entradas;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;

public class ArrivadosActivty  extends Activity{
	private ProgressDialog progress;
	private EntradasAdapter adapter;
	private ArrayList<Entradas> arraydir;
	private ListView list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.arrivados_activity);
        getActionBar().setDisplayHomeAsUpEnabled(true);

		list=(ListView)findViewById(R.id.arrivadoListView);
		arraydir = new ArrayList<Entradas>();
		adapter = new EntradasAdapter(this, arraydir);
		list.setAdapter(adapter);
		
		
		
		
	}

	public void getlist(){
		  RequestQueue reqQueue = Volley.newRequestQueue(this);
	        String url = "http://peanut.16mb.com/ws/index.php/entrada";
	        
	        JsonObjectRequest jr =new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

				@Override
				public void onResponse(JSONObject response) {
					// TODO Auto-generated method stub
			parseJSONRespone(response);
			progress.dismiss();

					adapter.notifyDataSetChanged();
					progress.dismiss();
			        Log.w("myApp1", "busco");

					
					
				}
			}, new ErrorListener() {

				@Override
				public void onErrorResponse(VolleyError error) {
					// TODO Auto-generated method stub
			       Toast.makeText(getApplicationContext(), "Error en el servidor", Toast.LENGTH_SHORT).show();
					Log.w("myApp1", "busco2");
					progress.dismiss();


					
				}
			});
	        progress = ProgressDialog.show(this,"","Loading users");
	      //  jr.setRetryPolicy(new DefaultRetryPolicy(
	        //        200000, 
	          //      DefaultRetryPolicy.DEFAULT_MAX_RETRIES, 
	            //    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
	        reqQueue.add(jr);

	}
	
	  private void parseJSONRespone(JSONObject response) {
			// TODO Auto-generated method stub
			try {
			//	JSONArray responseData = response.optJSONArray();
				JSONArray entries = response.getJSONArray("Entradas");
				//JSONObject Jarray = new JSONObject();


				for (int count = 0; count < entries.length(); count++) {

					JSONObject anEntry = entries.getJSONObject(count);
					Entradas entrada = new Entradas(anEntry.optInt("IdEntrada"), anEntry.optInt("IdMov"), anEntry.optInt("IdSKU"), 
							anEntry.optString("Lote"), anEntry.optInt("IdEstado"), anEntry.optString("Serie"), anEntry.optString("FechaLote"), 
							anEntry.optInt("Cantidad"), anEntry.optString("Unidad"), 
							anEntry.optString("Ubicacion"), anEntry.optString("Producto"), anEntry.optString("Fechamov"));
					 Log.w("myApp1", entrada.getProducto());
					arraydir.add(entrada);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		arraydir.clear();
		getlist();
	}
	
	 public boolean onOptionsItemSelected(MenuItem item) 
	    {
	        switch (item.getItemId()) 
	        {
	        case android.R.id.home: 
	            onBackPressed();
	            break;

	        default:
	            return super.onOptionsItemSelected(item);
	        }
	        return true;
	    }

}
