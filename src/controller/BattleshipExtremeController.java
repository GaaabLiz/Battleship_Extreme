package controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.BattleshipExtremeModel;
import model.Nave;
import model.PuntoCardinale;
import view.BattleshipExtremeView;
import view.ConsoleView;
import view.ImpostazioniPartitaView;
import view.ImpostazioniView;

public class BattleshipExtremeController {
	
	private BattleshipExtremeModel model;
	private BattleshipExtremeView view;
	private ActionListener uscitaDalGioco;
	private ActionListener apriRegoleGioco;
	private ActionListener nuovaPartita;
	private ActionListener apriImpostazioni;
	private ActionListener visualizzaConsole;
	private ActionListener calcolaTempoTrascorso;
	private ActionListener iniziaPartita;
	private ActionListener colpisciCella;
	private ActionListener nascondiSpinnerColpisci;

	
	Boolean cellagiacolpita = true;
	Boolean giocatore_ha_vinto = false;
	Boolean cpu_ha_vinto = false;
	
	/**
	 * @param model
	 * @param view
	 */
	public BattleshipExtremeController(BattleshipExtremeModel model, BattleshipExtremeView view) {
		super();
		this.model = model;
		this.view = view;
		
		// Inizializzazione degli event Handler
		set_Action_nuovaPartita();
		set_Action_uscitaDalGioco();
		set_Action_apriImpostazioni();
		set_Action_visualizzaConsole();
		set_Action_apriRegoleGioco();
		set_Action_calcolaTempoTrascorso();
		set_Action_iniziaPartita();
		set_Action_colpisciCella();
		set_Action_nascondiSpinnerColpisci();
		
		
		// Settaggio Action Listener
		view.getMenu_File_new().addActionListener(nuovaPartita);
		view.getMenu_File_esci().addActionListener(uscitaDalGioco);
		view.getMenu_Modifica_settings().addActionListener(apriImpostazioni);
		view.getMenu_Visualizza_console().addActionListener(visualizzaConsole);
		view.getMenu_Aiuto_regole().addActionListener(apriRegoleGioco);
		view.getBtn_nuovaPartita().addActionListener(nuovaPartita);
		view.getMenu_Partita_TempoCorrente().addActionListener(calcolaTempoTrascorso);
		view.getBtn_inziaPartita().addActionListener(iniziaPartita);
		view.getBtnColpisciCella().addActionListener(colpisciCella);
		view.getChckbxColpisciCoordCasuale().addActionListener(nascondiSpinnerColpisci);
	}
	
	
	
