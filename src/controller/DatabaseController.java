package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import model.BattleshipExtremeModel;
import model.Partita;

public class DatabaseController {
	
	private final String CONNECTION_URL = "mysql://q2ntj5yaru5q48fn:vl5rjwb3xh7okw42@gi6kn64hu98hy0b6.chr7pe7iynqr.eu-west-1.rds.amazonaws.com:3306/fmtakid77se46q71";
	private final String HOST = "gi6kn64hu98hy0b6.chr7pe7iynqr.eu-west-1.rds.amazonaws.com";
	private final String USERNAME = "q2ntj5yaru5q48fn";
	private final String PASSWORD = "vl5rjwb3xh7okw42";
	private final int PORT = 3306;
	private final String DATABASE_NAME = "fmtakid77se46q71";
	
	private Connection connessione;
	
	
	public Connection getConnessione() {
		if (connessione == null) {
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setDatabaseName(DATABASE_NAME);
			dataSource.setPort(PORT);
			dataSource.setServerName(HOST);
			dataSource.setUser(USERNAME);
			dataSource.setPassword(PASSWORD);
			
			try {
				connessione = dataSource.getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return connessione;
	}
	
	
	public ArrayList<Partita> getElencoPartiteFromDb(BattleshipExtremeModel model) throws SQLException {
		
		ArrayList<Partita> elencoPartite = new ArrayList<Partita>();
		Partita p;
		
		String sql = "SELECT * FROM battleship_games";
				
		PreparedStatement ps = getConnessione().prepareStatement(sql);		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			p = new Partita();
			p.setId(rs.getInt(1));
			p.setNomeGiocatore(rs.getString(2));
			p.setDataPartita(rs.getString(3));
			p.setPunteggioGiocatore(rs.getInt(4));
			p.setPunteggioCpu(rs.getInt(5));
			p.setDurataPartita(rs.getString(6));
			p.setDimMappa(rs.getInt(7));
			p.setNumNavi(rs.getInt(8));
			elencoPartite.add(p);
		}
		
		return elencoPartite;
	}
	
	
	public void addPartitaToDb(Partita p) throws SQLException {
		String tempNome = p.getNomeGiocatore();
		String tempDataPartita = p.getDataPartita();
		int tempPunteggiGiocatore = p.getPunteggioGiocatore();
		int tempPunteggioCpu = p.getPunteggioCpu();
		String tempDurataPartita = p.getDurataPartita();
		int tempDimMappa = p.getDimMappa();
		int tempNumNavi = p.getNumNavi();
		
		String sql = "INSERT INTO battleship_games (nome_giocatore, data_partita, punteggio_giocatore, punteggio_cpu, durata_partita, dim_mappa, num_navi) "
				+ "VALUES ('"+tempNome+ "', '" + tempDataPartita + "', " + tempPunteggiGiocatore + ", " + tempPunteggioCpu + ", '" + tempDurataPartita + "', " + tempDimMappa + ", " + tempNumNavi + ")";
		//System.out.println(sql);
		PreparedStatement ps = getConnessione().prepareStatement(sql);
		ps.executeUpdate();
	}

}
