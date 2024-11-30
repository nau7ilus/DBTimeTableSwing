package de.humboldtgym.dbswing;

import de.humboldtgym.dbswing.Controller.SearchController;
import de.humboldtgym.dbswing.Model.StationModel;
import de.humboldtgym.dbswing.View.SearchView;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MainWindow {

    private SearchView searchView;

    public MainWindow() {
        initialize();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MainWindow window = new MainWindow();
                window.searchView.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void initialize() {
        this.usingCustomFonts();
        searchView = new SearchView();
        StationModel stationModel = new StationModel();
        new SearchController(stationModel, searchView);

    }

    private void usingCustomFonts() {
        GraphicsEnvironment GE = GraphicsEnvironment.getLocalGraphicsEnvironment();
        List<String> AVAILABLE_FONT_FAMILY_NAMES = Arrays.asList(GE.getAvailableFontFamilyNames());
        try {
            List<File> LIST = Arrays.asList(new File("resources/fonts/D-DIN.otf"), new File("resources/fonts/D-DIN-Bold.otf"), new File("resources/fonts/D-DIN-Italic.otf"));
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
