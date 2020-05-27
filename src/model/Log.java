package model;

import java.time.LocalDateTime;

/**
 * Classe che rappresenta il singolo log che viene creato all'interno della partita.
 * @author Gabriele
 *
 */
public class Log {
	private String ora;
	private String tipo;
	private String fonte;
	private String testoLog;
	
	/**
	 * @param tipo il tipo di log
	 * @param fonte il nome del metodo che chiama il log
	 * @param testoLog il testo del log
	 */
	public Log(String tipo, String fonte, String testoLog) {
		super();
		this.tipo = tipo;
		this.fonte = fonte;
		this.testoLog = testoLog;
		setOra();
	}
	
	/**
	 * Metodo che crea e setta l'ora corrente.
	 */
	public void setOra() {
		String temp = LocalDateTime.now().toString();
		this.ora = temp.substring(11, 19);
	}

	/**
	 * @return the ora
	 */
	public String getOra() {
		return ora;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @return the fonte
	 */
	public String getFonte() {
		return fonte;
	}

	/**
	 * @return the testoLog
	 */
	public String getTestoLog() {
		return testoLog;
	}
	
}
