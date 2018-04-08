
package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @author Albertpv
 * @since 1.0
 * @version 1.0
 */
public class FileManager {

    public static void saveGraph(String filename, List<City> cities, List<Connection> connections) throws IOException {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String strJson = gson.toJson(new JsonGraphFormat(cities, connections));

        FileWriter writer = new FileWriter(filename);
        writer.write(strJson);
        writer.close();
    }

    public static void openGraph(String filename, boolean append) throws IOException {

        Gson gson = new GsonBuilder().create();

        BufferedReader br = new BufferedReader(new FileReader(filename));
        JsonGraphFormat model = gson.fromJson(br, JsonGraphFormat.class);

        Singleton singleton = Singleton.getInstance();

        if (!append) {
            singleton.setCities(model.cities);
            singleton.setConnections(model.connections);
        }
        else {
            singleton.appendCities(model.cities);
            singleton.appendConnections(model.connections);
        }

        br.close();
    }

    private static class JsonGraphFormat {

        private List<City> cities;
        private List<Connection> connections;

        public JsonGraphFormat(List<City> cities, List<Connection> connections) {
            this.cities = cities;
            this.connections = connections;
        }
    }
}
