package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;

import model.BattleshipExtremeModel;

public class ImpostazioniView extends JFrame {

	private BattleshipExtremeModel model;
	private BattleshipExtremeView view;

	/**
	 * @param model il model di mvc
	 * @param view la view di mvc
	 * @throws HeadlessException eccezione
	 */
	public ImpostazioniView(BattleshipExtremeModel model, BattleshipExtremeView view) throws HeadlessException {
		super();
		this.model = model;
		this.view = view;
		
		// Inizializzazione del JFrame
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(BattleshipExtremeView.class.getResource("/icons/settings.png")));
        this.setTitle("Impostazioni");
        this.setBounds(500, 300, 800, 500);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        
        // Inizializzazione del contentPane Principale
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(view.COLORE_BIANCO);
        
        // Settaggio Label
        JLabel label_titolo = new JLabel("IMPOSTAZIONI DI GIOCO");
        label_titolo.setFont(view.FONT_SEGOE_H1_BI);
        label_titolo.setBounds(278, 11, 228, 43);
		this.getContentPane().add(label_titolo);
		
		// Separatore 
		JSeparator separator = new JSeparator();
		separator.setBounds(24, 53, 739, 2);
		this.getContentPane().add(separator);
		
		// Tabbed pane
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(view.FONT_SEGOE_H2_P);
		tabbedPane.setBounds(24, 65, 739, 329);
		this.getContentPane().add(tabbedPane);
		
		
		
		/* Pannello GIOCO --------------*/ 
		
		// Pannello
		JPanel panelloGioco = new JPanel();
		tabbedPane.addTab("Gioco", null, panelloGioco, null);
		panelloGioco.setLayout(null);
		
		// Dimension min mappa
		JLabel label_min_mappa = new JLabel("Dimensione minima Mappa : ");
		label_min_mappa.setFont(view.FONT_SEGOE_H2_P);
		label_min_mappa.setBounds(10, 38, 213, 22);
		panelloGioco.add(label_min_mappa);
		
		JSpinner spinner_min_mappa = new JSpinner();
		spinner_min_mappa.setBounds(218, 37, 48, 30);
		if (model.PARTITA_INIZIATA) {
			spinner_min_mappa.setEnabled(false);
		}
		spinner_min_mappa.setValue(model.MIN_DIM_MAPPA);
		panelloGioco.add(spinner_min_mappa);
		
		// Dimensione massima mappa
		JLabel label_max_mappa = new JLabel("Dimensione massima Mappa : ");
		label_max_mappa.setFont(view.FONT_SEGOE_H2_P);
		label_max_mappa.setBounds(10, 92, 213, 22);
		panelloGioco.add(label_max_mappa);
		
		JSpinner spinner_max_mappa = new JSpinner();
		spinner_max_mappa.setBounds(223, 91, 48, 30);
		if (model.PARTITA_INIZIATA) {
			spinner_min_mappa.setEnabled(false);
		}
		spinner_max_mappa.setValue(model.MAX_DIM_MAPPA);
		panelloGioco.add(spinner_max_mappa);
		
		// Dimension min navi
		JLabel label_min_navi = new JLabel("Numero minimo di navi : ");
		label_min_navi.setFont(view.FONT_SEGOE_H2_P);
		label_min_navi.setBounds(10, 149, 213, 22);
		panelloGioco.add(label_min_navi);
		
		JSpinner spinner_min_navi = new JSpinner();
		spinner_min_navi.setBounds(197, 148, 48, 30);
		if (model.PARTITA_INIZIATA) {
			spinner_min_navi.setEnabled(false);
		}
		spinner_min_navi.setValue(model.MIN_NUM_NAVI);
		panelloGioco.add(spinner_min_navi);
		
		// Dimenione max navi
		JLabel label_max_navi = new JLabel("Numero massimo di navi : ");
		label_max_navi.setFont(view.FONT_SEGOE_H2_P);
		label_max_navi.setBounds(10, 208, 213, 22);
		panelloGioco.add(label_max_navi);
		
		JSpinner spinner_max_navi = new JSpinner();
		spinner_max_navi.setBounds(197, 207, 48, 30);
		if (model.PARTITA_INIZIATA) {
			spinner_max_navi.setEnabled(false);
		}
		spinner_max_navi.setValue(model.MAX_NUM_NAVI);
		panelloGioco.add(spinner_max_navi);
		
		
		
		
		/* Pannello CHEAT & DEBUG --------------*/ 
		
		JPanel pannello_CheatDebug = new JPanel();
		tabbedPane.addTab("Cheat & Debug", null, pannello_CheatDebug, null);
		pannello_CheatDebug.setLayout(null);
		
		JCheckBox chckbxMostraLeNavi = new JCheckBox("Mostra le navi della CPU (è un cheat!)");
		chckbxMostraLeNavi.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		chckbxMostraLeNavi.setBounds(26, 28, 291, 23);
		if (model.MOSTRA_NAVI_CPU) {
			chckbxMostraLeNavi.setSelected(true);
		}else {
			chckbxMostraLeNavi.setSelected(false);
		}
		pannello_CheatDebug.add(chckbxMostraLeNavi);
		
		
		JCheckBox chckbxMostraID = new JCheckBox("Mostra gli ID della navi nella mappa (è un cheat)");
		chckbxMostraID.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		chckbxMostraID.setBounds(26, 78, 335, 23);
		if (model.MOSTRA_NUMERI_NAVE) {
			chckbxMostraID.setSelected(true);
		}else {
			chckbxMostraID.setSelected(false);
		}
		pannello_CheatDebug.add(chckbxMostraID);
		
