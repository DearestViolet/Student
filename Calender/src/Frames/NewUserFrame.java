package Frames;

import Frames.NewLoginFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;


public class NewUserFrame extends NewLoginFrame {

    private JPasswordField passwordTextConfirm;
    public NewUserFrame(Controller control){
        super(control);
    }

    @Override
    public void createInterface() {
        createUserLabel();
        createPasswordLabel();
        addExtraPasswordLabel();
        createExitButton();
        addBackButton();
        createAccountButton();
    }

    private void addExtraPasswordLabel(){
        JLabel passwordLabel = new JLabel("Confirm Pass");
        passwordLabel.setBounds(10, 80, 100,25);
        passwordTextConfirm = new JPasswordField();
        passwordTextConfirm.setBounds(100,80,165,25);
        panel.add(passwordLabel);
        panel.add(passwordTextConfirm);
    }
    public void addBackButton(){
        JButton back = new JButton(new AbstractAction("Back") {
            @Override
            public void actionPerformed(ActionEvent e) {
                reference.changeFrame("login");
            }
        });
        back.setBounds(500,90,70,25);
        panel.add(back);
    }

    public void createAccountButton(){
        JButton new_account = new JButton(new AbstractAction("Create New Account") {
            @Override
            public void actionPerformed(ActionEvent e) {
                reference.createNewAccount(userText.getText(), passwordText.getText(),passwordTextConfirm.getText());
            }
        });
        new_account.setBounds(300,30,150,80);
        panel.add(new_account);
    }
}



