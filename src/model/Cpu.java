package model;

import java.awt.Point;
import java.util.Random;

public class Cpu extends Player{
	
	public final int NUMERO_GIOCATORE = 1;
		
	public Cpu () {
		super();
	}
	
	
	/**
	 * Metodo che genera coordinate valide per la dimensione mappa corrente.
	 * @param dimMappa la dimensione della mappa
	 * @return Coordinate generate casualmente
	 */
	public Point generaCoordinateCasuali(int dimMappa) {
		Random rand = new Random();
		 int rand_int1 = rand.nextInt(dimMappa); 
	     int rand_int2 = rand.nextInt(dimMappa);	     
	     return new Point(rand_int1, rand_int2);
	}
	
	
	/**
	 * Metodo che genera un intero casuale per la dim nave.
	 * @return l'intero generato
	 */
	public int generaDimNaveCasuale() {
			int min = 2;
		    int max = 6;
			Random rand = new Random();
		 int randomInt = (int) (Math.random() * (max - min + 1) + min);	     
	     return randomInt;
	}
	
	
	/**
	 * Genera un orientamento casuale per una nave della CPU
	 * @return Un PuntoCardinale a caso tra i 4
	 */
	public PuntoCardinale generaOrientamentoCasuale() {
		Random r = new Random();
		int rand_int1 = r.nextInt(4);
		PuntoCardinale c;
		
		switch (rand_int1) {
		case 0:
			c = PuntoCardinale.NORD;
			break;
		case 1:
			c = PuntoCardinale.SUD;
			break;
		case 2:
			c = PuntoCardinale.OVEST;
			break;
		case 3:
			c = PuntoCardinale.EST;
			break;

		default:
			c = null;
			break;
		}	
		return c;
	}
	
	
}
