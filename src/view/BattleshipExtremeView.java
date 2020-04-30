package view;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    //Dichirazione dei bordi dei programmi
    protected final LineBorder BORDER_GRIGINO = new LineBorder(Color.LIGHT_GRAY);
    
    private ActionListener azione_esciDalGioco;
    private JMenuBar barraDelMenu;
    private JMenu menu_File;
    private JMenuItem menu_File_esci;
    
    
    /**
     * Costruttore della View. Viene invocato nel mail. Qua si costruisce tutto il JFrame principale.
     */
    public BattleshipExtremeView() {
    	

        /* Operazioni preliminari e di inizializzazione della GUI principale */
    	inizializzazioneJFrame();
    	
    	/* Creazione e settaggio della barra dei menu */
    	inizializzazioneBarraMenu();

   
		
    }
    
    
    
    /**
     * Metodo che setta tutte le impostazioni iniziale del JFRame principale.
     */
    private void inizializzazioneJFrame() {    	
    	// Inizializzazione del JFrame
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(BattleshipExtremeView.class.getResource("/icons/ship.png")));
        this.setTitle(NOME_PROGRAMMA);
        this.setBounds(500, 500, LARGHEZZA_FINESTRA, ALTEZZA_FINESTRA);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);

        // Inizializzazione del contentPane Principale
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(COLORE_BIANCO);
    	
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
		menu_File_esci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		menu_File.add(menu_File_esci);
	}
}
