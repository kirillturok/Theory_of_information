package sample;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;


public class Controller {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button button_file;

    @FXML
    private Button button_start;

    @FXML
    private Label label_file;

    @FXML
    private TextField text_forKey;

    boolean fileIsChosen;

    static Stage primaryStage;


    @FXML
    void initialize() {
        button_file.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("C:\\Users\\Tom\\Desktop\\TI-2"));
            Main.file = fileChooser.showOpenDialog(button_file.getScene().getWindow());
            if (Main.file != null) {
                label_file.setText(Main.file.getName());
                fileIsChosen = true;
            } else {
                label_file.setText("Файл не выбран");
                fileIsChosen = false;
            }
        });

        button_start.setOnAction(actionEvent -> {
            if (!fileIsChosen) {
                JOptionPane.showMessageDialog(null,
                        "Файл не выбран.", "Ошибка", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (!getStartRegister(text_forKey.getText().trim())) {
                JOptionPane.showMessageDialog(null,
                        "Количество 0 и 1 в вводимом значении должно равняться 32.",
                        "Ошибка", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            Main.runTask();
            try {
                openResult();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    boolean getStartRegister(String key) {
        key = key.replaceAll("[^01]", "");
        if (key.length() != 32) return false;
        Main.startRegister = new BigInteger(key, 2).intValue();
        return true;
    }

    private void openResult() throws IOException {
        Stage result = new Stage();
        Controller2.stage=result;
        result.setTitle("Результат");
        result.initOwner(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("second.fxml"));
        result.setScene(new Scene(root, 700, 400));
        result.show();
    }

}
