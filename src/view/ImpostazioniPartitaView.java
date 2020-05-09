package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
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
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import model.BattleshipExtremeModel;
import model.Nave;
import model.PuntoCardinale;

public class ImpostazioniPartitaView extends JFrame {
	
	private BattleshipExtremeModel model;
	private BattleshipExtremeView view;
	private Boolean confermMappaNavi = false;
	private PannelloGriglia griglia;
	private JPanel panelloGrigliaCOntainer;
	JPanel panello_creaPosNavi;
	JLabel label_naviInserite;
	JTable table;
	int numNaviCreate = 0;
	Nave nTemp = new Nave(-1, 0, PuntoCardinale.EST, new Point(-1, -1));
	int iDcorrente = 1;

	@SuppressWarnings("serial")
	public ImpostazioniPartitaView(BattleshipExtremeModel model, BattleshipExtremeView view) {
		super();
		this.model = model;
		this.view = view;
		
		
		
		// Inizializzazione del JFrame
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(BattleshipExtremeView.class.getResource("/icons/settings.png")));
        this.setTitle("Impostazioni della partita");
        this.setBounds(700, 100, 650, 900);
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
		
		
		
		// Pannello nome giocatore
		JPanel panelloNomeGIocatore = new JPanel();
		panelloNomeGIocatore.setLayout(null);
		panelloNomeGIocatore.setBorder(new LineBorder(Color.GRAY));
		panelloNomeGIocatore.setBounds(39, 83, 555, 55);
		this.getContentPane().add(panelloNomeGIocatore);
		
		JLabel labelNomeGiocatore = new JLabel("Nome del giocatore (facoltativo): ");
		labelNomeGiocatore.setFont(view.FONT_SEGOE_H1_P);
		labelNomeGiocatore.setBounds(10, 15, 278, 25);
		panelloNomeGIocatore.add(labelNomeGiocatore);
		
		JTextField textField_nomeGIocatore = new JTextField();
		textField_nomeGIocatore.setFont(view.FONT_SEGOE_H1_P);
		textField_nomeGIocatore.setBounds(285, 14, 239, 27);
		panelloNomeGIocatore.add(textField_nomeGIocatore);
		textField_nomeGIocatore.setColumns(10);
		
		
		
