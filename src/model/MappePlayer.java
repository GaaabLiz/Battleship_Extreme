package model;

import java.awt.Point;
import java.util.ArrayList;

/**
 * <strong>Classe che rappresenta le 2 mappe di ogni Player</strong>
 * 
 * In questa classe sono contenute tutte le informazioni sulla mappa navi e sulla mappa tentativi di ogni Player
 * @author Gabriele Lizzos
 * @author Antonio Scalogna
 *
 */
public class MappePlayer {
	
	private int dimensioneMappa;						/* La dimensione della mappa di gioco */
	private Boolean spazioNavi[][];						/* Indica i campi della mappa occupati da delle navi */
	private int spazioNaviId[][];						/* Indica gli id delle navi piazzate nella mappa */
	private Boolean TentativiDiAffondEffettuati[][];	/* Indica le celle che il player ha tentato di colpire */
	private int celleOccupateDaNavi;					/* Indica il numero di cella occupate della mappa */
	private Boolean spazioNaviAvversarioAffondate[][];	/* Indica lo spazio navi che questo player ha affondato dell'avversario */
	
	
	/**
	 * Costruttore della classe 
	 * @param dimensioneMappa La dimensione della mappa settata nel model
	 */
	public MappePlayer(int dimensioneMappa) {
		this.dimensioneMappa = dimensioneMappa;
	}
	
	
	/**
	 * Aggiunge nello spazioNavi in maniera definitva una nave.
	 * 
	 * @param n La nave che si tenta di aggiungere nella mappa.
	 */
	public void piazzaNaveNellaMappa(Nave n) {
		int cordX = (int) n.getCoordinatePrua().getX();
		int cordY = (int) n.getCoordinatePrua().getY();
		int dim = n.getDimensioneNave();
		PuntoCardinale c = n.getOrientamento();
//		System.out.println("[DEBUG]: (piazzaNaveNellaMappa) La coordinata X della nave passsata è: " + cordX);
//		System.out.println("[DEBUG]: (piazzaNaveNellaMappa) La coordinata Y della nave passsata è: " + cordY);
//		System.out.println("[DEBUG]: (piazzaNaveNellaMappa) La dimensione della nave passsata è: " + dim);
//		System.out.println("[DEBUG]: (piazzaNaveNellaMappa) l'orientamento della nave passsata è: " + c);

		for (int i = 0; i < spazioNavi.length; i++) {
			for (int j = 0; j < spazioNavi.length; j++) {
				if ((i == cordX) && (j == cordY)) {
					int x_pezzoNaveDaIns = i;
					int y_pezzoNaveDaIns = j;
//					System.out.println("[DEBUG]: x pezzo da inserire = " + i);
//					System.out.println("[DEBUG]: y pezzo da inserire = " + j);
					for (int k = 0; k < dim; k++) {
//						System.out.println("[DEBUG]: Lo spazio navi X=" + x_pezzoNaveDaIns + " Y=" + y_pezzoNaveDaIns + " è stata settato true.");
						spazioNavi[x_pezzoNaveDaIns][y_pezzoNaveDaIns] = true;
						spazioNaviId[x_pezzoNaveDaIns][y_pezzoNaveDaIns] = n.getId();
						celleOccupateDaNavi++;
						switch (c) {
						case NORD:
							x_pezzoNaveDaIns--;
							break;
						case SUD:
							x_pezzoNaveDaIns++;;
							break;
						case EST:
							y_pezzoNaveDaIns++;;
							break;
						case OVEST:
							y_pezzoNaveDaIns--;
							break;
						default:
							break;
						}
						
					}
				}
			}
		}
		
	}
	
	
	
