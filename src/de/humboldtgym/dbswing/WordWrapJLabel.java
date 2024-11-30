package de.humboldtgym.dbswing;

import javax.swing.*;

public class WordWrapJLabel extends JLabel {

    public final static String HTML_WORD_WRAP = "<html><body style=\"text-align: justify;  text-justify: inter-word;\">%s</body></html>";

    private String notFormattedText = null;

    public WordWrapJLabel(String text) {
        super(getHTMLString(text));
        this.notFormattedText = text;
    }

    private static String getHTMLString(String text) {
        return String.format(HTML_WORD_WRAP, text);
    }

    @Override
    public void setText(String text) {
        this.notFormattedText = text;
        super.setText(getHTMLString(text));
    }

    public String getNotFormattedText() {
        return this.notFormattedText;
    }

}
