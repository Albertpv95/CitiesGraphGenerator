/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package Network;

import java.util.HashMap;

public class WSGoogleMaps {

    private static final String WS_ADDRESS      = "https://maps.googleapis.com/maps/api/";
    private static final String API_GEO         = "geocode/json?";
    private static final String API_DISTANCE    = "distancematrix/json?";

    private static final String API_KEY         = "AIzaSyBEQR1OfnE7BreWVxWCrP6lgZcaE3U6fuQ";

    private HttpRequest request;

    public WSGoogleMaps() {

        this.request = new HttpRequest();
    }

    public void geolocate(String address, HttpRequest.HttpReply reply) {

        HashMap<String, String> getData = prepareParameters(
                new String[]{"address", "key"},
                new String[]{address, API_KEY}
        );

        request.get(WS_ADDRESS + API_GEO, getData, reply);
    }

    public void distance(double srcLatitude, double srcLongitude, double destLatitude[], double destLongitude[], HttpRequest.HttpReply reply) {

        assert destLatitude.length == destLongitude.length;

        StringBuilder destinations = new StringBuilder();

        for (int i = 0; i < destLatitude.length; i++) {

            destinations.append(destLatitude[i]);
            destinations.append(",");
            destinations.append(destLongitude[i]);
            if (i < destLatitude.length - 1) destinations.append("|");
        }

        HashMap<String, String> getData = prepareParameters(
                new String[]{"origins", "destinations", "key"},
                new String[]{srcLatitude + "," + srcLongitude, destinations.toString(), API_KEY}
        );

        request.get(WS_ADDRESS + API_DISTANCE, getData, reply);
    }

    private HashMap<String, String> prepareParameters(String []keys, String []values) {

        HashMap<String, String> postParameters = new HashMap<>();


        for (int i = 0; i < keys.length; i++)
            postParameters.put(keys[i], values[i].replaceAll("\\s+", "+"));

        return postParameters;
    }
}
