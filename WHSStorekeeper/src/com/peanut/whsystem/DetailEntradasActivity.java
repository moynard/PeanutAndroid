package com.peanut.whsystem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.peanut.model.Entradas;
import com.peanut.model.UbicacionAlmacen;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class DetailEntradasActivity extends  Activity {
public Entradas entrada;
public TextView txtProducto;
public TextView txtCantidad;
public TextView txtUnidad;
public TextView txtSerie;
public TextView txtLote;
public TextView txtFechaLote;
public Button sendButton;
public AutoCompleteTextView txtSearch;
public String producto;
public String cantidad;
public int idEntrada;
public int idMovimiento;
public int idSKU;
public String lote;
public int idEstado;
public String serie;
UbicacionAlmacen ubicacion;
ArrayAdapter<String> adapter;
private static String[] ubicacionString ;
final Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_arrivados);
        getActionBar().setDisplayHomeAsUpEnabled(true);

		txtCantidad =(TextView)findViewById(R.id.txtCantidad);
		txtProducto =(TextView)findViewById(R.id.txtProducto);
		txtUnidad =(TextView)findViewById(R.id.txtUnidad);
		txtSerie =(TextView)findViewById(R.id.txtSerie);
		txtLote=(TextView)findViewById(R.id.txtlote);
		txtFechaLote=(TextView)findViewById(R.id.txtFecalote);
		txtSearch =(AutoCompleteTextView)findViewById(R.id.autocomplete);
		sendButton =(Button)findViewById(R.id.sendUbicacion);
		
		
		
		getUbicacion();
		

		Intent intent = getIntent();
		entrada = new Entradas(intent.getIntExtra("IdEntrada",0), intent.getIntExtra("idMovimiento",0),
				intent.getIntExtra("idSKU",0), intent.getStringExtra("Lote"), intent.getIntExtra("idEstado",0),
				intent.getStringExtra("serie"),
				intent.getStringExtra("fechalote"), intent.getIntExtra("Cantidad",0),
				intent.getStringExtra("Unidad"), "", 
				intent.getStringExtra("producto"), intent.getStringExtra("Fechamov"));
		Log.w("",Integer.toString( entrada.getCantidad()));
		Log.w("producto",entrada.getProducto().toString());
		Log.w("unidad",entrada.getUnidad());
		
		txtCantidad.setText(Integer.toString(entrada.getCantidad()));
		txtProducto.setText(entrada.getProducto().toString());
		txtUnidad.setText(entrada.getUnidad().toString());
		txtSerie.setText(entrada.getSerie().toString());
		txtLote.setText(entrada.getLote().toString());
		txtFechaLote.setText(entrada.getFechalote());
		
			sendButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			
				if(!txtSearch.equals(null)){
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						context);
		 
					// set title
					alertDialogBuilder.setTitle("Actualizar");
		 
					// set dialog message
					alertDialogBuilder
						.setMessage("Estas seguro que es la ubicacion exacta")
						.setCancelable(false)
						.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								// if this button is clicked, close
								// current activity
							//	De.this.finish();
								sendData(txtSearch.getText().toString(),Integer.toString( entrada.getIdEntrada()));

							}
						  })
						.setNegativeButton("No",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								// if this button is clicked, just close
								// the dialog box and do nothing
								dialog.cancel();
							}
						});
		 
						// create alert dialog
						AlertDialog alertDialog = alertDialogBuilder.create();
		 
						// show it
						alertDialog.show();
						}else{
							Toast.makeText(context, "Necesitas ingresar una Ubicacion", Toast.LENGTH_LONG).show();
						}
				
				
				
				
				
			
			}
		});
		
	
	}
	
	
	

	public void getUbicacion(){
		  RequestQueue reqQueue = Volley.newRequestQueue(this);
	        String url = "http://peanut.16mb.com/ws/index.php/ubicacion";
	        
	        JsonObjectRequest jr =new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

				@Override
				public void onResponse(JSONObject response) {
					// TODO Auto-generated method stub
					parseJSONRespone(response);
					
			  Log.w("myApp1", "busco");
			  Log.w("myapp",ubicacionString[0]);
			  	adapter = new ArrayAdapter<String>(getApplication(),
		                 android.R.layout.simple_dropdown_item_1line,ubicacionString );
		         txtSearch.setAdapter(adapter);

					
					
				}
			}, new ErrorListener() {

				@Override
				public void onErrorResponse(VolleyError error) {
					// TODO Auto-generated method stub
			       Toast.makeText(getApplicationContext(), "Error en el servidor", Toast.LENGTH_SHORT).show();
					Log.w("myApp1", "busco2");


					
				}
			});
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
				JSONArray entries = response.getJSONArray("Ubicaciones");
				//JSONObject Jarray = new JSONObject();

				ubicacionString =new String[entries.length()];
				
				for (int count = 0; count < entries.length(); count++) {
					
					JSONObject anEntry = entries.getJSONObject(count);
					 ubicacion = new UbicacionAlmacen(anEntry.optString("IdUbicacion"), anEntry.optString("IdUbicacionAlmacen"));


					 Log.w("myApp1", ubicacion.getIdUbicacionAlmacen());
					 
					 ubicacionString[count]= ubicacion.getIdUbicacionAlmacen().toString();
					 Log.w("ubicacion",ubicacionString[count]);
					 
				
				
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
 private void sendData(String ubicacion,String idEntrada){
	 
	 
	
      JSONObject reqBody = new JSONObject();
		try {
			reqBody.put("IdUbicacionAlmacen", ubicacion);
			

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  RequestQueue reqQueue = Volley.newRequestQueue(this);
	      String url = "http://peanut.16mb.com/ws/index.php/entrada/"+idEntrada;
      JsonObjectRequest jr =new JsonObjectRequest(Request.Method.PUT, url, reqBody, new Response.Listener<JSONObject>() {

			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stub
				
		  Log.w("myApp1", "busco");
		  AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					context);
	 
				// set title
				alertDialogBuilder.setTitle("Actualizar");
	 
				// set dialog message
				alertDialogBuilder
					.setMessage("Actualizacion Realizada con exito")
					.setCancelable(false)
					.setPositiveButton("OK",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							// if this button is clicked, close
							// current activity
						//	De.this.finish();
							  onBackPressed();

						}
					  });
					
	 
					// create alert dialog
					AlertDialog alertDialog = alertDialogBuilder.create();
	 
					// show it
					alertDialog.show();

		  

				
				
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
		       Toast.makeText(getApplicationContext(), "Error en el servidor", Toast.LENGTH_SHORT).show();
				Log.w("myApp1", "busco2");


				
			}
		});
    //  jr.setRetryPolicy(new DefaultRetryPolicy(
      //        200000, 
        //      DefaultRetryPolicy.DEFAULT_MAX_RETRIES, 
          //    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
      reqQueue.add(jr);
	 
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
