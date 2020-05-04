package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;

import model.BattleshipExtremeModel;
import model.PuntoCardinale;

public class PosizionamentoNaviView extends JFrame{
	
	private BattleshipExtremeModel model;
	private BattleshipExtremeView view;
	private PannelloGriglia griglia_navi;
	

	public PosizionamentoNaviView(BattleshipExtremeModel model, BattleshipExtremeView view, Object[] obj) {
		
		this.model = model;
		this.view = view;
		
		// Inizializzazione del JFrame
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(BattleshipExtremeView.class.getResource("/icons/maps.png")));
        this.setTitle("Posizionamento Navi Manuale");
        this.setBounds(500, 300, 800, 500);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        
        // Inizializzazione del contentPane Principale
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(view.COLORE_BIANCO);
        
        // Pannello griglia
        JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(27, 79, 350, 350);
		this.getContentPane().add(panel);
		panel.setLayout(new BorderLayout());
		
		griglia_navi = new PannelloGriglia(model.getDimensioneMappa());
		griglia_navi.setBackground(Color.WHITE);
		panel.add(griglia_navi, BorderLayout.CENTER);
		
		Boolean[][] spazioNavi = model.getMappe_Giocatore().getSpazioNavi();
		for (int i = 0; i < spazioNavi.length; i++) {
			for (int j = 0; j < spazioNavi.length; j++) {
				if (spazioNavi[i][j] == true) {
					griglia_navi.visualizzaPezzoNave(i, j, 0);
				}
			}
		}
		
		
		
		// Label e separatore
		JLabel lblPosizionamentoNavi = new JLabel("POSIZIONAMENTO NAVE");
		lblPosizionamentoNavi.setFont(view.FONT_SEGOE_H1_P);
		lblPosizionamentoNavi.setBounds(254, 11, 225, 43);
		this.getContentPane().add(lblPosizionamentoNavi);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(22, 50, 690, 18);
		this.getContentPane().add(separator);
		
		
		// Coordinata x
		JLabel labelX = new JLabel("Coordinata X Prua : ");
		labelX.setFont(view.FONT_SEGOE_H1_P);
		labelX.setBounds(444, 83, 158, 25);
		this.getContentPane().add(labelX);
		
		JSpinner spinner_X = new JSpinner();
		spinner_X.setFont(view.FONT_SEGOE_H1_P);
		spinner_X.setBounds(612, 83, 50, 32);
		this.getContentPane().add(spinner_X);
		
		
		// coordinata y
		JLabel labelY = new JLabel("Coordinata Y Prua : ");
		labelY.setFont(view.FONT_SEGOE_H1_P);
		labelY.setBounds(444, 125, 158, 25);
		this.getContentPane().add(labelY);
		
		JSpinner spinner_Y = new JSpinner();
		spinner_Y.setFont(view.FONT_SEGOE_H1_P);
		spinner_Y.setBounds(612, 125, 50, 32);
		this.getContentPane().add(spinner_Y);
		
		
		// Tipologia nave
		JLabel labelTipo = new JLabel("Tipologia Nave");
		labelTipo.setFont(view.FONT_SEGOE_H1_P);
		labelTipo.setBounds(488, 183, 127, 25);
		this.getContentPane().add(labelTipo);
		
		JComboBox comboBox_tipo = new JComboBox();
		comboBox_tipo.setModel(new DefaultComboBoxModel(new String[] 
				{ 
				"Cacciatorpedinieri (2 Celle)", 
				"Sottomarini (3 Celle)",
				"Incrociatori (4 Celle)",
				"Portaerei (5 Celle)",
				"Corazzate (6 Celle)"}));
		comboBox_tipo.setFont(view.FONT_SEGOE_H2_P);
		comboBox_tipo.setBounds(461, 210, 214, 32);
		this.getContentPane().add(comboBox_tipo);
		
		
		// Orinetamento
		JLabel labelOrient = new JLabel("Orientamento");
		labelOrient.setFont(view.FONT_SEGOE_H1_P);
		labelOrient.setBounds(505, 268, 110, 25);
		this.getContentPane().add(labelOrient);
		
