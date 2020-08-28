package Objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Memo implements Reminder, Serializable {
    private Memo parent;
    private ArrayList<Reminder> memo_list;
    private String name;

    public Memo (String name) {
        this.name = name;
        memo_list = new ArrayList<>();
    }

    public String getName(){
        return name;
    }

    public void setName(String name) { this.name = name; }

    public void add(Reminder temp){
        memo_list.add(temp);
    }

    public void add(ArrayList<Reminder> temp) {
        memo_list.addAll(temp);
    }

    public void remove(Reminder reminder) {

        List<Reminder> listOfReminder = new ArrayList<>();
        listOfReminder.add(reminder);
        memo_list.remove(reminder);
    }

    public ArrayList<Reminder> getMemo_list() {
        return memo_list;
    }

    public void setParent(Memo parent) {
        this.parent = parent;
    }

    public Memo getParent() {
        return parent;
    }

    @Override
    public String toString(){
        return name;
    }
//    public String toString() {
//        String represent = name;
//        int counter = 1;
//        for(Objects.Reminder reminder: memo_list){
//            represent += "\n   " + counter + ". " + reminder.getName();
//        }
//        return represent;
//    }
}