	/**
	 * Metodo usato solamente per scopi di debug. Usato per testare il settaggio TRUE/FALSE dello spazio navi 
	 */
	public void stampaSpazioNavi() {
		System.out.println();
		for (int i = 0; i < spazioNavi.length; i++) {
			for (int j = 0; j < spazioNavi.length; j++) {
				if (spazioNavi[i][j] == true) {
					System.out.print('▒');
				}else {
					System.out.print('|');
				}
				
			}
			System.out.println();
		}
	}
	
	
	/**
	 * Ogni volta che un player cerca di colpire la una nave dell'avversario, il suo tentativo (che abbia preso una nave o no)
	 * viene memorizzato nell'array dei tentativi
	 * @param p La coordinata corrispondente al tentativo effettauto
	 */
	public void aggiungiTentativoDiAffondamento(Point p) {
		for (int i = 0; i < TentativiDiAffondEffettuati.length; i++) {
			for (int j = 0; j < TentativiDiAffondEffettuati.length; j++) {
				if ((i == p.x) && (j == p.y)) {
					TentativiDiAffondEffettuati[i][j] = true;
				}
			}
		}
	}
	
	
	
	/**
	 * Metodo che aggiunge l'affondamento di una nave del giocatore che colpisce. Questo metodo inoltre setta 
	 * il numero di celle colpite dell'oggetto nave del player che è stato attaccato.
	 * 
	 * @param p Le coordinate della cella della nave che è stata colpita
	 * @param mappaAvversario La MappaNavi del giocatore che è stato colpito
	 * @param playerNaviColpite Il player che contiene tutte le navi che posso essere colpite (contiene anche quella appena colpita)
	 */
	public void aggiungiAffondamento(Point p, MappePlayer mappaAvversario, Player playerNaviColpite) {
		int idCorrenteNaveAffondata = 0;
		ArrayList<Nave> arrayNaviPlayerDaColpire = playerNaviColpite.getNaviPlayer();
		int [][]spazioNaviIdAvversario = mappaAvversario.getSpazioNaviId();
		
		for (int i = 0; i < spazioNaviAvversarioAffondate.length; i++) {
			for (int j = 0; j < spazioNaviAvversarioAffondate.length; j++) {
				if ((i == p.x) && (j == p.y)) {
					spazioNaviAvversarioAffondate[i][j] = true;
					
					idCorrenteNaveAffondata = spazioNaviIdAvversario[i][j];
					
					for (int j2 = 0; j2 < arrayNaviPlayerDaColpire.size(); j2++) {
						Nave NaveCorrente = arrayNaviPlayerDaColpire.get(j2);
						if (NaveCorrente.getId() == idCorrenteNaveAffondata) {
							NaveCorrente.setCelleColpite(NaveCorrente.getCelleColpite() + 1);
						}
					}
				}
			}
		}
	}
	
	
	
	/**
	 * Metodo che controlla se tutte le celle di una nave dato l'id sono state affondate dall'avversario
	 * @param p Le coordinate da controllare
	 * @param m la mappa che contiene gli ID della navi
	 * @param playerNaviColpite il giocatore che contiene tutte le navi che bisogna controllare se sono state colpite
	 * @return TRUE se la nave contenuta vicino alle coordinate è totalmente affondata
	 * @return FALSE se la nave non è totalmente affondata
	 */
	public Boolean controlloNaveColpitaAffondata(Point p, MappePlayer m, Player playerNaviColpite) {
		ArrayList<Nave> arrayNaviPlayerDaColpire = playerNaviColpite.getNaviPlayer();
		int[][] spazioNaviId = null;
		int idCorrenteNaveAffondata = 0;
		
		for (int i = 0; i < m.getSpazioNaviAvversarioAffondate().length; i++) {
			for (int j = 0; j < m.getSpazioNaviAvversarioAffondate().length; j++) {
				if ((i == p.x) && (j == p.y)) {
					spazioNaviId = m.getSpazioNaviId();
					idCorrenteNaveAffondata = spazioNaviId[i][j];
					for (int j2 = 0; j2 < arrayNaviPlayerDaColpire.size(); j2++) {
						Nave NaveCorrente = arrayNaviPlayerDaColpire.get(j2);
						if (NaveCorrente.getId() == idCorrenteNaveAffondata) {
							if (NaveCorrente.getCelleColpite() == NaveCorrente.getDimensioneNave()) {
								return true;
							}else {
								return false;
							}
						}
					}
				}
			}
		}
		return null;
	}
	
	
	