	private void set_Action_colpisciCella() {
		colpisciCella = new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				int coordX = 0;
				int coordY = 0;
				
				// Il giocatore cerca di colpire una cella avversaria
				if ((model.getTurnoAttuale() == 0) &&  (cpu_ha_vinto == false) && (giocatore_ha_vinto == false)) {
//					view.writeChatLineTurno(model.getTurniCount());
					if (view.getChckbxColpisciCoordCasuale().isSelected()) {
						Boolean cordGenerataOk = false;
						do {
							Point p = model.getCpu().generaCoordinateCasuali(model.getDimensioneMappa());
							cellagiacolpita = model.getGiocatore().controllaCellaGiaColpita(model.getMappe_Giocatore().getTentativiDiAffondEffettuati(), p);
							if (cellagiacolpita) {
								model.aggiungiLog("ERRORE", "Turno Giocatore", "Le coordinate casuali generate per il giocatore sono già state usate." + p);
								cordGenerataOk = false;
							}else {
								cordGenerataOk = true;
								coordX = p.x;
								coordY = p.y;
							}
						} while (cordGenerataOk == false);			
						
					}else {
						coordX = (int) view.getSpinnerValueX().getValue();
						coordY = (int) view.getSpinnerValueY().getValue();
						model.aggiungiLog("INFO", "Turno Giocatore", "La variabile X contenuta nello spinnerX è: " + coordX);
						model.aggiungiLog("INFO", "Turno Giocatore", "La variabile Y contenuta nello spinnerY è: " + coordY);
					}			
					cellagiacolpita = model.getGiocatore().controllaCellaGiaColpita(model.getMappe_Giocatore().getTentativiDiAffondEffettuati(), new Point(coordX, coordY));
					if (cellagiacolpita) {
						model.aggiungiLog("ERRORE", "Turno Giocatore", "CElla già tentata di colpirla :" + new Point(coordX, coordY));
						JFrame f=new JFrame();  
						JOptionPane.showMessageDialog(f,"Hai gia' tentato di colpire questa cella. Inserisci nuove coordinate.", "Errore Coordinate,", JOptionPane.ERROR_MESSAGE);  
					}else {
						model.aggiungiLog("DEBUG", "Turno Giocatore", "Le coordinate sono giuste (non hai mai colpito qui:" + new Point(coordX, coordY) + ")");
					
						model.getMappe_Giocatore().aggiungiTentativoDiAffondamento(new Point(coordX, coordY));
						view.updateTentativiDiAffondamento(0, model.getMappe_Giocatore().getTentativiDiAffondEffettuati());
						
						Boolean naveColpita = model.getGiocatore().colpisci(model.getMappe_Cpu(), new Point(coordX, coordY));
						if (naveColpita) {
							
							view.writeChatLine("Hai colpito una nave della CPU!");
							model.aggiungiLog("INFO", "Turno Giocatore", "La nave della CPU posizionata in X=" + coordX + " Y=" + coordY + " è stata colpita!");
							model.getMappe_Giocatore().aggiungiAffondamento(new Point(coordX, coordY), model.getMappe_Cpu(), model.getCpu());
							view.updateNaviAffondate(0, model.getMappe_Giocatore());
							
							//controllo se la nave è affondata (se è stata colpita)
							Boolean naveColpitaAffondata = model.getMappe_Giocatore().controlloNaveColpitaAffondata(new Point(coordX, coordY), model.getMappe_Cpu(), model.getCpu());
							if (naveColpitaAffondata) {
								model.aggiungiLog("INFO", "Turno Giocatore", "La nave che hai appena colpito è stata totalmente affondata.");
								view.writeChatLine("La nave che hai colpito è stata affondata.");
							}
							
							// Controllo se giocatore ha vinto
							giocatore_ha_vinto = model.getGiocatore().controllaVittoria(model.getMappe_Giocatore(), model.getMappe_Cpu());
							if ((cpu_ha_vinto == false) && (giocatore_ha_vinto == true)) {
								view.writeChatLine("Complimenti!");
								view.writeChatLine("Hai affondato tutte le navi della CPU");
								view.writeChatLine("Hai vinto la partita!");
								view.getPanello_GestioneTurno().setVisible(false);
							}else {
								
							}
						}else {
							view.writeChatLine("Non hai colpito nessuna nave della CPU");
						}
						model.setTurniCount(model.getTurniCount()+1);
						model.stoppaTimer();
						model.getGiocatore().aggiungiPunteggio(model.getSecondTimer(), naveColpita);
						view.getLabelValuePunteggio().setText(view.getLabelValuePunteggio().getText() + Integer.toString(model.getGiocatore().punteggio));
						chiamaCPUperTurno();
						view.getLabelValueTurno().setText(String.valueOf(model.getTurniCount()));
					}
				}
				
								
				
			}
		};
		
	}



	private void set_Action_iniziaPartita() {
		
		iniziaPartita = new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// Azioni visualizzazioni campo di gioco
				view.getBtn_inziaPartita().setEnabled(false);
				model.startaTimer();
				view.visualizzaCampoDiGioco();
				view.creaGriglie(model.getDimensioneMappa());			
				view.updateNaviPlayer(0, model.getMappe_Giocatore().getSpazioNavi(), model.getDimensioneMappa(), model.MOSTRA_NAVI_CPU);
				view.getBtn_nuovaPartita().setEnabled(false);
				view.getBtn_nuovaPartita().setVisible(true);
				view.getMenu_Partita_TempoCorrente().setEnabled(true);
				view.getPanelloPunteggioTempo().setVisible(true);
				view.getPanello_InformazioniPartitaMaster().setVisible(true);
				view.getPanello_GestioneTurno().setVisible(true);
				
				// Creazione e posizionamento navi CPU
				creaPosNaviCpu();
				
				// Se MOSTRA ID asserito visualizza ID
				if (model.MOSTRA_NUMERI_NAVE) {view.visualizzaIdNave(model.getMappe_Giocatore(), 0);}
				if (model.MOSTRA_NUMERI_NAVE) {view.visualizzaIdNave(model.getMappe_Cpu(), 1);}
				
				// gestione partita
				view.writeChatLine("Benvenuto su Battleship Extreme!");
				view.writeChatLine("La partita è iniziata!");
				view.writeChatLine("\n");
				model.setTurnoAttuale(model.CODICE_GIOCATORE);
				
				view.writeChatLineTurno(model.getTurniCount());
				view.writeChatLine("Ora è il turno del giocatore.");
				view.writeChatLine("Usa il riquadro sotto per colpire una cella avversaria.");
				view.writeChatLine("In attesa che il giocatore colpisca...");
				
				
				
			}
		};
		
		
	}



	private void set_Action_calcolaTempoTrascorso() {
		calcolaTempoTrascorso = new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				model.stoppaTimer();
				String temp = model.getActualTimer();
				 JFrame f=new JFrame();  
				 JOptionPane.showMessageDialog(f,"Dall'inizio della partita sono trascorsi " + temp + ".");  
			}
		};
		
	}
	
	
	private void set_Action_nascondiSpinnerColpisci() {
		nascondiSpinnerColpisci = new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				if(view.getChckbxColpisciCoordCasuale().isSelected()) {
					view.getSpinnerValueX().setEnabled(false);
					view.getSpinnerValueY().setEnabled(false);
				} else {
					view.getSpinnerValueX().setEnabled(true);
					view.getSpinnerValueY().setEnabled(true);
				}
			}
		};
		
	}



	private void set_Action_nuovaPartita() {
		nuovaPartita = new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				view.getMenu_Modifica_settings().setEnabled(false);
				ImpostazioniPartitaView impostazioniPartita = new ImpostazioniPartitaView(model, view);	
			}
		};
		
	}



	private void set_Action_uscitaDalGioco() {
		uscitaDalGioco = new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame s = new JFrame();
				int a=JOptionPane.showConfirmDialog(s,"Sei sicuro di uscire dal gioco?");  
				if(a==JOptionPane.YES_OPTION){  
				    s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
				    System.exit(0);
				} 		
			}
		};
		
	}
	
	
	private void set_Action_apriImpostazioni() {
		apriImpostazioni = new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				ImpostazioniView impostazioni = new ImpostazioniView(model, view);		
			}
		};
	}
	
	
	
	private void set_Action_visualizzaConsole() {
		visualizzaConsole = new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				ConsoleView console = new ConsoleView(model.getLogGioco(), view);		
			}
		};
	}
	
	
	private void set_Action_apriRegoleGioco() {
		apriRegoleGioco = new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				Runtime rt = Runtime.getRuntime();
				String url = "https://it.wikipedia.org/wiki/Battaglia_navale_(gioco)";
				try {
					rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
			}
		};
	}
	
	
	
	/**
	 * Metodo che posiziona casualmente della navi nella mappa della CPU in base al modello navi stabilito dal 
	 * giocatore.
	 */
	private void creaPosNaviCpu() {
		
		// Variabili per memorizzare info nave
		int idNave;
		int dimNave;
		Point CoordinatePruaNave = new Point();
		PuntoCardinale o;
		Boolean cordOrientOk = null;
		Boolean naveDentroLaMappa = null;
		Boolean naveNonInConflitto = null;
		int[] modello = model.getModelloNavi();
		
		
		// Stampa info
		model.aggiungiLog("INFO", "Crea/pos Navi CPU", "Ora la CPU genera' casualmente le sue navi in base alla dimensioni delle tue navi.");
		
		
		// Ciclo che si occupa della creazione/posizionamento navi della CPU
		for (int i = 0; i < model.getNumeroNavi(); i++) {
					
			/* Settaggio ID Nave ------------------------------*/
			idNave = i+1;
			
			/* controllo tipologia nave corrente dal modello */
			dimNave = modello[i];
			model.aggiungiLog("DEBUG", "Crea/pos Navi CPU", "Il modello della nave #" + (i+1) + " dice dim: " + modello[i]);
			
			
			/* Controllo coordinate e orientamento -------------*/
			do {
				
				/* generazione cordinate prua casuali */
				CoordinatePruaNave = model.getCpu().generaCoordinateCasuali(model.getDimensioneMappa());
							
				/* Generazione orientamento casuale */
				o = model.getCpu().generaOrientamentoCasuale();	
				
				/* Stampa valori per scopi di debug */
				model.aggiungiLog("DEBUG", "Crea/pos Navi CPU", "La dimensione della nave #" + (i+1) + " CPU e': " + dimNave);
				model.aggiungiLog("DEBUG", "Crea/pos Navi CPU", "Le coordinate della prua nave #"+ (i+1) + " sono: " + CoordinatePruaNave);
				model.aggiungiLog("DEBUG", "Crea/pos Navi CPU", "L'orientamento scelto per la nave #" + (i+1) + " e': " + o);

				
				/* Controllo fuoriuscita nave dalla mappa */
				naveDentroLaMappa = model.controlloFuoriuscitaNave(dimNave, CoordinatePruaNave, o);
				if (naveDentroLaMappa) {
					model.aggiungiLog("DEBUG", "Crea/pos Navi CPU", "La nave generata rientra perfettamente nella mappa.");
				}else {
					model.aggiungiLog("DEBUG", "Crea/pos Navi CPU", "La nave generata esce dalla mappa.");	
				}
				
				
				/* Controllo se ci sono navi vicine */
				naveNonInConflitto = model.controllaNaviVicine(dimNave, CoordinatePruaNave, o, model.getMappe_Cpu());
				if (naveNonInConflitto) {
					model.aggiungiLog("DEBUG", "Crea/pos Navi CPU", "La nave inserita non e' in conflitto con altre navi.");
				}else {
					model.aggiungiLog("DEBUG", "Crea/pos Navi CPU", "La nave inserita e' in conflitto o vicina con un altra nave.");
				}
				
				
				/* Se entrambi i controlli hanno successo procedi con il piazzamento nave */
				if (naveDentroLaMappa && naveNonInConflitto) {
					cordOrientOk = true;
				}else {
					cordOrientOk = false;
					model.aggiungiLog("DEBUG", "Crea/pos Navi CPU", "Almeno uno dei controlli sul posizionamento della nave e' fallito.");
				}
			} while (cordOrientOk == false);
			
			
			
			/* Creazione dell'oggetto nave ---------------------------*/
			Nave naveDefinitiva = new Nave(idNave, dimNave, o, CoordinatePruaNave);
						
			/* Aggiunta della nave alla CPU ---------------S-------*/
			model.getCpu().aggiungiNaveAlleMieNavi(naveDefinitiva);
						
			/* Aggiunta nave nello spazio navi del player */
			model.getMappe_Cpu().piazzaNaveNellaMappa(naveDefinitiva);
									
			/* aggiorna la view per visualizzare la mappa aggiornata con la nave appena aggiunta */
			view.updateNaviPlayer(1, model.getMappe_Cpu().getSpazioNavi(), model.getDimensioneMappa(), model.MOSTRA_NAVI_CPU);
			
			/* stampa informazioni */
			model.aggiungiLog("DEBUG", "Crea/pos Navi CPU", "La nave #" + (i+1) + " è stata generata e piazzata dalla CPU nella sua mappa.");
		}
	}
	
	
	private void chiamaCPUperTurno() {
		// La CPU fa la sua mossa
		model.setTurnoAttuale(model.CODICE_CPU);
		
		if ((model.getTurnoAttuale() == 1) && (giocatore_ha_vinto == false) && (cpu_ha_vinto == false)) {
			view.writeChatLine("\n");
			view.writeChatLineTurno(model.getTurniCount());
			view.writeChatLine("Ora è il turno della CPU!");
			view.writeChatLine("La CPU tenterà di colpire una tua nave.");
			view.writeChatLine("La CPU sta colpendo...");
//			try {
//				Thread.sleep(model.getSecondiTurnoCpu() * 1000);
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			
			Point CoordinateDaColpire = new Point();
			do {
				// Generazione coordinata
				CoordinateDaColpire = model.getCpu().generaCoordinateCasuali(model.getDimensioneMappa());
				
				// Controllo cella già colpita
				cellagiacolpita = model.getCpu().controllaCellaGiaColpita(model.getMappe_Cpu().getTentativiDiAffondEffettuati(), CoordinateDaColpire);
				if (cellagiacolpita) {
					model.aggiungiLog("ERRORE", "Turno CPU", "CElla già tentata di colpirla :" + CoordinateDaColpire);
				}else {
					model.aggiungiLog("DEBUG", "Turno CPU", "Cella non ancora colpita:" + CoordinateDaColpire);
				}
				
			} while (cellagiacolpita == true);
			
			model.getMappe_Cpu().aggiungiTentativoDiAffondamento(CoordinateDaColpire);
			view.updateTentativiDiAffondamento(1, model.getMappe_Cpu().getTentativiDiAffondEffettuati());
			
			Boolean naveColpita = model.getCpu().colpisci(model.getMappe_Giocatore(), CoordinateDaColpire);
			if (naveColpita) {
				
				view.writeChatLine("La CPU ha colpito una tua nave.");
				model.aggiungiLog("INFO", "Turno CPU", "La nave del giocatore posizionata in X=" + CoordinateDaColpire.x + " Y=" + CoordinateDaColpire.y + " è stata colpita!");
				
				//aggiungi affondamento della nave alla griglia navi
				model.getMappe_Cpu().aggiungiAffondamento(CoordinateDaColpire, model.getMappe_Giocatore(), model.getGiocatore());
				view.updateNaviAffondate(1, model.getMappe_Cpu());
				
				//controllo se la nave è affondata (se è stata colpita)
				Boolean naveColpitaAffondata = model.getMappe_Cpu().controlloNaveColpitaAffondata(CoordinateDaColpire, model.getMappe_Giocatore(), model.getGiocatore());
				if (naveColpitaAffondata) {
					view.writeChatLine("La nave che ti ha colpito è stata affondata!");
				}
				
				// Controllo se CPU ha vinto
				cpu_ha_vinto = model.getCpu().controllaVittoria(model.getMappe_Cpu(), model.getMappe_Giocatore());
				if ((cpu_ha_vinto == true) && (giocatore_ha_vinto == false)) {
					view.writeChatLine("Spiacenti!");
					view.writeChatLine("La CPU ha affondato tutte le tue navi");
					view.writeChatLine("Hai perso la partita.");
					view.getPanello_GestioneTurno().setVisible(false);
				}else {
				}
			}else {
				view.writeChatLine("CPU non ha colpito nessuna tua nave.");
			}
			
			view.writeChatLine("\n");
			model.setTurnoAttuale(model.CODICE_GIOCATORE);
			model.setTurniCount(model.getTurniCount()+1);
			model.stoppaTimer();
			model.getCpu().aggiungiPunteggio(model.getSecondTimer(), naveColpita);
			
			view.writeChatLineTurno(model.getTurniCount());
			view.writeChatLine("Ora è il turno del giocatore.");
			view.writeChatLine("Usa il riquadro sotto per colpire una cella avversaria.");
			view.writeChatLine("In attesa che il giocatore colpisca...");
			
			
		}
		
		
	}
	
	
	
	/**
	 * Metodo che esegue la partita vera e propria a Battaglia Navale. QUesto metodo viene chiamato quando
	 * il posizionamento navi di entrambi i giocatori e' completato.
	 */
	@SuppressWarnings("unused")
	private void eseguiPartita() {
		Boolean turno_Giocatore = true;
		Boolean turno_cpu = false;
		Boolean finePartita = false;
		Boolean giocatore_ha_vinto = false;
		Boolean cpu_ha_vinto = false;
		
		
		// Scrivi le primi righe di benvenuto nella chat
		
		
//		//Ciclo dei turni
//		do {
//			
//			
//			/* TURNO GIOCATORE ---------------------------------------------------------------- */
//			if ((turno_Giocatore == true) && (turno_cpu == false) && (cpu_ha_vinto == false)) {
//				
//				// Creazione dell'oggetto turno relativo al giocatore 
//				TurnoGiocatoreController turno = new TurnoGiocatoreController(model, view, controller);
//				
//				// Controllo se giocatore ha vinto
//				giocatore_ha_vinto = model.getGiocatore().controllaVittoria(model.getMappe_Giocatore(), model.getMappe_Cpu());
//				if ((cpu_ha_vinto == false) && (giocatore_ha_vinto == true)) {
//					System.out.println(controller.MSG_I + "COMPLIMENTI! Hai affondato tutte le navi della CPU e hai vinto la partita.");
//					finePartita = true;
//				}else {
//					finePartita = false;
//				}
//				
//				// Aumento turno
//				model.setNumeroTurno(model.getNumeroTurno()+1);
//				
////				//cambio turno
//				turno_Giocatore = false;
//				turno_cpu = true;
//				
//				controller.stampaLineeVuote(1);
//			}
//			
//			
//			/* TURNO CPU ---------------------------------------------------------------------- */
//			if ((turno_Giocatore == false) && (turno_cpu == true) && (giocatore_ha_vinto == false)) {
//				
//				// Creazione dell'oggetto turno relativo al giocatore 
//				TurnoCpuController turno = new TurnoCpuController(model, view, controller);
//				
//				// Controllo se CPU ha vinto
//				cpu_ha_vinto = model.getCpu().controllaVittoria(model.getMappe_Cpu(), model.getMappe_Giocatore());
//				if ((cpu_ha_vinto == true) && (giocatore_ha_vinto == false)) {
//					System.out.println(controller.MSG_I + "SPIACENTE! La CPU ha affondato tutte le tue navi e hai perso la partita.");
//					finePartita = true;
//				}else {
//					finePartita = false;
//				}
//				
//				// Aumento turno
//				model.setNumeroTurno(model.getNumeroTurno()+1);
//				
////				//cambio turno
//				turno_Giocatore = true;
//				turno_cpu = false;
//				
//				controller.stampaLineeVuote(1);
//			}
//			
//		} while (finePartita == false);
		
	}
	
	
}
