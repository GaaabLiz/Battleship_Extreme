package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import controller.BattleshipExtremeController;
import model.MappePlayer;

import java.awt.*;

/**
 * Questa è la classe che controlla la "view" di tutto il programma. Contiene la GUI
 * principale del programma.
 * @see BattleshipExtremeModel
 * @see BattleshipExtremeController
 * @author Gabriele
 */
@SuppressWarnings("serial")
public class BattleshipExtremeView extends JFrame{
	

    //Testo che viene mostrato nella barra del titolo del programma
    private final String NOME_PROGRAMMA = "Battleship Extreme";

    //Dimensione del JFrame (Larghezza in pixel)
    protected final int LARGHEZZA_FINESTRA = 1600;

    //Dimensione del JFrame (Altezza in pixel)
    protected final int ALTEZZA_FINESTRA = 900;

    //Dichiarazione colori della GUI
    protected final Color COLORE_BIANCO = Color.WHITE;
    protected final Color COLORE_BIANCHINO = new Color(249, 249, 255);
    protected final Color COLORE_VIOLINO = new Color(249, 242, 255);
    protected final Color COLORE_AZZURRINO = new Color(112, 161, 254);

    //Dichiarazione font del programma
    protected final Font FONT_SEGOE_H0_BI = new Font("Segoe UI", Font.BOLD | Font.ITALIC, 25);
    protected final Font FONT_SEGOE_H1_P = new Font("Segoe UI", Font.PLAIN, 18);
    protected final Font FONT_SEGOE_H1_BI = new Font("Segoe UI", Font.BOLD | Font.ITALIC, 18);
    protected final Font FONT_SEGOE_H2_P = new Font("Segoe UI", Font.PLAIN, 16);
    protected final Font FONT_SEGOE_H3_P = new Font("Segoe UI", Font.PLAIN, 14);

    //Dichirazione dei bordi dei programmi
    protected final LineBorder BORDER_GRIGINO = new LineBorder(Color.LIGHT_GRAY);
    protected final LineBorder BORDER_GRIGIO = new LineBorder(Color.GRAY);
    
    //Dichiarazioni componenti swing da passare al controller
    private JMenuBar barraDelMenu;
    private JMenu menu_File;
    private JMenu menu_Modifica;
    private JMenu menu_Visualizza;
    private JMenu menu_Partita;
    private JMenu menu_Aiuto;
    private JMenu menu_Info;
    private JMenuItem menu_File_new;
    private JMenuItem menu_File_esci;
    private JMenuItem menu_Modifica_settings;
    private JMenuItem menu_Visualizza_console;
    private JMenuItem menu_Visualizza_storicoPartite;
    private JMenuItem menu_Partita_TempoCorrente;
    private JMenuItem menu_Partita_GiocaAutomaticamente;
    private JMenuItem menu_Aiuto_regole;
    private JMenuItem menu_Aiuto_tutorial;
    private JMenuItem menu_Info_progetto;
    private JMenuItem menu_Info_sito;
    private JLabel label_titolo;
    private JLabel labelValueTurno;
    private JLabel labelValuePunteggio;
    private JLabel labelValue_tentativiDiaffondamento;
    private JLabel labelValue_nCelleAffondate;
    private JLabel labelValue_statoNavi;
    private JLabel labelValue_TurniGiocati;
    private JLabel labelValueCpu_tentativiDiaffondamento;
    private JLabel labelValueCpu_nCelleAffondate;
    private JLabel labelValueCpu_statoNavi;
    private JLabel labelValueCpu_TurniGiocati;
    private JButton btn_nuovaPartita;
    private JButton btn_inziaPartita;
    private JButton btnColpisciCella;
    private JPanel panello_griglia_navi_master;
    private JPanel panello_griglia_tentativi_master;
    private JPanel panelloGriglianaviContainer;
    private JPanel panelloGrigliTentativiContainer;
    private JPanel panelloPunteggioTempo;
    private JPanel panello_InformazioniPartitaMaster;
    private JPanel panello_GestioneTurno;
    private JSpinner spinnerValueX;
    private JSpinner spinnerValueY;
    private PannelloGriglia griglia_navi;
    private PannelloGriglia griglia_tentativi;
    private JTextArea textAreaChat;
    private JCheckBox chckbxColpisciCoordCasuale;
    
    
    /**
     * Costruttore della View. Viene invocato nel main. Qua si costruisce tutto il JFrame principale.
     */
    public BattleshipExtremeView() {
    	
    	/* ----------------------------------------------------------------- */
        /* Operazioni preliminari e di inizializzazione della GUI principale */
    	/* ----------------------------------------------------------------- */
    	
    	// Inizializzazione del JFrame
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(BattleshipExtremeView.class.getResource("/icons/ship.png")));
        this.setTitle(NOME_PROGRAMMA);
        this.setBounds(100, 100, LARGHEZZA_FINESTRA, ALTEZZA_FINESTRA);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        
        // Inizializzazione del contentPane Principale
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(COLORE_BIANCO);
    	
        
        
