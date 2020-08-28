import javax.swing.*;
import java.awt.event.ActionEvent;

public abstract class DefaultLoginFrame extends JFrame {
    public DefaultLoginFrame(String title){
        super(title);
        setSize(600,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
