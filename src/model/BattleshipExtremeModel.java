package model;

import java.awt.Point;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

import controller.BattleshipExtremeController;

/**
 * Classe che rappresenta il model del pattern MVC. Questa è la classe principale della logica del gioco.
 * 
 * @see BattleshipExtremeView
 * @see BattleshipExtremeController
 * @author Gabriele
 */
@SuppressWarnings("unused")
public class BattleshipExtremeModel {

	// # COSTANTI GLOBALI DEL GIOCO #-------------------------------------------------------------------------
	public final int CODICE_GIOCATORE = 0;				/* Codice identificativo del giocatore */
	public final int CODICE_CPU = 1;					/* Codice identificativo della CPU */
	
	// # VARIABILI PER IMPOSTAZIONI DEL GIOCO #----------------------------------------------------------------
	public int MAX_DIM_MAPPA = 20;						/* Dimensione massima della mappa */
	public int MIN_DIM_MAPPA = 10;						/* Dimensione minima della mappa  */
	public int MIN_NUM_NAVI = 2;						/* Dimensione minima delle navi   */
	public int MAX_NUM_NAVI = 20;						/* Dimensione massima delle navi  */
	public Boolean ABILITA_SALVATAGGIO_DB = true;		/* Abilita il salvataggio della partita nel DB */
	public Boolean MOSTRA_NUMERI_NAVE = false;			/* CHEAT: Mostra sulle celle occupate l'ID della nave. */
	public Boolean MOSTRA_NAVI_CPU = false; 			/* CHEAT: Mostra le navi della CPU nella mappa tentativi */
	public Boolean ABILITA_PARTITA_AUTOMATICA = false;  /* CHEAT: se abilitato la CPU può giocare anche il turno del giocatore */
	private int secondiTurnoCpu = 3;					/* Il tempo di attività della CPU (non ancora implementanto) */
		
	// # VARIABILI PER SCOPI INTERNI #--------------------------------------------------------------------------
	public Boolean PARTITA_INIZIATA = false;			/* Indentifica se la partita è in corso o no */	
	public int VINCITORE = -1;							/* Usata per identificare che vince la partita */
	public String directoryLog;							/* Directory PC per salvare i file di log */
	public String directoryPartite;						/* Directory PC per salvare i file delle partite */	
	private Boolean pathEsiste;							/* Controllo sul path */
	private Boolean pathIsCartella;						/* Controllo sul path */
	private Boolean pathCanWrite;						/* Controllo sul path */
			
	// # VARIABILI PRINCIPALI DEL GIOCO #----------------------------------------------------------------	
	private ArrayList<Log> LogGioco;					/* Elenco dei log del gioco */
	private int dimensioneMappa;						/* La dimensione delle mappe di ogni giocatore */
	private MappePlayer Mappe_Giocatore;				/* Le 2 mappe del giocatore (Navi e tentativi) */
	private MappePlayer Mappe_Cpu;						/* Le 2 mappe della CPU (Navi e tentativi) */
	private int numeroNavi;								/* Il numero di navi per ogni giocatore */ 			
	private Player giocatore;							/* Oggetto che indentifica l'utente */
	private Cpu cpu;									/* Oggetto che identifica il computer che gioca */	
	private int[] modelloNavi;							/* Modello che contiene i tipi di nave di ogni player */
	private int numeroTurno;							/* Indica il numero del turno attuale */
	private String nomeGiocatore;						/* Indica il nome del giocatore */
	private Instant startPartita;						/* Memorizza l'istante di avvio partita */
	private Instant stopPartita;						/* Memorizza un qualsiasi istante settato */
	private int turnoAttuale = 0;						/* Gestisce il turno attuale della partita */								
	private int turniCount = 1;							/* Varaibile per gestire il count dei turni */
			
		
	
