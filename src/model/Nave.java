package model;

import java.awt.Point;

/**
 * <strong>Questa classe rappresenta la singola nave all'interno del gioco.</strong>
 * 
 * @author Gabriele Lizzos
 * @author Antonio Scalogna
 */
public class Nave {
	
	// # VARIABILI PRINCIPALI DELLA SINGOLA NAVE #-----------------------------------------------------------
	private int id;							/* Un codice identificativo assegnato a ogni nave */
	private TipoNave tipoNave;				/* Indice il tipo di nave. */
	private PuntoCardinale orientamento;	/* Orientamento della nave rispetto alla prua */
	private Point coordinatePrua;			/* Coordinate della prua */
	private int dimensioneNave;				/* Indica quante celle occupa la nave (in base al tipo) */
	private int celleColpite;			/* Indica quali sono le celle della nave colpite dall'avversario */
	
	
	// # COSTANTI PUBBLICHE  #------------------------------------------------------------------------------
	public final char NORD = 'n';							/* carattere per indicare il rispettivo punto card */
	public final char SUD = 's';							/* carattere per indicare il rispettivo punto card */
	public final char OVEST = 'o';							/* carattere per indicare il rispettivo punto card */
	public final char EST = 'e';							/* carattere per indicare il rispettivo punto card */
	public final int DIMENSIONE_PATTUGLIATORE = 1;			/* dimensione della rispettiva nave */
	public final int DIMENSIONE_CACCIATORPEDINIERI = 2;		/* dimensione della rispettiva nave */
	public final int DIMENSIONE_SOTTOMARINI = 3;			/* dimensione della rispettiva nave */
	public final int DIMENSIONE_INCROCIATORI = 4;			/* dimensione della rispettiva nave */
	public final int DIMENSIONE_PORTAEREI = 5;				/* dimensione della rispettiva nave */
	public final int DIMENSIONE_CORAZZATA = 6;				/* dimensione della rispettiva nave */
	
	
	/**
	 * Costruttore del singolo oggetto di Tipo di NAVE
	 * @param id l'id della nave
	 * @param dimensioneNave Il numero di celle che occupa la nave (verra' poi definito il tipo in base a questo)
	 * @param orientamento Verso che direzioni si trova la poppa della nave
	 * @param coordinatePruaNave Le coordinate della prua della nave
	 */
	public Nave(int id, int dimensioneNave, PuntoCardinale orientamento, Point coordinatePruaNave) {
		
		// Settaggio delle variabili
		this.orientamento = orientamento;
		this.coordinatePrua = new Point(coordinatePruaNave.x, coordinatePruaNave.y);
		this.dimensioneNave = dimensioneNave;
		this.id = id;
		switch (dimensioneNave) {
		case 2:
			this.tipoNave = TipoNave.CACCIATORPEDINIERE;
			break;
		case 3:
			this.tipoNave = TipoNave.SOTTOMARINO;
			break;
		case 4:
			this.tipoNave = TipoNave.INCROCIATORE;
			break;
		case 5:
			this.tipoNave = TipoNave.PORTAEREI;
			break;
		default:
			break;
		}
	}



	/**
	 * Metodo che ritorna la tipologia nave corrispondente alla sua dimensione.
	 * 
	 * @return Il tipo di nave corrispondente di tipo Tiponave
	 */
	public String getTipologiaNave() {
		String temp = "";
		switch (tipoNave) {
		case CACCIATORPEDINIERE:
			temp = "un cacciatorpediniere";
			break;
		case SOTTOMARINO:
			temp = "un sottomarino";
			break;
		case INCROCIATORE:
			temp = "un incrociatore";
			break;
		case PORTAEREI:
			temp = "una portaerei";
			break;

		default:
			break;
		}
		
		return temp;
		
	}
	



	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @return the orientamento
	 */
	public PuntoCardinale getOrientamento() {
		return orientamento;
	}


	/**
	 * @return the coordinatePrua
	 */
	public Point getCoordinatePrua() {
		return coordinatePrua;
	}


	/**
	 * @return the dimensioneNave
	 */
	public int getDimensioneNave() {
		return dimensioneNave;
	}




	/**
	 * @return the celleColpite
	 */
	public int getCelleColpite() {
		return celleColpite;
	}




	/**
	 * @param celleColpite the celleColpite to set
	 */
	public void setCelleColpite(int celleColpite) {
		this.celleColpite = celleColpite;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}



	/**
	 * @param orientamento the orientamento to set
	 */
	public void setOrientamento(PuntoCardinale orientamento) {
		this.orientamento = orientamento;
	}



	/**
	 * @param coordinatePrua the coordinatePrua to set
	 */
	public void setCoordinatePrua(Point coordinatePrua) {
		this.coordinatePrua = coordinatePrua;
	}



	/**
	 * @param dimensioneNave the dimensioneNave to set
	 */
	public void setDimensioneNave(int dimensioneNave) {
		this.dimensioneNave = dimensioneNave;
	}
	
	
	
}
