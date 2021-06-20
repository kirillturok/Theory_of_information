package sample;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import javax.swing.*;


public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonChooseFile;

    @FXML
    private Button buttonStart;

    @FXML
    private Label labelFile;

    @FXML
    private RadioButton radioDecrypt;

    @FXML
    private RadioButton radioEncrypt;

    @FXML
    private TextField textK;

    @FXML
    private TextField textP;

    @FXML
    private TextField textX;

    boolean fileIsChosen = false;


    @FXML
    void onChooseFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("C:\\Users\\Tom\\Desktop\\TI-3"));
        Main.file = fileChooser.showOpenDialog(buttonChooseFile.getScene().getWindow());
        if (Main.file != null) {
            labelFile.setText(Main.file.getName());
            fileIsChosen = true;
        } else {
            labelFile.setText("Файл не выбран");
            fileIsChosen = false;
        }
    }

    @FXML
    void onStart(ActionEvent event) {
        if (!textP.getText().matches("\\d+")) {
            JOptionPane.showMessageDialog(null,
                    "Значение p должно быть натуральным числом.", "Ошибка", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (!textX.getText().matches("\\d+")) {
            JOptionPane.showMessageDialog(null,
                    "Значение х должно быть натуральным числом.", "Ошибка", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (!textK.getText().matches("\\d+")) {
            JOptionPane.showMessageDialog(null,
                    "Значение k должно быть натуральным числом.", "Ошибка", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int p;
        try {
            p = Integer.parseInt(textP.getText());
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null,
                    "Не удалось преобразовать р.", "Ошибка", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if(p<257){
            JOptionPane.showMessageDialog(null,
                    "Чичло p должно быть больше 256.",
                    "Ошибка", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if(!Main.isPrime(p)){
            JOptionPane.showMessageDialog(null,
                    "Число p должно быть простым.",
                    "Ошибка", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int x;
        try {
            x= Integer.parseInt(textX.getText());
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null,
                    "Не удалось преобразовать x.", "Ошибка", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if(x<=1||x>=p-1){
            JOptionPane.showMessageDialog(null,
                    "Значение х должно быть натуральным числом, меньшим р-1 и не равным 1.",
                    "Ошибка", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int k;
        try {
            k = Integer.parseInt(textK.getText());
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null,
                    "Не удалось преобразовать k.", "Ошибка", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if(k<=1||k>=p-1){
            JOptionPane.showMessageDialog(null,
                    "Значение k должно быть натуральным числом, меньшим р-1 и не равным 1.",
                    "Ошибка", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if(!Main.coPrime(k,p-1)){
            JOptionPane.showMessageDialog(null,
                    "Число к должно быть взаимно простым с р-1.",
                    "Ошибка", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if(!fileIsChosen){
            JOptionPane.showMessageDialog(null,
                    "Необходимо выбрать файл.",
                    "Ошибка", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if(radioEncrypt.isSelected()) Main.encr = true;
        else Main.encr = false;
        Main.startTask(p,x,k);
    }

    @FXML
    void initialize() {
        ToggleGroup tg = new ToggleGroup();
        radioEncrypt.setToggleGroup(tg);
        radioDecrypt.setToggleGroup(tg);
        radioEncrypt.fire();
    }

}