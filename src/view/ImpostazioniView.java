package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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
	 * @param model
	 * @throws HeadlessException
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
