package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import model.BattleshipExtremeModel;

public class ImpostazioniPartitaView extends JFrame {
	
	private BattleshipExtremeModel model;
	private BattleshipExtremeView view;
	JPanel panello_creaPosNavi;
	JLabel label_naviInserite;

	@SuppressWarnings("serial")
	public ImpostazioniPartitaView(BattleshipExtremeModel model, BattleshipExtremeView view) {
		super();
		this.model = model;
		this.view = view;
		int numNaviCreate = 0;
		
		
		// Inizializzazione del JFrame
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(BattleshipExtremeView.class.getResource("/icons/settings.png")));
        this.setTitle("Impostazioni della partita");
        this.setBounds(700, 100, 650, 800);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        
        // Inizializzazione del contentPane Principale
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(view.COLORE_BIANCO);
        
        // Settaggio Label
        JLabel label_titolo = new JLabel("IMPOSTAZIONI DELLA PARTITA");
        label_titolo.setFont(view.FONT_SEGOE_H1_BI);
        label_titolo.setBounds(174, 11, 286, 43);
		this.getContentPane().add(label_titolo);
		
		// Separatore 
		JSeparator separator = new JSeparator();
		separator.setBounds(39, 65, 555, 2);
		this.getContentPane().add(separator);
		
		
		
		// Pannello dimensione mappa
		JPanel panello_mappa = new JPanel();
		panello_mappa.setBorder(new LineBorder(Color.GRAY));
		panello_mappa.setBounds(39, 101, 555, 55);
		this.getContentPane().add(panello_mappa);
		panello_mappa.setLayout(null);
		
		// Label dimensione mappa
		int minM = model.MIN_DIM_MAPPA;
		int maxM = model.MAX_DIM_MAPPA;
		JLabel label_mappa = new JLabel("Dimensione mappa di Gioco (min " + minM + ", max " + maxM + ") : ");
		label_mappa.setBounds(45, 15, 397, 25);
		label_mappa.setFont(view.FONT_SEGOE_H1_P);
		panello_mappa.add(label_mappa);
		
		// Spinner mappa
		JSpinner spinner_mappa = new JSpinner();
		spinner_mappa.setBounds(443, 11, 50, 32);
		spinner_mappa.setFont(view.FONT_SEGOE_H1_P);
		spinner_mappa.setValue(minM);
		panello_mappa.add(spinner_mappa);

		// Pannello navi
		JPanel panello_navi = new JPanel();
		panello_navi.setLayout(null);
		panello_navi.setBorder(view.BORDER_GRIGIO);
		panello_navi.setBounds(39, 167, 555, 55);
		this.getContentPane().add(panello_navi);
		
		// Label navi
		int minN = model.MIN_NUM_NAVI;
		int maxN = model.MAX_NUM_NAVI;
		JLabel label_navi = new JLabel("Numero navi con cui giocare (min " + minN + ", max " + maxN + ") : ");
		label_navi.setFont(view.FONT_SEGOE_H1_P);
		label_navi.setBounds(45, 15, 397, 25);
		panello_navi.add(label_navi);
		
