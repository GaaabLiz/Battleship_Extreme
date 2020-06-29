package model;

import java.awt.Point;
import java.util.ArrayList;

/**
 * <strong>Classe che rappresenta uno dei giocatori. </strong>
 * 
 * Questa classe rappresenta principalemte il giocatore, in quanto la CPU
 * estende questa classe (avra' tutti i metodi di questa classe + altri). 
 * @author Gabriele Lizzos
 * @author Antonio Scalogna
 */
public class Player {
	public ArrayList<Nave> navi;
	public final int NUMERO_GIOCATORE = 0;
	public int punteggio;
	public int turniGiocati;
	public int mieCelleNaviAffondate;

	
	/**
	 * Costruttore del Player
	 */
	public Player() {
		this.navi = new ArrayList<Nave>();
	}
	
	
	/**
	 * Metodo che viene usato per aggiungere una nave alla proprio lista di navi del Player che chiama
	 * questo metodo.
	 * @param n La nave da aggiungere
	 */
	public void aggiungiNaveAlleMieNavi(Nave n) {
		navi.add(n);
	}
	
	
	/**
	 * Metodo che controlla se un player ha gia' tentato di colpire una cella della mappa avversaria
	 * @param spazioTentaticiPlayer Lo spazio tentativi di affondamento del player che chiama questo metodo
	 * @param p Le coordinate da controllare
	 * @return TRUE se la cella è gia stata scelta per colpire, FALSE altrimenti
	 */
	public Boolean controllaCellaGiaColpita(Boolean[][] spazioTentaticiPlayer, Point p) {
		for (int i = 0; i < spazioTentaticiPlayer.length; i++) {
			for (int j = 0; j < spazioTentaticiPlayer.length; j++) {
				if( (i== p.x) && (j == p.y)) {
					if (spazioTentaticiPlayer[i][j] == true) {
						return true;
					}else {
						return false;
					}
				}
			}
		}
		return null;
	}
	
	
	/**
	 * Metodo utilizzato da entrambi i player per colpire una nave avversaria.
	 * @param mappa La mappa da colpire
	 * @param p La coordinata da colpire
	 * @return TRUE se la cella corrispondente contiene una nave (e quidni colpita), FALSE altrimenti.
	 */
	public Boolean colpisci(MappePlayer mappa, Point p) {
		if(mappa.getTipoCella(p.x, p.y) == Cella.OCCUPATA) {
			return true;
		}else {
			return false;
		}
		
		
	}

	/**
	 * @return Il contenitore delle navi di ogni giocatore
	 */
	public ArrayList<Nave> getNaviPlayer() {
		return navi;
	}
	
	
	/**
	 * Metodo che controlla la vittoria di una Player. Viene confrontata la mappa affondamento di un player con la mappa
	 * navi dell'avversario.
	 * 
	 * @param MappaAffondamentiGiocatore La mappa che contiene gli affondamenti
	 * @param MappaNaviCpu La mappa che contiene tutte le navi dell'opponente
	 * @return TRUE se il giocatore ha vinto, FALSE altrimenti.
	 */
	public Boolean controllaVittoria(MappePlayer MappaAffondamentiGiocatore, MappePlayer MappaNaviCpu) {
		Boolean [][] spazioNaviCpu = MappaNaviCpu.getSpazioNavi();
		Boolean [][] naviAffondateGiocatore = MappaAffondamentiGiocatore.getSpazioNaviAvversarioAffondate();
		int celleNaviAffondate = 0;
		
		for (int i = 0; i < spazioNaviCpu.length; i++) {
			for (int j = 0; j < spazioNaviCpu.length; j++) {
				if ( (spazioNaviCpu[i][j] == true) && (naviAffondateGiocatore[i][j]==true)) {
//					System.out.println("[DEBUG]: lo spazio navi CPU ("+i+")("+j+") e' uguale a naviAffGioc.");
					celleNaviAffondate++;
				}
			}
		}
		
//		System.out.println("[DEBUG]: (controlla vittoria giocatore) Il numero di celle affondate è: " + celleNaviAffondate);
//		System.out.println("[DEBUG]: (controlla vittoria giocatore) Il numero di celle occupati dalle navi CPU è: " + MappaNaviCpu.getCelleOccupateDaNavi());
		
		if (celleNaviAffondate == MappaNaviCpu.getCelleOccupateDaNavi()) {
			
			return true;
		}else {
			return false;
		}
	}
	
	
	public void aggiungiPunteggio(Long secondi, Boolean naveColpita) {
		int temp = 0;
		if( (secondi <60)) {
			if (naveColpita) {
				temp = temp + 500;
			}else {
				temp = temp + 10;
			}
		}else if((secondi >= 60) && (secondi < 120)) {
			if (naveColpita) {
				temp = temp + 400;
			}else {
				temp = temp + 5;
			}
		}else if((secondi >= 120) && (secondi < 180)) {
			if (naveColpita) {
				temp = temp + 350;
			}else {
				temp = temp + 3;
			}
		}else if((secondi >= 180) && (secondi < 240)) {
			if (naveColpita) {
				temp = temp + 200;
			}else {
				temp = temp + 2;
			}
		}else if((secondi >= 240) && (secondi < 300)) {
			if (naveColpita) {
				temp = temp + 100;
			}else {
				temp = temp + 1;
			}
		}else if((secondi >= 300)) {
			if (naveColpita) {
				temp = temp + 50;
			}else {
				temp = temp + 0;
			}
		}
		punteggio = punteggio + temp;		
	}
	
	
	
	public int getNumeroCelleNaviPlayer() {
		int temp = 0;
		for (int i = 0; i < navi.size(); i++) {
			temp = temp + navi.get(i).getDimensioneNave();
		}
		return temp;
	}
	
	
	
}
