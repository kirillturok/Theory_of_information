package sample;

public class Stolbcovy {
    private static String key;
    private static int columns[];

    public static String encrypt(String k) {
        key = k.toLowerCase();
        createColumns();
        String encrypted = createEncrMatrix(Main.message);
        return encrypted;
    }

    public static String decrypt(String k) {
        key = k.toLowerCase();
        createColumns();
        String decrypted = createDecrMatrix(Main.message);
        return decrypted;
    }

    private static void createColumns() {
        columns = new int[key.length()];
        int collumnsNum = 1;
        for (int letter = 'a'; letter <= 'z' && collumnsNum <= key.length(); letter++)
            for (int i = 0; i < key.length(); i++) {
                if (key.charAt(i) == (char) letter && columns[i] == 0) {
                    columns[i] = collumnsNum;
                    collumnsNum++;
                }
            }
    }

    private static String createEncrMatrix(String text) {
        int m = (int) Math.ceil((double) text.length() / key.length());
        int n = key.length();
        char arr[][] = new char[m][n];

        int count = 0;

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n && count < text.length(); j++) {
                arr[i][j] = text.charAt(count);
                count++;
            }

        StringBuilder str = new StringBuilder();

        for (int num = 1; num <= columns.length; num++)
            for (int i = 0; i < columns.length; i++)
                if (columns[i] == num) {
                    for (int j = 0; j < arr.length && arr[j][i] != '\0'; j++) {
                        str.append(arr[j][i]);
                    }
                    break;
                }

        return str.toString();
    }

    private static String createDecrMatrix(String text) {
        int m, n, ostatok;
        n = key.length();

        if ((ostatok = text.length() % key.length()) == 0)
            m = text.length() / key.length();
        else
            m = text.length() / key.length() + 1;

        char[][] matrix = new char[m][n];
        int pointer = 0;

        for (int num = 1; num <= columns.length; num++)
            for (int j = 0; j < columns.length; j++)
                if (columns[j] == num) {
                    if (ostatok == 0) {
                        for (int i = 0; i < m; i++) {
                            matrix[i][j] = text.charAt(pointer);
                            pointer++;
                        }
                    } else {
                        if (j + 1 <= ostatok) {
                            for (int i = 0; i < m; i++) {
                                matrix[i][j] = text.charAt(pointer);
                                pointer++;
                            }
                        } else
                            for (int i = 0; i < m - 1; i++) {
                                matrix[i][j] = text.charAt(pointer);
                                pointer++;
                            }

                    }
                }

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++)
                if (matrix[i][j] == '\0')
                    break;
                else
                    str.append(matrix[i][j]);

        return str.toString();
    }
}


