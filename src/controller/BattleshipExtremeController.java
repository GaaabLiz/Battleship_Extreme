package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.BattleshipExtremeModel;
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
		
		
		// Settaggio Action Listener
		view.getMenu_File_new().addActionListener(nuovaPartita);
		view.getMenu_File_esci().addActionListener(uscitaDalGioco);
		view.getMenu_Modifica_settings().addActionListener(apriImpostazioni);
		view.getMenu_Visualizza_console().addActionListener(visualizzaConsole);
		view.getMenu_Aiuto_regole().addActionListener(apriRegoleGioco);
		view.getBtn_nuovaPartita().addActionListener(nuovaPartita);
		view.getMenu_Partita_TempoCorrente().addActionListener(calcolaTempoTrascorso);
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
	
	
}
