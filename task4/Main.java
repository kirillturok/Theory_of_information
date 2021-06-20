package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;

public class Main extends Application {

    static Controller controller;

    static File file;
    static String path = "C:\\Users\\Tom\\Desktop\\TI-4";
    static String codec = "windows-1251";

    static int p, q, r, d;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        controller = fxmlLoader.getController();
        primaryStage.show();
    }

    public static void startSign() {
        r = p * q;
        StringBuilder sb = readFile(file);
        //byte[] M = readFile(file);
        //int m = hashFunc(new String(M, Charset.forName(codec)));
        int m = hashFunc(sb.toString());
        int S = fastExp(m, d, r);
        String add = String.format("|%d", S);
        //String content = new String(M, Charset.forName(codec));
        saveFile(sb.toString() + add);
        controller.setResult("", m, S,"");
    }

    public static void startCheck() {
        r = p * q;
        StringBuilder sb = readFile(file);
        //byte[] input = readFile(file);
        //String sb = new String(input, Charset.forName(codec));
        for(int i=0;i<sb.length();i++){
            System.out.println("|"+sb.charAt(i)+"|");
        }
        int lastN = sb.lastIndexOf("|");
        String lastLine = sb.substring(lastN + 1);
        System.out.println(lastLine);
        String fileContent="";
        if(lastN!=-1)
        fileContent = sb.substring(0, lastN);
        int S = Integer.parseInt(lastLine);

        int fr = (q-1)*(p-1);
        int e = getE(fr,d);

        int m = fastExp(S, e, r);

        byte[] content = fileContent.getBytes();
        int h = hashFunc(new String(content, Charset.forName(codec)));

        if (m == h)
            controller.setResult("Подпись верна.", h, S,String.valueOf(m));
        else
            controller.setResult("Подпись не верна.", h, S,String.valueOf(m));
    }

    private static int hashFunc(String arr) {
        int H = 100;
        for (int i = 0; i < arr.length(); i++) {
            System.out.println((int)arr.charAt(i));
            int x = H + arr.charAt(i);
            H = fastExp(x, 2, r);
        }
        return H;
    }

    public static int fastExp(int a, int z, int n) {
        int x = 1;
        while (z != 0) {
            while (z % 2 == 0) {
                z = z / 2;
                a = (a * a) % n;
            }
            z = z - 1;
            x = (x * a) % n;
        }
        return x;
    }

    private static int getE(int a, int b) {
        int d0 = a;
        int d1 = b;
        int x0 = 1;
        int x1 = 0;
        int y0 = 0;
        int y1 = 1;
        while (d1 > 1) {
            int q = d0 / d1;
            int d2 = d0 % d1;
            int x2 = x0 - q * x1;
            int y2 = y0 - q * y1;
            d0 = d1;
            d1 = d2;
            x0 = x1;
            x1 = x2;
            y0 = y1;
            y1 = y2;
        }
        if (y1 < 0) y1 += a;
        return y1;
    }

    private static StringBuilder readFile(File file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder stringBuilder = new StringBuilder();
            String temp;
            while((temp=br.readLine())!=null){
                stringBuilder.append(temp);
            }
            br.close();
            return stringBuilder;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
        byte[] text;
        try {
            text = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return text;*/
        return null;
    }

    private static void saveFile(String result) {
        System.out.println("result|"+result+"|"+result.length());
        try {
            String newPath = path + "\\signed_" + file.getName();
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(newPath), Charset.forName(codec)));
            out.write(result);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
