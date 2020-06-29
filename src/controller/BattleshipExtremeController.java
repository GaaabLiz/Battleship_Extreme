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
import view.StatistichePartitaView;
import view.StoricoPartiteView;

/**
 * Classe che aggiunge tutti gli actionListener ai pulsante del programma. Rapprensenta il Controller del pattern MVC.
 * @see BattleshipExtremeModel
 * @see BattleshipExtremeView
 * @author Gabriele
 *
 */
public class BattleshipExtremeController {
	
	// MVC
	private BattleshipExtremeModel model;
	private BattleshipExtremeView view;
	
	// Definizione degli action Listener
	private ActionListener uscitaDalGioco;
	private ActionListener apriRegoleGioco;
	private ActionListener nuovaPartita;
	private ActionListener apriImpostazioni;
	private ActionListener visualizzaConsole;
	private ActionListener calcolaTempoTrascorso;
	private ActionListener iniziaPartita;
	private ActionListener colpisciCella;
	private ActionListener nascondiSpinnerColpisci;
	private ActionListener giocaAutomaticamente;
	private ActionListener visualizzaStoricoPartite;
	private ActionListener visualizzaInfoProgetto;
	private ActionListener apriSitoWeb;

	// Variabili di controllo partita.
	Boolean cellagiacolpita = true;
	Boolean giocatore_ha_vinto = false;
	Boolean cpu_ha_vinto = false;
	Boolean partitaVinta = false;
	Boolean qualcunoHaVinto = false;
	
