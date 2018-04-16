

package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Albertpv
 * @version 1.0
 */
public class Singleton {

    private List<City> cities;
    private List<Connection> connections;

    private static Singleton instance;

    private Singleton() {

        connections = new ArrayList<>();
        cities = new ArrayList<>();
    }

    public static Singleton getInstance() {

        if (instance == null) instance = new Singleton();

        return instance;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public void addCity(City city) {

        this.cities.add(city);
    }

    public void appendCities(List<City> cities) {

        this.cities.addAll(cities);
    }

    public List<Connection> getConnections() {
        return connections;
    }

    public void setConnections(List<Connection> connections) {
        this.connections = connections;
    }

    public void appendConnections(List<Connection> connections) {
        this.connections.addAll(connections);
    }
}
