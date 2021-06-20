package sample;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;
import java.util.ResourceBundle;

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
    private Button button_chooseFile;

    @FXML
    private RadioButton button_decimac;

    @FXML
    private Button button_start;

    @FXML
    private RadioButton button_stolbcoviy;

    @FXML
    private RadioButton button_vijenera;

    @FXML
    private Label label_file;

    @FXML
    private RadioButton radio_decrypt;

    @FXML
    private RadioButton radio_encrypt;

    @FXML
    private TextField text_key;


    @FXML
    void initialize() {
        button_stolbcoviy.setSelected(true);
        radio_encrypt.setSelected(true);
        ToggleGroup group = new ToggleGroup();
        button_stolbcoviy.setToggleGroup(group);
        button_vijenera.setToggleGroup(group);
        button_decimac.setToggleGroup(group);
        ToggleGroup group1 = new ToggleGroup();
        radio_encrypt.setToggleGroup(group1);
        radio_decrypt.setToggleGroup(group1);

        button_chooseFile.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("C:\\Users\\Tom\\Desktop\\TI"));
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("text file (*.txt)", "*.txt"));
            File file = fileChooser.showOpenDialog(button_chooseFile.getScene().getWindow());
            if (file != null) {
                label_file.setText(file.getName());
                //Main.message="";
                StringBuilder sb = new StringBuilder();
                try(BufferedReader br = new BufferedReader(new FileReader(file))) {
                    int c;
                    while((c=br.read())!=-1){
                        sb.append((char)c);
                    }
                    Main.message = sb.toString();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                /*StringBuilder sb = new StringBuilder();
                try(FileReader fileReader = new FileReader(file)) {
                    char c;
                    while((c = (char) fileReader.read())!=-1){
                        sb.append((char)c);
                    }
                    Main.message = sb.toString();
                    System.out.println("mess: "+Main.message);
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } ;*/

                /*try {

                    List<String> message = Files.readAllLines(file.toPath(), Charset.forName("Utf-8"));
                    StringBuilder str = new StringBuilder();
                    for (String l : message) str.append(l);
                    Main.message = str.toString();
                    //System.out.println("|||"+Main.message+"|||");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }*/
            } else {
                label_file.setText("Файл не выбран");
                Main.message = "";
            }
        });

        button_start.setOnAction(e -> {
            if (Main.message=="") JOptionPane.showMessageDialog(null,
                    "Файл не выбран.", "Ошибка", JOptionPane.INFORMATION_MESSAGE);
            else {

                if (button_stolbcoviy.isSelected()) {
                    String key = text_key.getText().replaceAll("\\W|\\d|_", "");
                    if (key.equals("")) JOptionPane.showMessageDialog(null,
                            "Значение ключа должно содержать символы латинского алфавита.",
                            "Ошибка ввода", JOptionPane.INFORMATION_MESSAGE);
                    else {
                        try {
                            Main.process(1, radio_encrypt.isSelected() ? true : false, key);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                }
                //Посмотреть характеристики ключа
                //
                //
                else if (button_vijenera.isSelected()) {
                    String key = getRussian(text_key.getText());
                    if (key.equals("")) JOptionPane.showMessageDialog(null,
                            "Значение ключа должно содержать символы русского алфавита.",
                            "Ошибка ввода", JOptionPane.INFORMATION_MESSAGE);
                    else {
                        try {
                            Main.process(2, radio_encrypt.isSelected() ? true : false, key);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                } else if (button_decimac.isSelected()) {
                    String key = text_key.getText().replaceAll("\\D", "");
                    //System.out.println("!!!!key: "+Integer.valueOf(key));
                    if (key.equals(""))
                        JOptionPane.showMessageDialog(null,
                                "Значение ключа должно содержать число.",
                                "Ошибка ввода", JOptionPane.INFORMATION_MESSAGE);
                    else if(!vzaimno_prostye(Integer.valueOf(key),26))
                        JOptionPane.showMessageDialog(null,
                                "Значение ключа и количество символов английского алфавита должны быть взаимно простыми числами.",
                                "Неверный ключ", JOptionPane.INFORMATION_MESSAGE);
                    else {
                        try {
                            Main.process(3, radio_encrypt.isSelected() ? true : false, key);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                }

            }
        });
    }

    private static String getRussian(String text) {
        text=text.toLowerCase();
        StringBuilder sb = new StringBuilder(text);
        for (int j = sb.length() - 1; j >= 0; j--)
            if (!((sb.charAt(j) >= 'а' && sb.charAt(j) <= 'я')||sb.charAt(j)=='ё'))
                sb.deleteCharAt(j);
        return sb.toString();
    }

    private static boolean vzaimno_prostye(int a, int b){
        while(a != 0 && b != 0) {
            if(a > b)
            a = a % b;
            else
            b = b % a;
        }
        return (a+b==1)? true: false;
    }

}
