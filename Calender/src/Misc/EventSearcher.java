package Misc;//Unused Class

import Managers.EventManager;
import Managers.MemoManager;
import Objects.Event;

import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventSearcher implements Serializable {



//    public List<Objects.Event> searchEvents(String tagsOrMemo) throws FileNotFoundException, ParseException {
////        List<Objects.Event> events = loadAllEvents();
////        List<Objects.Event> searched = new ArrayList<>();
////        for (Objects.Event event : events){
////            if (event.tags.contains(tagsOrMemo)) {
////                searched.add(event);
////            }
////        }
////
////        return searched;
//        return null;
//    }
//
//    public List<Objects.Event> searchEvents(Date searchingDate) throws FileNotFoundException, ParseException {
////        List<Objects.Event> events = loadAllEvents();
////        List<Objects.Event> searched = new ArrayList<>();
////        for (Objects.Event event : events){
////            if (Managers.Utils.isSameDay(event.start, searchingDate) || Managers.Utils.isSameDay(event.end, searchingDate)) {
////
////                searched.add(event);
////            }
////        }
////        return searched;
//        return null;
//    }

    public List<Event> searchByNames(List<String> names) throws SQLException {
        EventManager manager = new EventManager();
        List<Event> events = manager.getEvents();
       // Map<Objects.Event, Objects.Memo> events = manager.associated;
        List<Event> searched = new ArrayList<>();
        for (Event event: events){
            if (names.contains(String.valueOf(event.getName()))) {
                searched.add(event);
            }
        }
        return searched;
    }

    public List<Event> searchByTags(List<String> tags) throws SQLException {
        EventManager manager = new EventManager();
        List<Event> events = manager.getEvents();
        List<Event> searched = new ArrayList<>();
        for (Event event: events){
            for (String tag : tags){
                if (event.getTag().contains(tag)) {
                    searched.add(event);
                }
            }
        }
        return searched;
    }

    public List<Event> searchByDates(ArrayList<LocalDateTime[]> dates) throws SQLException {
        EventManager manager = new EventManager();
        List<Event> events = manager.getEvents();
        List<Event> searched = new ArrayList<>();
        for (Event event: events) {
            for (LocalDateTime[] date : dates) {
                if (event.getDate().equals(dates)) {
                    searched.add(event);
                }
            }
        }
//        for (Objects.Event event: events){
//            if (startDates.contains(String.valueOf(event.getStart()))) {
//                searched.add(event);
//            }
//        }
        return searched;
    }


        public List<Event> searchByMemos(List<String> memos) throws SQLException {
        EventManager manager = new EventManager();
        List<Event> events = manager.getEvents();
        List<Event> searched = new ArrayList<>();
        MemoManager MM = new MemoManager();
            for (Event event: events){
                for (String memo : memos){
                    if (MM.getMemos().contains(memo)) {
                        searched.add(event);
                    }
                }
//        for (Objects.Event event: events){
//
//        }
/////////////////////////////
//            if (memos.contains(String.valueOf(manager.getAssociatedMemo(event)))) {
//                searched.add(event);
//            }
        }
        return searched;
}

}