    	/* ------------------------------------------ */
    	/* Creazione e settaggio della barra dei menu */
        /* ------------------------------------------ */
    	
        // Inizializzazione della barra del menu
        barraDelMenu = new JMenuBar();
		this.setJMenuBar(barraDelMenu);
		
		// Menu : FILE
		menu_File = new JMenu("File");
		menu_File.setFont(FONT_SEGOE_H1_P);
		barraDelMenu.add(menu_File);
		
		// Menu : FILE : NEW
		menu_File_new = new JMenuItem("Nuova partita");
		menu_File_new.setFont(FONT_SEGOE_H2_P);
		menu_File.add(menu_File_new);
		
		// Menu : FILE : ESCI
		menu_File_esci = new JMenuItem("Esci dal gioco");
		menu_File_esci.setFont(FONT_SEGOE_H2_P);
		menu_File.add(menu_File_esci);
		
		// Menu : MODIFICA
		menu_Modifica = new JMenu("Modifica");
		menu_Modifica.setFont(FONT_SEGOE_H1_P);
		barraDelMenu.add(menu_Modifica);
		
		// Menu : MODIFICA : IMPOSTAZIONI DI GIOCO
		menu_Modifica_settings = new JMenuItem("Impostazioni di gioco");
		menu_Modifica_settings.setFont(FONT_SEGOE_H2_P);
		menu_Modifica.add(menu_Modifica_settings);
		
		// Menu : VISUALIZZA
		menu_Visualizza = new JMenu("Visualizza");
		menu_Visualizza.setFont(FONT_SEGOE_H1_P);
		barraDelMenu.add(menu_Visualizza);
		
		// Menu : VISUALIZZA : CONSOLE
		menu_Visualizza_console = new JMenuItem("Console di gioco");
		menu_Visualizza_console.setFont(FONT_SEGOE_H2_P);
		menu_Visualizza.add(menu_Visualizza_console);
		
		// Menu : VISUALIZZA : STORICO PARTITE
		menu_Visualizza_storicoPartite= new JMenuItem("Storico Partite");
		menu_Visualizza_storicoPartite.setFont(FONT_SEGOE_H2_P);
		menu_Visualizza.add(menu_Visualizza_storicoPartite);
		menu_Visualizza_storicoPartite.setVisible(true);
		
		// Menu : PARTITA
		menu_Partita = new JMenu("Partita");
		menu_Partita.setFont(FONT_SEGOE_H1_P);
		barraDelMenu.add(menu_Partita);
		
		// Menu : PARTITA : TEMPO TRASCORSO
		menu_Partita_TempoCorrente = new JMenuItem("Calcola tempo trascorso");
		menu_Partita_TempoCorrente.setFont(FONT_SEGOE_H2_P);
		menu_Partita_TempoCorrente.setEnabled(false);
		menu_Partita.add(menu_Partita_TempoCorrente);
		
		// Menu : PARTITA : PARtita AUTOMATICA
		menu_Partita_GiocaAutomaticamente= new JMenuItem("Fai giocare la CPU al tuo posto");
		menu_Partita_GiocaAutomaticamente.setFont(FONT_SEGOE_H2_P);
		menu_Partita_GiocaAutomaticamente.setEnabled(false);
		menu_Partita_GiocaAutomaticamente.setVisible(false);
		menu_Partita.add(menu_Partita_GiocaAutomaticamente);
		
		// Menu : AIUTO
		menu_Aiuto = new JMenu("Aiuto");
		menu_Aiuto.setFont(FONT_SEGOE_H1_P);
		barraDelMenu.add(menu_Aiuto);
		
		// Menu : AIUTO : REGOLE
		menu_Aiuto_regole = new JMenuItem("Regole di Battaglia Navale");
		menu_Aiuto_regole.setFont(FONT_SEGOE_H2_P);
		menu_Aiuto.add(menu_Aiuto_regole);
		
		// Menu : AIUTO : TUTORIAL 
		menu_Aiuto_tutorial = new JMenuItem("Guida del gioco");
		menu_Aiuto_tutorial.setFont(FONT_SEGOE_H2_P);
		menu_Aiuto.add(menu_Aiuto_tutorial);
		
		// Menu : INFO
		menu_Info = new JMenu("Info");
		menu_Info.setFont(FONT_SEGOE_H1_P);
		barraDelMenu.add(menu_Info);
		
		// Menu : INFO : PROGETTO 
		menu_Info_progetto = new JMenuItem("Info sul progetto");
		menu_Info_progetto.setFont(FONT_SEGOE_H2_P);
		menu_Info.add(menu_Info_progetto);
		
