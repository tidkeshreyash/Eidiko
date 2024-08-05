package PriorityQueue;

import java.util.Iterator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(1);
        pq.add(2);
        pq.add(3);
        pq.add(4);

        Iterator itr = pq.iterator();

        while(itr.hasNext()){
            System.out.println(itr.next());
        }
        pq.add(5);
        pq.add(5);
        pq.remove();
        System.out.println(pq);
    }
}
