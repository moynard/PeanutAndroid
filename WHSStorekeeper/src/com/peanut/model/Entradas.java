package com.peanut.model;


public class Entradas {
protected int idEntrada;
protected int idMovimiento;
protected int idSKU;
protected String lote;
protected int estado;
protected String serie;
protected String Fechalote;
protected int cantidad;
protected String unidad;
protected String idUbicacion;
protected String producto;
protected String Fechamov;
long  id;





public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public Entradas(int idEntrada, int idMovimiento, int idSKU, String lote,
		int estado, String serie, String fechalote, int cantidad,
		String unidad, String idUbicacion, String producto, String fechamov) {
	super();
	this.idEntrada = idEntrada;
	this.idMovimiento = idMovimiento;
	this.idSKU = idSKU;
	this.lote = lote;
	this.estado = estado;
	this.serie = serie;
	Fechalote = fechalote;
	this.cantidad = cantidad;
	this.unidad = unidad;
	this.idUbicacion = idUbicacion;
	this.producto = producto;
	Fechamov = fechamov;
}
public int getIdEntrada() {
	return idEntrada;
}
public void setIdEntrada(int idEntrada) {
	this.idEntrada = idEntrada;
}
public int getIdMovimiento() {
	return idMovimiento;
}
public void setIdMovimiento(int idMovimiento) {
	this.idMovimiento = idMovimiento;
}
public int getIdSKU() {
	return idSKU;
}
public void setIdSKU(int idSKU) {
	this.idSKU = idSKU;
}
public String getLote() {
	return lote;
}
public void setLote(String lote) {
	this.lote = lote;
}
public int getEstado() {
	return estado;
}
public void setEstado(int estado) {
	this.estado = estado;
}
public String getSerie() {
	return serie;
}
public void setSerie(String serie) {
	this.serie = serie;
}
public String getFechalote() {
	return Fechalote;
}
public void setFechalote(String fechalote) {
	Fechalote = fechalote;
}
public int getCantidad() {
	return cantidad;
}
public void setCantidad(int cantidad) {
	this.cantidad = cantidad;
}
public String getUnidad() {
	return unidad;
}
public void setUnidad(String unidad) {
	this.unidad = unidad;
}
public String getIdUbicacion() {
	return idUbicacion;
}
public void setIdUbicacion(String idUbicacion) {
	this.idUbicacion = idUbicacion;
}
public String getProducto() {
	return producto;
}
public void setProducto(String producto) {
	this.producto = producto;
}
public String getFechamov() {
	return Fechamov;
}
public void setFechamov(String fechamov) {
	Fechamov = fechamov;
}




}