	/**
	 *  Costruttore del model. 
	 *  Vengono inzializzati i log e create le directory del gioco.
	 *  @see Log
	 *  @see Partita
	 */
	public BattleshipExtremeModel() {
		
		// Settaggio iniziale dei log del gioco
		setLogGioco();
		
		// Creazione directory
		directoryLog = System.getenv("APPDATA") + "\\Battleshipextreme\\Log";
		directoryPartite = System.getenv("APPDATA") + "\\Battleshipextreme\\Partite";		
		
		//inizializzazione del path e creazione del path sottoforma di file
		File pathLog = new File(directoryLog);
		File pathPartite = new File(directoryPartite);
		
		//controllo esistenza path (se non esiste la crea)
		if (pathLog.exists()) {
			pathEsiste = true;
			aggiungiLog("DEBUG", "DIRECTORY LOG", "Il path esiste.");
		}else {
			pathEsiste = false;
			aggiungiLog("DEBUG", "DIRECTORY LOG", "Il path non esiste e sta per essere creato.");
			pathLog.mkdir();
		}
		if (pathPartite.exists()) {
			pathEsiste = true;
			aggiungiLog("DEBUG", "DIRECTORY PARTITE", "Il path esiste.");
		}else {
			pathEsiste = false;
			aggiungiLog("DEBUG", "DIRECTORY PARTITE", "Il path non esiste e sta per essere creato.");
			pathPartite.mkdir();
		}
		
		//controllo se FILE è una directory
		if (pathLog.isDirectory()) {
			pathIsCartella = true;
			aggiungiLog("DEBUG", "DIRECTORY LOG", "Il path è una cartella ed è giusto che sia così.");
		}else {
			pathIsCartella = false;
			aggiungiLog("DEBUG", "DIRECTORY LOG", "Il path non è una cartella.");
		}
		if (pathPartite.isDirectory()) {
			pathIsCartella = true;
			aggiungiLog("DEBUG", "DIRECTORY PARTITE", "Il path è una cartella ed è giusto che sia così.");
		}else {
			pathIsCartella = false;
			aggiungiLog("DEBUG", "DIRECTORY PARTITE", "Il path non è una cartella.");
		}
		
		//controllo permesso di accesso nella directory
		if (pathLog.canWrite()) {
			pathCanWrite = true;
			aggiungiLog("DEBUG", "DIRECTORY LOG", "Nel path è possibile scrivere.");
		}else {
			pathCanWrite = false;
			aggiungiLog("DEBUG", "DIRECTORY LOG", "Nel path non è possibile scrivere.");
		}
		
		//controllo permesso di accesso nella directory
		if (pathPartite.canWrite()) {
			pathCanWrite = true;
			aggiungiLog("DEBUG", "DIRECTORY PARTITE", "Nel path è possibile scrivere.");
		}else {
			pathCanWrite = false;
			aggiungiLog("DEBUG", "DIRECTORY PARTITE", "Nel path non è possibile scrivere.");
		}			
	}
		
	
	
