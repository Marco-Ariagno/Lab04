package it.polito.tdp.lab04.model;

public class Iscrizione {
	
	private int matricola;
	private String codins;
	/**
	 * @param matricola
	 * @param codins
	 */
	public Iscrizione(int matricola, String codins) {
		super();
		this.matricola = matricola;
		this.codins = codins;
	}
	public int getMatricola() {
		return matricola;
	}
	public void setMatricola(int matricola) {
		this.matricola = matricola;
	}
	public String getCodins() {
		return codins;
	}
	public void setCodins(String codins) {
		this.codins = codins;
	}
	

}
