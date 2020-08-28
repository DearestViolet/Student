//import java.io.Serializable;
//import java.sql.*;
//import java.time.LocalDateTime;
//import java.util.*;
//
//public class EventSeriesManager implements Serializable {
//
//    private Map<Objects.EventSeries, Objects.Memo> associated;
//    private ArrayList<Objects.Event> eventSeries;
//
//    public EventSeriesManager(){
//        associated = new HashMap<>();
//        eventSeries = new ArrayList<Objects.Event>();
//    }
//
//    public Objects.EventSeries createSeries(ArrayList<Objects.Event> events, String seriesName) throws SQLException{
////        Connection connection = Managers.Utils.getConnection();
////        String insertNewEventQuery = "INSERT INTO `Objects.EventSeries` (`name`) VALUES (?);";
////        PreparedStatement statement = connection.prepareStatement(insertNewEventQuery);
////        statement.setString(1, seriesName);
////
////        statement.executeUpdate();
////
////        ResultSet cursor = statement.getGeneratedKeys();
////        cursor.next();
////        int id = cursor.getInt(1);
////        cursor.close();
////        statement.close();
////
////        String updateEventQuery = "UPDATE `Objects.Event` SET series_id=? WHERE id=?;";
////        statement = connection.prepareStatement(updateEventQuery);
////        for (Objects.Event event : events){
////            statement.setInt(1, id);
////            statement.setInt(2, event.getId());
////            statement.executeUpdate();
////        }
////
////        statement.close();
////        connection.close();
////
////        return new Objects.EventSeries(id, seriesName);
////    }
////        eventSeries.put(seriesName, events);
//        eventSeries.addAll(events);
////        associated.put(seriesName, getAssociatedMemo(eventSeries));
//        return new Objects.EventSeries(seriesName, events);
//    }
//
//    public Objects.EventSeries createSeries(String title, ArrayList<String> tags, LocalDateTime start, LocalDateTime end, Misc.Frequency frequency, int duration) throws SQLException {
//        ArrayList<Objects.Event> events = new ArrayList<>();
//        Managers.EventManager manager = new Managers.EventManager();
//        Objects.Event event = manager.create(title, tags, start, end);
//        events.add(event);
//        LocalDateTime newStart = start;
//        LocalDateTime newEnd = end;
//
//        for (int i=0 ; i< duration - 1 ; i++) {
//            newStart = frequency.addTime(newStart);
//            newEnd = frequency.addTime(newEnd);
//            events.add(manager.create(title, tags, newStart, newEnd));
//
//        }
//        return createSeries(events, title);
//    }
//
//    public Objects.Memo getAssociatedMemo(Objects.EventSeries eventSeries){
//        return associated.get(eventSeries);
//    }
//
//    public void addMemo(Objects.Memo memo, Objects.EventSeries eventSeries) {
//        associated.put(eventSeries, memo);
//    }
//
//    @Override
//    public String toString() {
//        return "EventSeriesManager{}";
//    }
//}