		// Pannello dimensione mappa
		JPanel panello_mappa = new JPanel();
		panello_mappa.setBorder(new LineBorder(Color.GRAY));
		panello_mappa.setBounds(39, 149, 555, 55);
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
		panello_navi.setBounds(39, 215, 555, 55);
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
		JButton btn_confermaNavi = new JButton("Conferma e inizia posizionamento navi");
		btn_confermaNavi.setFont(view.FONT_SEGOE_H1_P);
		btn_confermaNavi.setBounds(147, 293, 345, 43);
		btn_confermaNavi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int naviInserite = (int) spinner_navi.getValue();
				int mappaInserita = (int) spinner_mappa.getValue();
				Boolean condinzione1 =  ((naviInserite <= model.MAX_NUM_NAVI) && (naviInserite >= model.MIN_NUM_NAVI));
				Boolean condinzione2 =  ((mappaInserita <= model.MAX_DIM_MAPPA) && (mappaInserita >= model.MIN_DIM_MAPPA));
				if (condinzione1 && condinzione2) {
					JFrame f = new JFrame();			
					int a=JOptionPane.showConfirmDialog(f,"Confermi di voler giocare con " + naviInserite + " navi in una mappa di " + mappaInserita + "X" + mappaInserita + " caselle?");  
					if(a==JOptionPane.YES_OPTION){  
//					    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
						model.setNumeroNavi(naviInserite);
						model.aggiungiLog("INFO", "Impostazioni partita", "Il numero della navi della partita corrente è settata a: " + model.getNumeroNavi());
						model.setDimensioneMappa(mappaInserita);
						model.aggiungiLog("INFO", "Impostazioni partita", "La dimensione della mappa nella partita attuale e' stata settata a : " + model.getDimensioneMappa());
						model.setMappe_Giocatore(model.getDimensioneMappa());
						model.setMappe_Cpu(model.getDimensioneMappa());
						model.setModelloNavi();
						model.setGiocatore();	
						model.setCpu();
						model.getMappe_Giocatore().setSpazioNavi();
						model.getMappe_Cpu().setSpazioNavi();
						if (textField_nomeGIocatore.getText().length() != 0) {
							model.setNomeGiocatore(textField_nomeGIocatore.getText());
						}					
						spinner_mappa.setEnabled(false);
						spinner_navi.setEnabled(false);
						btn_confermaNavi.setEnabled(false);
						btn_confermaNavi.setVisible(false);
						textField_nomeGIocatore.setEnabled(false);
						visualizzaPannellonavi();
						label_naviInserite.setText("Hai inserito " + numNaviCreate + " / " + model.getNumeroNavi() + " navi.");
						confermMappaNavi = true;
						
						// Creazione della griglia nel tab 3
						griglia = new PannelloGriglia(model.getDimensioneMappa());
						griglia.setBackground(Color.WHITE);
						panelloGrigliaCOntainer.add(griglia, BorderLayout.CENTER);
					}
				}else {
					JFrame f = new JFrame();
					JOptionPane.showMessageDialog(f,"Ce almeno un valore inserito che non rientra nel range di valori ammissibili. Riprovare.","Errore",JOptionPane.ERROR_MESSAGE);  
				}
				  
				
			}
		});
		this.getContentPane().add(btn_confermaNavi);
		
		
		// PPannello che contiene tutto la creazione pos navi
		panello_creaPosNavi = new JPanel();
		panello_creaPosNavi.setBounds(39, 381, 555, 390);
		panello_creaPosNavi.setBackground(view.COLORE_BIANCO);
		panello_creaPosNavi.setVisible(false);
		this.getContentPane().add(panello_creaPosNavi);
		panello_creaPosNavi.setLayout(null);
		
		// PANE: MAIN
		JTabbedPane tabbedPane_main = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_main.setFont(view.FONT_SEGOE_H2_P);
		tabbedPane_main.setBounds(10, 11, 535, 368);
		panello_creaPosNavi.add(tabbedPane_main);
		
		
		// PANE 1 ------------------------------------------------
		
		// PANE 1: Crea posiziona navi
		JPanel panello_CreaPos = new JPanel();
		tabbedPane_main.addTab("Crea e posiziona navi", null, panello_CreaPos, null);
		panello_CreaPos.setBackground(view.COLORE_BIANCO);
		panello_CreaPos.setLayout(null);
		
		// label info
		JLabel labelInfoCreaPos = new JLabel("Hai a disposizione 2 modalità per creare navi.");
		labelInfoCreaPos.setFont(view.FONT_SEGOE_H1_P);
		labelInfoCreaPos.setBounds(80, 12, 364, 25);
		panello_CreaPos.add(labelInfoCreaPos);
		
		// Tabbed Pane per le due tipologie di inserimento navi
		JTabbedPane tabbedPane_posNavi = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_posNavi.setFont(view.FONT_SEGOE_H3_P);
		tabbedPane_posNavi.setBounds(10, 54, 510, 267);
		panello_CreaPos.add(tabbedPane_posNavi);
		
		// Pannello inserimento navi manuali
		JPanel panello_PosNaviManuale = new JPanel();
		tabbedPane_posNavi.addTab("Crea nave manualmente", null, panello_PosNaviManuale, null);
		panello_PosNaviManuale.setLayout(null);
		
		// PANE 1.1 : labelInfo
		JLabel labelInfoManual = new JLabel("Inserisci le seguenti informazioni per creare la nave.");
		labelInfoManual.setFont(view.FONT_SEGOE_H3_P);
		labelInfoManual.setBounds(10, 11, 485, 25);
		panello_PosNaviManuale.add(labelInfoManual);
		
		// PANE 1.1 : label x prua
		JLabel labelXPrua = new JLabel("X Prua : ");
		labelXPrua.setFont(view.FONT_SEGOE_H2_P);
		labelXPrua.setBounds(10, 69, 56, 25);
		panello_PosNaviManuale.add(labelXPrua);
		
		// PANE 1.1 : spinner x prua
		JSpinner spinner_XPRua = new JSpinner();
		spinner_XPRua.setFont(view.FONT_SEGOE_H2_P);
		spinner_XPRua.setBounds(76, 65, 50, 32);
		panello_PosNaviManuale.add(spinner_XPRua);
		
		// PANE 1.1 : label y prua
		JLabel labelYPrua = new JLabel("Y Prua : ");
		labelYPrua.setFont(view.FONT_SEGOE_H2_P);
		labelYPrua.setBounds(10, 105, 56, 25);
		panello_PosNaviManuale.add(labelYPrua);
		
		// PANE 1.1 : spinner y prua
		JSpinner spinner_YPRua = new JSpinner();
		spinner_YPRua.setFont(view.FONT_SEGOE_H2_P);
		spinner_YPRua.setBounds(76, 108, 50, 32);
		panello_PosNaviManuale.add(spinner_YPRua);
		
		// PANE 1.1 : label tipo
		JLabel labelTiponave = new JLabel("Tipologia Nave : ");
		labelTiponave.setFont(view.FONT_SEGOE_H2_P);
		labelTiponave.setBounds(161, 66, 123, 25);
		panello_PosNaviManuale.add(labelTiponave);
		
		// PANE 1.1 : combobox tipoNave
		JComboBox comboBox_TipoNave = new JComboBox();
		comboBox_TipoNave.setFont(view.FONT_SEGOE_H2_P);
		comboBox_TipoNave.setBounds(281, 62, 214, 32);
		comboBox_TipoNave.setModel(new DefaultComboBoxModel(new String[] 
				{ 
				"Cacciatorpedinieri (2 Celle)", 
				"Sottomarini (3 Celle)",
				"Incrociatori (4 Celle)",
				"Portaerei (5 Celle)",
				"Corazzate (6 Celle)"}));
		panello_PosNaviManuale.add(comboBox_TipoNave);
		
		// PANE 1.1 : label orinetamento
		JLabel labelOrient = new JLabel("Orientamento : ");
		labelOrient.setFont(view.FONT_SEGOE_H2_P);
		labelOrient.setBounds(160, 108, 110, 25);
		panello_PosNaviManuale.add(labelOrient);
		
		// PANE 1.1 : combobox orinemtaneto
		JComboBox comboBox_Orinet = new JComboBox();
		comboBox_Orinet.setFont(view.FONT_SEGOE_H2_P);
		comboBox_Orinet.setModel(new DefaultComboBoxModel(new String[] {"Nord", "Sud", "Ovest", "Est"}));
		comboBox_Orinet.setBounds(280, 108, 110, 25);
		panello_PosNaviManuale.add(comboBox_Orinet);
		
		// PANE 1.1 : btn conferma
		JButton btn_tentaPiazzamento = new JButton("Tenta creazione nave");
		btn_tentaPiazzamento.setFont(view.FONT_SEGOE_H2_P);
		btn_tentaPiazzamento.setBounds(137, 184, 231, 38);
		btn_tentaPiazzamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// contrllo gia navi inserite tutte
				if(numNaviCreate == model.getNumeroNavi()) {
					JFrame f=new JFrame();  
				    JOptionPane.showMessageDialog(f,"Hai già inserito tutte le navi richieste! Clicca sul 'Inizia Partita' per incominciare a giocare.", "Avviso", JOptionPane.WARNING_MESSAGE);
				} else {
					// Prendo i valori inseriti dall'utente
					int coordX = (int) spinner_XPRua.getValue();
					int coordY = (int) spinner_YPRua.getValue();
					Point p = new Point(coordX, coordY);
					PuntoCardinale orinetamento = null;
					String o = (String) comboBox_Orinet.getSelectedItem();
					int dim = comboBox_TipoNave.getSelectedIndex() + 2;
					
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
						
						// Stampa messaggio nave giusta
						JFrame f=new JFrame();  
					    JOptionPane.showMessageDialog(f,"Le impostazioni inserite sono correte e la nave e' stata creata.");
						
					    /* Creazione dell'oggetto nave ---------------------------*/
						Nave naveDefinitiva = new Nave(iDcorrente, dim, orinetamento, p);
						
						/* Aggiunta della nave al giocatore ----------------------*/
						model.getGiocatore().aggiungiNaveAlleMieNavi(naveDefinitiva);
						
						/* aggiungi il tipo di nave corrente al modelllo che dovrà usare la cpu per creare le sue navi */
						model.aggiungiModelloNave(iDcorrente-1, dim);
						
						/* Aggiunta nave nello spazio navi del player */
						model.getMappe_Giocatore().piazzaNaveNellaMappa(naveDefinitiva);
						
						/* aggiorna la view per visualizzare la mappa aggiornata con la nave appena aggiunta */
						updateGrigliaPan3(model.getMappe_Giocatore().getSpazioNavi(), model.getDimensioneMappa());
					    
						/* Stampa nella mappa gli ID della nave se opzioni di debug asserita */
//						if (model.MOSTRA_NUMERI_NAVE) {view.visualizzaIdNave(model.getMappe_Giocatore(), 0);}

						/* Aggiutna nave alle variabili */
						numNaviCreate++;
						label_naviInserite.setText("Hai inserito " + numNaviCreate + " / " + model.getNumeroNavi() + " navi.");
						
						/* Aggiunta della nave nella tabella */
						DefaultTableModel m = (DefaultTableModel) table.getModel();
						int temp_id = naveDefinitiva.getId();
						String tempTipo = naveDefinitiva.getTipologiaNave();
						int temp_dim = naveDefinitiva.getDimensioneNave();
						String tempcoordinate = "(" + naveDefinitiva.getCoordinatePrua().x + ", " + naveDefinitiva.getCoordinatePrua().y + ")";
						String temp_orin = naveDefinitiva.getOrientamento().toString();
						Object datiDaInserire[] = {temp_id, tempTipo, temp_dim, tempcoordinate, temp_orin};
						m.addRow(datiDaInserire);
						
						/* Aumento id */
						iDcorrente++;
						
					}else {
						JFrame f=new JFrame();  
					    JOptionPane.showMessageDialog(f,"La nave inserita esce dalla mappa oppure e' in conflitto con altre navi.","Errore Posizionamento navi",JOptionPane.ERROR_MESSAGE); 
					}
				}
				
				
			}
		});
		panello_PosNaviManuale.add(btn_tentaPiazzamento);
		
		// PANE 1.2 : Pannello navi random
		JPanel panello_PosNaviRandom = new JPanel();
		tabbedPane_posNavi.addTab("Genera nave random", null, panello_PosNaviRandom, null);
		panello_PosNaviRandom.setLayout(null);
		
		// PANE 1.2 : labelInfo
		JLabel labelInfoRand = new JLabel("Con queste opzione i dati della nave saranno creati casualmente dal gioco.");
		labelInfoRand.setFont(view.FONT_SEGOE_H3_P);
		labelInfoRand.setBounds(10, 11, 460, 25);
		panello_PosNaviRandom.add(labelInfoRand);
		
		// PANE 1.2 : btn conferma
		JButton btnGeneraNaveCasuale = new JButton("Genera nave casuale");
		btnGeneraNaveCasuale.setFont(view.FONT_SEGOE_H2_P);
		btnGeneraNaveCasuale.setBounds(137, 115, 231, 38);
		btnGeneraNaveCasuale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Boolean infoNaveOk = false;
				Point p;
				PuntoCardinale orinetamento;
				int dim;
				
				// contrllo gia navi inserite tutte
				if(numNaviCreate == model.getNumeroNavi()) {
					JFrame f=new JFrame();  
				    JOptionPane.showMessageDialog(f,"Hai già inserito tutte le navi richieste! Clicca sul 'Inizia Partita' per incominciare a giocare.", "Avviso", JOptionPane.WARNING_MESSAGE);
				} else {
					do {
						
						
						
						// Genero a caso dei valori della nave
						p = model.getCpu().generaCoordinateCasuali(model.getDimensioneMappa());
						orinetamento = model.getCpu().generaOrientamentoCasuale();
						dim = model.getCpu().generaDimNaveCasuale();
						
						// Stampo i debug nella console
						model.aggiungiLog("INFO", "Posizionamento Navi Random", "INPUT NAVE (X): " + p.x);
						model.aggiungiLog("INFO", "Posizionamento Navi Random", "INPUT NAVE (Y): " + p.y);
						model.aggiungiLog("INFO", "Posizionamento Navi Random", "INPUT NAVE (DIM): " + dim);
						model.aggiungiLog("INFO", "Posizionamento Navi Random", "INPUT NAVE (ORIENT): " + orinetamento);				
						
						// COntrolli sui dati della nave
						Boolean controlloFuoriMappa = model.controlloFuoriuscitaNave(dim, p, orinetamento);
						model.aggiungiLog("INFO", "Posizionamento Navi Random", "Controllo fuori mappa: " + controlloFuoriMappa);
						Boolean controlloNaviVicine = model.controllaNaviVicine(dim, p, orinetamento, model.getMappe_Giocatore());
						model.aggiungiLog("INFO", "Posizionamento Navi Random", "Controllo navi vicine: " + controlloNaviVicine);
						
						
						if (controlloFuoriMappa && controlloNaviVicine) { 
							infoNaveOk = true;
						}
						
					} while (infoNaveOk == false);
					
									
					// Stampa messaggio nave giusta
					JFrame f=new JFrame();  
				    JOptionPane.showMessageDialog(f,"È stata creata una nave casuale.");
					
				    /* Creazione dell'oggetto nave ---------------------------*/
					Nave naveDefinitiva = new Nave(iDcorrente, dim, orinetamento, p);
					
					/* Aggiunta della nave al giocatore ----------------------*/
					model.getGiocatore().aggiungiNaveAlleMieNavi(naveDefinitiva);
					
					/* aggiungi il tipo di nave corrente al modelllo che dovrà usare la cpu per creare le sue navi */
					model.aggiungiModelloNave(iDcorrente-1, dim);
					
					/* Aggiunta nave nello spazio navi del player */
					model.getMappe_Giocatore().piazzaNaveNellaMappa(naveDefinitiva);
					
					/* aggiorna la view per visualizzare la mappa aggiornata con la nave appena aggiunta */
					updateGrigliaPan3(model.getMappe_Giocatore().getSpazioNavi(), model.getDimensioneMappa());
				    
					/* Stampa nella mappa gli ID della nave se opzioni di debug asserita */
//						if (model.MOSTRA_NUMERI_NAVE) {view.visualizzaIdNave(model.getMappe_Giocatore(), 0);}

					/* Aggiutna nave alle variabili */
					numNaviCreate++;
					label_naviInserite.setText("Hai inserito " + numNaviCreate + " / " + model.getNumeroNavi() + " navi.");
					
					/* Aggiunta della nave nella tabella */
					DefaultTableModel m = (DefaultTableModel) table.getModel();
					int temp_id = naveDefinitiva.getId();
					String tempTipo = naveDefinitiva.getTipologiaNave();
					int temp_dim = naveDefinitiva.getDimensioneNave();
					String tempcoordinate = "(" + naveDefinitiva.getCoordinatePrua().x + ", " + naveDefinitiva.getCoordinatePrua().y + ")";
					String temp_orin = naveDefinitiva.getOrientamento().toString();
					Object datiDaInserire[] = {temp_id, tempTipo, temp_dim, tempcoordinate, temp_orin};
					m.addRow(datiDaInserire);
					
					/* Aumento id */
					iDcorrente++;
				}
				
				
				
			}
		});
		panello_PosNaviRandom.add(btnGeneraNaveCasuale);
		
		
		// PANE 2 ------------------------------------------------
		
		// PANE 2 : pannello vedi nave create
		JPanel panello_VediNaviCreate = new JPanel();
		tabbedPane_main.addTab("Vedi elenco navi", null, panello_VediNaviCreate, null);
		panello_VediNaviCreate.setBackground(view.COLORE_BIANCO);
		panello_VediNaviCreate.setLayout(null);
		
		// PANE 2 : label info navi
		label_naviInserite = new JLabel("Hai inserito 0 / 10 navi.");
		label_naviInserite.setForeground(Color.GRAY);
		label_naviInserite.setFont(view.FONT_SEGOE_H1_P);
		label_naviInserite.setBounds(169, 11, 191, 25);
		panello_VediNaviCreate.add(label_naviInserite);		
		
		// PANE 2 :tabella elenco
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 57, 510, 264);
		panello_VediNaviCreate.add(scrollPane);
		
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Tipo", "Dim", "Coordinate", "Orientamento"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Integer.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER); 
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		table.setRowHeight(25);
		table.setFont(view.FONT_SEGOE_H3_P);
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(0).setMinWidth(5);
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.getColumnModel().getColumn(1).setMinWidth(25);
		table.getColumnModel().getColumn(2).setPreferredWidth(20);
		table.getColumnModel().getColumn(2).setMinWidth(10);
		table.getColumnModel().getColumn(4).setPreferredWidth(78);
		table.getColumnModel().getColumn(4).setMinWidth(26);
		scrollPane.setViewportView(table);
		
		
		// PANE 3 ------------------------------------------------
		
		JPanel panello_VediNaviPos = new JPanel();
		tabbedPane_main.addTab("Vedi Navi posizionate", null, panello_VediNaviPos, null);
		panello_VediNaviPos.setBackground(view.COLORE_BIANCO);
		panello_VediNaviPos.setLayout(null);
		
		panelloGrigliaCOntainer = new JPanel();
		panelloGrigliaCOntainer.setBounds(110, 12, 309, 309);
		panello_VediNaviPos.add(panelloGrigliaCOntainer);
		panelloGrigliaCOntainer.setLayout(new BorderLayout());
		