	/**
	 * Costruttore del controller. Vengono assegnati tutti gli action listener ai vari componenti.
	 * @param model Il model del MVC
	 * @param view La view principale
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
		set_Action_giocaAutomaticamente();
		set_Action_visualizzaStoricoPartite();
		set_Action_visualizzaInfoProgetto();
		set_Action_apriSitoWeb();
		
		
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
		view.getMenu_Partita_GiocaAutomaticamente().addActionListener(giocaAutomaticamente);
		view.getMenu_Visualizza_storicoPartite().addActionListener(visualizzaStoricoPartite);
		view.getMenu_Info_progetto().addActionListener(visualizzaInfoProgetto);
		view.getMenu_Info_sito().addActionListener(apriSitoWeb);
	}
	
	
	/**
	 * Settaggio actionListener relativo al componente.
	 */
	private void set_Action_visualizzaInfoProgetto() {
		visualizzaInfoProgetto = new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				String riga1 = "BattleshipExtreme |  Ver. 2.2" + "\n\n";
				String riga2 = "Questo è un progetto universitario per l'esame di Ingegneria del software. Anno accademico 2019/2020." + "\n\n";
				String riga3 = "lizzosgabriele@live.it";
				JFrame f=new JFrame();  
				JOptionPane.showMessageDialog(f,riga1 + riga2 + riga3);  
			}
		};
	}


	/**
	 * Settaggio actionListener relativo al componente.
	 */
	private void set_Action_visualizzaStoricoPartite() {
		visualizzaStoricoPartite = new ActionListener() {		
			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent e) {
				StoricoPartiteView s = new StoricoPartiteView(model, view);
			}
		};
		
	}
	
	
	/**
	 * Settaggio actionListener relativo al componente.
	 */
	private void set_Action_apriSitoWeb() {
		apriSitoWeb = new ActionListener() {		
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
	 * Settaggio actionListener relativo al componente. Qua si implemnta la logica dei turni dei giocatori.
	 */
	private void set_Action_colpisciCella() {
		colpisciCella = new ActionListener() {		
			@SuppressWarnings("unused")
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
							model.getCpu().mieCelleNaviAffondate = model.getCpu().mieCelleNaviAffondate + 1;
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
								partitaVinta = true;
								model.VINCITORE = 0;
							}else {
								
							}
						}else {
							view.writeChatLine("Non hai colpito nessuna nave della CPU");
						}
						model.setTurniCount(model.getTurniCount()+1);
						model.stoppaTimer();
						model.getGiocatore().aggiungiPunteggio(model.getSecondTimer(), naveColpita);
						int punteggioAttuale = model.getGiocatore().punteggio;
						view.getLabelValuePunteggio().setText(String.valueOf(punteggioAttuale));
						model.getGiocatore().turniGiocati = model.getGiocatore().turniGiocati + 1;
						view.getLabelValueTurno().setText(String.valueOf(model.getTurniCount()));
						view.getLabelValue_TurniGiocati().setText(String.valueOf(model.getGiocatore().turniGiocati));
						view.getLabelValue_tentativiDiaffondamento().setText(String.valueOf(model.getMappe_Giocatore().getNumTentativiDiAffondEffettuati()) + " / " + model.getMappe_Giocatore().getNumeroCelleMappa());
						view.getLabelValue_nCelleAffondate().setText(String.valueOf(model.getGiocatore().mieCelleNaviAffondate) + " / " + String.valueOf(model.getGiocatore().getNumeroCelleNaviPlayer()));
						view.getLabelValue_statoNavi().setText(String.valueOf(model.getMappe_Cpu().getMieNaviAffondate()) + " / " + model.getNumeroNavi());
						chiamaCPUperTurno();
						model.aggiungiLog("DEBUG", "Info giocatore", "Il turno attuale è: " + model.getTurniCount()); 
						model.aggiungiLog("DEBUG", "Info giocatore", "I turni giocati dal giocatore sono: " + model.getGiocatore().turniGiocati); 
						model.aggiungiLog("DEBUG", "Info giocatore", "I tentativi di affondamento effettuati dal giocatore sono: " + model.getMappe_Giocatore().getNumTentativiDiAffondEffettuati()); 
						model.aggiungiLog("DEBUG", "Info giocatore", "Le celle affondate del giocatore sono: " + model.getGiocatore().mieCelleNaviAffondate); 
						model.aggiungiLog("DEBUG", "Info giocatore", "Le navi affondate del giocatore sono: " + model.getMappe_Cpu().getMieNaviAffondate()); 
						
						
					}
				}
				
				if (partitaVinta) {
					StatistichePartitaView risultati_partita = new StatistichePartitaView(model, view);
				}
				
								
				
			}
		};
		
	}
	
	
	/**
	 * Settaggio actionListener relativo al componente.
	 */
	private void set_Action_giocaAutomaticamente() {
		
		giocaAutomaticamente = new ActionListener() {		
			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent e) {
				
				do {
					
					int coordX = 0;
					int coordY = 0;
					
					// Il giocatore cerca di colpire una cella avversaria
					if ((model.getTurnoAttuale() == 0) &&  (cpu_ha_vinto == false) && (giocatore_ha_vinto == false)) {
//						view.writeChatLineTurno(model.getTurniCount());
						
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
								model.getCpu().mieCelleNaviAffondate = model.getCpu().mieCelleNaviAffondate + 1;
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
									partitaVinta = true;
									model.VINCITORE = 0;
								}else {
									
								}
							}else {
								view.writeChatLine("Non hai colpito nessuna nave della CPU");
							}
							model.setTurniCount(model.getTurniCount()+1);
							model.stoppaTimer();
							model.getGiocatore().aggiungiPunteggio(model.getSecondTimer(), naveColpita);
							int punteggioAttuale = model.getGiocatore().punteggio;
							view.getLabelValuePunteggio().setText(String.valueOf(punteggioAttuale));
							model.getGiocatore().turniGiocati = model.getGiocatore().turniGiocati + 1;
							view.getLabelValueTurno().setText(String.valueOf(model.getTurniCount()));
							view.getLabelValue_TurniGiocati().setText(String.valueOf(model.getGiocatore().turniGiocati));
							view.getLabelValue_tentativiDiaffondamento().setText(String.valueOf(model.getMappe_Giocatore().getNumTentativiDiAffondEffettuati()) + " / " + model.getMappe_Giocatore().getNumeroCelleMappa());
							view.getLabelValue_nCelleAffondate().setText(String.valueOf(model.getGiocatore().mieCelleNaviAffondate) + " / " + String.valueOf(model.getGiocatore().getNumeroCelleNaviPlayer()));
							view.getLabelValue_statoNavi().setText(String.valueOf(model.getMappe_Cpu().getMieNaviAffondate()) + " / " + model.getNumeroNavi());
							chiamaCPUperTurno();
							
						}
					}
					
					if (partitaVinta) {
						qualcunoHaVinto = true;
						StatistichePartitaView risultati_partita = new StatistichePartitaView(model, view);
					}
					
				} while (qualcunoHaVinto == false);
				
				
				view.getMenu_Partita_GiocaAutomaticamente().setEnabled(false);
			}
		};	
	}


	/**
	 * Settaggio actionListener relativo al componente. Qua vengono effettuate tutte le operazioni preliminari all'inizio del gioco.
	 */
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
				view.getMenu_File_new().setEnabled(false);
				view.getBtn_nuovaPartita().setVisible(true);
				view.getMenu_Partita_TempoCorrente().setEnabled(true);
				view.getPanelloPunteggioTempo().setVisible(true);
				view.getPanello_InformazioniPartitaMaster().setVisible(true);
				view.getPanello_GestioneTurno().setVisible(true);
				view.getMenu_Partita_GiocaAutomaticamente().setEnabled(true);
				
				// Inizializzazione valori di default per nuova partita
				inizializzaLabelConValoriModel();
				model.getGiocatore().punteggio = 0;
				model.getCpu().punteggio = 0;
				model.getMappe_Giocatore().setSpazioNavi();
				model.getMappe_Cpu().setSpazioNavi();
				model.setTurnoAttuale(0);
				
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


	/**
	 * Settaggio actionListener relativo al componente.
	 */
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
	
	
	/**
	 * Settaggio actionListener relativo al componente.
	 */
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


	/**
	 * Settaggio actionListener relativo al componente.
	 */
	private void set_Action_nuovaPartita() {
		nuovaPartita = new ActionListener() {		
			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent e) {
				view.getMenu_Modifica_settings().setEnabled(false);
				ImpostazioniPartitaView impostazioniPartita = new ImpostazioniPartitaView(model, view);	
			}
		};
		
	}


	/**
	 * Settaggio actionListener relativo al componente.
	 */
	private void set_Action_uscitaDalGioco() {
		uscitaDalGioco = new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame s = new JFrame();
				int a=JOptionPane.showConfirmDialog(s,"Sei sicuro di uscire dal gioco?");  
				if(a==JOptionPane.YES_OPTION){  
				    s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
				    model.scriviFileDiLog();
				    System.exit(0);
				} 		
			}
		};
		
	}
	
	
	/**
	 * Settaggio actionListener relativo al componente.
	 */
	private void set_Action_apriImpostazioni() {
		apriImpostazioni = new ActionListener() {		
			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent e) {
				ImpostazioniView impostazioni = new ImpostazioniView(model, view);		
			}
		};
	}
	
	
	/**
	 * Settaggio actionListener relativo al componente.
	 */
	private void set_Action_visualizzaConsole() {
		visualizzaConsole = new ActionListener() {		
			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent e) {
				ConsoleView console = new ConsoleView(model.getLogGioco(), view);		
			}
		};
	}
	
	
	/**
	 * Settaggio actionListener relativo al componente.
	 */
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
	
	
	/**
	 * Metodo che controlla e gestisce il turno della CPU. Viene chiamato quando il turno del giocatore finisce.
	 */
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
				model.getGiocatore().mieCelleNaviAffondate = model.getGiocatore().mieCelleNaviAffondate + 1;
				
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
					partitaVinta = true;
					qualcunoHaVinto = true;
					model.VINCITORE = 1;
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
			model.getCpu().turniGiocati = model.getCpu().turniGiocati + 1;
			view.getLabelValueCpu_TurniGiocati().setText(String.valueOf(model.getCpu().turniGiocati));
			view.getLabelValueCpu_tentativiDiaffondamento().setText(String.valueOf(model.getMappe_Cpu().getNumTentativiDiAffondEffettuati()) + " / " + model.getMappe_Cpu().getNumeroCelleMappa());
			view.getLabelValueCpu_nCelleAffondate().setText(String.valueOf(model.getCpu().mieCelleNaviAffondate) + " / "+ model.getCpu().getNumeroCelleNaviPlayer());
			view.getLabelValueCpu_statoNavi().setText(String.valueOf(model.getMappe_Giocatore().getMieNaviAffondate()) + " / " + model.getNumeroNavi());
			model.aggiungiLog("DEBUG", "Info Cpu", "Il turno attuale è: " + model.getTurniCount()); 
			model.aggiungiLog("DEBUG", "Info Cpu", "I turni giocati dalla cpu sono: " + model.getCpu().turniGiocati); 
			model.aggiungiLog("DEBUG", "Info Cpu", "I tentativi di affondamento effettuati dalla cpu sono: " + model.getMappe_Cpu().getNumTentativiDiAffondEffettuati()); 
			model.aggiungiLog("DEBUG", "Info Cpu", "Le celle affondate della cpu sono: " + model.getCpu().mieCelleNaviAffondate); 
			model.aggiungiLog("DEBUG", "Info Cpu", "Le navi affondate dalla cpu sono: " + model.getMappe_Giocatore().getMieNaviAffondate()); 
			
			if (!cpu_ha_vinto) {
				view.writeChatLineTurno(model.getTurniCount());
				view.writeChatLine("Ora è il turno del giocatore.");
				view.writeChatLine("Usa il riquadro sotto per colpire una cella avversaria.");
				view.writeChatLine("In attesa che il giocatore colpisca...");
			}
			
			
			
		}
		
		
	}
	
	
	/**
	 * Metodo che setta tutte le label di informazioni dei giocatore con i valori di default.
	 */
	private void inizializzaLabelConValoriModel() {
		view.getLabelValue_nCelleAffondate().setText("0 /" + model.getGiocatore().getNumeroCelleNaviPlayer());
		view.getLabelValue_tentativiDiaffondamento().setText("0 / " + (model.getDimensioneMappa()*model.getDimensioneMappa()));
		view.getLabelValue_statoNavi().setText("0 / " + model.getNumeroNavi());
		view.getLabelValue_TurniGiocati().setText("0");
		view.getLabelValueCpu_nCelleAffondate().setText("0 /" + model.getGiocatore().getNumeroCelleNaviPlayer());
		view.getLabelValueCpu_tentativiDiaffondamento().setText("0 / " + (model.getMappe_Giocatore().getNumeroCelleMappa()));
		view.getLabelValueCpu_statoNavi().setText("0 / " + model.getNumeroNavi());
		view.getLabelValueCpu_TurniGiocati().setText("0");
	}
	
	
}
