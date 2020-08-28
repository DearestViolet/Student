import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class EventSearcher implements Serializable {



//    public List<Event> searchEvents(String tagsOrMemo) throws FileNotFoundException, ParseException {
////        List<Event> events = loadAllEvents();
////        List<Event> searched = new ArrayList<>();
////        for (Event event : events){
////            if (event.tags.contains(tagsOrMemo)) {
////                searched.add(event);
////            }
////        }
////
////        return searched;
//        return null;
//    }
//
//    public List<Event> searchEvents(Date searchingDate) throws FileNotFoundException, ParseException {
////        List<Event> events = loadAllEvents();
////        List<Event> searched = new ArrayList<>();
////        for (Event event : events){
////            if (Utils.isSameDay(event.start, searchingDate) || Utils.isSameDay(event.end, searchingDate)) {
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
       // Map<Event, Memo> events = manager.associated;
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
//        for (Event event: events){
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
//        for (Event event: events){
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
