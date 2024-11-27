package de.humboldtgym.dbswing.View;

import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import static de.humboldtgym.dbswing.Constants.DB_BLUE_COLOR;

public class StationSearch extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtSucheNachEinem;
	
	public StationSearch() {
		setBackground(DB_BLUE_COLOR);
		setLayout(null);
		
		JLabel lblHeadline = new JLabel("Bahnhofstafel");
		lblHeadline.setForeground(Color.WHITE);
		lblHeadline.setBounds(38, 78, 81, 21);
		add(lblHeadline);
		
		JLabel lblActionDescription = new JLabel("Bahnhof auswählen und Informationen zu Mobilität, Ausstattung und Services enthalten");
		lblActionDescription.setHorizontalAlignment(SwingConstants.LEFT);
		lblActionDescription.setVerticalAlignment(SwingConstants.TOP);
		lblActionDescription.setForeground(Color.WHITE);
		lblActionDescription.setBounds(37, 110, 220, 36);
		add(lblActionDescription);
		
		txtSearch = new JTextField();
		txtSearch.setToolTipText("Suche nach einem Bahnhof");
		txtSearch.setBounds(38, 146, 198, 21);
		add(txtSearch);
		txtSearch.setColumns(20);
		
		JLabel lblSearchHint = new JLabel("Wie zum Beispiel Berlin Hauptbahnhof, Düsseldorf Hbf oder Hamburg Hbf. Schleife ist auch ein schöner Bahnhof.");
		lblSearchHint.setHorizontalAlignment(SwingConstants.LEFT);
		lblSearchHint.setVerticalAlignment(SwingConstants.TOP);
		lblSearchHint.setForeground(Color.WHITE);
		lblSearchHint.setBounds(38, 178, 272, 21);
		add(lblSearchHint);

	}
}
