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
import java.util.Arrays;
import java.util.List;

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

    @Override
    public void onWindowChangeRequested(Station station) {
        System.out.println("Changing to window: " + station);
        openTimetableWindow(station);
    }
}
