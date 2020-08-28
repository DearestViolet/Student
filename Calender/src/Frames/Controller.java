package Frames;

import Managers.UserManager;
import Objects.Alert;
import Objects.Event;
import Objects.Memo;
import Objects.Reminder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller{

    private Presenter presenter;
    private UserManager userManager;
    private String currentUser;

    public Controller() throws IOException, ClassNotFoundException {
        userManager = new UserManager("Storage.csv");
        presenter = new Presenter(this);
    }

    public void saveToFile() throws IOException {
        userManager.saveToFile("Storage.csv");
    }


    public void login(String username, String password) {
        if(userManager.login(username,password) == null) {
            presenter.errorMessage("Invalid Username or Password");
        }
        else{
            presenter.present("main_frame");
            currentUser = username;
            presenter.initiateUserData();
        }
    }

    public void createEvent(String name, String note, String tag, String date, String start_time, String end_time ){
        String start_time_final = date +" "+ start_time;
        String end_time_final = date +" "+ end_time;
        if(userManager.createEvent(currentUser, name, note, tag, start_time_final, end_time_final)){
            presenter.notificationMessage("Your Objects.Event has been successfully created");
        }
        else {
            presenter.notificationMessage("Something went wrong, please try again");
        }
        presenter.present("main_frame");
        presenter.initiateUserData();

    }

    public void createEventSeries(String name, String note, String tag, String date, String start_time, String end_time){
        if(userManager.createEventSeries(currentUser, name, note, tag, date, start_time,end_time)){
            presenter.notificationMessage("Your Objects.Event has been successfully created");
        }
        else {
            presenter.notificationMessage("Something went wrong, please try again");
        }
        presenter.present("main_frame");
        presenter.initiateUserData();
    }

    public void createReminder(){
        int temp = presenter.createReminderOption();
        if(temp == 0){
            presenter.present("memo_creator");
            presenter.initiateUserData();}
        else if(temp == 1)
            presenter.present("event_creator");
        else
            presenter.present("alert_creator");
    }

    public void view(Reminder r){
        if(r instanceof Memo){
            presenter.present("memo_view");
            presenter.initiateUserData(r);
        }
        else if(r instanceof Event){
            presenter.present("event_view");
            presenter.initiateUserData(r);
        }
        else if(r instanceof Alert){
            presenter.present("alert_view");
            presenter.initiateUserData(r);}
        else{
            presenter.errorMessage("Nothing to View");
            presenter.present("main_frame");
            presenter.initiateUserData();
        }
    }
    public void view(){
        int selection = presenter.createViewOption();
        if(selection == 0){
            presenter.present("memo_view");
            presenter.initiateUserData();
        }
        else if (selection == 1){
            presenter.present("event_selection");
            presenter.initiateUserData();
        }
        else
            presenter.present("alert_view");
            presenter.initiateUserData();
    }

    public void deleteMemo(String m){
        userManager.deleteMemo(currentUser,m);
        presenter.present("main_frame");
        presenter.initiateUserData();
    }

    public ArrayList<Event> getAllEvents(){
        return userManager.getAllEvents(currentUser);
    }


    public List<Memo> getAllMemos(){
        return userManager.getAllMemos(currentUser);
    }

    public void createNewAccount(String username, String password, String confirmation){
        if(username.equals("") | password.equals("") | confirmation.equals("")){
            presenter.errorMessage("You must fill in all the credentials");
        }
        else if(confirmation.equals(password)) {
            if (userManager.login(username, password) != null) {
                presenter.errorMessage("Objects.User Already Exists");
            } else {
                if (!userManager.add_user(username, password)){
                    presenter.errorMessage("Objects.User Already Exists");
                }
                else{
                    presenter.notificationMessage("Your account has been created");
                    presenter.present("login");
                }
            }
        }
        else
            presenter.errorMessage("Password and Confirm Pass are not the same");
    }

    public void changeFrame(String s){
        presenter.present(s);
    }

    public void exitToMain(){
        presenter.present("main_frame");
        presenter.initiateUserData();
    }

    public Object getUserInfo(String args, String args2){
        return userManager.getUserInfo(currentUser, args, args2);
    }

    public void addPresenter(Presenter presenter){
        this.presenter = presenter;
    }

    public void createMemo(String parentMemo, String name){
        if(userManager.createMemo(currentUser, parentMemo, name)){
            presenter.notificationMessage("Your Objects.Memo has been created");
            presenter.present("main_frame");
            presenter.initiateUserData();
        }
        else{
            presenter.errorMessage("Whoops! You might have made this memo already!");
        }

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        Managers.UserManager reset = new Managers.UserManager("Storage.csv");
//        reset.saveToFile("Storage.csv");
        Controller test = new Controller();
    }
}
