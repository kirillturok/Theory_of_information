package sample;

public class Decim {

    public static String encrypt(String k) {
        StringBuilder sb = new StringBuilder();
        String message = Main.message;
        int key = Integer.valueOf(k);
        for (int i = 0; i < message.length(); i++)
            sb.append((char) (((message.charAt(i) - 'a') * key) % 26 + 'a'));
        return sb.toString();

    }

    public static String decrypt(String k) {
        int key = Integer.valueOf(k);
        String message = Main.message;
        //int euler26 = eulerFunction(26);


        //int inverse = modPow(key, euler26 - 1);
        int inverse = modInverse(key, 26);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < message.length(); i++)
            sb.append((char) ((message.charAt(i) - 'a') * inverse % 26 + 'a'));
        return sb.toString();
    }


    static int modInverse(int a, int m) {

        for (int x = 1; x < m; x++)
            if (((a % m) * (x % m)) % m == 1)
                return x;
        return 1;
    }
/*
    private static int eulerFunction(int number) {
        int result = number;
        for (int i = 2; i < Math.sqrt(number) + 1; i++) {
            if (number % i == 0) {
                while (number % i == 0) {
                    number /= i;
                }
                result -= result / i;
            }
        }
        if (number > 1) {
            result -= result / number;
        }
        return result;
    }

    // Fast modulo multiplication.
    private static int modPow(int base,int exp) {
        int result = 1;
        while (exp > 0) {
            if (exp % 2 == 1) {
                result = (result * base) % 26;
            }
            base = (base * base) % 26;
            exp /= 2;
        }
        return result;
    }*/
}
