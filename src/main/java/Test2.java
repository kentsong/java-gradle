import java.util.ArrayList;
import java.util.List;

public class Test2 {

    static String ori = "abcde";

    public static void main(String[] args) {
        List<String[]> list = new ArrayList<>();
        for(String[] srray : list){

        }

        Function function = new Function<String, Void>() {
            @Override
            public Void apply(String s) {
//                return s.length();
                return null;
            }
        };

        System.out.println(function.apply("abcde"));

        String s = ori;
        ori = "1";
        System.out.println(s);

    }

    public static int gen(){
        return 300 + (int)(Math.random() * 300);
    }


    interface Function<T, R>{
        R apply(T arg);
    }



}
