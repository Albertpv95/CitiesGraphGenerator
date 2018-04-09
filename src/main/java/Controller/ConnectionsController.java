package Controller;

import Model.City;
import Model.Singleton;
import Network.DistanceParser;
import Network.HttpRequest;
import Network.WSGoogleMaps;
import View.MainView;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Albertpv
 * @since 1.8+
 * @version 1.0
 */
public class ConnectionsController implements ActionListener, ChangeListener {

    private MainView view;
    private Singleton singleton;

    public ConnectionsController(MainView view) {

        this.view = view;
        this.singleton = Singleton.getInstance();
    }

    private void fillDestinations(List<City> cities, int []indices, double []lat, double []lng) {

        for (int i = 0; i < indices.length; i++) {

            City c = cities.get(indices[i]);
            lat[i] = c.getLatitude();
            lng[i] = c.getLongitude();
        }
    }

    private void processSuccessResult(List<String> routesErrors) {
        
        if (routesErrors.isEmpty()) {

            JOptionPane.showMessageDialog(
                    null,
                    "Routes calculated successfully!"
            );
        }
        else {

            StringBuilder sb = new StringBuilder();
            for (String s : routesErrors) {

                sb.append(s);
                sb.append("\n");
            }

            JOptionPane.showMessageDialog(
                    null,
                    "The following routes could not be calculated: \n" + sb.toString(),
                    routesErrors.size() + " could not be calculated",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void distanceProcess(City first, double[] lat, double[] lng, List<City> cities, int []indices) {

        WSGoogleMaps wsGoogle = new WSGoogleMaps();
        wsGoogle.distance(
                first.getLatitude(),
                first.getLongitude(),
                lat,
                lng,
                new HttpRequest.HttpReply() {
                    @Override
                    public void onSuccess(String data) {

                        List<String> routesErrors = DistanceParser.parseDistances(
                                data,
                                first.getName(),
                                cities,
                                indices
                        );

                        processSuccessResult(routesErrors);
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

    @Override
    public void actionPerformed(ActionEvent e) {

        Actions action = Actions.valueOf(e.getActionCommand());

        switch (action) {

            case ADD_ROUTE:

                List<City> cities = singleton.getCities();
                City first = cities.get(view.getRouteFirstSelectedCityIndex());

                int []indices = view.getRouteSecondSelectedCityIndices();
                double []lat = new double[indices.length];
                double []lng = new double[indices.length];
                fillDestinations(cities, indices, lat, lng);

                distanceProcess(first, lat, lng, cities, indices);

                break;
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {

        if (view.isConnectionsPanelVisible()) {

            List<String> namesList = singleton.getCities().stream()
                    .map(City::getAddress)
                    .collect(Collectors.toList());

            view.clearRoutesPanelCities();
            view.fillRoutesPanelCities(namesList);
        }
    }
}
