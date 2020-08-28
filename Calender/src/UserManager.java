import java.io.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserManager implements Serializable {

    private static final Logger logger = Logger.getLogger(UserManager.class.getName());
    private static final Handler consoleHandler = new ConsoleHandler();

    private Map<String, User> users;

    public UserManager(String filePath) throws IOException, ClassNotFoundException {
        users = new HashMap<String, User>();
        logger.setLevel(Level.ALL);
        consoleHandler.setLevel(Level.ALL);
        logger.addHandler(consoleHandler);

        // Reads serializable objects from file.
        // Populates the record list using stored data, if it exists.
        File file = new File(filePath);
        if (file.exists()) {
            readFromFile(filePath);
        } else {
            file.createNewFile();
        }
    }

    //if login is invalid will return null
    public User login(String username, String password){
        if(users.containsKey(username)){
            if((users.get(username).getPassword().equals(password))){
                return users.get(username);
            }
        }
        return null;
    }

    public void saveToFile(String filePath) throws IOException {

        OutputStream file = new FileOutputStream(filePath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);
        output.writeObject(users);
        output.close();
    }

    public void readFromFile(String path) throws ClassNotFoundException {

        try {
            InputStream file = new FileInputStream(path);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            // deserialize the Map
            users = (HashMap<String, User>) input.readObject();
            input.close();
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Cannot read from input.", ex);
        }
    }

    public boolean add_user(String username, String password){
       User temp = create_user(username,password);
       if(users.get(username) != null){
           return false;
       }
       else{
           users.put(username, temp);
           return true;
       }
    }

    public User create_user(String username, String password){
        User newUser = new User(username, password);
        return newUser;
    }


    /**
     * Retrieves user from the database
     *
     * @param username username of the user
     * @param password password of the user
     * @return User object corresponding credentials, null if such user does not exists
     * @exception SQLException if a database access error occurs
     */
    public User getUser(String username, String password) throws SQLException {
        Connection connection = Utils.getConnection();
        String selectUserQuery = "SELECT * FROM `User` WHERE username=? AND password=?;";
        PreparedStatement statement = connection.prepareStatement(selectUserQuery);
        statement.setString(1, username);
        statement.setString(2, password);

        ResultSet cursor = statement.executeQuery();
        User user;
        if (cursor.next()) {
            user = new User(cursor.getString("username"),
                    cursor.getString("password"));
        } else {
            user = null;
        }
        cursor.close();
        statement.close();
        connection.close();
        return user;
    }

    public void deleteMemo(String username, String m){
        users.get(username).deleteMemo(m);
    }
    public Object getUserInfo(String username, String args, String args2){
        User temp = users.get(username);
        switch (args){
            case "name":
                return temp.getUsername();
            case "memos":
                return getAllMemos(username);
            case "memo":
                return users.get(username).getMemo(args2);
            default:
                return null;
        }
    }

    public boolean createMemo(String username, String parentMemo, String name){
        return users.get(username).createMemo(parentMemo, name);
    }

    public ArrayList<Event> getAllEvents(String username){
        return users.get(username).getAllEvents();
    }

    public boolean createEvent(String username, String name, String note, String tag, String start_time, String end_time){
        ArrayList<String> newTags = new ArrayList<>( Arrays.asList(tag.split(",")));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime start_time_final = LocalDateTime.parse(start_time, formatter);
        LocalDateTime end_time_final = LocalDateTime.parse(end_time, formatter);
        return users.get(username).createEvent(name, note, newTags, start_time_final,end_time_final);
    }

    public boolean createEventSeries(String username, String name, String note, String tag, String date, String start_time, String end_time){
        ArrayList<String> newTags = new ArrayList<>( Arrays.asList(tag.split(",")));
        ArrayList<String> newStartTimes = new ArrayList<>(Arrays.asList(start_time.split(",")));
        ArrayList<String> newEndTimes = new ArrayList<>(Arrays.asList(end_time.split(",")));
        ArrayList<LocalDateTime []> finalDates = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        for(int i = 0; i < newStartTimes.size(); i++){
            LocalDateTime tempStart = LocalDateTime.parse(date + " " + newStartTimes.get(i), formatter);
            LocalDateTime tempEnd = LocalDateTime.parse(date + " " + newEndTimes.get(i), formatter);
            finalDates.add(new LocalDateTime[]{tempStart, tempEnd});
        }
        return users.get(username).createEventSeries(name, note, newTags, finalDates);
    }

    public List<Memo> getAllMemos(String username){
        return users.get(username).getAllMemos();
    }


}