		// Menu : INFO : SITO 
		menu_Info_sito = new JMenuItem("Sito web del progetto");
		menu_Info_sito.setFont(FONT_SEGOE_H2_P);
		menu_Info.add(menu_Info_sito);
        
        
		
		
        /* ----------------------------------------------------- */
    	/* Creazione del titolo e del pulsante di inizio Partita */
        /* ----------------------------------------------------- */
    	
        // Label del titolo
		label_titolo = new JLabel("");
		label_titolo.setIcon(new ImageIcon(BattleshipExtremeView.class.getResource("/images/titleHome_3.png")));
		label_titolo.setBounds(507, 30, 570, 65);
 		this.getContentPane().add(label_titolo); 	
	
    	// Pulsante di nuova partita
 		btn_nuovaPartita = new JButton("Nuova Partita");
 		btn_nuovaPartita.setFont(FONT_SEGOE_H1_P);
 		btn_nuovaPartita.setBounds(617, 96, 152, 43);
		this.getContentPane().add(btn_nuovaPartita);
		
		// Pulsante di Inizia partita
		btn_inziaPartita = new JButton("Inzia Partita");
		btn_inziaPartita.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btn_inziaPartita.setBounds(823, 96, 152, 43);
		btn_inziaPartita.setVisible(true);
		btn_inziaPartita.setEnabled(false);
		this.getContentPane().add(btn_inziaPartita);
    	
    	
    	this.setVisible(true);
    	
    	
    	
    	
    	/* ----------------------------------------------------- */
    	/* Creazione del campop da gioco : Griglia Navi          */
        /* ----------------------------------------------------- */
    	
