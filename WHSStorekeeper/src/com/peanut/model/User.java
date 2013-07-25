package com.peanut.model;

public class User {
protected int idUsuario;
protected int idPerfil;
protected String email;
protected int estatus;




public User(int idUsuario, int idPerfil, String email, int estatus) {
	super();
	this.idUsuario = idUsuario;
	this.idPerfil = idPerfil;
	this.email = email;
	this.estatus = estatus;
}
public int getIdUsuario() {
	return idUsuario;
}
public void setIdUsuario(int idUsuario) {
	this.idUsuario = idUsuario;
}
public int getIdPerfil() {
	return idPerfil;
}
public void setIdPerfil(int idPerfil) {
	this.idPerfil = idPerfil;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public int getEstatus() {
	return estatus;
}
public void setEstatus(int estatus) {
	this.estatus = estatus;
}


}
