package de.humboldtgym.dbswing;

import de.humboldtgym.dbswing.Controller.SearchController;
import de.humboldtgym.dbswing.Controller.TimetableController;
import de.humboldtgym.dbswing.Model.Station;
import de.humboldtgym.dbswing.Model.StationModel;
import de.humboldtgym.dbswing.Model.TimetableModel;
import de.humboldtgym.dbswing.View.SearchView;
import de.humboldtgym.dbswing.View.TimetableView;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MainWindow implements WindowChangeListener {

    private TimetableView timetableView;
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
        SearchController searchController = new SearchController(stationModel, searchView);
        searchController.setWindowChangeListener(this);

    }

    public void openTimetableWindow(Station station) {
        searchView.setVisible(false);
        timetableView = new TimetableView(station);
        TimetableModel timetableModel = new TimetableModel(station);
        new TimetableController(timetableModel, timetableView);
        timetableModel.loadTrips();
        timetableView.setVisible(true);
    }

    private void usingCustomFonts() {
        GraphicsEnvironment GE = GraphicsEnvironment.getLocalGraphicsEnvironment();
        List<String> AVAILABLE_FONT_FAMILY_NAMES = Arrays.asList(GE.getAvailableFontFamilyNames());
        try {
            List<String> fontPaths = Arrays.asList(
                    "/fonts/D-DIN.otf",
                    "/fonts/D-DIN-Bold.otf",
                    "/fonts/D-DIN-Italic.otf"
            );

            for (String fontPath : fontPaths) {
                try (InputStream fontStream = getClass().getResourceAsStream(fontPath)) {
                    if (fontStream != null) {
                        Font FONT = Font.createFont(Font.TRUETYPE_FONT, fontStream);
                        if (!AVAILABLE_FONT_FAMILY_NAMES.contains(FONT.getFontName())) {
                            GE.registerFont(FONT);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Font file not found: " + fontPath);
                    }
                }
            }
        } catch (FontFormatException | IOException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
    }


    @Override
    public void onWindowChangeRequested(Station station) {
        System.out.println("Changing to window: " + station);
        openTimetableWindow(station);
    }
}
