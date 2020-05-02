package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.BattleshipExtremeModel;
import view.BattleshipExtremeView;

public class BattleshipExtremeController {
	
	private BattleshipExtremeModel model;
	private BattleshipExtremeView view;
	private ActionListener uscitaDalGioco;
	private ActionListener apriRegoleGioco;
	
	/**
	 * @param model
	 * @param view
	 */
	public BattleshipExtremeController(BattleshipExtremeModel model, BattleshipExtremeView view) {
		super();
		this.model = model;
		this.view = view;
		
		// Inizializzazione degli event Handler
		set_Action_uscitaDalGioco();
		set_Action_apriRegoleGioco();
		
		// Settaggio Action Listener
		view.getMenu_File_esci().addActionListener(uscitaDalGioco);
		view.getMenu_Aiuto_regole().addActionListener(apriRegoleGioco);
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