		JCheckBox chckbxPartitaAutomatica = new JCheckBox("Abilita partita automatica (è un cheat)");
		if (model.ABILITA_PARTITA_AUTOMATICA) {
			chckbxPartitaAutomatica.setSelected(true);
		}else {
			chckbxPartitaAutomatica.setSelected(false);
		}
		chckbxPartitaAutomatica.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		chckbxPartitaAutomatica.setBounds(26, 128, 291, 23);
		pannello_CheatDebug.add(chckbxPartitaAutomatica);
		
		
		
		
		/* Pannello Partita --------------*/ 
		
		JPanel pannello_Partita = new JPanel();
		tabbedPane.addTab("Partita", null, pannello_Partita, null);
		pannello_CheatDebug.setLayout(null);
		
		JLabel lblNewLabel_1_4 = new JLabel("Secondi di attività turno CPU :");
		lblNewLabel_1_4.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_4.setBounds(20, 32, 213, 22);
		pannello_Partita.add(lblNewLabel_1_4);
		
		JSpinner spinner_SecCpu = new JSpinner();
		spinner_SecCpu.setValue(model.getSecondiTurnoCpu());
		spinner_SecCpu.setBounds(236, 31, 48, 30);
		spinner_SecCpu.setEnabled(false);
		pannello_Partita.add(spinner_SecCpu);
		
		
		/* Pannello database --------------*/ 
		
		JPanel pannello_Database = new JPanel();
		tabbedPane.addTab("Database", null, pannello_Database, null);
		pannello_Database.setLayout(null);
		
		JCheckBox chckbxConsentiIlSalvataggio = new JCheckBox("Consenti il salvataggio delle statistiche delle tue partite nel database.");
		if(model.ABILITA_SALVATAGGIO_DB) {
			chckbxConsentiIlSalvataggio.setSelected(true);
		}else {
			chckbxConsentiIlSalvataggio.setSelected(false);
		}	
		chckbxConsentiIlSalvataggio.setSelected(true);
		chckbxConsentiIlSalvataggio.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		chckbxConsentiIlSalvataggio.setBounds(20, 30, 502, 23);
		pannello_Database.add(chckbxConsentiIlSalvataggio);
		
		
		
		
		
		
		
		
		// Pulsanti di conferma/annulla
		JButton btn_conferma = new JButton("Applica Modifiche");
		btn_conferma.setBounds(208, 405, 189, 45);
		btn_conferma.setFont(view.FONT_SEGOE_H1_P);
		btn_conferma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				model.MIN_DIM_MAPPA = (int) spinner_min_mappa.getValue();
				model.aggiungiLog("INFO", "Impostazioni", "La dimensione minima della mappa e' stata settata a: " + model.MIN_DIM_MAPPA);
				
				model.MAX_DIM_MAPPA = (int) spinner_max_mappa.getValue();
				model.aggiungiLog("INFO", "Impostazioni", "La dimensione massima della mappa e' stata settata a: " + model.MAX_DIM_MAPPA);
				
				model.MIN_NUM_NAVI = (int) spinner_min_navi.getValue();
				model.aggiungiLog("INFO", "Impostazioni", "Il numero minimo di navi è stata settato a: " + model.MIN_NUM_NAVI);
				
				model.MAX_NUM_NAVI = (int) spinner_max_navi.getValue();
				model.aggiungiLog("INFO", "Impostazioni", "Il numero massimo di navi è stata settato a: " + model.MAX_NUM_NAVI);
				
				model.MOSTRA_NAVI_CPU = chckbxMostraLeNavi.isSelected();
				model.aggiungiLog("INFO", "Impostazioni", "Il mostra navi CPU è settato a: " + model.MOSTRA_NAVI_CPU);
				
				model.MOSTRA_NUMERI_NAVE = chckbxMostraID.isSelected();
				model.aggiungiLog("INFO", "Impostazioni", "Il mostra ID nave è settato a: " + model.MOSTRA_NUMERI_NAVE);
				
				model.ABILITA_PARTITA_AUTOMATICA = chckbxPartitaAutomatica.isSelected();
				model.aggiungiLog("INFO", "Impostazioni", "La partita automatica è settata a: " + model.ABILITA_PARTITA_AUTOMATICA);
				view.getMenu_Partita_GiocaAutomaticamente().setVisible(true);
				
				model.setSecondiTurnoCpu((int) spinner_SecCpu.getValue());
				model.aggiungiLog("INFO", "Impostazioni", "I secondi di attività di CPU sono: " + model.getSecondiTurnoCpu());
				
				model.ABILITA_SALVATAGGIO_DB = chckbxConsentiIlSalvataggio.isSelected();
				model.aggiungiLog("INFO", "Impostazioni", "Il salvataggio partite su DB è impostato su: " + model.ABILITA_SALVATAGGIO_DB);
				chiudiJFrame();
			}
		});
		this.getContentPane().add(btn_conferma);
		
		
		JButton btn_annulla = new JButton("Annulla");
		btn_annulla.setBounds(407, 405, 135, 45);
		btn_annulla.setFont(view.FONT_SEGOE_H1_P);
		btn_annulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chiudiJFrame();
			}
		});
		this.getContentPane().add(btn_annulla);
		
		
		
		this.setVisible(true);
	}
	
	
	private void chiudiJFrame() {
		this.setVisible(false);
		
	}
	
	
	
	
}
