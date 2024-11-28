package de.humboldtgym.dbswing.View;

import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import de.humboldtgym.dbswing.WordWrapJLabel;

import static de.humboldtgym.dbswing.Constants.DB_BLUE_COLOR;

public class StationSearch extends JPanel {

	private JTextField txtSearch;
	
	public StationSearch() {
		setBackground(DB_BLUE_COLOR);
		setLayout(null);
		
		WordWrapJLabel lblHeadline = new WordWrapJLabel("Bahnhofstafel");
		lblHeadline.setForeground(Color.WHITE);
		lblHeadline.setBounds(41, 86, 115, 21);
		lblHeadline.setFont(new Font("D-DIN", Font.BOLD, 17));
		add(lblHeadline);
		
		WordWrapJLabel lblActionDescription = new WordWrapJLabel("Bahnhof auswählen und Informationen zu Mobilität, Ausstattung und Services enthalten");
		lblActionDescription.setFont(new Font("D-DIN Bold", Font.PLAIN, 15));
		lblActionDescription.setHorizontalAlignment(SwingConstants.LEFT);
		lblActionDescription.setVerticalAlignment(SwingConstants.TOP);
		lblActionDescription.setForeground(Color.WHITE);
		lblActionDescription.setBounds(41, 118, 326, 28);
		add(lblActionDescription);
		
		txtSearch = new JTextField();
		txtSearch.setToolTipText("Suche nach einem Bahnhof");
		txtSearch.setBounds(41, 157, 198, 21);
		add(txtSearch);
		txtSearch.setColumns(20);
		
		WordWrapJLabel lblSearchHint = new WordWrapJLabel("Wie zum Beispiel Berlin Hauptbahnhof, Düsseldorf Hbf oder Hamburg Hbf. Schleife ist auch ein schöner Bahnhof.");
		lblSearchHint.setHorizontalAlignment(SwingConstants.LEFT);
		lblSearchHint.setVerticalAlignment(SwingConstants.TOP);
		lblSearchHint.setForeground(Color.WHITE);
		lblSearchHint.setBounds(41, 189, 329, 28);
		add(lblSearchHint);

	}
}