//		griglia = new PannelloGriglia(10);
//		System.out.println("Il pannello griglia + impostato a 10");
//		griglia.setBackground(Color.WHITE);
//		panelloGrigliaCOntainer.add(griglia, BorderLayout.CENTER);
		
		
	
		
		
		// Pulsanti finali
		JButton btnIniziaPartita = new JButton("Inizia Partita");
		btnIniziaPartita.setFont(view.FONT_SEGOE_H1_P);
		btnIniziaPartita.setBounds(111, 805, 189, 45);
		btnIniziaPartita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (confermMappaNavi == false) {
					JFrame f = new JFrame();
					JOptionPane.showMessageDialog(f,"Prima di procedere confermare la dimensione della mappa e il numero navi (Pulsante qua sopra).","Errore",JOptionPane.ERROR_MESSAGE);
				}else {
					if (numNaviCreate == model.getNumeroNavi()) {
						JFrame f = new JFrame();
						JOptionPane.showMessageDialog(f,"Tutte le navi sono state inserite. Ora inizia il gioco.");
						chiudiJFrame();
						model.startaTimer();
						view.visualizzaCampoDiGioco();
						view.creaGriglie(model.getDimensioneMappa());
						view.updateNaviPlayer(0, model.getMappe_Giocatore().getSpazioNavi(), model.getDimensioneMappa(), model.MOSTRA_NAVI_CPU);
						view.getBtn_nuovaPartita().setEnabled(false);
						view.getBtn_nuovaPartita().setVisible(false);
						view.getMenu_Partita_TempoCorrente().setEnabled(true);
						view.getPanelloPunteggioTempo().setVisible(true);
						view.getPanello_InformazioniPartitaMaster().setVisible(true);
					}else {
						JFrame f = new JFrame();
						JOptionPane.showMessageDialog(f,"Non sono state create tutte le navi richieste. Hai inserito " + numNaviCreate + "/" + model.getNumeroNavi(),"Errore",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnIniziaPartita.setEnabled(true);
		this.getContentPane().add(btnIniziaPartita);
		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.setFont(view.FONT_SEGOE_H1_P);
		btnAnnulla.setBounds(310, 805, 189, 45);
		btnAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.getMenu_Modifica_settings().setEnabled(true);
				chiudiJFrame();
			}
		});
		this.getContentPane().add(btnAnnulla);
	
		
		
		
		this.setVisible(true);
	}
	
	
	
	
	private void updateGrigliaPan3 (Boolean[][] spazioNavi, int dimensioneMappa) {
		for (int i = 0; i < dimensioneMappa; i++) {
			for (int j = 0; j < dimensioneMappa; j++) {
				if (spazioNavi[i][j] == true) {
					griglia.visualizzaPezzoNave(i, j, 0);					
				}
			}
		}
	}
	
	
	
	private void visualizzaPannellonavi() {
		panello_creaPosNavi.setVisible(true);
	}
	
	

	
	private void chiudiJFrame() {
		this.setVisible(false);
	}
	

}
