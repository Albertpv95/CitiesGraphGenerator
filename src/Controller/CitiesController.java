package Controller;

import Model.City;
import Model.Singleton;
import Network.GeocodeParser;
import Network.HttpRequest;
import Network.WSGoogleMaps;
import View.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>This class manages the view panel that ables the user to
 * add cities to the system. Any instance from this class can search
 * a city on Google Maps and add the city to the list of current ones.</p>
 *
 * @see WSGoogleMaps
 *
 * @since 1.0
 * @version 1.0
 */
public class CitiesController implements ActionListener {

    private MainView view;

    private WSGoogleMaps wsGoogle;

    private List<City> currentCities;

    public CitiesController(MainView view) {

        this.view = view;

        this.wsGoogle = new WSGoogleMaps();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Actions action = Actions.valueOf(e.getActionCommand());

        switch (action) {

            case ADD_CITY:

                addCity();

                break;

            case SEARCH_CITY:

                searchCity();

                break;
        }
    }

    private void searchCity() {

        String city = view.getCityText();

        wsGoogle.geolocate(city, new HttpRequest.HttpReply() {
            @Override
            public void onSuccess(String data) {

                currentCities = GeocodeParser.getCityData(data);
                for (City c : currentCities)
                    System.out.println(c.toString());

                List<String> namesList = currentCities.stream()
                        .map(City::getAddress)
                        .collect(Collectors.toList());

                view.fillCityResults(namesList);

            }

            @Override
            public void onError(String message) {

                JOptionPane.showMessageDialog(
                        null,
                        message,
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }

    private void addCity() {

        int index = view.getSelectedCityResultIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(view,
                    "Select a city before trying to add one",
                    "Error adding the city",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        else {
            City selected = currentCities.get(index);
            Singleton singleton = Singleton.getInstance();
            singleton.addCity(selected);

            view.clearCityText();
            view.clearCityResults();
        }
    }
}
