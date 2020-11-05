package myChat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class Controller {
    @FXML
    public AnchorPane anchorinscroll;
    @FXML
    private TextField textField;
    @FXML
    private TextArea textArea;
    @FXML
    private ScrollPane scrollPane;
    Sender s=new Sender();
    public void initialize(){

        Receiver rc=new Receiver(textArea);
        Thread th=new Thread(rc);
        th.start();
    }
    public void sendTxt() {
        scrollPane.setVvalue(1);
        s.send(textField.getText());
        textField.setText("");
    }

    public void connectOn(ActionEvent actionEvent) {
    }
}




