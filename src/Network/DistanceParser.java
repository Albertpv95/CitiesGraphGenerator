/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package Network;

import Model.City;
import Model.Connection;
import Model.Singleton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DistanceParser {

    public static List<String> parseDistances(String json, String originCity, List<City> cities, int indices[]) {
        LinkedList<Connection> connections = new LinkedList<>();
        List<String> errors = new LinkedList<>();
        Gson gson = new GsonBuilder().create();

        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

        JsonArray jsResults = jsonObject.getAsJsonArray("rows");
        if (jsResults.size() == 0
                || !jsResults.get(0).getAsJsonObject().has("elements")) {    // any route could be calculated

            for (int i = 0; i < indices.length; i++)
                errors.add(originCity + " to " + cities.get(indices[i]));

            return errors;
        }
        jsResults = jsResults.get(0).getAsJsonObject().getAsJsonArray("elements");

        int length = jsResults.size();

        for (int i = 0; i < length; i++) {

            JsonObject item = jsResults.get(i).getAsJsonObject();
            if (!item.get("status").getAsString().equals("OK")
                    || (!item.has("distance") && !item.has("duration"))) {

                errors.add(originCity + " to " + cities.get(indices[i]));
                continue;
            }

            int distance = item.get("distance").getAsJsonObject().get("value").getAsInt();
            int duration = item.get("duration").getAsJsonObject().get("value").getAsInt();

            Connection connection = new Connection(
                    originCity,
                    cities.get(indices[i]).getName(),
                    distance,
                    duration
            );

            connections.add(connection);

        }

        Singleton singleton = Singleton.getInstance();
        singleton.appendConnections(connections);

        return errors;
    }
}
