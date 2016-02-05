package com.hackerrank.dto;

/***********************
 * Clase que representa la respuesta del cliente
 **********************/ 
public class Respuesta {
	private String msgError;
	private boolean error;
	private int N;
	private int M;
	private String respuesta;
	private int matrixId;
	private int suma;
	private int casos;
	
	public int getN() {
		return N;
	}
	public void setN(int n) {
		N = n;
	}
	public int getM() {
		return M;
	}
	public void setM(int m) {
		M = m;
	}
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	public String getMsgError() {
		return msgError;
	}
	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public int getMatrixId() {
		return matrixId;
	}
	public void setMatrixId(int matrixId) {
		this.matrixId = matrixId;
	}
	public int getSuma() {
		return suma;
	}
	public void setSuma(int suma) {
		this.suma = suma;
	}
	public int getCasos() {
		return casos;
	}
	public void setCasos(int casos) {
		this.casos = casos;
	}
}
