package com.peanut.adapters;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.peanut.adapters.EntradasAdapter.EntradasViewHolder;
import com.peanut.model.Entradas;
import com.peanut.model.Salidas;
import com.peanut.whsystem.DetailEntradasActivity;
import com.peanut.whsystem.DetailSalidasActivity;
import com.peanut.whsystem.R;

public class SalidasAdapter extends BaseAdapter {
	protected Activity activity;
	protected ArrayList<Salidas> items;
	protected Context context;
	
static class EntradasViewHolder{
	TextView txproducto;
	TextView txtCantidad;
	TextView txtUnidad;
	TextView txtFechamov;
		
	}

	
public SalidasAdapter(Activity activity, ArrayList<Salidas> items) {
	super();
this.activity = activity;
this.items = items;
this.context =activity.getApplicationContext();

}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return items.get(position).getIdSalida();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final EntradasViewHolder viewHolder;
		View cell = convertView;
		if(convertView == null){
			LayoutInflater inf =(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		cell=inf.inflate(R.layout.item_arrivado,null);
		viewHolder = new EntradasViewHolder();
		viewHolder.txproducto =(TextView)cell.findViewById(R.id.txtNombre);
		viewHolder.txtCantidad =(TextView)cell.findViewById(R.id.txtCantidad);
		viewHolder.txtUnidad =(TextView)cell.findViewById(R.id.txtUnidad);
		viewHolder.txtFechamov=(TextView)cell.findViewById(R.id.txtFecha);
		
		cell.setTag(viewHolder);
		}else{
			viewHolder =(EntradasViewHolder)cell.getTag();
			}
		final Salidas salida = items.get(position);
		if(salida!=null){
			
			viewHolder.txproducto.setText(salida.getProducto().toString());
			viewHolder.txtCantidad.setText(Integer.toString(salida.getCantidad()) );
			viewHolder.txtUnidad.setText(salida.getUnidad().toString());
			viewHolder.txtFechamov.setText(salida.getFechamov().toString());
		}
		cell.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context.getApplicationContext(),DetailSalidasActivity.class);
Log.w("IDEntrada",Integer.toString( salida.getIdSalida()));
Log.w("PRodcuto",salida.getProducto().toString());

				intent.putExtra("producto",viewHolder.txproducto.getText().toString());
				intent.putExtra("IdEntrada", salida.getIdSalida());
				intent.putExtra("idMovimiento",salida.getIdMovimiento());
				intent.putExtra("idSKU",salida.getIdSKU());
				intent.putExtra("idEstado",salida.getEstado());
				intent.putExtra("Cantidad",salida.getCantidad());
				intent.putExtra("Lote",salida.getLote());
				intent.putExtra("fechalote", salida.getFechalote());
				intent.putExtra("serie", salida.getSerie());
				intent.putExtra("Unidad", salida.getUnidad());
				intent.putExtra("Fechamov",salida.getFechamov());
			activity.startActivity(intent);
			}
		});
		
	
		
		return cell;
	}

}
