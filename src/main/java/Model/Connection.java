package Model;

/**
 * @author Albertpv
 * @version 1.0
 */
public class Connection {

    private String from;
    private String to;
    private int distance;       // meters
    private int duration;       // seconds

    public Connection(String from, String to, int distance, int duration) {
        this.from = from;
        this.to = to;
        this.distance = distance;
        this.duration = duration;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
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
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", distance=" + distance +
                ", duration=" + duration +
                '}';
    }
}
