package Frames;

import Frames.*;
import Objects.Reminder;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class Presenter {
    JFrame currentFrame;
    Map<String, JFrame> frameLibrary;
    Controller control;

    public Presenter(Controller control){
        this.control = control;
        frameLibrary = new HashMap<>();
        currentFrame = new NewLoginFrame(control);
        frameLibrary.put("login", currentFrame);
        currentFrame.setVisible(true);
        construct();
    }

    public void present(String key){
        currentFrame.dispose();
        currentFrame = frameLibrary.get(key);
        currentFrame.setVisible(true);
    }

    public void initiateUserData(){
       Initialize temp = (Initialize) currentFrame;
       temp.initiateData();
    }

    public void initiateUserData(Reminder r){
        Initializer temp = (Initializer) currentFrame;
        temp.initiateData(r);
    }
    public int createReminderOption(){
        Object[] options = {"Memo",
                "Event",
                "Alert"};
        int n = JOptionPane.showOptionDialog(currentFrame,
                "What Reminder would you like to create?",
                "Create Reminder",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[2]);
        return n;
    }

    public int createViewOption(){
        Object[] options = {"Memo",
                "Event",
                "Alert"};
        int n = JOptionPane.showOptionDialog(currentFrame,
                "What Reminder would you like to view?",
                "View",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[2]);
        return n;
    }

    private void construct(){
        frameLibrary.put("create_new_account" , new NewUserFrame(control));
        frameLibrary.put("main_frame", new MainUserFrame(control));
        frameLibrary.put("event_creator", new EventCreatorFrame(control));
        //frameLibrary.put("alert_creator", new AlertCreatorFrame(control));
        frameLibrary.put("memo_creator", new MemoCreatorFrame(control));
        frameLibrary.put("memo_view", new MemoViewFrame(control, null));
        frameLibrary.put("event_view", new EventViewFrame(control));
//        frameLibrary.put("alert_view", new AlertViewFrame(control));
        frameLibrary.put("event_selection", new EventSelectorFrame(control));
    }


    public void errorMessage(String message){
        JOptionPane.showMessageDialog(currentFrame,
                message,
                "Error",
                JOptionPane.WARNING_MESSAGE);
    }

    public void notificationMessage(String message){
        JOptionPane.showMessageDialog(currentFrame,
                message);
    }
}
