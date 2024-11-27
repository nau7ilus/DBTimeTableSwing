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
		
		JLabel lblNewLabel_1 = new JLabel("Bahnhof auswählen und Informationen zu Mobilität, Ausstattung und Services enthalten");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(37, 110, 220, 36);
		add(lblNewLabel_1);
		
		txtSucheNachEinem = new JTextField();
		txtSucheNachEinem.setToolTipText("Suche nach einem Bahnhof");
		txtSucheNachEinem.setBounds(38, 146, 198, 21);
		add(txtSucheNachEinem);
		txtSucheNachEinem.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Wie zum Beispiel Berlin Hauptbahnhof, Düsseldorf Hbf oder Hamburg Hbf. Schleife ist auch ein schöner Bahnhof.");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(38, 178, 272, 21);
		add(lblNewLabel_2);

	}
}
