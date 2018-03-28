package View;

import Controller.Actions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class MainView extends JFrame {

    private static final int DIM_HEIGHT = 350;
    private static final int DIM_WIDTH  = 600;


    private JTextField jtCity;
    private DefaultListModel<String> citiesResult;
    private JButton jbAddCity;
    private JButton jbSearch;

    private DefaultListModel<String> cities;
    private JButton jbAddRoute;

    public MainView() {

        this.setJMenuBar(createMenuBar());

        this.add(createTabs());

        this.setSize(DIM_WIDTH, DIM_HEIGHT);
        this.setVisible(true);
        this.repaint();
        this.setLocationRelativeTo(null);
    }

    public void addCityPanelListeners(ActionListener listener) {

        this.jbAddCity.addActionListener(listener);
        this.jbAddCity.setActionCommand(Actions.ADD_CITY.toString());

        this.jbSearch.addActionListener(listener);
        this.jbSearch.setActionCommand(Actions.SEARCH_CITY.toString());
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
        JList<String> jlResults = new JList<>(citiesResult);
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
        JList<String> jlCities = new JList<>(cities);
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
        JList<String> jlCities2 = new JList<>(cities);
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
        JMenuItem jmiImport = new JMenuItem("Open");
        JMenuItem jmiSave   = new JMenuItem("Save");
        JMenuItem jmiSaveAs = new JMenuItem("Save as...");
        JMenuItem jmAbout = new JMenuItem("About");

        jmSettings.add(jmiImport);
        jmSettings.addSeparator();
        jmSettings.add(jmiSave);
        jmSettings.add(jmiSaveAs);
        jmSettings.addSeparator();
        jmSettings.add(jmAbout);

        jmBar.add(jmSettings);

        return jmBar;
    }

    private JTabbedPane createTabs() {

        JTabbedPane jtPane = new JTabbedPane();

        JPanel jp1 = createAddCityPanel();
        jtPane.addTab("Add city", jp1);

        JPanel jp2 = createAddRoutesPanel();
        jtPane.addTab("Add route", jp2);

        return jtPane;
    }

}
