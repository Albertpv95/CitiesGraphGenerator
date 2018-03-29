/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package Model;

public class Connection {

    private String city1;
    private String city2;
    private int distance;    // meters
    private int duration;       // seconds

    public Connection(String city1, String city2, int distance, int duration) {
        this.city1 = city1;
        this.city2 = city2;
        this.distance = distance;
        this.duration = duration;
    }

    public String getCity1() {
        return city1;
    }

    public void setCity1(String city1) {
        this.city1 = city1;
    }

    public String getCity2() {
        return city2;
    }

    public void setCity2(String city2) {
        this.city2 = city2;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Connection{" +
                "city1='" + city1 + '\'' +
                ", city2='" + city2 + '\'' +
                ", distance=" + distance +
                ", duration=" + duration +
                '}';
    }
}
