package Objects;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Event implements Reminder, Serializable {

    private String name;
    private String note;
    private ArrayList<String> tag;
    private ArrayList<LocalDateTime []> times;

    public Event(String name){
        this.name=name;
        times = new ArrayList<>();
        tag = new ArrayList<>();
    }

    public LocalDateTime [] getDate(){
        return times.get(0);
    }

    public ArrayList<LocalDateTime[]> getTimes() {
        return times;
    }

    public void addTime(LocalDateTime start, LocalDateTime end){
        LocalDateTime [] temp = {start, end};
        times.add(temp);
    }

    public void addTime(LocalDateTime[] time){
        times.add(time);
    }

    public void addTime(ArrayList<LocalDateTime[]> time){
        for(LocalDateTime [] i: time){
            times.add(i);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public ArrayList<String> getTag() {
        return tag;
    }

    public void setTag(ArrayList<String> tag) {
        this.tag = tag;
    }

    public void addTag(String tag){
        this.tag.add(tag);
    }
}
