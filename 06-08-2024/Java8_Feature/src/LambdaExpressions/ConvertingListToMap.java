package LambdaExpressions;

import java.util.ArrayList;
import java.util.*;

class ListItems{
    int id;
    String name;

    public ListItems(int id,String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }



    public String getName() {
        return name;
    }


}
public class ConvertingListToMap {
    public static void main(String[] args) {
        ArrayList<ListItems> list = new ArrayList<>();
        list.add(new ListItems(1,"Shreyash"));
        list.add(new ListItems(2,"ST"));
        list.add(new ListItems(3,"SST"));
        list.add(new ListItems(4,"SS"));

        Map<Integer,String> mp = new HashMap<>();

        list.forEach(
                (n) -> {
                    mp.put(n.getId(),n.getName());
                });

        System.out.println(mp);

    }
}
