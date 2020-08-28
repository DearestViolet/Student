package Objects;

import Misc.EventSearcher;
import Managers.AlertManager;
import Managers.EventManager;
import Managers.MemoManager;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

    private int id;
    private String username;
    private String password;
    private EventManager em;
    private EventSearcher es;
    private AlertManager am;
    private MemoManager mm;



    // Need to be protected or public?
    public User(String username, String password){
        this.username = username;
        this.password = password;

        em = new EventManager();
        es = new EventSearcher();
        am = new AlertManager();
        mm = new MemoManager();
    }

    public User() {
        em = new EventManager();
        es = new EventSearcher();
        am = new AlertManager();
        mm = new MemoManager();
    }


    /////////////////// Objects.Event ///////////////////
    public EventManager getEm() {
        return em;
    }

    public boolean createEvent(String name, String note, ArrayList<String> tags, LocalDateTime start, LocalDateTime end){
        return em.create(name, note, tags, start, end);
    }

    public boolean createEventSeries(String name, String note, ArrayList<String> tags, ArrayList<LocalDateTime []> times){
        return em.createSeries(name, note, tags, times);
    }

    public EventSearcher getEs() { return es; }

    public ArrayList<Event> getAllEvents(){
        return em.getEvents();
    }

    /////////////////// Objects.EventSeries ///////////////////
    /////////////////// Objects.Alert ///////////////////
    public AlertManager getAm() {
        return am;
    }

    /////////////////// Objects.Memo ///////////////////
    public MemoManager getMm() {
        return mm;
    }

    public List<Memo> getAllMemos(){
        return mm.getMemos();
    }

    public boolean createMemo(String parentMemo, String name){
        return mm.createMemo(parentMemo, name);
    }

    public Memo getMemo(String memo){
        return mm.findMemo(memo);
    }

    public void deleteMemo(String m){
        mm.deleteMemo(m);
    }
    /////////////////// Account ///////////////////
    public void setId(int id) {
        this.id = id;
    }

    public int getId() { return this.id; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }










}

