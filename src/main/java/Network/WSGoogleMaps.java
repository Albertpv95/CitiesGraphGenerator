package Network;

import java.util.HashMap;

/**
 * <p>This class manages the access to the Google Maps API via Web Service.
 * In this current version, it allows to obtain geolocation data given an address
 * as well as calculate distances from a (latitude, longitude) point to other ones.</p>
 *
 *
 * @author Albertpv
 * @since 1.8+
 * @version 1.0
 */
public class WSGoogleMaps {

    private static final String WS_ADDRESS      = "https://maps.googleapis.com/maps/api/";
    private static final String API_GEO         = "geocode/json?";
    private static final String API_DISTANCE    = "distancematrix/json?";

    /**
     * This constant must be modified with the KEY value got
     * at <a href="https://developers.google.com/maps/documentation/geocoding/get-api-key">Get API Key</a>.
     */
    private static final String API_KEY         = "AIzaSyBEQR1OfnE7BreWVxWCrP6lgZcaE3U6fuQ";

    private HttpRequest request;

    public WSGoogleMaps() {

        this.request = new HttpRequest();
    }

    /**
     * Calls Google Maps Geocode API to get geocoding data for a given address.
     *
     * @param address   The name of the site to search.
     * @param reply     The object that will manage the response.
     */
    public void geolocate(String address, HttpRequest.HttpReply reply) {

        HashMap<String, String> getData = prepareParameters(
                new String[]{"address", "key"},
                new String[]{address, API_KEY}
        );

        request.get(WS_ADDRESS + API_GEO, getData, reply);
    }

    /**
     * Calls Google Maps Distance Matrix API to get the distance data from an
     * origin given point to multiple ones.
     *
     * @param srcLatitude   The origin point latitude.
     * @param srcLongitude  The origin point longitude.
     * @param destLatitude  The destination points latitudes.
     * @param destLongitude The destination points longitudes.
     * @param reply         The object that will manage the response.
     */
    public void distance(double srcLatitude, double srcLongitude, double destLatitude[], double destLongitude[], HttpRequest.HttpReply reply) {

        assert destLatitude.length == destLongitude.length;

        StringBuilder destinations = new StringBuilder();

        for (int i = 0; i < destLatitude.length; i++) { // prepare the destination params

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
