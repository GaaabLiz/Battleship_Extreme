package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.border.LineBorder;

import java.awt.*;

/**
 * Questa è la classe che controlla la "view" di tutto il programma. Contiene la GUI
 * principale del programma.
 * @author GaaabLiz
 */
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
    
    private JMenuBar barraDelMenu;
    private JMenu menu_File;
    private JMenu menu_Modifica;
    private JMenu menu_Visualizza;
    private JMenu menu_Aiuto;
    private JMenu menu_Info;
    private JMenuItem menu_File_new;
    private JMenuItem menu_File_esci;
    private JMenuItem menu_Modifica_settings;
    private JMenuItem menu_Visualizza_console;
    private JMenuItem menu_Aiuto_regole;
    private JMenuItem menu_Aiuto_tutorial;
    private JMenuItem menu_Info_progetto;
    private JLabel label_titolo;
    private JButton btn_nuovaPartita;
    
    
    /**
     * Costruttore della View. Viene invocato nel mail. Qua si costruisce tutto il JFrame principale.
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
		
		// Menu : AIUTO
		menu_Aiuto = new JMenu("Aiuto");
		menu_Aiuto.setFont(FONT_SEGOE_H1_P);
		barraDelMenu.add(menu_Aiuto);
		
		// Menu : AIUTO : REGOLE
		menu_Aiuto_regole = new JMenuItem("Regole di Battaglia Navale");
		menu_Aiuto_regole.setFont(FONT_SEGOE_H2_P);
		menu_Aiuto.add(menu_Aiuto_regole);
        
        
		
		
        /* ----------------------------------------------------- */
    	/* Creazione del titolo e del pulsante di inizio Partita */
        /* ----------------------------------------------------- */
    	
        // Label del titolo
 		label_titolo = new JLabel("");
 		label_titolo.setIcon(new ImageIcon(BattleshipExtremeView.class.getResource("/images/titleHome_2.png")));
 		label_titolo.setBounds(453, 30, 677, 65);
 		this.getContentPane().add(label_titolo); 	
	
    	// Pulsante di nuova partita
 		btn_nuovaPartita = new JButton("Nuova Partita");
 		btn_nuovaPartita.setFont(FONT_SEGOE_H1_P);
 		btn_nuovaPartita.setBounds(742, 115, 152, 43);
		this.getContentPane().add(btn_nuovaPartita);
    	
    	
    	this.setVisible(true);
    }
    
    
    
    /**
     * Metodo che crea e setta la barra dei menu e tutti i suoi sotto menu.
     */
	private void inizializzazioneBarraMenu() {
		
		// Inizializzazione della barra del menu
        barraDelMenu = new JMenuBar();
		this.setJMenuBar(barraDelMenu);
		
		// Menu : FILE
		menu_File = new JMenu("File");
		menu_File.setFont(FONT_SEGOE_H1_P);
		barraDelMenu.add(menu_File);
		
		// Menu : FILE : ESCI
		menu_File_esci = new JMenuItem("Esci dal gioco");
		menu_File_esci.setFont(FONT_SEGOE_H2_P);
		menu_File.add(menu_File_esci);
		
		// Menu : AIUTO
		menu_Aiuto = new JMenu("Aiuto");
		menu_Aiuto.setFont(FONT_SEGOE_H1_P);
		barraDelMenu.add(menu_Aiuto);
		
		// Menu : AIUTO : REGOLE
		menu_Aiuto_regole = new JMenuItem("Regole di Battaglia Navale");
		menu_Aiuto_regole.setFont(FONT_SEGOE_H2_P);
		menu_Aiuto.add(menu_Aiuto_regole);
		
		// Menu : AIUTO : TUTORIAL
		menu_Aiuto_tutorial = new JMenuItem("Guida all'uso");
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
	}
	
	
	
	
	private void inizializzazioneTitoloEStart() {
		
		// Label del titolo
		label_titolo = new JLabel("");
		label_titolo.setIcon(new ImageIcon(BattleshipExtremeView.class.getResource("/images/titleHome_2.png")));
		label_titolo.setBounds(361, 30, 850, 65);
		this.getContentPane().add(label_titolo);
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
	
	
	
	
	
	
	
	
	
}
