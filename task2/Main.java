package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;

public class Main extends Application {

    static File file;
    static byte[] text;
    static byte[] result;

    static byte[] key;

    static int startRegister;

    static final int bit1 = new BigInteger("00000000000000000000000000000001", 2).intValue();
    static final int bit27 = new BigInteger("00000100000000000000000000000000", 2).intValue();
    static final int bit28 = new BigInteger("00001000000000000000000000000000", 2).intValue();
    static final int bit32 = new BigInteger("10000000000000000000000000000000", 2).intValue();

    public static void runTask() {
        readFile();
        result = encrypt(text);
        saveFile();
    }

    public static byte[] encrypt(byte[] text) {
        byte[] newText = new byte[text.length];
        key = new byte[text.length];
        int register = startRegister;
        for (int i = 0; i < text.length; i++) {
            key[i] = (byte) (register >> 24);
            newText[i] = (byte) (text[i] ^ key[i]);
            register = shiftRegister(register);
        }
        return newText;
    }

    private static int shiftRegister(int register) {
        for(int i=0;i<8;i++) {
            byte numOf1 = 0;
            if ((register & bit1) == bit1) numOf1++;
            if ((register & bit27) == bit27) numOf1++;
            if ((register & bit28) == bit28) numOf1++;
            if ((register & bit32) == bit32) numOf1++;
            register <<= 1;
            if (numOf1 % 2 == 1) register |= 1;
        }
        return register;
    }


    private static void readFile() {
        try {
            text = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    private static void saveFile() {
        try {
            File saveFile = new File("C:\\Users\\Tom\\Desktop\\TI-2\\0_"+file.getName());
            FileOutputStream fileOutputStream = new FileOutputStream(saveFile);
            fileOutputStream.write(result);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Controller.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("TI-2");
        primaryStage.setScene(new Scene(root, 400, 150));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}