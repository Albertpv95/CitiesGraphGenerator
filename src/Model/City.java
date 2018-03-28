
package Model;

import com.google.gson.annotations.Expose;

import java.util.Arrays;

public class City {

    private String name;
    @Expose private String address;
    private String country;
    private double latitude;
    private double longitude;

    public City(String name, String address, String country, double latitude, double longitude) {
        this.name = name;
        this.address = address;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", country='" + country + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
