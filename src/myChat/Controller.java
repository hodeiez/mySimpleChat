package myChat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import myChat.tcp.Client;
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
    public ScrollPane tcpAreaScroll;
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
    Client c;

    public void initialize() {
        textArea.setEditable(false);
        tcpTextArea.setEditable(false);

        //  rc = new Receiver(textArea);
    }

    public void connectTCPon(ActionEvent actionEvent) {
        c = new Client(tcpTextArea, imagePane, tcpField);

        Thread th2 = new Thread(c);
        th2.start();

    }

    public void sendTxtTCP() {
        c.sendText(tcpField.getText());
        tcpField.setText("");
        tcpAreaScroll.setVvalue(1);
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


    public void connectedMes() {
        s.send(u.getName() + " is connected!");
    }


    public void startServer(ActionEvent actionEvent) {
        Server s = new Server();
        Thread th = new Thread(s);
        th.start();
    }
}