	/**
	 * Inzializza lo spazio navi del player, settando tutte le celle a FALSE
	 */
	public void setSpazioNavi() {
		this.spazioNavi = new Boolean [dimensioneMappa][dimensioneMappa];
		this.spazioNaviId = new int [dimensioneMappa][dimensioneMappa];
		this.TentativiDiAffondEffettuati = new Boolean[dimensioneMappa][dimensioneMappa];
		this.spazioNaviAvversarioAffondate = new Boolean[dimensioneMappa][dimensioneMappa];
		for (int i = 0; i < this.spazioNavi.length ; i++) {
			for (int k = 0; k < this.spazioNavi.length; k++) {
				this.spazioNavi[i][k] = false;
				this.TentativiDiAffondEffettuati[i][k] = false;
				this.spazioNaviAvversarioAffondate[i][k] = false;
				this.spazioNaviId[i][k] = 0;
			}	
		}
	}
		

	/**
	 * @return Lo spazio navi del player
	 */
	public Boolean[][] getSpazioNavi() {
		return spazioNavi;
	}
	
	
	
	/**
	 * @return the spazioNaviId
	 */
	public int[][] getSpazioNaviId() {
		return spazioNaviId;
	}
	
	


	/**
	 * @return the tentativiDiAffondEffettuati
	 */
	public Boolean[][] getTentativiDiAffondEffettuati() {
		return TentativiDiAffondEffettuati;
	}
	
	
	


	/**
	 * @return the spazioNaviAvversarioAffondate
	 */
	public Boolean[][] getSpazioNaviAvversarioAffondate() {
		return spazioNaviAvversarioAffondate;
	}
	
	
	


	/**
	 * @return the celleOccupateDaNavi
	 */
	public int getCelleOccupateDaNavi() {
		return celleOccupateDaNavi;
	}


	/**
	 * Restituisce il tipo della cella nelle coordinate passate come argomento a questo metodo.
	 * @param p Le coordinate sottoforma di punto
	 * @return LIBERA se la cella è libera. Ovvero che non ci sono navi in questa cella.
	 * @return FUORIMAPPA se la cella non è contenuta nella mappa.
	 */
	protected Cella getTipoCella(Point p) {
		if (p != null) {
			if ( ((p.x < 0) || (p.x > (dimensioneMappa-1))) || ((p.y < 0) || (p.y > (dimensioneMappa-1))) ) {
				return Cella.FUORIMAPPA;
			}
			
			if (spazioNavi[p.x][p.y] == false) {
				return Cella.LIBERA;
			}else if (spazioNavi[p.x][p.y] == true) {
				return Cella.OCCUPATA;
			}
		}
		return null;	
	}
	
	
	
	/**
	 * Restituisce il tipo della cella nelle coordinate passate come argomento a questo metodo.
	 * @param X la coordinata X
	 * @param Y la coordinata Y
	 * @return LIBERA se la cella è libera. Ovvero che non ci sono navi in questa cella.
	 * @return FUORIMAPPA se la cella non è contenuta nella mappa.
	 */
	protected Cella getTipoCella(int x, int y) {
		if ( ((x < 0) || (x > (dimensioneMappa-1))) || ((y < 0) || (y > (dimensioneMappa-1))) ) {
			return Cella.FUORIMAPPA;
		}
		
		if (spazioNavi[x][y] == false) {
			return Cella.LIBERA;
		}else if (spazioNavi[x][y] == true) {
			return Cella.OCCUPATA;
		}
		
		return null;	
	}

	
	
	
	
	
}
