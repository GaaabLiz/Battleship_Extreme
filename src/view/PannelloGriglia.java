package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class PannelloGriglia extends JPanel{
	
	// # VARIABILI PRINCIPALI DELLA GRIGLIA #----------------------------------------------------------------
		private JLabel[][] griglia;
		private int dimensioneGriglia;
		
		// # COSTANTI PER GESTIRE COLORI E FONT #-----------------------------------------------------------------
		private static final Color COLORE_NAVE = new Color(154,192,249);
		private static final Color COLORE_CELLA = Color.WHITE;
		private static final Color COLORE_PREVIEW = new Color(244,228, 170);
		private static final Color COLORE_NAVE_CPU = new Color(180,120, 49);
		private static final Color COLORE_TENTATIVO = Color.BLACK;
		private static final Color COLORE_BORDO = Color.BLACK;
		private static final Color COLORE_SFONDO = Color.LIGHT_GRAY;
		private static final Font FONT_TENTATIVO = new Font("Tahoma", Font.PLAIN, 20);
		
		
		
		/**
		 * Costruttore della griglia. Crea una griglia con la stessa dimensione della mappa.
		 * @param dimensioneMappa La dimensione della mappa di gioco.
		 */
		public PannelloGriglia(int dimensioneMappa) {
			this.setLayout(new BorderLayout());
			this.dimensioneGriglia = dimensioneMappa;
			
			JPanel pannelloGrid = new JPanel();
			pannelloGrid.setLayout(new GridLayout(dimensioneGriglia+1,  dimensioneGriglia+1));
			griglia = new JLabel[dimensioneGriglia+1][dimensioneGriglia+1];
			Border bordo = BorderFactory.createLineBorder(COLORE_BORDO);
			
			for(int y = 0; y <= dimensioneGriglia; y++)
			{
				for(int x = 0; x <= dimensioneGriglia; x++)
				{
					griglia[y][x] = new JLabel();
					griglia[y][x].setPreferredSize(new Dimension(30,30));
					
					griglia[y][x].setHorizontalAlignment(JButton.CENTER);
					griglia[y][x].setVerticalAlignment(JButton.CENTER);
					
					griglia[y][x].setOpaque(true);
					
					griglia[y][x].setBackground(COLORE_CELLA);
					griglia[y][x].setBorder(bordo);
					
					griglia[y][x].setName("["+(x-1)+","+(y-1)+"]");
				
					pannelloGrid.add(griglia[y][x]);
					this.add(pannelloGrid, BorderLayout.CENTER);
					
					if (y==0 && x!=0) {
						griglia[y][x].setText(((Integer)(x-1)).toString());
						griglia[y][x].setBackground(COLORE_SFONDO);
						griglia[y][x].setFont(new Font("Helvetica", Font.BOLD, 14));
					}
					else if (y!=0 && x==0) {
						griglia[y][x].setText(((Integer)(y-1)).toString());
						griglia[y][x].setBackground(COLORE_SFONDO);
						griglia[y][x].setFont(new Font("Helvetica", Font.BOLD, 14));
					}
					else
					griglia[0][0].setBackground(COLORE_SFONDO);
				}
				
				this.setVisible(true);
			}
		}
		
		/**
		 * Dopo aver inserito le coordinate X e Y mostra sulla mappa una preview del punto in cui comparirà la nave 
		 * dopo i controlli. Questa preview viene eseguita prima dell'inserimento dell'orientamento da parte dell'utente.
		 * Se dopo l'inserimento dell'orientamento la nave non può essere inserita con tale orientamento il programma
		 * richiede l'inserimento delle nuove coordinate e la vecchia preview della prua viene eliminata.
		 * 
		 * @param p Il punto all'interno della mappa contenente le coordinata della prua
		 * @param visibilita TRUE se si vuole vedere la preview nel punto. FALSE altrimenti.
		 */
		public void previewPrua(Point p, Boolean visibilita) {
			int coordX = p.x;
			int coordY = p.y;
			
			for (int i = 0; i < griglia.length; i++) {
				for (int j = 0; j < griglia.length; j++) {
					if ((i == coordX) && (j == coordY)) {
						if (visibilita) {
							griglia[coordX+1][coordY+1].setBackground(COLORE_PREVIEW);
						}else {
							if (griglia[coordX+1][coordY+1].getBackground() == COLORE_NAVE) {
								griglia[coordX+1][coordY+1].setBackground(COLORE_NAVE);
							}else {
								griglia[coordX+1][coordY+1].setBackground(COLORE_CELLA);
							}
						}
						
					}
				}
			}
		}
		
		
		
		/**
		 * Metodo che cambia il colore della cella con il colore nave. Questo metodo serve per visualizzare, dato in
		 * ingresso lo spazioNavidel player, tutte le celle in cui ci soSno navi.
		 * @param x X della cella
		 * @param y Y della cella
		 * @param p Il codice player di cui visualizzare pezzo nave
		 */
		public void visualizzaPezzoNave(int x, int y, int p) {
//			System.out.println("[DEBUG]: Ho colorato di BLU (metodo GridPanel): X=" + x + " Y=" + y);
			if (p == 0) {
				griglia[x+1][y+1].setBackground(COLORE_NAVE);
			} else {
				griglia[x+1][y+1].setBackground(COLORE_NAVE_CPU);
			}
			
		}
		
		
		/**
		 * Visualizza nella cella selezionata l'id della nave che la occupa
		 * @param id l'id della nave da mostrare nella JLabel
		 * @param x X della cella
		 * @param y Y della cella
		 */
		public void visualizzaIdNave(int id, int x, int y) {
			griglia[x+1][y+1].setFont(FONT_TENTATIVO);
			griglia[x+1][y+1].setText(String.valueOf(id));
		}


		/**
		 * Mostra sulla mappa un tentativo di affondamento nave
		 * @param x X della cella
		 * @param y Y della cella
		 */
		public void visualizzaTentativiDiAffondamento(int x, int y) {
			griglia[x+1][y+1].setText("X");
			griglia[x+1][y+1].setFont(FONT_TENTATIVO);	
		}
		
		
		/**
		 * Mostra sulla mappa la cella di una nave affondata
		 * @param x X della cella
		 * @param y Y della cella
		 * @param codicePlayer Il codice relativo al player in questione
		 */
		public void visualizzaNaviAffondate(int x, int y, int codicePlayer) {
			griglia[x+1][y+1].setText("X");
			griglia[x+1][y+1].setForeground(COLORE_TENTATIVO);
			if (codicePlayer == 0) {
				griglia[x+1][y+1].setBackground(COLORE_NAVE);
			}else {
				griglia[x+1][y+1].setBackground(COLORE_NAVE_CPU);
			}
			griglia[x+1][y+1].setFont(FONT_TENTATIVO);
		}
}
