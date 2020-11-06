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
    @FXML
    private TextField textField;
    @FXML
    private TextArea textArea;
    @FXML
    private ScrollPane scrollPane;
    @FXML
            private TextField nameF;
    Sender s=new Sender();
    User u=new User();
    public void initialize(){

        Receiver rc=new Receiver(textArea);
        Thread th=new Thread(rc);
        th.start();
    }
    public void sendTxt() {
        scrollPane.setVvalue(1);
        u.setName(nameF.getText());
        s.send(u.getName()+": " + textField.getText());
        textField.setText("");
    }

    public void connectOn(ActionEvent actionEvent) {
    }
}




