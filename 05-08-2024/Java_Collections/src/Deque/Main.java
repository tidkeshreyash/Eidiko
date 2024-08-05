package Deque;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(1);
        dq.add(2);
        dq.add(3);
        dq.add(4);

        Iterator itr = dq.iterator();

        while(itr.hasNext()){
            System.out.print(itr.next() + " ");
        }
        System.out.println();
        dq.addFirst(0);

        System.out.println(dq);

        dq.removeLast();
        System.out.println(dq);


    }
}
