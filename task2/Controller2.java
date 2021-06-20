package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;


public class Controller2 {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button button_close;

    @FXML
    private TextArea field_key;

    @FXML
    private TextArea field_original;

    @FXML
    private TextArea field_result;

    static Stage stage;


    @FXML
    void initialize() {
        button_close.setOnAction(actionEvent -> {
            stage.hide();
        });

        if(Main.text.length<20){
            StringBuffer sb = new StringBuffer();
            for(byte b : Main.text){
                sb.append(String.format("%8s",
                        Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
                sb.append(" ");
            }
            field_original.setText(sb.toString());

            sb = new StringBuffer();
            for(byte b : Main.key){
                sb.append(String.format("%8s",
                        Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
                sb.append(" ");
            }
            field_key.setText(sb.toString());

            sb = new StringBuffer();
            for(byte b : Main.result){
                sb.append(String.format("%8s",
                        Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
                sb.append(" ");
            }
            field_result.setText(sb.toString());
        }else{
            int length = Main.text.length;
            StringBuffer sb = new StringBuffer("Первые 10 байт\n");
            for(int i=0;i<10;i++){
                sb.append(String.format("%8s",
                        Integer.toBinaryString(Main.text[i] & 0xFF)).replace(' ', '0'));
                sb.append(" ");
            }
            sb.append("\nПоследние 10 байт\n");
            for(int i=length-10;i<length;i++){
                sb.append(String.format("%8s",
                        Integer.toBinaryString(Main.text[i] & 0xFF)).replace(' ', '0'));
                sb.append(" ");
            }
            field_original.setText(sb.toString());

            sb = new StringBuffer("Первые 10 байт\n");
            for(int i=0;i<10;i++){
                sb.append(String.format("%8s",
                        Integer.toBinaryString(Main.key[i] & 0xFF)).replace(' ', '0'));
                sb.append(" ");
            }
            sb.append("\nПоследние 10 байт\n");
            for(int i=length-10;i<length;i++) {
                sb.append(String.format("%8s",
                        Integer.toBinaryString(Main.key[i] & 0xFF)).replace(' ', '0'));
                sb.append(" ");
            }
            field_key.setText(sb.toString());

            sb = new StringBuffer("Первые 10 байт\n");
            for(int i=0;i<10;i++){
                sb.append(String.format("%8s",
                        Integer.toBinaryString(Main.result[i] & 0xFF)).replace(' ', '0'));
                sb.append(" ");
            }
            sb.append("\nПоследние 10 байт\n");
            for(int i=length-10;i<length;i++){
                sb.append(String.format("%8s",
                        Integer.toBinaryString(Main.result[i] & 0xFF)).replace(' ', '0'));
                sb.append(" ");
            }
            field_result.setText(sb.toString());
        }
        field_original.setEditable(false);
        field_key.setEditable(false);
        field_result.setEditable(false);
    }

}
