package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

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
}
