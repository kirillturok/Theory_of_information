package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public class Main extends Application {
    static File file;
    static Stage prStage;
    static Crypt crypt;
    static boolean encr;
    static byte[] result;

    public static void startTask(int p,int x,int k) {
        crypt = new Crypt(p,x,k);
        openForG();
    }

    public static void afterG(){
        ControllerForG.stage.hide();
        byte[] text = readFile();
        result=encr?crypt.encrypt(text):crypt.decrypt(text);
        saveFile(result);
        openResult();
    }

    private static byte[] readFile() {
        byte[] text;
        try {
            text = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return text;
    }

    private static void saveFile(byte[] result) {
        try {
            File saveFile = new File("C:\\Users\\Tom\\Desktop\\TI-3\\0_"+file.getName());
            FileOutputStream fileOutputStream = new FileOutputStream(saveFile);
            fileOutputStream.write(result);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
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

    private static void openForG(){
        Stage result = new Stage();
        ControllerForG.stage=result;
        result.setTitle("Выбор g");
        result.initOwner(prStage);
        try {
        Parent root = FXMLLoader.load(Main.class.getResource("forG.fxml"));
        result.setScene(new Scene(root));
        result.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void openResult(){
        Stage result = new Stage();
        ControllerResult.stage=result;
        result.setTitle("Результат");
        result.initOwner(prStage);
        try {
            Parent root = FXMLLoader.load(Main.class.getResource("result.fxml"));
            result.setScene(new Scene(root));
            result.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        prStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Task 3");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
