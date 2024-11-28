package de.humboldtgym.dbswing;

import java.awt.EventQueue;

import javax.swing.JFrame;

import de.humboldtgym.dbswing.View.StationSearch;

public class MainWindow {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainWindow() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		StationSearch stationSearchPanel = new StationSearch();
		
		frame.add(stationSearchPanel);
		
	}

}
