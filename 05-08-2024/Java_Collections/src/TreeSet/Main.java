package TreeSet;

import java.util.TreeSet;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        TreeSet<Integer> al=new TreeSet<>();
        al.add(1);
        al.add(2);
        al.add(3);
        al.add(4);

        Iterator itr=al.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
    }
}
