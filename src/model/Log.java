package model;

import java.time.LocalDateTime;

public class Log {
	private String ora;
	private String tipo;
	private String fonte;
	private String testoLog;
	
	/**
	 * @param tipo
	 * @param fonte
	 * @param testoLog
	 */
	public Log(String tipo, String fonte, String testoLog) {
		super();
		this.tipo = tipo;
		this.fonte = fonte;
		this.testoLog = testoLog;
		setOra();
	}
	
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
