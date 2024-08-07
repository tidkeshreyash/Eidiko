package OptionalClass;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        String[] str = new String[10];
        Optional<String> checkNull = Optional.ofNullable(str[5]);
        if(checkNull.isPresent()){
            String lowercase = str[5].toLowerCase();
            System.out.println(lowercase);
        }else {
            System.out.println("String Value is not present");
        }


        
    }
}