	/**
	 * Aggiunge un log all'interno dell'arraylist dei log del gioco. Tutti i log sono visibili nella console del gioco
	 * oppure alla chiusura del programma nel file di testo memorizzato nella directory (vedi impostazioni per vedere directory).
	 * @param tipo Il tipo del log
	 * @param fonte Il nome del metodo che chiama il log (per identificare chi chiama il log)
	 * @param Text Il testo del log
	 * @see Log
	 */
	public void aggiungiLog(String tipo, String fonte, String Text) {
		Log g = new Log(tipo, fonte, Text);
		LogGioco.add(g);
	}
		
		
	/**
	 * Metodo che starta il timer. Verrà usato insieme a stoppaTimer() per calcolare la durata della partita.
	 */
	public void startaTimer() {
		startPartita = Instant.now();
	}
		
	
	/**
	 * Metodo che setta il timer nell'istante chiamato. 
	 * Verrà usato insieme a iniziaTimer() per calcolare la durata della partita.
	 */
	public void stoppaTimer() {
		stopPartita = Instant.now();
	}
		
	
	/**
	 * Ritorna il numero di secondi tra i due instanti startPartita e stopPartita.
	 * Per chiamare questo metodo entrambi gli instanti devono essere settati!
	 * @return Il numerodi secondi tra gli istanti. 
	 */
	public Long getSecondTimer() {
		Duration Interval = Duration.between(startPartita, stopPartita);
		return Interval.getSeconds();
	}
		
	
	/**
	 * Metodo che calcola effettivamente il tempo tra i due istanti restituendo il numero esatto di minuti e secondi.
	 * @return Una stringa che rappresenta il numero di minuti e secondi.
	 */
	public String getActualTimer() {
		Long secondi = getSecondTimer();
		int minuti = 0;
		Boolean secondiMinori = false;
		
		do {
			if(secondi > 59) {
				minuti++;
				secondi = secondi - 60;
			} else {
				secondiMinori = true;
			}
		} while (secondiMinori == false);
		
		String temp = minuti + " Minuti" + " e " + secondi + " secondi";
		return temp;		
	}

		
	/**
	 * Inizializzazione del modello navi.
	 * @see Nave
	 */
	public void setModelloNavi() {
		this.modelloNavi = new int[numeroNavi];
		for (int i = 0; i < modelloNavi.length; i++) {
			modelloNavi[i] = 0;
		}
	}
		
		
	/**
	 * Metodo che controlla se una nave data le coordinate della sua prua e l'orientamento della poppa, essa 
	 * va fuori dalla mappa o no. Questo metodo non controlla se ci sono altre navi vicine.
	 * 
	 * @param dimNave Il numero di celle che occcupa la nave.
	 * @param coordinate Le coordinate della prua della nave
	 * @param verso La direzione della poppa.
	 * @return TRUE se la nave rientra dalla mappa, FALSE se esce dalla mappa.
	 * @see MappePlayer
	 */
	public Boolean controlloFuoriuscitaNave(int dimNave, Point coordinate, PuntoCardinale verso) {
		Boolean[] celleNaviControllate = new Boolean[dimNave];
		int posNaviCorrette = 0;
		int x_da_controllare = 0;
		int y_da_controllare = 0;
		
		//inizializza tutte le cella navi a false (tranne la prua che è gia stata controllata)
		for (int i = 0; i < celleNaviControllate.length; i++) {
			if (i == 0) {
				celleNaviControllate[i] = true;
			}else {
				celleNaviControllate[i] = false;
			}				
		}
		
		// Settaggio se una delle celle della nave è di tipo FUORIMAPPA
		for (int i = 0; i < dimensioneMappa; i++) {
			for (int j = 0; j < dimensioneMappa; j++) {
				if ((i == coordinate.x) && (j == coordinate.y)) {
					x_da_controllare = i;
					y_da_controllare = j;			
					for (int j2 = 0; j2 < celleNaviControllate.length; j2++) {
//							System.out.println("[DEBUG]: (controllo fuori mappa) Sto per controllare X=" + x_da_controllare + " Y=" + y_da_controllare);
						Point tempCoord = new Point(x_da_controllare, y_da_controllare);
						if (getMappe_Giocatore().getTipoCella(tempCoord) == Cella.FUORIMAPPA) {
							celleNaviControllate[j2] = false;
//								System.out.println("[DEBUG]: (controllo fuori mappa) La cella X=" + x_da_controllare + " Y=" + y_da_controllare + " è di tipo fuorimappa");
						}else {
							celleNaviControllate[j2] = true;
						}
						switch (verso) {
						case NORD:
							x_da_controllare--;
							break;	
						case SUD:
							x_da_controllare++;
							break;
						case OVEST:
							y_da_controllare--;
							break;
						case EST:
							y_da_controllare++;
							break;
						default:
							break; 
						}
						
					}
				}
			}
		}
		
		
		// se tutte le celle delle navi non escono dalla mappa ritorna TRUE, altrimenti false
		for (int i = 0; i < celleNaviControllate.length; i++) {
			if (celleNaviControllate[i] == true) {
				posNaviCorrette++;
			}
		}			
		if (posNaviCorrette == dimNave) {
			return true;
		}else {
			return false;
		}	
	}
		
		
	/**
	 * Metodo che controlla se ci sono navi vicine o coincidenti con la nave che si vuote piazzare nella mappa.
	 * 
	 * @param dimNave La dimensione della nave
	 * @param coordinate Le coordinate della prua della nave
	 * @param verso La direzione della poppa della nave
	 * @param mappe la mappa corrisponde al giocatore che chiama questo metodo
	 * @return TRUE se la nave puo' essere piazzata, FALSE altrimenti
	 * @see MappePlayer
	 */
	public boolean controllaNaviVicine(int dimNave, Point coordinate, PuntoCardinale verso, MappePlayer mappe) {
		// Definizione veriabili
		Boolean[] celleNaviControllate = new Boolean[dimNave];
		int posNaviCorrette = 0;
		int x_da_controllare = 0;
		int y_da_controllare = 0;
		Cella TipoCellaSopra = null;
		Cella TipoCellaDestra = null;
		Cella TipoCellaSinistra = null;
		Cella TipoCellaSotto = null;
		
		//inizializza tutte le cella navi a false
		for (int i = 0; i < celleNaviControllate.length; i++) {
			celleNaviControllate[i] = false;			
		}
		
		
		for (int i = 0; i < dimensioneMappa; i++) { // Scorri tutto lo spazio navi (X)
			for (int j = 0; j < dimensioneMappa; j++) {	// Scorri tutto lo spazio navi (Y)
				if ((i == coordinate.x) && (j == coordinate.y)) {	// Quando i,j sono le coordinate entra in IF
					x_da_controllare = i;
					y_da_controllare = j;			
					for (int j2 = 0; j2 < celleNaviControllate.length; j2++) {	// Scorri le celle vicine alla prua fino a dim nave
//							System.out.println("[DEBUG]: (controllo navi vicine) Sto per controllare X=" + x_da_controllare + " Y=" + y_da_controllare);
						Point p = new Point(x_da_controllare, y_da_controllare);						
						if (mappe.getTipoCella(p) == Cella.OCCUPATA) { // Se prua nave è occupata
							celleNaviControllate[j2] = false;
//								System.out.println("[DEBUG]: (controllo navi vicine) La cella X=" + x_da_controllare + " Y=" + y_da_controllare + " è di tipo occupata");
						}else { //se nella coordinata non è inserita nessuna nave, controlla i bordi
							TipoCellaSopra = mappe.getTipoCella(x_da_controllare - 1, y_da_controllare);
							TipoCellaSotto = mappe.getTipoCella(x_da_controllare + 1, y_da_controllare);
							TipoCellaDestra = mappe.getTipoCella(x_da_controllare, y_da_controllare + 1);
							TipoCellaSinistra = mappe.getTipoCella(x_da_controllare, y_da_controllare-1);
//								System.out.println("[DEBUG]: (controllo navi vicine) La cella sopra è: " + TipoCellaSopra);
//								System.out.println("[DEBUG]: (controllo navi vicine) La cella sotto è: " + TipoCellaSotto);
//								System.out.println("[DEBUG]: (controllo navi vicine) La cella destra è: " + TipoCellaDestra);
//								System.out.println("[DEBUG]: (controllo navi vicine) La cella sinistra è: " + TipoCellaSinistra);
							
							switch (verso) {
							case NORD: // se orientamento NORD procedi controllo verso sopra
								if (j2 == 0) {
									if ((TipoCellaSopra == Cella.LIBERA) && 
										((TipoCellaSotto == Cella.LIBERA)||(TipoCellaSotto == Cella.FUORIMAPPA)) &&
										((TipoCellaDestra == Cella.LIBERA)||(TipoCellaDestra == Cella.FUORIMAPPA)) &&
										((TipoCellaSinistra == Cella.LIBERA)||(TipoCellaSinistra == Cella.FUORIMAPPA)) ) {
										celleNaviControllate[j2] = true;
//											System.out.println("[DEBUG]: (controllo navi vicine) cellaNaviControllate[" + j2 + "] = " + celleNaviControllate[j2]);
									}else {
//											System.out.println("[DEBUG]: (controllo navi vicine) cellaNaviControllate[" + j2 + "] = " + celleNaviControllate[j2]);
										celleNaviControllate[j2] = false;
									}
								} else {
									if ( ((TipoCellaSopra == Cella.LIBERA) || (TipoCellaSopra == Cella.FUORIMAPPA)) && 
										 ((TipoCellaDestra == Cella.LIBERA) || (TipoCellaDestra == Cella.FUORIMAPPA)) && 
										 ((TipoCellaSinistra == Cella.LIBERA) || (TipoCellaSinistra == Cella.FUORIMAPPA)) ) {
											celleNaviControllate[j2] = true;
//												System.out.println("[DEBUG]: (controllo navi vicine) cellaNaviControllate[" + j2 + "] = " + celleNaviControllate[j2]);
										}else {
											celleNaviControllate[j2] = false;
//												System.out.println("[DEBUG]: (controllo navi vicine) cellaNaviControllate[" + j2 + "] = " + celleNaviControllate[j2]);
										}
								}
								break;
								
							case SUD:
								if (j2 == 0) {
									if ((TipoCellaSotto == Cella.LIBERA) && 
										((TipoCellaSopra == Cella.LIBERA)||(TipoCellaSopra == Cella.FUORIMAPPA)) &&
										((TipoCellaDestra == Cella.LIBERA)||(TipoCellaDestra == Cella.FUORIMAPPA)) &&
										((TipoCellaSinistra == Cella.LIBERA)||(TipoCellaSinistra == Cella.FUORIMAPPA)) ) {
										celleNaviControllate[j2] = true;
//											System.out.println("[DEBUG]: (controllo navi vicine) cellaNaviControllate[" + j2 + "] = " + celleNaviControllate[j2]);
									}else {
										celleNaviControllate[j2] = false;
//											System.out.println("[DEBUG]: (controllo navi vicine) cellaNaviControllate[" + j2 + "] = " + celleNaviControllate[j2]);
									}
								} else {
									if ( ((TipoCellaSotto == Cella.LIBERA) || (TipoCellaSotto == Cella.FUORIMAPPA)) && 
										 ((TipoCellaDestra == Cella.LIBERA) || (TipoCellaDestra == Cella.FUORIMAPPA)) && 
										 ((TipoCellaSinistra == Cella.LIBERA) || (TipoCellaSinistra == Cella.FUORIMAPPA)) ) {
											celleNaviControllate[j2] = true;
//												System.out.println("[DEBUG]: (controllo navi vicine) cellaNaviControllate[" + j2 + "] = " + celleNaviControllate[j2]);
										}else {
											celleNaviControllate[j2] = false;
//												System.out.println("[DEBUG]: (controllo navi vicine) cellaNaviControllate[" + j2 + "] = " + celleNaviControllate[j2]);
										}
								}
								break;
								
							case EST: 
								if (j2 == 0) {
									if ((TipoCellaDestra == Cella.LIBERA) && 
										((TipoCellaSopra == Cella.LIBERA)||(TipoCellaSopra == Cella.FUORIMAPPA)) &&
										((TipoCellaSotto == Cella.LIBERA)||(TipoCellaSotto == Cella.FUORIMAPPA)) &&
										((TipoCellaSinistra == Cella.LIBERA)||(TipoCellaSinistra == Cella.FUORIMAPPA)) ) {
										celleNaviControllate[j2] = true;
//											System.out.println("[DEBUG]: (controllo navi vicine) cellaNaviControllate[" + j2 + "] = " + celleNaviControllate[j2]);
									}else {
										celleNaviControllate[j2] = false;
//											System.out.println("[DEBUG]: (controllo navi vicine) cellaNaviControllate[" + j2 + "] = " + celleNaviControllate[j2]);
									}
								} else {
									if ( ((TipoCellaSotto == Cella.LIBERA) || (TipoCellaSotto == Cella.FUORIMAPPA)) && 
										 ((TipoCellaDestra == Cella.LIBERA) || (TipoCellaDestra == Cella.FUORIMAPPA)) && 
										 ((TipoCellaSopra == Cella.LIBERA) || (TipoCellaSopra == Cella.FUORIMAPPA)) ) {
											celleNaviControllate[j2] = true;
//												System.out.println("[DEBUG]: (controllo navi vicine) cellaNaviControllate[" + j2 + "] = " + celleNaviControllate[j2]);
										}else {
											celleNaviControllate[j2] = false;
//												System.out.println("[DEBUG]: (controllo navi vicine) cellaNaviControllate[" + j2 + "] = " + celleNaviControllate[j2]);
										}
								}
								break;
								
							case OVEST:
								if (j2 == 0) {
									if ((TipoCellaSinistra == Cella.LIBERA) && 
										((TipoCellaSopra == Cella.LIBERA)||(TipoCellaSopra == Cella.FUORIMAPPA)) &&
										((TipoCellaSotto == Cella.LIBERA)||(TipoCellaSotto == Cella.FUORIMAPPA)) &&
										((TipoCellaDestra == Cella.LIBERA)||(TipoCellaDestra == Cella.FUORIMAPPA)) ) {
										celleNaviControllate[j2] = true;
//											System.out.println("[DEBUG]: (controllo navi vicine) cellaNaviControllate[" + j2 + "] = " + celleNaviControllate[j2]);
									}else {
										celleNaviControllate[j2] = false;
//											System.out.println("[DEBUG]: (controllo navi vicine) cellaNaviControllate[" + j2 + "] = " + celleNaviControllate[j2]);
									}
								} else {
									if ( ((TipoCellaSotto == Cella.LIBERA) || (TipoCellaSotto == Cella.FUORIMAPPA)) && 
										 ((TipoCellaSinistra == Cella.LIBERA) || (TipoCellaSinistra == Cella.FUORIMAPPA)) && 
										 ((TipoCellaSopra == Cella.LIBERA) || (TipoCellaSopra == Cella.FUORIMAPPA)) ) {
											celleNaviControllate[j2] = true;
//												System.out.println("[DEBUG]: (controllo navi vicine) cellaNaviControllate[" + j2 + "] = " + celleNaviControllate[j2]);
										}else {
											celleNaviControllate[j2] = false;
//												System.out.println("[DEBUG]: (controllo navi vicine) cellaNaviControllate[" + j2 + "] = " + celleNaviControllate[j2]);
										}
								}
								break;
							default:
								break;
							}						
						}
						switch (verso) {
						case NORD:
							x_da_controllare--;
							break;
						case SUD:
							x_da_controllare++;
							break;
						case EST:
							y_da_controllare++;
							break;
						case OVEST:
							y_da_controllare--;
							break;
						default:
							break;
						}
					}
				}
			}
		}
		
		// se tutte le celle delle navi non escono dalla mappa ritorna TRUE, altrimenti false
				for (int i = 0; i < celleNaviControllate.length; i++) {
					if (celleNaviControllate[i] == true) {
						posNaviCorrette++;
					}
				}				
				if (posNaviCorrette == dimNave) {
					return true;
				}else {
					return false;
				}
	}
		
		
		
		
	/**
	 * Aggiorna il tipo della singola nave del modello delle navi. 
	 * Ogni giocatore crea le navi in base al modello.
	 * Questo metodo serve alla CPU per avere un modello base per creare le sue navi (stesse del giocatore) 
	 * 
	 * @param indice indice i-esimo della nave
	 * @param dim dimensione che l'iesima nave del modello deve avere
	 */
	public void aggiungiModelloNave(int indice, int dim) {
		modelloNavi[indice] = dim;
	}
		
		
	/**
	 * Metodo che scrive nella directory il file di log in formato .txt contenente tutti i log creati dal programma.
	 * @see Log
	 */
	public void scriviFileDiLog() {
		Date date = new Date(); 
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_HHmm");	
		String data = formatter.format(date);
//			System.out.println(data);
		File fout = new File(directoryLog + "\\" + data + ".txt");
		
		
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(fout);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		
		ArrayList<Log> logDaStampare = getLogGioco();
		
		for (int i = 0; i < logDaStampare.size(); i++) {
			
			String tempOra = "{" + logDaStampare.get(i).getOra() + "}";
			String tempTipo = "[" + logDaStampare.get(i).getTipo() + "]";
			String tempFonte = "(" + logDaStampare.get(i).getFonte() + ")";
			String tempText = logDaStampare.get(i).getTestoLog();
			String temp;
			
			temp = tempOra + " " + tempTipo + " " + tempFonte + " " + tempText;
					
			try {
				bw.write(temp);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				bw.newLine();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	 
		try {
			bw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
		
		
	/**
	 * Meetodo che scrive nella directory del programma il file contenente tutte le informazioni sulla partita.
	 * @param p La partita da salvare su file.
	 */
	public void scriviPartitaSuFile(Partita p) {
		Date date = new Date(); 
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_HHmm");	
		String data = formatter.format(date);
		File f = new File(directoryPartite + "\\" + data + ".dat");
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(f));
			out.writeObject(p);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
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
	
	/**
	 * @return L'oggetto che identifica le mappe del giocatore
	 */
	public MappePlayer getMappe_Giocatore() {
		return Mappe_Giocatore;
	}


	/**
	 * Crea l'oggetto che identifica le mappe del giocatore
	 * 
	 * @param dimensioneMappa La dimensione della mappa di gioco
	 */
	public void setMappe_Giocatore(int dimensioneMappa) {
		Mappe_Giocatore = new MappePlayer(dimensioneMappa);
	}


	/**
	 * @return L'oggetto che identifica le mappe della CPU
	 */
	public MappePlayer getMappe_Cpu() {
		return Mappe_Cpu;
	}


	/**
	 * Crea l'oggetto che identifica le mappe della CPU
	 * 
	 * @param dimensioneMappa La dimensione della mappa di gioco
	 */
	public void setMappe_Cpu(int dimensioneMappa) {
		Mappe_Cpu = new MappePlayer(dimensioneMappa);
	}
	
	
	/**
	 * @return L'oggetto di tipo Player che rappresenta il giocatore
	 */
	public Player getGiocatore() {
		return giocatore;
	}


	/**
	 * Crea un nuovo oggetto di tipo Player per il giocatore
	 */
	public void setGiocatore() {
		this.giocatore = new Player();
	}


	/**
	 * @return L'oggetto che rappresenta la CPU. Ricordare che la classe CPU estende player quindi tutti i 
	 * metodi della classe Player sono disponibile nella classe CPU
	 */
	public Cpu getCpu() {
		return cpu;
	}


	/**
	 * Crea l'oggetto di tipo CPU che gestisce il computer che gioca.
	 */
	public void setCpu() {
		this.cpu = new Cpu();
	}

	/**
	 * @return the nomeGiocatore
	 */
	public String getNomeGiocatore() {
		return nomeGiocatore;
	}

	/**
	 * @param nomeGiocatore the nomeGiocatore to set
	 */
	public void setNomeGiocatore(String nomeGiocatore) {
		this.nomeGiocatore = nomeGiocatore;
	}

	/**
	 * @return the modelloNavi
	 */
	public int[] getModelloNavi() {
		return modelloNavi;
	}

	/**
	 * @return the turnoAttuale
	 */
	public int getTurnoAttuale() {
		return turnoAttuale;
	}

	/**
	 * @param turnoAttuale the turnoAttuale to set
	 */
	public void setTurnoAttuale(int turnoAttuale) {
		this.turnoAttuale = turnoAttuale;
	}

	/**
	 * @return the secondiTurnoCpu
	 */
	public int getSecondiTurnoCpu() {
		return secondiTurnoCpu;
	}

	/**
	 * @param secondiTurnoCpu the secondiTurnoCpu to set
	 */
	public void setSecondiTurnoCpu(int secondiTurnoCpu) {
		this.secondiTurnoCpu = secondiTurnoCpu;
	}

	/**
	 * @return the turniCount
	 */
	public int getTurniCount() {
		return turniCount;
	}

	/**
	 * @param turniCount the turniCount to set
	 */
	public void setTurniCount(int turniCount) {
		this.turniCount = turniCount;
	}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
