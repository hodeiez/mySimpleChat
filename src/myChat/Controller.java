package myChat;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Sphere;
import myChat.tcp.Server;
import myChat.udp.Receiver;
import myChat.udp.Sender;

public class Controller {
    @FXML
    public AnchorPane anchorinscroll;
    public TextField portF;
    public TextField groupF;
    public TextField iFF;
    public TextField iFF1;
    public StackPane imagePane;
    public TextField tcpField;
    public TextArea statusArea;
    @FXML
    private TextArea tcpTextArea;
    @FXML
    private TextField textField;
    @FXML
    private TextArea textArea;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private TextField nameF;
    Sender s;
    User u = new User();
    Receiver rc;
    Thread th;

    public void initialize() {
textArea.setEditable(false);
        //  rc = new Receiver(textArea);

    }

    public void sendTxt() {
        String sendTxt = textField.getText();
        textField.setText("");
        scrollPane.setVvalue(1);

        if (s != null)
            s.send(u.getName() + ": " + sendTxt);

    }

    public void connectOn(ActionEvent actionEvent) { ///have to clean this logic and put in Model??

        u.setName(nameF.getText());
        if (rc == null) {
            rc = new Receiver(textArea);
            s = new Sender();
            connectedMes();
        } else {
            try {
                Integer.parseInt(portF.getText());
                s = new Sender(Integer.parseInt(portF.getText()), groupF.getText(), iFF.getText());
                rc = new Receiver(textArea, Integer.parseInt(portF.getText()), groupF.getText(), iFF1.getText());
                connectedMes();
            } catch (NumberFormatException e) {
                textArea.appendText("write a number for port\n");
            }
        }
        th = new Thread(rc);
        th.start();
    }

    public void connectTCPon(ActionEvent actionEvent) {
        Server s = new Server(tcpTextArea, imagePane); //<-change to client, the gui is in client side.
         Thread th2=new Thread(s);
        th2.start();

    }
    public void connectedMes() {
        s.send(u.getName() + " is connected!");
    }
}




