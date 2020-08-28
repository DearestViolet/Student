//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.util.Date;
//import java.util.Timer;
//import java.util.TimerTask;
//
//public class Alert {
//    private String message;
//    private Date notification;
//}


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.SimpleTimeZone;
import java.util.Timer;
        import java.util.TimerTask;
        import java.time.LocalDateTime;

public class Alert implements Serializable {
    private LocalDateTime alertDateTime;
    private String alertMessage;
    private Timer timer;

    public void changeTime(LocalDateTime date){
        timer.cancel();
        alertDateTime = date;
        timer = new Timer();

        long currentMillis = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long alertMillis = alertDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long difference =  alertMillis - currentMillis;

        if(difference >= 0){
            timer.schedule(new AlertTask(), difference);
        } else {
            System.out.println("not a valid time change. Could not change the alarm time.");
        }
    }


    public Alert(LocalDateTime date, String message){
                alertDateTime = date;
                alertMessage = message;
                timer = new Timer();
                timer.schedule(new AlertTask(), java.util.Date.from(alertDateTime.atZone(ZoneId.systemDefault()).toInstant()));
            }


            class AlertTask extends TimerTask {
                public void run(){
            System.out.println(alertMessage);
            timer.cancel();
        }
    }

    public void cancel(){
        if(timer != null) {
            timer.cancel();
        }
        this.timer = null;
    }

    public void resume(){
        timer = new Timer();
        timer.schedule(new AlertTask(), java.util.Date.from(alertDateTime.atZone(ZoneId.systemDefault()).toInstant()));
    }

    @Override
    public String toString() {
        return "{" +
                "Time:" + alertDateTime +
                ", Message:'" + alertMessage + '}';
    }

    public LocalDateTime getAlertDateTime() {
        return this.alertDateTime;
    }

    public void setAlertDateTime(LocalDateTime alertDateTime) {
        this.cancel();
        this.alertDateTime = alertDateTime;
        this.resume();
    }

    public String getAlertMessage() {
        return this.alertMessage;
    }

    public void setAlertMessage(String alertMessage) {
        this.alertMessage = alertMessage;
    }

    public static void main(String[] args) {
        LocalDateTime alertTime = LocalDateTime.of(2020, 4, 18, 6, 51);
        LocalDateTime alertTime2 = LocalDateTime.of(2020, 4, 18, 6, 51);

        Alert alert1 = new Alert(alertTime, "lets gooooo");
        alert1.changeTime(alertTime2);
    }
}