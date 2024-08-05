package LinkedHashSet;

import java.util.LinkedHashSet;

public class Main {
        public static void main(String[] args) {

            LinkedHashSet<String> set = new LinkedHashSet<>();


            set.add("Apple");
            set.add("Banana");
            set.add("Mango");
            set.add("Watermelon");
            set.add("Orange");

            System.out.println("LinkedHashSet after adding elements: " + set);

              set.remove("Orange");

            System.out.println("New Set " + set);

            set.remove("Banana");

            System.out.println("New Set " + set);



        }
}
