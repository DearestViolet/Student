import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Frequency implements Serializable {

    //    private String hourly;
//    private String daily;
//    private String weekly;
//    private String monthly;
    private String frequency;

    public Frequency(String frequency) {
        this.frequency = frequency;
    }

    public LocalDateTime addTime(LocalDateTime date) {
        switch (frequency) {
            case "1":
                date.plus(1, ChronoUnit.DAYS);
                break;
            case "2":
                date.plus(7, ChronoUnit.DAYS);
                break;
            case "3":
                date.plus(1, ChronoUnit.MONTHS);
                break;
        }
        return date;
    }
}