package Managers;

import Objects.Event;
import Objects.Memo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EventManager implements Serializable {

    private Map<Event, Memo> associated; //stores associated Memos
    private ArrayList<Event> events;

    public EventManager() {
        associated = new HashMap<>();
        events = new ArrayList<>();
    }

    public Boolean create(String name, String note, ArrayList<String> tag, LocalDateTime start, LocalDateTime end) {
        for(Event i: events){
            if(i.getName().equals(name))
                return false;
        }
        Event event = new Event(name);
        event.setTag(tag);
        event.setNote(note);
        event.addTime(start, end);
        events.add(event);
        return true;
    }

    public Boolean createSeries(String name, String note, ArrayList<String> tag, ArrayList<LocalDateTime []> times){
        for(Event i: events){
            if(i.getName().equals(name))
                return false;
        }
        Event event = new Event(name);
        event.setTag(tag);
        event.setNote(note);
        event.addTime(times);
        events.add(event);
        return true;
    }

//    public Objects.Event createSeries(String name, ArrayList<String> tag, LocalDateTime start, LocalDateTime end,
//                              Misc.Frequency frequency, int numOccurrences) {
//        Objects.Event eventSeries = new Objects.Event(name);
//        eventSeries.setTag(tag);
//        LocalDateTime toChangeStart = start;
//        LocalDateTime toChangeEnd = end;
//        for (int i = 0; i < numOccurrences; i++) {
//            eventSeries.addTime(toChangeStart, toChangeEnd);
//            toChangeStart = frequency.addTime(toChangeStart);
//            toChangeEnd = frequency.addTime(toChangeEnd);
//        }
//        return eventSeries;
//    }

    public void add(Event event) {
        events.add(event);
    }

    public void addMemo(Event event, Memo memo) {
        associated.put(event, memo);
    }

    public Event getEvent(int index) {
        return events.get(index);
    }

    public void linkEvent(ArrayList<Event> events, String name) {
        Event newEvent = new Event(name);
        for (Event event : events) {
            newEvent.addTime(event.getDate());
        }
        events.add(newEvent);
    }

    public ArrayList<Event> search(String keyword) {
        ArrayList<Event> searchEvents = new ArrayList<>();
        for (Event event : events) {
            if (event.getName().equals(keyword) | event.getTag().contains(keyword)) {
                searchEvents.add(event);
            }
        }
        return searchEvents;
    }

    public ArrayList<Event> search(LocalDateTime date) {
        ArrayList<Event> searchEvents = new ArrayList<>();
        for (Event event : events) {
            for (LocalDateTime[] time : event.getTimes()) {
                if (time[0].equals(date))
                    searchEvents.add(event);
            }
        }
        return searchEvents;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void viewAll() {
        if (events.size() == 0) {
            System.out.println("You have no events to view.");
        }
        for (int i = 0; i < events.size(); i++) {
            int num = i + 1;
            System.out.println(num + ". " + events.get(i));
        }
    }
}