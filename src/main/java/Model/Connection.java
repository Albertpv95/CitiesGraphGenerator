package Model;

/**
 * @author Albertpv
 * @since 1.8+
 * @version 1.0
 */
public class Connection {

    private String city1;
    private String city2;
    private int distance;       // meters
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
