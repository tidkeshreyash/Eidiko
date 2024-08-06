package Stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

        System.out.println(list);

        List<Integer> ls = list.stream().filter(i -> i%2 == 0).collect(Collectors.toList());

        System.out.println(ls);

        List<Integer> ls1 = list.stream().map(i -> i+5).collect(Collectors.toList());

        System.out.println(ls1);

    }
}
