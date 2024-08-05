package Map;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<Integer,Integer> mp = new HashMap<>();
        mp.put(1,10);
        mp.put(2,20);
        mp.put(3,30);
        mp.put(4,40);

        for(Map.Entry<Integer,Integer> ele : mp.entrySet()){
            System.out.println(ele.getKey() + " --> " + ele.getValue());
        }

        System.out.println(mp);

    }
}
