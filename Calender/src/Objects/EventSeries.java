package Objects;

import Objects.Event;

import java.io.Serializable;
import java.util.ArrayList;


public class EventSeries implements Serializable, Reminder {
//    private int id;
    private ArrayList<Event> events;
    private String name;

//    public Objects.EventSeries(int seriesId, String seriesName){
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

//    public ArrayList<Objects.Event> getEvents() {
//        return events;
//    }

//    public void setEvents(ArrayList<Objects.Event> events) {
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
//        return obj instanceof Objects.EventSeries && ((Objects.EventSeries)obj).id == id;
//    }

//    @Override
//    public String toString() {
//        return "Objects.EventSeries{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                '}';
//    }

    @Override
    public String toString() {
        return "Objects.EventSeries{" +
                "events=" + events +
                ", name='" + name + '\'' +
                '}';
    }
}
