package sample;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;


public class ControllerResult {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonOk;

    @FXML
    private TextArea text;

    public static Stage stage;


    @FXML
    void onOk(ActionEvent event) {
        stage.hide();
    }

    @FXML
    void initialize() {
        StringBuffer sb = new StringBuffer();
        if (Main.crypt.arr.size() < 32) {
            for (int i = 0; i < Main.crypt.arr.size(); i++) {
                sb.append(Main.crypt.arr.get(i));
                sb.append(" ");
            }
        } else {
            sb.append("Первые 16 байт: ");
            for (int i = 0; i < 16; i++) {
                sb.append(Main.crypt.arr.get(i));
                sb.append(" ");
            }
            sb.append("\n\nПоследние 16 байт: ");
            for (int i = Main.crypt.arr.size() - 16; i < Main.crypt.arr.size(); i++) {
                sb.append(Main.crypt.arr.get(i));
                sb.append(" ");
            }

        }
        text.setText(sb.toString());
    }
}
