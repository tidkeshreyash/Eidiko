package Stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Filter {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(9);
        list.add(8);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(5);
        list.add(8);

        list.stream().filter(
                i -> (i%2==0)
                )
                .forEach(
                        i -> System.out.print(i + " ")
                );
        System.out.println();
        list.stream().sorted().forEach(i -> System.out.print(i + " "));
        System.out.println();
        list.stream().distinct().sorted().forEach(i -> System.out.print(i + " "));

        int arr[] = {5,7,1,9,4,3};
        System.out.println();
        Arrays.stream(arr).forEach(i -> System.out.print(i + " "));

    }
}
