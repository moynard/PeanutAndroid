package com.peanut.adapters;

import java.util.ArrayList;

import com.peanut.model.Entradas;
import com.peanut.whsystem.DetailEntradasActivity;
import com.peanut.whsystem.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class EntradasAdapter extends BaseAdapter {
	protected Activity activity;
	protected ArrayList<Entradas> items;
	protected Context context;
	
static class EntradasViewHolder{
	TextView txproducto;
	TextView txtCantidad;
	TextView txtUnidad;
	TextView txtFechamov;
		
	}

	
	public EntradasAdapter(Activity activity, ArrayList<Entradas> items) {
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
		return items.get(position).getIdEntrada();
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
		final Entradas entrada = items.get(position);
		if(entrada!=null){
			
			viewHolder.txproducto.setText(entrada.getProducto().toString());
			viewHolder.txtCantidad.setText(Integer.toString(entrada.getCantidad()) );
			viewHolder.txtUnidad.setText(entrada.getUnidad().toString());
			viewHolder.txtFechamov.setText(entrada.getFechamov().toString());
		}
		cell.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context.getApplicationContext(),DetailEntradasActivity.class);
Log.w("IDEntrada",Integer.toString( entrada.getIdEntrada()));
Log.w("PRodcuto",entrada.getProducto().toString());

				intent.putExtra("producto",viewHolder.txproducto.getText().toString());
				intent.putExtra("IdEntrada", entrada.getIdEntrada());
				intent.putExtra("idMovimiento",entrada.getIdMovimiento());
				intent.putExtra("idSKU",entrada.getIdSKU());
				intent.putExtra("idEstado",entrada.getEstado());
				intent.putExtra("Cantidad",entrada.getCantidad());
				intent.putExtra("Lote",entrada.getLote());
				intent.putExtra("fechalote", entrada.getFechalote());
				intent.putExtra("serie", entrada.getSerie());
				intent.putExtra("Unidad", entrada.getUnidad());
				intent.putExtra("Fechamov",entrada.getFechamov());
			activity.startActivity(intent);
			}
		});
		
	
		
		return cell;
	}

}
