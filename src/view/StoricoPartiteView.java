package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.BattleshipExtremeModel;
import model.Partita;

public class StoricoPartiteView extends JFrame {
	
	private ArrayList<Partita> elencoPartite;

	@SuppressWarnings("serial")
	public StoricoPartiteView(BattleshipExtremeModel model, BattleshipExtremeView view) {
		
		// Leggi le partita dalla cartella
		File folder = new File(model.directoryPartite);
		File[] listOfFiles = folder.listFiles();
		
		if (listOfFiles.length != 0) {
			for (int i = 0; i < listOfFiles.length; i++) {
				
				ObjectInputStream in;
				try {
					in = new ObjectInputStream(new FileInputStream(listOfFiles[i]));
					try {
						elencoPartite.add((Partita) in.readObject());
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println("PARTITA_NOME_GIOCATORE: " + elencoPartite.get(i).getNomeGiocatore());
					System.out.println("PARTITA_PUNTEGGIO_GIOCATORE: " + elencoPartite.get(i).getPunteggioGiocatore());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		}
		

		for (File file : listOfFiles) {
		    if (file.isFile()) {
		        System.out.println(file.getName());
		    }
		}

		// Inizializzazione del JFrame
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(BattleshipExtremeView.class.getResource("/icons/gaming.png")));
        this.setTitle("Storico partite");
        this.setBounds(500, 300, 782, 569);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        
        // Inizializzazione del contentPane Principale
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(view.COLORE_BIANCO);
        
		
		JLabel lblStoricoPartiteSalvate = new JLabel("STORICO PARTITE SALVATE");
		lblStoricoPartiteSalvate.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblStoricoPartiteSalvate.setBounds(260, 11, 245, 43);
		this.getContentPane().add(lblStoricoPartiteSalvate);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(13, 53, 739, 2);
		this.getContentPane().add(separator);
		
		JButton btnChiudi = new JButton("Chiudi");
		btnChiudi.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btnChiudi.setBounds(322, 476, 122, 43);
		btnChiudi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				chiudiJFrame();
			}
		});
		this.getContentPane().add(btnChiudi);
		
//		JScrollPane scrollPane = new JScrollPane();
//		scrollPane.setBounds(10, 82, 743, 283);
//		this.getContentPane().add(scrollPane);
//		
//		JTable table = new JTable();
//		table.setModel(new DefaultTableModel(
//			new Object[][] {
//			},
//			new String[] {
//				"N\u00B0", "Nome giocatore", "Punteggio giocatore", "Punteggio cpu", "Durata partita", "Dim. Mappa", "N\u00B0 navi"
//			}
//		) {
//			Class[] columnTypes = new Class[] {
//				Integer.class, String.class, Integer.class, Integer.class, String.class, Integer.class, Integer.class
//			};
//			public Class getColumnClass(int columnIndex) {
//				return columnTypes[columnIndex];
//			}
//			boolean[] columnEditables = new boolean[] {
//				false, false, false, false, false, false, false
//			};
//			public boolean isCellEditable(int row, int column) {
//				return columnEditables[column];
//			}
//		});
//		table.getColumnModel().getColumn(0).setPreferredWidth(41);
//		table.getColumnModel().getColumn(1).setPreferredWidth(104);
//		table.getColumnModel().getColumn(1).setMinWidth(39);
//		table.getColumnModel().getColumn(2).setPreferredWidth(109);
//		table.getColumnModel().getColumn(2).setMinWidth(50);
//		table.getColumnModel().getColumn(3).setPreferredWidth(87);
//		table.getColumnModel().getColumn(4).setPreferredWidth(112);
//		table.getColumnModel().getColumn(6).setPreferredWidth(54);
//		scrollPane.setViewportView(table);
//		
//		JLabel lblNewLabel_1_4 = new JLabel("Numero partite salvate:");
//		lblNewLabel_1_4.setFont(new Font("Segoe UI", Font.PLAIN, 18));
//		lblNewLabel_1_4.setBounds(13, 396, 196, 34);
//		frame.getContentPane().add(lblNewLabel_1_4);
//		
//		JLabel lblNewLabel_1_4_1 = new JLabel("Tempo totale di gioco:");
//		lblNewLabel_1_4_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
//		lblNewLabel_1_4_1.setBounds(347, 396, 196, 34);
//		frame.getContentPane().add(lblNewLabel_1_4_1);
//		
//		JLabel lblNewLabel_1_1_2_2_1_1_1 = new JLabel("0");
//		lblNewLabel_1_1_2_2_1_1_1.setForeground(new Color(65, 105, 225));
//		lblNewLabel_1_1_2_2_1_1_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
//		lblNewLabel_1_1_2_2_1_1_1.setBounds(207, 402, 63, 25);
//		frame.getContentPane().add(lblNewLabel_1_1_2_2_1_1_1);
//		
//		JLabel lblNewLabel_1_1_2_2_1_1_1_1 = new JLabel("0 ore 00 minuti 00 secondi");
//		lblNewLabel_1_1_2_2_1_1_1_1.setForeground(new Color(65, 105, 225));
//		lblNewLabel_1_1_2_2_1_1_1_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
//		lblNewLabel_1_1_2_2_1_1_1_1.setBounds(540, 402, 212, 25);
//		frame.getContentPane().add(lblNewLabel_1_1_2_2_1_1_1_1);
		
		this.setVisible(true);
	}
	
	
	private void chiudiJFrame() {
		this.setVisible(false);
		
	}

}
