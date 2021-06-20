package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Crypt {
    private int p;
    private int x;
    private  int k;
    private int g;
    ArrayList<Integer> arr = new ArrayList<>();

    public Crypt(int p, int x, int k){
        this.p=p;
        this.x=x;
        this.k=k;
    }

    public byte[] encrypt(byte[] text){
        int y = fastExp(g, x, p);
        int a = fastExp(g, k, p);
        int pointer=0;
        byte[] result = new byte[text.length*4];
        for (int i = 0; i < text.length; i++) {
            int m = Byte.toUnsignedInt(text[i]);
            int b = (fastExp(y, k, p) * (m % p)) % p;
            result[pointer++]=(byte) (a >> 8);
            result[pointer++]=(byte) a;
            result[pointer++]=(byte) (b >> 8);
            result[pointer++]=(byte) b;
            arr.add(a);
            arr.add(b);
        }
        return result;
    }

    public byte[] decrypt(byte[] text){
        byte[] result = new byte[text.length/4];
        int pointer=0;

        for (int i = 0; i < text.length; i += 4) {
            int a = Byte.toUnsignedInt(text[i]);
            a = (a << 8) + Byte.toUnsignedInt(text[i + 1]);
            int b = Byte.toUnsignedInt(text[i + 2]);
            b = (b << 8) + Byte.toUnsignedInt(text[i + 3]);
            int m = b * fastExp(fastExp(a, x, p), p - 2, p) % p;
            result[pointer++]=(byte) m;
            arr.add(m);
        }
        return result;
    }

    public void setG(int g){
        this.g=g;
    }

    public ObservableList<Integer> getListG(){
        ObservableList<Integer> observableList = FXCollections.observableArrayList();
        for (int i = 2; i < p; i++) {
            if (isPrimRoot(p, i))
                observableList.add(i);
        }
        return observableList;
    }

    public boolean isPrimRoot(int p, int g) {
        ArrayList<Integer> primFactors = getPrimFactors(p - 1);
        for (Integer factor : primFactors) {
            if (fastExp(g, (p - 1) / factor, p) == 1) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Integer> getPrimFactors(int value) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 2; i <= Math.sqrt(value); i++) {
            while (value % i == 0) {
                result.add(i);
                value /= i;
            }
        }
        if (value != 1) {
            result.add(value);
        }
        return result;
    }

    public int fastExp(int a, int z, int n){
        int x=1;
        while( z!=0) {
            while(z%2==0) {
                z=z / 2;
                a=(a * a)% n;
            }
            z=z-1;
            x=(x * a) % n;
        }
        return x;
    }


}
