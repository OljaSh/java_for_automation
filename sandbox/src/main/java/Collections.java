import java.util.Arrays;
import java.util.List;

public class Collections {

    public static void main(String[] args) {

        String[] langs = {"java", "VC#", "Python", "PHP"};
        List<String> languages = Arrays.asList("java", "VC#", "Python", "PHP");

        for (int  i = 0; i < langs.length; i++){
            System.out.println("I want to know " + langs[i]);
        }
        //equal
        for (String l : langs) {
            System.out.println("I want to know " + l);
        }


        for (int i = 0; i < languages.size(); i++) {
            System.out.println("I want to know " + languages.get(i));
        }
        //equal
        for (String l : languages) {
            System.out.println("I want to know " + l);
        }


    }
}
