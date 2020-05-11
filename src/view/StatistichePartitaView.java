package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import model.BattleshipExtremeModel;

public class StatistichePartitaView extends JFrame {

	@SuppressWarnings("serial")
	public StatistichePartitaView(BattleshipExtremeModel model, BattleshipExtremeView view) {
		
		// Inizializzazione del JFrame
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(BattleshipExtremeView.class.getResource("/icons/result.png")));
        this.setTitle("Statistiche partita");
        this.setBounds(700, 100, 650, 900);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        
        // Inizializzazione del contentPane Principale
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(view.COLORE_BIANCO);
        
        // Settaggio Label
        JLabel label_titolo = new JLabel("RISULTATI DELLA PARTITA");
        label_titolo.setFont(view.FONT_SEGOE_H1_BI);
        label_titolo.setBounds(174, 11, 286, 43);
		this.getContentPane().add(label_titolo);
		
		// Separatore 
		JSeparator separator = new JSeparator();
		separator.setBounds(39, 65, 555, 2);
		this.getContentPane().add(separator);
		
		String tempPlayer = "";
		if (model.VINCITORE == 0) {
			tempPlayer = "HAI VINTO";
		}else {
			tempPlayer = "HAI PERSO";
		}
		JLabel labelVittoriaPerdita = new JLabel(tempPlayer);
		labelVittoriaPerdita.setFont(new Font("Segoe UI", Font.BOLD, 35));
		labelVittoriaPerdita.setBounds(216, 102, 194, 43);
		labelVittoriaPerdita.setForeground(view.COLORE_AZZURRINO);
		this.getContentPane().add(labelVittoriaPerdita);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(17, 207, 592, 436);
		this.getContentPane().add(scrollPane);
		
		JTable table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Punteggio partita", String.valueOf(model.getGiocatore().punteggio), model.getCpu().punteggio},
				{"Navi Affondate", String.valueOf(model.getMappe_Cpu().getMieNaviAffondate()) + " / " + model.getNumeroNavi(), String.valueOf(model.getMappe_Giocatore().getMieNaviAffondate()) + " / " + model.getNumeroNavi()},
				{"Turni giocati", String.valueOf(model.getGiocatore().turniGiocati), String.valueOf(model.getCpu().turniGiocati)},
				{"Tentativi di affondamento", String.valueOf(model.getMappe_Giocatore().getNumTentativiDiAffondEffettuati()) + " / " + model.getMappe_Giocatore().getNumeroCelleMappa(), String.valueOf(model.getMappe_Cpu().getNumTentativiDiAffondEffettuati()) + " / " + model.getMappe_Cpu().getNumeroCelleMappa()},
				{"Numero Celle affondate", String.valueOf(model.getGiocatore().mieCelleNaviAffondate) + " / "+ model.getCpu().getNumeroCelleNaviPlayer(), String.valueOf(model.getCpu().mieCelleNaviAffondate) + " / "+ model.getCpu().getNumeroCelleNaviPlayer()},
			},
			new String[] {
				"Descrizione", "Info Giocatore", "Info Cpu"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class
			};
			boolean[] columnEditables = new boolean[] {
					false, false, false
				};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER); 
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		table.setRowHeight(25);
		table.setFont(view.FONT_SEGOE_H3_P);
		table.getColumnModel().getColumn(0).setPreferredWidth(186);
		table.getColumnModel().getColumn(0).setMinWidth(57);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(1).setMinWidth(6);
		table.getColumnModel().getColumn(2).setPreferredWidth(155);
		scrollPane.setViewportView(table);
		
		JButton btnSalvaRisultati = new JButton("Termina partita");
		btnSalvaRisultati.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btnSalvaRisultati.setBounds(97, 807, 189, 43);
		btnSalvaRisultati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chiudiJFrame();
			}
		});
		this.getContentPane().add(btnSalvaRisultati);
		
		JCheckBox chckbxSalva = new JCheckBox("Salva i risultati della partita nello storico partite");
		chckbxSalva.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		chckbxSalva.setBounds(117, 756, 399, 23);
		this.getContentPane().add(chckbxSalva);
		
		JButton btnChiudiGioco = new JButton("Termina e chiudi");
		btnChiudiGioco.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btnChiudiGioco.setBounds(301, 807, 189, 43);
		btnChiudiGioco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame s = new JFrame();
				int a=JOptionPane.showConfirmDialog(s,"Sei sicuro di uscire dal gioco?");  
				if(a==JOptionPane.YES_OPTION){  
				    s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
				    System.exit(0);
				} 	
			}
		});
		this.getContentPane().add(btnChiudiGioco);
		
		JLabel lblNewLabel_1_1_1_2_1_1_1_1 = new JLabel("Durata partita :");
		lblNewLabel_1_1_1_2_1_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel_1_1_1_2_1_1_1_1.setBounds(184, 654, 118, 25);
		this.getContentPane().add(lblNewLabel_1_1_1_2_1_1_1_1);
		
		JLabel lblNewLabel_1_1_2_2_1_1_1 = new JLabel(model.getActualTimer());
		lblNewLabel_1_1_2_2_1_1_1.setForeground(new Color(65, 15, 225));
		lblNewLabel_1_1_2_2_1_1_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_1_1_2_2_1_1_1.setBounds(313, 655, 250, 25);
		this.getContentPane().add(lblNewLabel_1_1_2_2_1_1_1);
		
		this.setVisible(true);
	}
	
	private void chiudiJFrame() {
		this.setVisible(false);
	}

}