		// Spinner navi
		JSpinner spinner_navi = new JSpinner();
		spinner_navi.setFont(view.FONT_SEGOE_H1_P);
		spinner_navi.setBounds(443, 11, 50, 32);
		spinner_navi.setValue(minN);
		panello_navi.add(spinner_navi);
		
		
		// Pulsante conferma navi e mappa
		JButton btn_confermaNavi = new JButton("Conferma mappa e navi");
		btn_confermaNavi.setFont(view.FONT_SEGOE_H1_P);
		btn_confermaNavi.setBounds(190, 247, 238, 45);
		btn_confermaNavi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame f = new JFrame();
				int naviInserite = (int) spinner_navi.getValue();
				int mappaInserita = (int) spinner_mappa.getValue();
				int a=JOptionPane.showConfirmDialog(f,"Confermi di voler giocare con " + naviInserite + " navi in una mappa di " + mappaInserita + "X" + mappaInserita + " caselle?");  
				if(a==JOptionPane.YES_OPTION){  
//				    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
					model.setNumeroNavi(naviInserite);
					model.aggiungiLog("INFO", "Impostazioni partita", "Il numero della navi della partita corrente è settata a: " + model.getNumeroNavi());
					model.setDimensioneMappa(mappaInserita);
					model.aggiungiLog("INFO", "Impostazioni partita", "La dimensione della mappa nella partita attuale e' stata settata a : " + model.getDimensioneMappa());
					spinner_mappa.setEnabled(false);
					spinner_navi.setEnabled(false);
					btn_confermaNavi.setEnabled(false);
					btn_confermaNavi.setVisible(false);
					visualizzaPannellonavi();
					label_naviInserite.setText("Hai inserito " + numNaviCreate + " / " + model.getNumeroNavi() + " navi.");
				}  
				
			}
		});
		this.getContentPane().add(btn_confermaNavi);
		
		
		// PPannello che contiene tutto la creazione pos navi
		panello_creaPosNavi = new JPanel();
		panello_creaPosNavi.setBorder(view.BORDER_GRIGIO);
		panello_creaPosNavi.setBounds(39, 294, 555, 390);
		panello_creaPosNavi.setVisible(false);
		this.getContentPane().add(panello_creaPosNavi);
		panello_creaPosNavi.setLayout(null);
		
		//Label info
		JLabel label_info = new JLabel("Ora devi creare e inserire le navi nella mappa.");
		label_info.setFont(view.FONT_SEGOE_H1_P);
		label_info.setBounds(95, 11, 364, 25);
		panello_creaPosNavi.add(label_info);
		
		// Pulsante crea navi manualmente
		JButton btn_manualCreaNavi = new JButton("Crea e posiziona nave manualmente");
		btn_manualCreaNavi.setFont(view.FONT_SEGOE_H1_P);
		btn_manualCreaNavi.setBounds(111, 94, 333, 33);
		panello_creaPosNavi.add(btn_manualCreaNavi);
		
		// Label numero navi isnerite
		label_naviInserite = new JLabel("Hai inserito " + numNaviCreate + " / " + model.getNumeroNavi() + " navi.");
		label_naviInserite.setForeground(view.COLORE_AZZURRINO);
		label_naviInserite.setFont(view.FONT_SEGOE_H1_P);
		label_naviInserite.setBounds(182, 58, 191, 25);
		panello_creaPosNavi.add(label_naviInserite);
		
		// Pulsante che crea navi random
		JButton btnCreaEPosiziona_2 = new JButton("Crea e posiziona nave (Random)");
		btnCreaEPosiziona_2.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btnCreaEPosiziona_2.setBounds(111, 139, 333, 33);
		panello_creaPosNavi.add(btnCreaEPosiziona_2);
		
		// Tabella navi inserite
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 204, 535, 175);
		panello_creaPosNavi.add(scrollPane);
		
		JTable table;
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Tipo", "Dim", "Coordinate"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
		
		
		
		// Pulsanti finali
		JButton btnIniziaPartita = new JButton("Inizia Partita");
		btnIniziaPartita.setFont(view.FONT_SEGOE_H1_P);
		btnIniziaPartita.setBounds(115, 705, 189, 45);
		btnIniziaPartita.setEnabled(true);
		this.getContentPane().add(btnIniziaPartita);
		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.setFont(view.FONT_SEGOE_H1_P);
		btnAnnulla.setBounds(314, 705, 189, 45);
		btnAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chiudiJFrame();
			}
		});
		this.getContentPane().add(btnAnnulla);
	
		
		
		
		this.setVisible(true);
	}
	
	
	private void visualizzaPannellonavi() {
		panello_creaPosNavi.setVisible(true);
	}
	
	

	
	private void chiudiJFrame() {
		this.setVisible(false);
	}
	

}
