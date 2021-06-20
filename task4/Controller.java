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
    private Button buttonChoose;

    @FXML
    private Button buttonStart;

    @FXML
    private Label labelFile;

    @FXML
    private Label labelResult;

    @FXML
    private Label labelOld;

    @FXML
    private RadioButton radioCheck;

    @FXML
    private RadioButton radioSub;

    @FXML
    private TextField textD;

    @FXML
    private TextField textH;

    @FXML
    private TextField textP;

    @FXML
    private TextField textQ;

    @FXML
    private TextField textS;

    private boolean fileIsChosen=false;



    @FXML
    void onChoose(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(Main.path));
        Main.file = fileChooser.showOpenDialog(buttonChoose.getScene().getWindow());
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
        int p,q,d;
        try {
            p=Integer.parseInt(textP.getText().trim());
        } catch (NumberFormatException e) {
            showMessage("Значение р должно быть натуральным числом.");
            return;
        }
        try {
            q=Integer.parseInt(textQ.getText().trim());
        } catch (NumberFormatException e) {
            showMessage("Значение q должно быть натуральным числом.");
            return;
        }
        try {
            d=Integer.parseInt(textD.getText().trim());
        } catch (NumberFormatException e) {
            showMessage("Значение d должно быть натуральным числом.");
            return;
        }
        if(!isPrime(p)){
            showMessage("Число р должно быть простым.");
            return;
        }
        if(!isPrime(q)){
            showMessage("Число q должно быть простым.");
            return;
        }
        if(!coPrime(p,q)){
            showMessage("Числа p и q должны быть взаимно протыми.");
            return;
        }
        int fr=(p-1)*(q-1);
        if(d<=1||d>=fr){
            showMessage("d должно быть в диапазоне от 1 до ф(r).");
            return;
        }
        if(!coPrime(d,fr)){
            showMessage("d должно быть взаимно простым с ф(r).");
            return;
        }
        //todo
        //check d

        if (!fileIsChosen){
            showMessage("Необходимо выбрать файл.");
            return;
        }
        Main.p=p;
        Main.q=q;
        Main.d=d;
        if(radioSub.isSelected()) Main.startSign();
        else Main.startCheck();
    }

    @FXML
    void initialize() {
        ToggleGroup tg = new ToggleGroup();
        radioSub.setToggleGroup(tg);
        radioCheck.setToggleGroup(tg);
        radioSub.fire();
    }

    public static boolean isPrime(int a){
        for(int i=2;i<Math.sqrt(a);i++){
            if(a%i==0) return false;
        }
        return true;
    }

    public static boolean coPrime(int a, int b){
        while(a != 0 && b != 0) {
            if(a > b)
                a = a % b;
            else
                b = b % a;
        }
        return (a+b==1)? true: false;
    }

    public void setResult(String message,int h, int S,String m){
        labelResult.setText(message);
        textH.setText(String.valueOf(h));
        textS.setText(String.valueOf(S));
        labelOld.setText(m);
    }

    public void showMessage(String m) {
        JOptionPane.showMessageDialog(null,
                m, "Ошибка", JOptionPane.INFORMATION_MESSAGE);
    }
}
