package model;

import java.util.ArrayList;

public class BattleshipExtremeModel {
	
	// # COSTANTI PUBBLICHE DEL GIOCO #-----------------------------------------------------------------
		public int MAX_DIM_MAPPA = 20;						/* Dimensione massima della mappa */
		public int MIN_DIM_MAPPA = 10;						/* Dimensione minima della mappa  */
		public int MIN_NUM_NAVI = 2;						/* Dimensione minima delle navi   */
		public int MAX_NUM_NAVI = 20;						/* Dimensione massima delle navi  */
		public final int CODICE_GIOCATORE = 0;				/* Codice identificativo del giocatore */
		public final int CODICE_CPU = 1;					/* Codice identificativo della CPU */
		public Boolean PARTITA_INIZIATA = false;			/* Indentifica se la partita è in corso o no */
		public Boolean ABILITA_NAVE_DIM_1 = false;			/* Abilita la dimensione della nave corrispondete */
		
		// # COSTANTI USATE PER SCOPI DI DEBUG #-------------------------------------------------------------
		public final Boolean MOSTRA_NUMERI_NAVE = false;	/* Mostra sulle celle occupate l'ID della nave. */
		public final Boolean MOSTRA_NAVI_CPU = false; 		/* Mostra le navi della CPU nella mappa tentativi */
		
		// # VARIABILI PRINCIPALI DEL GIOCO #----------------------------------------------------------------
		private ArrayList<Log> LogGioco;
		private int dimensioneMappa;						/* La dimensione delle mappe di ogni giocatore */
//		private MappePlayer Mappe_Giocatore;				/* Le 2 mappe del giocatore (Navi e tentativi) */
//		private MappePlayer Mappe_Cpu;						/* Le 2 mappe della CPU (Navi e tentativi) */
		private int numeroNavi;								/* Il numero di navi per ogni giocatore */ 			
//		private Player giocatore;							/* Oggetto che indentifica l'utente */
//		private Cpu cpu;									/* Oggetto che identifica il computer che gioca */	
//		private int[] modelloNavi;							/* Modello che contiene i tipi di nave di ogni player */
//		private int numeroTurno;							/* Indica il numero del turno attuale */
		
		
		
		
		/**
		 * 
		 */
		public BattleshipExtremeModel() {
			setLogGioco();
		}
		
		public void aggiungiLog(String tipo, String fonte, String Text) {
			Log g = new Log(tipo, fonte, Text);
			LogGioco.add(g);
		}

		
		
		
		
		
		
		
		
		
		
		/* GETTERS AND SETTERS -------------------------------------------------------------------------------- */
		
		
		/**
		 * @return the logGioco
		 */
		public ArrayList<Log> getLogGioco() {
			return LogGioco;
		}

		/**
		 * @param logGioco the logGioco to set
		 */
		public void setLogGioco() {
			LogGioco = new ArrayList<Log>();
		}

		/**
		 * @return the dimensioneMappa
		 */
		public int getDimensioneMappa() {
			return dimensioneMappa;
		}

		/**
		 * @param dimensioneMappa the dimensioneMappa to set
		 */
		public void setDimensioneMappa(int dimensioneMappa) {
			this.dimensioneMappa = dimensioneMappa;
		}

		/**
		 * @return the numeroNavi
		 */
		public int getNumeroNavi() {
			return numeroNavi;
		}

		/**
		 * @param numeroNavi the numeroNavi to set
		 */
		public void setNumeroNavi(int numeroNavi) {
			this.numeroNavi = numeroNavi;
		}
		
		
		
		
		
		
		
}
