import java.io.Serializable;
import java.util.ArrayList;


public class EventSeries implements Serializable, Reminder {
//    private int id;
    private ArrayList<Event> events;
    private String name;

//    public EventSeries(int seriesId, String seriesName){
    public EventSeries(String seriesName, ArrayList<Event> events){
//        this.id = seriesId;
        this.name = seriesName;
        this.events = events;
//        events = new ArrayList<>();
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

//    public int getId() {
//        return id;
//    }

//    public ArrayList<Event> getEvents() {
//        return events;
//    }

//    public void setEvents(ArrayList<Event> events) {
//        this.events = events;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    @Override
//    public boolean equals(Object obj) {
//        return obj instanceof EventSeries && ((EventSeries)obj).id == id;
//    }

//    @Override
//    public String toString() {
//        return "EventSeries{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                '}';
//    }

    @Override
    public String toString() {
        return "EventSeries{" +
                "events=" + events +
                ", name='" + name + '\'' +
                '}';
    }
}
