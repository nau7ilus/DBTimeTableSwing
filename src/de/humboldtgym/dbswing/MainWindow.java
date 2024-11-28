package de.humboldtgym.dbswing;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
		this.usingCustomFonts();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		StationSearch stationSearchPanel = new StationSearch();
		
		frame.add(stationSearchPanel);
		
	}
	
	private void usingCustomFonts() {
	    GraphicsEnvironment GE = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    List<String> AVAILABLE_FONT_FAMILY_NAMES = Arrays.asList(GE.getAvailableFontFamilyNames());
	    try {
	        List<File> LIST = Arrays.asList(
	          new File("resources/fonts/D-DIN.otf"),
	          new File("resources/fonts/D-DIN-Bold.otf"),
	          new File("resources/fonts/D-DIN-Italic.otf")
	        );
	        for (File LIST_ITEM : LIST) {
	            if (LIST_ITEM.exists()) {
	                Font FONT = Font.createFont(Font.TRUETYPE_FONT, LIST_ITEM);
	                if (!AVAILABLE_FONT_FAMILY_NAMES.contains(FONT.getFontName())) {
	                   GE.registerFont(FONT);
	                }
	            }
	        }
	    } catch (FontFormatException | IOException exception) {
	        JOptionPane.showMessageDialog(null, exception.getMessage());
	    }
	}

}
