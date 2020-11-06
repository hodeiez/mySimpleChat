package myChat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import myChat.udp.Receiver;
import myChat.udp.Sender;

public class Controller {
    @FXML
    public AnchorPane anchorinscroll;
    public TextField portF;
    public TextField groupF;
    public TextField iFF;
    public TextField iFF1;
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

    public void initialize() {

        //  rc = new Receiver(textArea);

    }

    public void sendTxt() {
        String sendTxt = textField.getText();
        textField.setText("");
        scrollPane.setVvalue(1);
        u.setName(nameF.getText());
        if (s != null)
            s.send(u.getName() + ": " + sendTxt);

    }

    public void connectOn(ActionEvent actionEvent) {
        if (rc == null) {
            rc = new Receiver(textArea);
            s = new Sender();
        } else {
            try {
                Integer.parseInt(portF.getText());
                s = new Sender(Integer.parseInt(portF.getText()), groupF.getText(), iFF.getText());
                rc = new Receiver(textArea, Integer.parseInt(portF.getText()), groupF.getText(), iFF1.getText());
            } catch (NumberFormatException e) {
                textArea.appendText("write a number for port\n");
            }
        }
        Thread th = new Thread(rc);
        th.start();
    }
}