		JComboBox comboBox_orient = new JComboBox();
		comboBox_orient.setModel(new DefaultComboBoxModel(new String[] {"Nord", "Sud", "Ovest", "Est"}));
		comboBox_orient.setFont(view.FONT_SEGOE_H2_P);
		comboBox_orient.setBounds(505, 296, 110, 25);
		this.getContentPane().add(comboBox_orient);
		
		// Pulsante contrllo nave
		JButton btnCheck = new JButton("Tenta il piazzamento");
		btnCheck.setFont(view.FONT_SEGOE_H1_P);
		btnCheck.setBounds(460, 384, 202, 45);
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Prendo i valori inseriti dall'utente
				int coordX = (int) spinner_X.getValue();
				int coordY = (int) spinner_Y.getValue();
				Point p = new Point(coordX, coordY);
				PuntoCardinale orinetamento = null;
				String o = (String) comboBox_orient.getSelectedItem();
				int dim = comboBox_tipo.getSelectedIndex() + 2;
				
				switch (o) {
				case "Nord":
					orinetamento = PuntoCardinale.NORD;
					break;
				case "Sud":
					orinetamento = PuntoCardinale.SUD;
					break;
				case "Est":
					orinetamento = PuntoCardinale.EST;
					break;
				case "Ovest":
					orinetamento = PuntoCardinale.OVEST;
					break;

				default:
					break;
				}
				
				// Stampo i debug nella console
				model.aggiungiLog("INFO", "Posizionamento Navi Manuale", "INPUT NAVE (X): " + coordX);
				model.aggiungiLog("INFO", "Posizionamento Navi Manuale", "INPUT NAVE (Y): " + coordY);
				model.aggiungiLog("INFO", "Posizionamento Navi Manuale", "INPUT NAVE (DIM): " + dim);
				model.aggiungiLog("INFO", "Posizionamento Navi Manuale", "INPUT NAVE (ORIENT): " + orinetamento);
				
				
				// COntrolli sui dati della nave
				Boolean controlloFuoriMappa = model.controlloFuoriuscitaNave(dim, p, orinetamento);
				model.aggiungiLog("INFO", "Posizionamento Navi Manuale", "Controllo fuori mappa: " + controlloFuoriMappa);
				Boolean controlloNaviVicine = model.controllaNaviVicine(dim, p, orinetamento, model.getMappe_Giocatore());
				model.aggiungiLog("INFO", "Posizionamento Navi Manuale", "Controllo navi vicine: " + controlloNaviVicine);
				
				if (controlloFuoriMappa && controlloNaviVicine) {
					    obj[0] = dim;
					    obj[1] = orinetamento;
					    obj[2] = p;
					    model.aggiungiLog("DEBUG", "Export Posizionamento Navi", "OBJ[0] = " + obj[0]);
					    model.aggiungiLog("DEBUG", "Export Posizionamento Navi", "OBJ[1] = " + obj[1]);
					    model.aggiungiLog("DEBUG", "Export Posizionamento Navi", "OBJ[2] = " + obj[2]);
					    JFrame f=new JFrame();  
					    JOptionPane.showMessageDialog(f,"Le impostazioni inserite sono correte e la nave e' stata creata.");
					    nascondiJFrame();
				}else {
					JFrame f=new JFrame();  
				    JOptionPane.showMessageDialog(f,"La nave inserita esce dalla mappa oppure e' in conflitto con altre navi.","Errore Posizionamento navi",JOptionPane.ERROR_MESSAGE); 
				}
			}
		});
		this.getContentPane().add(btnCheck);
		
		this.setVisible(true);
	}
	
	private void nascondiJFrame() {
		this.setVisible(false);
		this.dispose();
	}

}
