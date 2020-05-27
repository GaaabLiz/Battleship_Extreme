package view;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.Log;

/**
 * Classe che crea una nuova finestea contenente solamente la console del gioco. serve per vedere tutti i log del gioco.
 * @author Gabriele
 *
 */
@SuppressWarnings("serial")
public class ConsoleView extends JFrame {

	
	/**
	 * Costruttore della console
	 * @param logs l'elenco dei log del programma attuali.
	 * @param view la view principale del programma.
	 * @throws HeadlessException in caso di errore
	 */
	public ConsoleView(ArrayList<Log> logs, BattleshipExtremeView view) throws HeadlessException {
		super();
		
		// Inizializzazione del JFrame
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(BattleshipExtremeView.class.getResource("/icons/console.png")));
        this.setTitle("Console di gioco");
        this.setBounds(500, 300, 1400, 800);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setResizable(false);
        
        // Inizializzazione del contentPane Principale
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(view.COLORE_BIANCO);
        
        // Settaggio TextArea
        JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 1364, 739);
		this.getContentPane().add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(view.FONT_SEGOE_H1_P);
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		for (int i = 0; i < logs.size(); i++) {
			String tempOra = "{" + logs.get(i).getOra() + "}";
			String tempTipo = "[" + logs.get(i).getTipo() + "]";
			String tempFonte = "(" + logs.get(i).getFonte() + ")";
			String tempText = logs.get(i).getTestoLog();
			if (i == 0) {
				textArea.setText(textArea.getText() + tempOra + " " + tempTipo + " " + tempFonte + " " + tempText);
			}else {
				textArea.setText(textArea.getText() + "\n" + tempOra + " " + tempTipo + " " + tempFonte + " " + tempText);
			}
			
		}
		
		this.setVisible(true);
	}
	
	
}
