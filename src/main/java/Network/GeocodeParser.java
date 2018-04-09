package Network;

import Model.City;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Albertpv
 * @version 1.0
 */
public class GeocodeParser {

    private static final String TYPE_COUNTRY = "country";

    private static String getCountryCode(JsonArray jsAddress) {
        int length = jsAddress.size();

        for (int i = 0; i < length; i++) {

            JsonObject item = jsAddress.get(i).getAsJsonObject();

            if (!item.has("types")) continue;

            JsonArray types = item.getAsJsonArray("types");
            int lengthTypes = types.size();
            for (int j = 0; j < lengthTypes; j++) {

                String type = types.get(j).getAsString();
                if (type.equals(TYPE_COUNTRY)) return item.get("short_name").getAsString();
            }
        }

        return "";
    }

    public static List<City> getCityData(String jsonData) {
        ArrayList<City> cities = new ArrayList<>();
        Gson gson = new GsonBuilder().create();

        JsonObject jsonObject = gson.fromJson(jsonData, JsonObject.class);

        JsonArray jsResults = jsonObject.getAsJsonArray("results");
        int length = jsResults.size();

        for (int i = 0; i < length; i++) {

            JsonObject jsCity = jsResults.get(i).getAsJsonObject();

            JsonArray jsAddress = jsCity.getAsJsonArray("address_components");
            if (jsAddress.size() == 0) continue; // it's an error
            if (!jsCity.has("geometry")) continue;
            if (!jsCity.getAsJsonObject("geometry").has("location")) continue;

            String name = jsAddress.get(0).getAsJsonObject().get("long_name").getAsString();
            String address = jsCity.get("formatted_address").getAsString();

            JsonObject jsLocation = jsCity.getAsJsonObject("geometry").getAsJsonObject("location");
            double latitude     = jsLocation.get("lat").getAsDouble();
            double longitude    = jsLocation.get("lng").getAsDouble();

            String countryCode = getCountryCode(jsAddress);
            City city = new City(name, address, countryCode, latitude, longitude);
            cities.add(city);
        }

        return cities;
    }
}
