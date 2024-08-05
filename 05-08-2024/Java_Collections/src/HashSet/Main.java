package HashSet;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        HashSet<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);

//        Iterator itr = set2.iterator();

//        while(itr.hasNext()){
//            System.out.println(itr.next());
//
//        }

        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        HashSet<Integer> set2 = new HashSet(list);
        set2.add(4);

        Iterator itr = set2.iterator();

        while(itr.hasNext()){
            System.out.println(itr.next());

        }

    }
}