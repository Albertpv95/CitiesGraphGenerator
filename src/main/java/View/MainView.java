package View;

import Controller.Actions;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author Albertpv
 * @version 1.0
 */
public class MainView extends JFrame {

    private static final int DIM_HEIGHT = 350;
    private static final int DIM_WIDTH  = 600;

    private JTabbedPane jtPane;

    private JTextField jtCity;
    private DefaultListModel<String> citiesResult;
    private JList<String> jlResults;

    private JButton jbAddCity;
    private JButton jbSearch;

    private DefaultListModel<String> cities;
    private JList<String> jlCities;
    private JList<String> jlCities2;
    private JButton jbAddRoute;

    /* ****** MENU ****** */
    private JMenuItem jmiImport;
    private JMenuItem jmiSave;
    private JMenuItem jmiSaveAs;
    private JMenuItem jmAbout;

    private JMenuItem jmKey;


    public MainView() {

        this.setJMenuBar(createMenuBar());

        this.add(createTabs());

        this.setSize(DIM_WIDTH, DIM_HEIGHT);
        this.setVisible(true);
        this.repaint();
        this.setLocationRelativeTo(null);
        this.setTitle("Cities Graph Generator");
    }

    public boolean isConnectionsPanelVisible() {

        return jtPane.getSelectedIndex() == 1;
    }

    public void addCityPanelListeners(ActionListener listener) {

        this.jbAddCity.addActionListener(listener);
        this.jbAddCity.setActionCommand(Actions.ADD_CITY.toString());

        this.jbSearch.addActionListener(listener);
        this.jbSearch.setActionCommand(Actions.SEARCH_CITY.toString());
    }

    public void addConnectionsPanelListeners(ActionListener listener, ChangeListener changeListener) {

        this.jtPane.addChangeListener(changeListener);

        this.jbAddRoute.addActionListener(listener);
        this.jbAddRoute.setActionCommand(Actions.ADD_ROUTE.toString());
    }

    public void addMenuListeners(ActionListener listener) {

        this.jmiImport.addActionListener(listener);
        this.jmiImport.setActionCommand(Actions.OPEN_FILE.toString());

        this.jmiSave.addActionListener(listener);
        this.jmiSave.setActionCommand(Actions.SAVE_FILE.toString());

        this.jmiSaveAs.addActionListener(listener);
        this.jmiSaveAs.setActionCommand(Actions.SAVE_AS_FILE.toString());

        this.jmAbout.addActionListener(listener);
        this.jmAbout.setActionCommand(Actions.ABOUT.toString());

    }

    public void addSettingsListener(ActionListener listener) {

        this.jmKey.addActionListener(listener);
        this.jmKey.setActionCommand(Actions.SET_KEY.toString());
    }

    public String getCityText() {

        return jtCity.getText();
    }

    public void clearCityText() {

        this.jtCity.setText("");
    }

    public void fillCityResults(List<String> results) {

        for (String r : results)
            this.citiesResult.addElement(r);
    }

    public void clearCityResults() {

        this.citiesResult.clear();
    }

    public int getSelectedCityResultIndex() {

        return jlResults.getSelectedIndex();
    }

    public void clearRoutesPanelCities() {

        cities.clear();
    }

    public void fillRoutesPanelCities(List<String> addresses) {

        for (String s : addresses)
            cities.addElement(s);
    }

    public int getRouteFirstSelectedCityIndex() {

        return jlCities.getSelectedIndex();
    }

    public int[] getRouteSecondSelectedCityIndices() {

        return jlCities2.getSelectedIndices();
    }

    private JPanel createAddCityPanel() {

        JPanel jpAll = new JPanel();
        jpAll.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = c.gridy = 0;
        c.weightx = 1.0;

        this.jtCity = new JTextField();
        jpAll.add(jtCity, c);

        c.gridx = 1;
        c.weightx = 0.0;
        jbSearch = new JButton("Search city");
        jpAll.add(jbSearch, c);

        JLabel jl2 = new JLabel("Choose the right result");
        citiesResult = new DefaultListModel<>();
        jlResults = new JList<>(citiesResult);
        jlResults.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        c.gridx = 0;
        c.gridy = 1;

        JScrollPane jsp = new JScrollPane(jlResults);
        jpAll.add(jl2, c);

        c.gridy = 2;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        jpAll.add(jsp, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 0.0;

        JPanel jpButtons = new JPanel();
        jpButtons.setLayout(new BoxLayout(jpButtons, BoxLayout.Y_AXIS));

        jbAddCity = new JButton("Add city");
        c.gridx = 1;

        jpButtons.add(jbAddCity);
        jpAll.add(jpButtons, c);

        return jpAll;
    }

    private JPanel createAddRoutesPanel() {

        JPanel jpAll = new JPanel();
        jpAll.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.0;

        JLabel jlFirst = new JLabel("First city");
        jpAll.add(jlFirst, c);

        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 1;
        this.cities = new DefaultListModel<>();
        jlCities = new JList<>(cities);
        jlCities.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        JScrollPane jsp1 = new JScrollPane(jlCities);
        jpAll.add(jsp1, c);

        c.insets = new Insets(0, 5, 0, 0);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 0;
        c.gridx = 1;
        c.weighty = 0.0;
        JLabel jlSecond = new JLabel("Choose multiple cities to connect the first one");
        jpAll.add(jlSecond, c);

        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 1;
        jlCities2 = new JList<>(cities);
        jlCities2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane jsp2 = new JScrollPane(jlCities2);
        jpAll.add(jsp2, c);

        c.insets = new Insets(0, 0, 0, 0);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 2;
        c.gridx = 0;
        c.gridwidth = 2;
        c.weightx = 0.0;
        this.jbAddRoute = new JButton("Generate routes");
        jpAll.add(jbAddRoute, c);

        return jpAll;
    }

    private JMenuBar createMenuBar() {

        JMenuBar jmBar = new JMenuBar();

        JMenu jmSettings    = new JMenu("File");
        jmiImport   = new JMenuItem("Open");
        jmiSave     = new JMenuItem("Save");
        jmiSaveAs   = new JMenuItem("Save as...");
        jmAbout     = new JMenuItem("About");

        jmSettings.add(jmiImport);
        jmSettings.addSeparator();
        jmSettings.add(jmiSave);
        jmSettings.add(jmiSaveAs);
        jmSettings.addSeparator();
        jmSettings.add(jmAbout);

        jmBar.add(jmSettings);

        JMenu jmProgramSettings    = new JMenu("Settings");

        jmKey = new JMenuItem("Set API Key");
        jmProgramSettings.add(jmKey);

        jmBar.add(jmProgramSettings);


        return jmBar;
    }

    private JTabbedPane createTabs() {

        jtPane = new JTabbedPane();

        JPanel jp1 = createAddCityPanel();
        jtPane.addTab("Add city", jp1);

        JPanel jp2 = createAddRoutesPanel();
        jtPane.addTab("Add route", jp2);

        return jtPane;
    }

}
