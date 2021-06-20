package sample;

public class Vijener {
    private static char[] let = {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у',
            'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я'};

    private static int getNum(char c){
        for(int i=0;i<let.length;i++) if(let[i]==c) return i;
        return 0;
    }

    public static String encrypt(String key) {
        String message = Main.message;
        //System.out.println("ebcr:Message::"+message);
        String progrKey = makeKey(key, message.length());
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<message.length();i++)
            sb.append(let[(getNum(message.charAt(i))+getNum(progrKey.charAt(i)))%33]);
        return sb.toString();
    }

    public static String decrypt(String key){
        String message = Main.message;
        //System.out.println("decr:message::"+message);
        String progrKey = makeKey(key, message.length());
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<message.length();i++)
            sb.append(let[(getNum(message.charAt(i))-getNum(progrKey.charAt(i))+33)%33]);
        return sb.toString();
    }

    private static String makeKey(String key, int length){
        StringBuilder sb = new StringBuilder(key);
        int pointer=0;
        while(sb.length()<length){
            sb.append(let[(getNum(sb.charAt(pointer))+1)%33]);
            pointer++;
        }
        //System.out.println("KEY::"+sb.toString());
        return sb.toString();
    }
}
