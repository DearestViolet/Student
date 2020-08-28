package Managers;

import Objects.Alert;
import Objects.MultipleAlert;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class AlertManager implements Serializable {

    private List<Alert> oneTimeAlerts = new ArrayList<>();
    private List<MultipleAlert> multipleTimeAlerts = new ArrayList<>();

    public void createAlert(LocalDateTime date, String message){
        Alert alert = new Alert(date, message);
        oneTimeAlerts.add(alert);
    }

    public void createAlert(LocalDateTime date, String message, int repeated, int duration, String durationUnit){
        MultipleAlert mulAlert = new MultipleAlert(date, message, repeated, duration, durationUnit);
        multipleTimeAlerts.add(mulAlert);
    }

    @Override
    public String toString() {
        return "Alerts: " + '\n' +
                "Onetimealerts: " + oneTimeAlerts + '\n' +
                "Multipletimealerts: " + multipleTimeAlerts;
    }

    public void cancelAll(){
        for(Alert alert : oneTimeAlerts){
            alert.cancel();
        }
        for (MultipleAlert alert : multipleTimeAlerts) {
            alert.cancel();
        }
    }

    public void resumeAll(){
        for(Alert alert : oneTimeAlerts){
            alert.resume();
        }
        for (MultipleAlert alert : multipleTimeAlerts) {
            alert.resume();
        }
    }

    // update

    public void updateAlert(Alert alert, LocalDateTime date) {
        alert.setAlertDateTime(date);
    }

    public void updateAlert(Alert alert, String message) {
        alert.setAlertMessage(message);
    }

    // delete

    public void deleteAlert(Alert alert) { alert.cancel(); }

    public void changeTime(LocalDateTime time){
        for(Alert alert : oneTimeAlerts){
            alert.changeTime(time);
        }
        for (MultipleAlert alert : multipleTimeAlerts) {
            alert.changeTime(time);
        }
    }


//    public static void main(String[] args) {
//        LocalDateTime alertTime = LocalDateTime.of(2020, 4, 18, 7, 18);
//        LocalDateTime alertTime2 = LocalDateTime.of(2020, 4, 18, 7, 19);
//        Managers.AlertManager manager = new Managers.AlertManager();
//        manager.createAlert(alertTime, "It is the time!");
//        manager.createAlert(alertTime, "multiple alert time", 3, 2, "min");
//        manager.changeTime(alertTime2);
//    }

}