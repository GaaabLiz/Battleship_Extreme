package controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.BattleshipExtremeModel;
import model.Log;

public class WindowMainListener implements WindowListener {
	
	private BattleshipExtremeModel model;
	
	public WindowMainListener(BattleshipExtremeModel model) {
		this.model = model;
		
	}
	
	
	@Override
	public void windowOpened(WindowEvent e) {
		

	}

	@Override
	public void windowClosing(WindowEvent e) {
		
		model.scriviFileDiLog();

	}

	@Override
	public void windowClosed(WindowEvent e) {
		
		model.scriviFileDiLog();
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
