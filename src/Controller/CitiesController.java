/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package Controller;

import Model.City;
import Model.Singleton;
import Network.GeocodeParser;
import Network.HttpRequest;
import Network.WSGoogleMaps;
import View.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

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

                int index = view.getSelectedCityResultIndex();
                City selected = currentCities.get(index);
                Singleton singleton = Singleton.getInstance();
                singleton.addCity(selected);

                view.clearCityText();
                view.clearCityResults();
                
                break;

            case SEARCH_CITY:

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


                break;
        }
    }


}