    	// PANNELLO MAIN : Pannello della griglia delle navi
    	panello_griglia_navi_master = new JPanel();
    	panello_griglia_navi_master.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(65, 105, 225)));
    	panello_griglia_navi_master.setBackground(Color.WHITE);
    	panello_griglia_navi_master.setBounds(10, 191, 521, 572);
		this.getContentPane().add(panello_griglia_navi_master);
		panello_griglia_navi_master.setLayout(null);
		panello_griglia_navi_master.setVisible(false);
		
		// Pannello label
		JPanel panelloLabelnavi = new JPanel();
		panelloLabelnavi.setBackground(new Color(230, 230, 250));
		panelloLabelnavi.setBounds(10, 11, 500, 39);
		panello_griglia_navi_master.add(panelloLabelnavi);
		panelloLabelnavi.setVisible(true);
		
		// Label titolo griglia
		JLabel labelGrigliaNavi = new JLabel("GRIGLIA DELLA NAVI");
		labelGrigliaNavi.setFont(new Font("Segoe UI", Font.BOLD, 18));
		panelloLabelnavi.add(labelGrigliaNavi);
		
		// Pannello della griglia
		panelloGriglianaviContainer = new JPanel();
		panelloGriglianaviContainer.setBackground(Color.WHITE);
		panelloGriglianaviContainer.setBounds(10, 61, 500, 500);
		panello_griglia_navi_master.add(panelloGriglianaviContainer);
		panelloGriglianaviContainer.setLayout(new BorderLayout());
		
		// griglia delle navi
		griglia_navi = new PannelloGriglia(10);
		griglia_navi.setBackground(Color.WHITE);
		panelloGriglianaviContainer.add(griglia_navi, BorderLayout.CENTER);
		
		
		
		
		
		/* ----------------------------------------------------- */
    	/* Creazione del campop da gioco : Griglia Tenativi      */
        /* ----------------------------------------------------- */
		
		// PANNELLO MAIN : Pannello della griglia delle navi tenativi
		panello_griglia_tentativi_master = new JPanel();
		panello_griglia_tentativi_master.setLayout(null);
		panello_griglia_tentativi_master.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(65, 105, 225)));
		panello_griglia_tentativi_master.setBackground(Color.WHITE);
		panello_griglia_tentativi_master.setBounds(541, 191, 521, 572);
		panello_griglia_tentativi_master.setVisible(false);
		this.getContentPane().add(panello_griglia_tentativi_master);
		
		// Pannello label
		JPanel panelloLabeltent = new JPanel();
		panelloLabeltent.setBackground(new Color(230, 230, 250));
		panelloLabeltent.setBounds(10, 11, 501, 39);
		panello_griglia_tentativi_master.add(panelloLabeltent);
		
		// Label titolo griglia
		JLabel labelTent = new JLabel("GRIGLIA DEI TENTATIVI");
		labelTent.setFont(new Font("Segoe UI", Font.BOLD, 18));
		panelloLabeltent.add(labelTent);
    	
		// Pannel container griglia
		panelloGrigliTentativiContainer = new JPanel();
		panelloGrigliTentativiContainer.setBounds(10, 61, 501, 500);
		panelloGrigliTentativiContainer.setBackground(Color.WHITE);
		panello_griglia_tentativi_master.add(panelloGrigliTentativiContainer);
		panelloGrigliTentativiContainer.setLayout(new BorderLayout());
		
		// griglia delle navi
		griglia_tentativi = new PannelloGriglia(10);
		griglia_tentativi.setBackground(Color.WHITE);
		panelloGrigliTentativiContainer.add(griglia_tentativi, BorderLayout.CENTER);
		
		
		
		
		/* ----------------------------------------------------- */
    	/* Barra del punteggio/tempo                             */
        /* ----------------------------------------------------- */
		
		// Pannello
		panelloPunteggioTempo = new JPanel();
		panelloPunteggioTempo.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panelloPunteggioTempo.setBackground(Color.WHITE);
		panelloPunteggioTempo.setBounds(10, 774, 1052, 49);
		panelloPunteggioTempo.setVisible(false);
		this.getContentPane().add(panelloPunteggioTempo);
		panelloPunteggioTempo.setLayout(null);
		
		// Turno 
		JLabel labelTurno = new JLabel("Numero turno: ");
		labelTurno.setBounds(145, 11, 125, 25);
		panelloPunteggioTempo.add(labelTurno);
		labelTurno.setFont(FONT_SEGOE_H1_P);
		
		labelValueTurno = new JLabel("1");
		labelValueTurno.setBounds(267, 12, 227, 25);
		panelloPunteggioTempo.add(labelValueTurno);
		labelValueTurno.setForeground(new Color(65, 105, 225));
		labelValueTurno.setFont(new Font("Segoe UI", Font.BOLD, 16));	
    	
		// Punteggio
		JLabel labelPunteggio = new JLabel("Punteggio :");
		labelPunteggio.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		labelPunteggio.setBounds(688, 10, 91, 25);
		panelloPunteggioTempo.add(labelPunteggio);
		
		labelValuePunteggio = new JLabel("0");
		labelValuePunteggio.setForeground(new Color(65, 105, 225));
		labelValuePunteggio.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labelValuePunteggio.setBounds(789, 12, 60, 25);
		panelloPunteggioTempo.add(labelValuePunteggio);
		
		
		
		
		
		/* ----------------------------------------------------- */
    	/* PANNELLO INFORMAZIONI PARTITA                         */
        /* ----------------------------------------------------- */
		
		// Pannello principale informazione partita
		panello_InformazioniPartitaMaster = new JPanel();
		panello_InformazioniPartitaMaster.setBounds(1101, 11, 473, 588);
		this.getContentPane().add(panello_InformazioniPartitaMaster);
		panello_InformazioniPartitaMaster.setLayout(null);
		panello_InformazioniPartitaMaster.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(128, 128, 128)));
		panello_InformazioniPartitaMaster.setBackground(Color.WHITE);
		panello_InformazioniPartitaMaster.setVisible(false);
		
		// pannello + label info pannello
		JPanel panelloLabelInforPartita = new JPanel();
		panelloLabelInforPartita.setBackground(Color.WHITE);
		panelloLabelInforPartita.setBounds(10, 11, 453, 39);
		panello_InformazioniPartitaMaster.add(panelloLabelInforPartita);
		
		JLabel labelInfoPartita = new JLabel("INFORMAZIONI PARTITA");
		labelInfoPartita.setFont(new Font("Segoe UI", Font.BOLD, 18));
		panelloLabelInforPartita.add(labelInfoPartita);
		
		// Tabbed pain principale
		JTabbedPane tabbedPaneInfoPartita = new JTabbedPane(JTabbedPane.TOP);
		tabbedPaneInfoPartita.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		tabbedPaneInfoPartita.setBounds(10, 61, 453, 516);
		panello_InformazioniPartitaMaster.add(tabbedPaneInfoPartita);
		
		// Pannello CHAT
		JPanel panello_chat = new JPanel();
		tabbedPaneInfoPartita.addTab("Attività partita", null, panello_chat, null);
		panello_chat.setLayout(null);
		
		JScrollPane scrollPaneChat = new JScrollPane();
		scrollPaneChat.setBounds(0, 0, 448, 477);
		panello_chat.add(scrollPaneChat);
		
		textAreaChat = new JTextArea();
		textAreaChat.setEditable(false);
		textAreaChat.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		scrollPaneChat.setViewportView(textAreaChat);
		
		
		// Pannello Info giocatore
		JPanel panello_InfoGIocatore = new JPanel();
		panello_InfoGIocatore.setBackground(new Color(255, 255, 255));
		tabbedPaneInfoPartita.addTab("Info Giocatore", null, panello_InfoGIocatore, null);
		panello_InfoGIocatore.setLayout(null);
		
		JLabel lblNewLabel_1_1_1_2_1 = new JLabel("Tentativi di affondamento :");
		lblNewLabel_1_1_1_2_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel_1_1_1_2_1.setBounds(93, 28, 226, 25);
		panello_InfoGIocatore.add(lblNewLabel_1_1_1_2_1);
		
		labelValue_tentativiDiaffondamento = new JLabel("150");
		labelValue_tentativiDiaffondamento.setForeground(new Color(65, 105, 225));
		labelValue_tentativiDiaffondamento.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labelValue_tentativiDiaffondamento.setBounds(317, 29, 85, 25);
		panello_InfoGIocatore.add(labelValue_tentativiDiaffondamento);
		
		JLabel lblNewLabel_1_1_1_2_1_1 = new JLabel("N° celle affondate :");
		lblNewLabel_1_1_1_2_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel_1_1_1_2_1_1.setBounds(110, 84, 161, 25);
		panello_InfoGIocatore.add(lblNewLabel_1_1_1_2_1_1);
		
		labelValue_nCelleAffondate = new JLabel("0 / 100");
		labelValue_nCelleAffondate.setForeground(new Color(65, 105, 225));
		labelValue_nCelleAffondate.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labelValue_nCelleAffondate.setBounds(281, 85, 63, 25);
		panello_InfoGIocatore.add(labelValue_nCelleAffondate);
		
		JLabel lblNewLabel_1_1_1_2_1_1_1 = new JLabel("Stato navi : ");
		lblNewLabel_1_1_1_2_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel_1_1_1_2_1_1_1.setBounds(153, 141, 93, 25);
		panello_InfoGIocatore.add(lblNewLabel_1_1_1_2_1_1_1);
		
		labelValue_statoNavi = new JLabel("0 / 10");
		labelValue_statoNavi.setForeground(new Color(65, 105, 225));
		labelValue_statoNavi.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labelValue_statoNavi.setBounds(256, 142, 63, 25);
		panello_InfoGIocatore.add(labelValue_statoNavi);
		
		JLabel lblNewLabel_1_1_1_2_1_1_1_1 = new JLabel("Turni giocati : ");
		lblNewLabel_1_1_1_2_1_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel_1_1_1_2_1_1_1_1.setBounds(153, 201, 118, 25);
		panello_InfoGIocatore.add(lblNewLabel_1_1_1_2_1_1_1_1);
		
		labelValue_TurniGiocati = new JLabel("0");
		labelValue_TurniGiocati.setForeground(new Color(65, 105, 225));
		labelValue_TurniGiocati.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labelValue_TurniGiocati.setBounds(269, 202, 63, 25);
		panello_InfoGIocatore.add(labelValue_TurniGiocati);
		
		
		
		
		// PPanellop info CPU
		JPanel panello_InfoCpu = new JPanel();
		panello_InfoCpu.setBackground(new Color(255, 255, 255));
		panello_InfoCpu.setLayout(null);
		tabbedPaneInfoPartita.addTab("Info Cpu", null, panello_InfoCpu, null);
		
		JLabel lblNewLabel_1_1_1_2_11 = new JLabel("Tentativi di affondamento :");
		lblNewLabel_1_1_1_2_11.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel_1_1_1_2_11.setBounds(93, 28, 226, 25);
		panello_InfoCpu.add(lblNewLabel_1_1_1_2_11);
		
		labelValueCpu_tentativiDiaffondamento = new JLabel("150");
		labelValueCpu_tentativiDiaffondamento.setForeground(new Color(65, 105, 225));
		labelValueCpu_tentativiDiaffondamento.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labelValueCpu_tentativiDiaffondamento.setBounds(317, 29, 85, 25);
		panello_InfoCpu.add(labelValueCpu_tentativiDiaffondamento);
		
		JLabel lblNewLabel_1_1_1_2_1_112 = new JLabel("N° celle affondate :");
		lblNewLabel_1_1_1_2_1_112.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel_1_1_1_2_1_112.setBounds(110, 84, 161, 25);
		panello_InfoCpu.add(lblNewLabel_1_1_1_2_1_112);
		
		labelValueCpu_nCelleAffondate = new JLabel("0 / 100");
		labelValueCpu_nCelleAffondate.setForeground(new Color(65, 105, 225));
		labelValueCpu_nCelleAffondate.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labelValueCpu_nCelleAffondate.setBounds(281, 85, 63, 25);
		panello_InfoCpu.add(labelValueCpu_nCelleAffondate);
		
		JLabel lblNewLabel_1_1_1_22_1_1_1 = new JLabel("Stato navi : ");
		lblNewLabel_1_1_1_22_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel_1_1_1_22_1_1_1.setBounds(153, 141, 93, 25);
		panello_InfoCpu.add(lblNewLabel_1_1_1_22_1_1_1);
		
		labelValueCpu_statoNavi = new JLabel("0 / 10");
		labelValueCpu_statoNavi.setForeground(new Color(65, 105, 225));
		labelValueCpu_statoNavi.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labelValueCpu_statoNavi.setBounds(256, 142, 63, 25);
		panello_InfoCpu.add(labelValueCpu_statoNavi);
		
		JLabel lblNewLabel_11_1_1_2_1_1_1_1 = new JLabel("Turni giocati : ");
		lblNewLabel_11_1_1_2_1_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel_11_1_1_2_1_1_1_1.setBounds(153, 201, 118, 25);
		panello_InfoCpu.add(lblNewLabel_11_1_1_2_1_1_1_1);
		
		labelValueCpu_TurniGiocati = new JLabel("0");
		labelValueCpu_TurniGiocati.setForeground(new Color(65, 105, 225));
		labelValueCpu_TurniGiocati.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labelValueCpu_TurniGiocati.setBounds(269, 202, 63, 25);
		panello_InfoCpu.add(labelValueCpu_TurniGiocati);
		
		
		
		
		/* ----------------------------------------------------- */
    	/* PANNELLO GESTIONE TURNO                               */
        /* ----------------------------------------------------- */
		
		panello_GestioneTurno = new JPanel();
		panello_GestioneTurno.setLayout(null);
		panello_GestioneTurno.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(128, 128, 128)));
		panello_GestioneTurno.setBackground(Color.WHITE);
		panello_GestioneTurno.setBounds(1101, 618, 473, 205);
		panello_GestioneTurno.setVisible(false);
		this.getContentPane().add(panello_GestioneTurno);
		
		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setBounds(10, 11, 453, 39);
		panello_GestioneTurno.add(panel_1_1_1);
		panel_1_1_1.setBackground(new Color(255, 255, 255));
		
		JLabel lblNewLabel_2_1 = new JLabel("GESTIONE TURNO");
		lblNewLabel_2_1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		panel_1_1_1.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("Inserisci le coordinate della cella che vuoi colpire.");
		lblNewLabel_1_1_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel_1_1_1_2.setBounds(33, 61, 406, 25);
		panello_GestioneTurno.add(lblNewLabel_1_1_1_2);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Coordinata X : ");
		lblNewLabel_1_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_1_2.setBounds(43, 101, 101, 25);
		panello_GestioneTurno.add(lblNewLabel_1_1_2);
		
		spinnerValueX = new JSpinner();
		spinnerValueX.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		spinnerValueX.setBounds(150, 97, 50, 32);
		panello_GestioneTurno.add(spinnerValueX);
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("Coordinata Y : ");
		lblNewLabel_1_1_2_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_1_2_1.setBounds(265, 101, 101, 25);
		panello_GestioneTurno.add(lblNewLabel_1_1_2_1);
		
		spinnerValueY = new JSpinner();
		spinnerValueY.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		spinnerValueY.setBounds(372, 97, 50, 32);
		panello_GestioneTurno.add(spinnerValueY);
		
		btnColpisciCella = new JButton("Colpisci Cella");
		btnColpisciCella.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnColpisciCella.setBounds(252, 156, 153, 38);
		panello_GestioneTurno.add(btnColpisciCella);
		
		chckbxColpisciCoordCasuale = new JCheckBox("Coordinate casuali");
		chckbxColpisciCoordCasuale.setBackground(Color.WHITE);
		chckbxColpisciCoordCasuale.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		chckbxColpisciCoordCasuale.setBounds(18, 167, 153, 23);
		panello_GestioneTurno.add(chckbxColpisciCoordCasuale);
		
		
    }
    
    
    public void visualizzaCampoDiGioco() {
    	panello_griglia_navi_master.setVisible(true);
    	panello_griglia_tentativi_master.setVisible(true);
    }
    
    

	
	
	public void creaGriglie(int dimensioneMappa) {
		
		// Griglia Navi
		panelloGriglianaviContainer.remove(griglia_navi);
		panelloGriglianaviContainer.revalidate();
		panelloGriglianaviContainer.repaint();
		
		griglia_navi = new PannelloGriglia(dimensioneMappa);
		griglia_navi.setBackground(Color.WHITE);
		panelloGriglianaviContainer.add(griglia_navi, BorderLayout.CENTER);
		panelloGriglianaviContainer.revalidate();
		panelloGriglianaviContainer.repaint();
		
		
		// Griglia tentativi
		panelloGrigliTentativiContainer.remove(griglia_tentativi);
		panelloGrigliTentativiContainer.revalidate();
		panelloGrigliTentativiContainer.repaint();
		
		griglia_tentativi = new PannelloGriglia(dimensioneMappa);
		griglia_tentativi.setBackground(Color.WHITE);
		panelloGrigliTentativiContainer.add(griglia_tentativi, BorderLayout.CENTER);
		panelloGrigliTentativiContainer.revalidate();
		panelloGrigliTentativiContainer.repaint();
		
		
	}
	
	
	/**
	 * Aggiorna la griglia visualizzando nella mappa tutte le navi del player.
	 * @param CodicePlayer il codice player relativo al player in questione
	 * @param spazioNavi lo spazio navi del player
	 * @param dimensioneMappa la dimensione della mappa
	 * @param mostraNaviCpu variabile di debug che mostra sulla mappa tenativi le navi della CPU
	 */
	public void updateNaviPlayer(int CodicePlayer, Boolean[][] spazioNavi, int dimensioneMappa, Boolean mostraNaviCpu) {		
		for (int i = 0; i < dimensioneMappa; i++) {
			for (int j = 0; j < dimensioneMappa; j++) {
				if (spazioNavi[i][j] == true) {
//					System.out.println("[DEBUG]: sto colorando X=" + i + "Y=" + j);
					if (CodicePlayer == 0) {
						griglia_navi.visualizzaPezzoNave(i, j, CodicePlayer);
					}else if (CodicePlayer == 1){
						if (mostraNaviCpu) {
							griglia_tentativi.visualizzaPezzoNave(i, j, CodicePlayer);
						}	
					}
					
				}
			}
		}		
	}
	
	
	
	/**
	 * Metodo usato solamente per scopi di debug, mostra per ogni cella della mappa in cui è presente una nave, il suo id
	 * 
	 * @param mappe le mappe del player di cuisi volgiono visualizzare gli ID
	 * @param CodicePlayer Il codice identificato del player
	 */
	public void visualizzaIdNave(MappePlayer mappe, int CodicePlayer) {
		int[][] s = mappe.getSpazioNaviId();
		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < s.length; j++) {
				if (mappe.getSpazioNaviId()[i][j] != 0) {
					if (CodicePlayer == 0) {
						griglia_navi.visualizzaIdNave(s[i][j], i, j);
					}else if (CodicePlayer == 1) {
						griglia_tentativi.visualizzaIdNave(s[i][j], i, j);
					}
					
				}			
			}
		}
	}
	
	
	
	public void writeChatLine(String s) {

		if(textAreaChat.getText().length() != 0) {
			textAreaChat.setText(textAreaChat.getText() +  "\n" + "> "+ s);
		}else {
			textAreaChat.setText(textAreaChat.getText() +"> "+ s);
		}
		
	}
	
	public void writeChatLineTurno(int numeroTurno) {

		
		textAreaChat.setText(textAreaChat.getText() +  "\n" + "# TURNO "+ numeroTurno + "# --------------------------------------------");
		
		
	}
	
	
	/**
	 * Aggiorna la griglia relativa al giocatore mostrando i rispettivi tenativi di affondamkento 
	 * delle navi avversarie.
	 * 
	 * @param p Il player di cui si vogliono visualizzare i tentativi nella mappa avversaria
	 * @param tentativiDiAffondEffettuati lo spazio navi corrispondenti ai tentativi
	 */
	public void updateTentativiDiAffondamento(int p, Boolean[][] tentativiDiAffondEffettuati) {
		
		for (int i = 0; i < tentativiDiAffondEffettuati.length; i++) {
			for (int j = 0; j < tentativiDiAffondEffettuati.length; j++) {
				if (tentativiDiAffondEffettuati[i][j] == true) {
					switch (p) {
					case 0:
						griglia_tentativi.visualizzaTentativiDiAffondamento(i,j);
						break;
					case 1:
						griglia_navi.visualizzaTentativiDiAffondamento(i,j);
						break;			
					default:
						break;
					}
					
				}
			}
		}
		
	}
	
	
	/**
	 * Aggionra la mappa tentativi del player, mostrando sulla griglia tutti i tentativi di affondamento
	 * effettuati.
	 * 
	 * @param codicePlayer il codice idnetificato del p'layer
	 * @param m La mappa che contiene gli affondamento da visualizzare
	 */
	public void updateNaviAffondate(int codicePlayer, MappePlayer m) {
		Boolean[][] affondamenti = m.getSpazioNaviAvversarioAffondate();
		for (int i = 0; i < affondamenti.length; i++) {
			for (int j = 0; j < affondamenti.length; j++) {
				if (affondamenti[i][j] == true) {
					switch (codicePlayer) {
					case 0:
						griglia_tentativi.visualizzaNaviAffondate(i, j, 0);
						break;
					case 1:
						griglia_navi.visualizzaNaviAffondate(i, j, 1);
						break;

					default:
						break;
					}
				}
			}
		}
	}
	
	
	
	
	

	/**
	 * @return the menu_File_new
	 */
	public JMenuItem getMenu_File_new() {
		return menu_File_new;
	}



	/**
	 * @return the menu_File_esci
	 */
	public JMenuItem getMenu_File_esci() {
		return menu_File_esci;
	}
	
	

	/**
	 * @return the menu_Modifica_settings
	 */
	public JMenuItem getMenu_Modifica_settings() {
		return menu_Modifica_settings;
	}
	

	/**
	 * @return the menu_Visualizza_console
	 */
	public JMenuItem getMenu_Visualizza_console() {
		return menu_Visualizza_console;
	}



	/**
	 * @return the menu_Aiuto_regole
	 */
	public JMenuItem getMenu_Aiuto_regole() {
		return menu_Aiuto_regole;
	}



	/**
	 * @return the btn_nuovaPartita
	 */
	public JButton getBtn_nuovaPartita() {
		return btn_nuovaPartita;
	}


	/**
	 * @return the panelloPunteggioTempo
	 */
	public JPanel getPanelloPunteggioTempo() {
		return panelloPunteggioTempo;
	}


	/**
	 * @return the menu_Partita_TempoCorrente
	 */
	public JMenuItem getMenu_Partita_TempoCorrente() {
		return menu_Partita_TempoCorrente;
	}


	/**
	 * @return the panello_InformazioniPartitaMaster
	 */
	public JPanel getPanello_InformazioniPartitaMaster() {
		return panello_InformazioniPartitaMaster;
	}


	/**
	 * @return the btn_inziaPartita
	 */
	public JButton getBtn_inziaPartita() {
		return btn_inziaPartita;
	}


	/**
	 * @return the panello_GestioneTurno
	 */
	public JPanel getPanello_GestioneTurno() {
		return panello_GestioneTurno;
	}


	/**
	 * @return the textAreaChat
	 */
	public JTextArea getTextAreaChat() {
		return textAreaChat;
	}


	/**
	 * @return the btnColpisciCella
	 */
	public JButton getBtnColpisciCella() {
		return btnColpisciCella;
	}


	/**
	 * @return the spinnerValueX
	 */
	public JSpinner getSpinnerValueX() {
		return spinnerValueX;
	}


	/**
	 * @return the spinnerValueY
	 */
	public JSpinner getSpinnerValueY() {
		return spinnerValueY;
	}


	/**
	 * @return the chckbxColpisciCoordCasuale
	 */
	public JCheckBox getChckbxColpisciCoordCasuale() {
		return chckbxColpisciCoordCasuale;
	}


	/**
	 * @return the labelValueTurno
	 */
	public JLabel getLabelValueTurno() {
		return labelValueTurno;
	}


	/**
	 * @return the labelValuePunteggio
	 */
	public JLabel getLabelValuePunteggio() {
		return labelValuePunteggio;
	}


	/**
	 * @return the labelValue_tentativiDiaffondamento
	 */
	public JLabel getLabelValue_tentativiDiaffondamento() {
		return labelValue_tentativiDiaffondamento;
	}


	/**
	 * @return the labelValue_nCelleAffondate
	 */
	public JLabel getLabelValue_nCelleAffondate() {
		return labelValue_nCelleAffondate;
	}


	/**
	 * @return the labelValue_statoNavi
	 */
	public JLabel getLabelValue_statoNavi() {
		return labelValue_statoNavi;
	}


	/**
	 * @return the labelValue_TurniGiocati
	 */
	public JLabel getLabelValue_TurniGiocati() {
		return labelValue_TurniGiocati;
	}


	/**
	 * @return the labelValueCpu_tentativiDiaffondamento
	 */
	public JLabel getLabelValueCpu_tentativiDiaffondamento() {
		return labelValueCpu_tentativiDiaffondamento;
	}


	/**
	 * @return the labelValueCpu_nCelleAffondate
	 */
	public JLabel getLabelValueCpu_nCelleAffondate() {
		return labelValueCpu_nCelleAffondate;
	}


	/**
	 * @return the labelValueCpu_statoNavi
	 */
	public JLabel getLabelValueCpu_statoNavi() {
		return labelValueCpu_statoNavi;
	}


	/**
	 * @return the labelValueCpu_TurniGiocati
	 */
	public JLabel getLabelValueCpu_TurniGiocati() {
		return labelValueCpu_TurniGiocati;
	}


	/**
	 * @return the menu_Partita_GiocaAutomaticamente
	 */
	public JMenuItem getMenu_Partita_GiocaAutomaticamente() {
		return menu_Partita_GiocaAutomaticamente;
	}


	/**
	 * @return the menu_Visualizza_storicoPartite
	 */
	public JMenuItem getMenu_Visualizza_storicoPartite() {
		return menu_Visualizza_storicoPartite;
	}


	/**
	 * @return the menu_Aiuto_tutorial
	 */
	public JMenuItem getMenu_Aiuto_tutorial() {
		return menu_Aiuto_tutorial;
	}


	/**
	 * @return the menu_Info_progetto
	 */
	public JMenuItem getMenu_Info_progetto() {
		return menu_Info_progetto;
	}


	/**
	 * @return the menu_Info_sito
	 */
	public JMenuItem getMenu_Info_sito() {
		return menu_Info_sito;
	}
	
	
	
	
	
	
	
	
	
	
	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
