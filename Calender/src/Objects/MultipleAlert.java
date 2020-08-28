package Objects;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Timer;
import java.util.TimerTask;


public class MultipleAlert implements Serializable {
    private LocalDateTime alertDateTime;
    private String alertMessage;
    private int frequency;
    private int duration;
    private Timer timer;
    private String duUnit;
    private int originalDuration;

    public MultipleAlert(LocalDateTime date, String message,
                         int repeated, int duration, String durationUnit) {
        alertDateTime = date;
        alertMessage = message;
        frequency = repeated;
        if (durationUnit.equals("hrs")) {
            this.duration = duration * 3600000;
        }
        if (durationUnit.equals("min")) {
            this.duration = duration * 60000;
        }
        duUnit = durationUnit;
        originalDuration = duration;
        timer = new Timer();
        timer.scheduleAtFixedRate(new AlertTask(), java.util.Date.from(
                alertDateTime.atZone(ZoneId.systemDefault()).toInstant()), this.duration);
    }

    public void changeTime(LocalDateTime date){
        timer.cancel();
        alertDateTime = date;
        timer = new Timer();

        long currentMillis = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long alertMillis = alertDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long difference =  alertMillis - currentMillis;

        if(difference >= 0){
            timer.schedule(new MultipleAlert.AlertTask(), difference);
        } else {
            System.out.println("not a valid time change. Could not change the alarm time.");
        }
    }

    class AlertTask extends TimerTask {
        public void run() {
            for (int i = 0; i < frequency; i++) {
                System.out.println(alertMessage);
            }
            timer.cancel();
        }

    }

    public void cancel(){
        timer.cancel();
    }

    public void resume(){
        timer = new Timer();
        timer.schedule(new AlertTask(), java.util.Date.from(
                alertDateTime.atZone(ZoneId.systemDefault()).toInstant()), this.duration);
    }

    @Override
    public String toString() {
        return "{" +
                "Time:" + alertDateTime +
                ", Message:'" + alertMessage + '\'' +
                ", Repeated:" + frequency + " times" +
                ", Duration:" + originalDuration + duUnit + '}';
    }
}