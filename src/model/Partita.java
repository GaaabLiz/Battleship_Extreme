package model;

import java.io.Serializable;

public class Partita implements Serializable{
	
	private String nomeGiocatore;
	private int punteggioGiocatore;
	private int punteggioCpu;
	private String durataPartita;
	private int dimMappa;
	private int numNavi;
	
	/**
	 * @param nomeGiocatore
	 * @param punteggioGiocatore
	 * @param punteggioCpu
	 * @param durataPartita
	 */
	public Partita(String nomeGiocatore, int punteggioGiocatore, int punteggioCpu, String durataPartita) {
		super();
		this.nomeGiocatore = nomeGiocatore;
		this.punteggioGiocatore = punteggioGiocatore;
		this.punteggioCpu = punteggioCpu;
		this.durataPartita = durataPartita;
	}

	public Partita() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the nomeGiocatore
	 */
	public String getNomeGiocatore() {
		return nomeGiocatore;
	}

	
	/**
	 * @return the durataPartita
	 */
	public String getDurataPartita() {
		return durataPartita;
	}

	/**
	 * @param nomeGiocatore the nomeGiocatore to set
	 */
	public void setNomeGiocatore(String nomeGiocatore) {
		this.nomeGiocatore = nomeGiocatore;
	}


	/**
	 * @param durataPartita the durataPartita to set
	 */
	public void setDurataPartita(String durataPartita) {
		this.durataPartita = durataPartita;
	}

	/**
	 * @return the punteggioGiocatore
	 */
	public int getPunteggioGiocatore() {
		return punteggioGiocatore;
	}

	/**
	 * @return the punteggioCpu
	 */
	public int getPunteggioCpu() {
		return punteggioCpu;
	}

	/**
	 * @param punteggioGiocatore the punteggioGiocatore to set
	 */
	public void setPunteggioGiocatore(int punteggioGiocatore) {
		this.punteggioGiocatore = punteggioGiocatore;
	}

	/**
	 * @param punteggioCpu the punteggioCpu to set
	 */
	public void setPunteggioCpu(int punteggioCpu) {
		this.punteggioCpu = punteggioCpu;
	}

	/**
	 * @return the dimMappa
	 */
	public int getDimMappa() {
		return dimMappa;
	}

	/**
	 * @return the numNavi
	 */
	public int getNumNavi() {
		return numNavi;
	}

	/**
	 * @param dimMappa the dimMappa to set
	 */
	public void setDimMappa(int dimMappa) {
		this.dimMappa = dimMappa;
	}

	/**
	 * @param numNavi the numNavi to set
	 */
	public void setNumNavi(int numNavi) {
		this.numNavi = numNavi;
	}
	
	
	
	
	
	
	
	
	
	
	

}
