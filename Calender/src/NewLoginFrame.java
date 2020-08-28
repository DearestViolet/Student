import javax.naming.ldap.Control;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class NewLoginFrame extends DefaultLoginFrame {

    protected Controller reference;
    protected JPanel panel;
    protected JTextField userText;
    protected JPasswordField passwordText;
    protected JButton login, account;

    public JButton getLogin() {
        return login;
    }

    public JButton getAccount() {
        return account;
    }

    public NewLoginFrame(Controller control) {
        super("Login");
        panel = new JPanel();
        createInterface();
        this.add(panel);
        panel.setLayout(null);
        reference = control;
    }

    public void createInterface(){
        createUserLabel();
        createPasswordLabel();
        createLoginButton();
        createNewAccountButton();
        createExitButton();
    }

    public void createUserLabel(){
        JLabel user = new JLabel("Username");
        user.setBounds(10,20,80,25);
        panel.add(user);
        userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        panel.add(userText);
    }

    public void createPasswordLabel(){
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 50, 80,25);
        passwordText = new JPasswordField();
        passwordText.setBounds(100,50,165,25);
        panel.add(passwordText);
        panel.add(passwordLabel);
    }

    public void createLoginButton(){
        login = new JButton(new AbstractAction("Login") {
            @Override
            public void actionPerformed(ActionEvent e) {
                reference.login(userText.getText(), passwordText.getText());
            }
        });
        login.setBounds(100,80,165,25);
        panel.add(login);
    }

    public void createExitButton(){
        JButton exit = new JButton(new AbstractAction("Save") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    reference.saveToFile();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        exit.setBounds(500,120,70,25);
        panel.add(exit);
    }

    public void createNewAccountButton(){
        account = new JButton(new AbstractAction("Create New Account") {
            @Override
            public void actionPerformed(ActionEvent e) {
                    reference.changeFrame("create_new_account");
            }
        });
        account.setBounds(100,110,165,25);
        panel.add(account);
    }

    public JPanel getPanel(){
        return panel;
    }

}
