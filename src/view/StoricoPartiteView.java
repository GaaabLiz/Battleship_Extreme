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
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.BattleshipExtremeModel;
import model.Partita;

public class StoricoPartiteView extends JFrame {
	
	private ArrayList<Partita> elencoPartite;
	private JTable table;
	private Boolean viaLiberaPerInserimento = false;

	@SuppressWarnings("serial")
	public StoricoPartiteView(BattleshipExtremeModel model, BattleshipExtremeView view) {
		
		
		// db
		Boolean stato_connessione = false;
		//DatabaseController d = new DatabaseController();
		
		/*try {
			stato_connessione = d.getConnessione().isClosed();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		if (!stato_connessione) {
			model.aggiungiLog("INFO", "DATABASE", "La connessione al database è aperta.");
			viaLiberaPerInserimento = true;
		}else {
			model.aggiungiLog("ERRORE", "DATABASE", "La connessione al database è chiusa.");
			viaLiberaPerInserimento = false;
			JFrame f=new JFrame();  
		    JOptionPane.showMessageDialog(f,"Spiacente! La connessione al database non è riuscita. Non sara' possibile vedere lo storico partite. Contattare lo sviluppatore.","Errore di connessione",JOptionPane.ERROR_MESSAGE);     
		}
		
		
		
		
		// Leggi le partita dalla cartella
//		File folder = new File(model.directoryPartite);
//		File[] listOfFiles = folder.listFiles();
//		
//		if (listOfFiles.length != 0) {
//			for (int i = 0; i < listOfFiles.length; i++) {
//				
//				ObjectInputStream in;
//				try {
//					in = new ObjectInputStream(new FileInputStream(listOfFiles[i]));
//					try {
//						elencoPartite.add((Partita) in.readObject());
//					} catch (ClassNotFoundException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//					System.out.println("PARTITA_NOME_GIOCATORE: " + elencoPartite.get(i).getNomeGiocatore());
//					System.out.println("PARTITA_PUNTEGGIO_GIOCATORE: " + elencoPartite.get(i).getPunteggioGiocatore());
//				} catch (FileNotFoundException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				
//				
//			}
//		}
//		
//
//		for (File file : listOfFiles) {
//		    if (file.isFile()) {
//		        System.out.println(file.getName());
//		    }
//		}
		
		
		
		
		

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
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 82, 743, 283);
		this.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"N\u00B0", "Nome giocatore", "Data Partita", "Punteggio giocatore", "Punteggio cpu", "Durata partita", "Dim. Mappa", "N\u00B0 navi"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, Integer.class, Integer.class, String.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(41);
		table.getColumnModel().getColumn(1).setPreferredWidth(104);
		table.getColumnModel().getColumn(1).setMinWidth(39);
		table.getColumnModel().getColumn(3).setPreferredWidth(109);
		table.getColumnModel().getColumn(3).setMinWidth(50);
		table.getColumnModel().getColumn(4).setPreferredWidth(87);
		table.getColumnModel().getColumn(5).setPreferredWidth(112);
		table.getColumnModel().getColumn(7).setPreferredWidth(54);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER); 
        table.setRowHeight(25);
		table.setFont(view.FONT_SEGOE_H3_P);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);	
        table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);	
        table.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);	
        table.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);	
		scrollPane.setViewportView(table);
		
		if (viaLiberaPerInserimento) {
			/*
			try {
				elencoPartite = d.getElencoPartiteFromDb(model);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
			model.aggiungiLog("INFO", "DATABASE", "Sono stati trovati nel database " + elencoPartite.size() + " partite.");
			
			DefaultTableModel m = (DefaultTableModel) table.getModel();
			for (int i = 0; i < elencoPartite.size(); i++) {			
				int temp_id = elencoPartite.get(i).getId();
				String TempNomeGiocatore = elencoPartite.get(i).getNomeGiocatore();
				String tempDataPartita = elencoPartite.get(i).getDataPartita();
				int tempPunteggioGiocatore = elencoPartite.get(i).getPunteggioGiocatore();
				int tempPunteggioCpu = elencoPartite.get(i).getPunteggioCpu();
				String tempDurataPartita = elencoPartite.get(i).getDurataPartita();
				int tempDimMappa = elencoPartite.get(i).getDimMappa();
				int tempNumNavi = elencoPartite.get(i).getNumNavi();
				model.aggiungiLog("DEBUG", "DATABASE", "Il valore 'ID' della partita #" + i + " e': " + temp_id + ".");
				model.aggiungiLog("DEBUG", "DATABASE", "Il valore 'NOMEGIOCATORE' della partita #" + i + " e': " + TempNomeGiocatore + ".");
				model.aggiungiLog("DEBUG", "DATABASE", "Il valore 'PunteggioGiocatore' della partita #" + i + " e': " + tempPunteggioGiocatore + ".");
				model.aggiungiLog("DEBUG", "DATABASE", "Il valore 'PunteggioCpu' della partita #" + i + " e': " + tempPunteggioCpu + ".");
				model.aggiungiLog("DEBUG", "DATABASE", "Il valore 'DurataPartita' della partita #" + i + " e': " + tempDurataPartita + ".");
				model.aggiungiLog("DEBUG", "DATABASE", "Il valore 'DimMappa' della partita #" + i + " e': " + tempDimMappa + ".");
				model.aggiungiLog("DEBUG", "DATABASE", "Il valore 'NumNavi' della partita #" + i + " e': " + tempNumNavi + ".");
				Object datiDaInserire[] = {temp_id, TempNomeGiocatore, tempDataPartita, tempPunteggioGiocatore, tempPunteggioCpu, tempDurataPartita, tempDimMappa, tempNumNavi};
				m.addRow(datiDaInserire);
			}
		}
		
		
		
		JLabel lblNewLabel_1_4 = new JLabel("Numero partite totali:");
		lblNewLabel_1_4.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel_1_4.setBounds(13, 396, 196, 34);
		lblNewLabel_1_4.setVisible(false);
		this.getContentPane().add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_4_1 = new JLabel("Tempo totale di gioco:");
		lblNewLabel_1_4_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel_1_4_1.setBounds(347, 396, 196, 34);
		lblNewLabel_1_4_1.setVisible(false);
		this.getContentPane().add(lblNewLabel_1_4_1);
		
		JLabel lblNewLabel_1_1_2_2_1_1_1 = new JLabel("0");
		lblNewLabel_1_1_2_2_1_1_1.setForeground(new Color(65, 105, 225));
		lblNewLabel_1_1_2_2_1_1_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_1_1_2_2_1_1_1.setBounds(196, 402, 63, 25);
		lblNewLabel_1_1_2_2_1_1_1.setVisible(false);
		this.getContentPane().add(lblNewLabel_1_1_2_2_1_1_1);
		
		JLabel lblNewLabel_1_1_2_2_1_1_1_1 = new JLabel("0 ore 00 minuti 00 secondi");
		lblNewLabel_1_1_2_2_1_1_1_1.setForeground(new Color(65, 105, 225));
		lblNewLabel_1_1_2_2_1_1_1_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_1_1_2_2_1_1_1_1.setBounds(540, 402, 212, 25);
		lblNewLabel_1_1_2_2_1_1_1_1.setVisible(false);
		this.getContentPane().add(lblNewLabel_1_1_2_2_1_1_1_1);
		
		
		
		
		
		
		this.setVisible(true);
	}
	
	
	private void chiudiJFrame() {
		this.setVisible(false);
		
	}

}
