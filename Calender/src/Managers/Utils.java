package Managers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;

public class Utils {

    private static final String DATABASE_PATH = "calendar.db";

    private static final String CREATE_EVENT_QUERY = "create table if not exists Objects.Event (id INTEGER primary key, " +
            "title TEXT not null, " +
            "tags TEXT not null, " +
            "start LONG not null, " +
            "end LONG not null, " +
            "series_id int " +
            "   references Objects.EventSeries " +
            ");";
    private static final String CREATE_EVENT_SERIES_QUERY = "create table if not exists Objects.EventSeries " +
            "(id integer not null constraint EventSeries_pk primary key autoincrement, name text not null);" +
            "create unique index IF NOT EXISTS EventSeries_id_uindex on Objects.EventSeries (id);";
    private static final String CREATE_USER_QUERY = "create table if not exists Objects.User (id integer not null " +
            "constraint User_pk " +
            " primary key autoincrement," +
            " username text not null," +
            " password text not null" +
            ");" +
            "create unique index IF NOT EXISTS User_username_uindex " +
            " on Objects.User (username);";

    public static boolean isSameDay(Date first, Date second){
        Calendar firstCal = Calendar.getInstance();
        Calendar secondCal = Calendar.getInstance();
        firstCal.setTime(first);
        secondCal.setTime(second);
        return firstCal.get(Calendar.YEAR) == secondCal.get(Calendar.YEAR) &&
                firstCal.get(Calendar.MONTH) == secondCal.get(Calendar.MONTH) &&
                firstCal.get(Calendar.DAY_OF_MONTH) == secondCal.get(Calendar.DAY_OF_MONTH);
    }

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e){
            throw new RuntimeException("JDBC for sqlite should be added.");
        }

        File dbFile = new File(DATABASE_PATH);
        if (!dbFile.exists()) {
            try {
                dbFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Connection connection = DriverManager.getConnection(String.format("jdbc:sqlite:%s", DATABASE_PATH));

        Statement createTableStatement = connection.createStatement();
        createTableStatement.execute(CREATE_EVENT_QUERY);
        createTableStatement.execute(CREATE_EVENT_SERIES_QUERY);
        createTableStatement.execute(CREATE_USER_QUERY);
        createTableStatement.close();

        return connection;
    }
}
