

package Model;

import java.util.ArrayList;
import java.util.List;

public class Singleton {

    private List<City> cities; // temporal, it will be a hashmap
    private List<Connection> connections;

    private static Singleton instance;

    private Singleton() {

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
