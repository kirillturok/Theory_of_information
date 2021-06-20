package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.Charset;

public class Main extends Application {

    static String message;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Алгоритмы шифрования");
        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void process(int alg, boolean encr, String key) throws IOException {
        String toSave = "";
        switch (alg) {
            //столбцовый
            case 1: {
                delForbidden(true);
                if (encr) toSave = Stolbcovy.encrypt(key);
                else toSave = Stolbcovy.decrypt(key);
                break;
            }
            //виженера
            case 2: {
                delForbidden(false);
                if (encr) toSave = Vijener.encrypt(key);
                else toSave = Vijener.decrypt(key);
                break;
            }
            //децимаций
            case 3: {
                delForbidden(true);
                if(encr) toSave = Decim.encrypt(key);
                else toSave = Decim.decrypt(key);
                break;
            }
        }
        save(toSave);
    }

    private static void delForbidden(boolean english) {
        message=message.toLowerCase();
        if (english) {
            message = message.replaceAll("\\W|\\d|_", "");
        } else {
            StringBuilder sb = new StringBuilder(message);
            for (int j = sb.length() - 1; j >= 0; j--)
                if (!((sb.charAt(j) >= 'а' && sb.charAt(j) <= 'я')||sb.charAt(j)=='ё'))
                    sb.deleteCharAt(j);
            message = sb.toString();
        }
    }
/*
    private static void save(String text) {
        File file = new File("C:\\Users\\Tom\\Desktop\\TI\\notes.txt");
        try (FileWriter writer = new FileWriter(file, Charset.forName("windows-1251"), false)) {
            writer.write(text);
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }*/

    private static void save(String text) throws IOException {
        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\Tom\\Desktop\\TI\\0_save.txt"), Charset.forName("Utf-8")));
            out.append(text);
            out.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
