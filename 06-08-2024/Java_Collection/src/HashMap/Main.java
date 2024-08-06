package HashMap;

import java.util.*;

public class Main {
    public static void main(String[] args) {
//        int arr[] = {6,2,8,3,5,1};
        HashMap<Integer,Integer> mp = new HashMap<>();

//        for(int i=0; i<arr.length; i++){
//

        mp.put(1,2);
        mp.put(2,2);
        mp.put(3,2);
        mp.put(4,2);

        for(Map.Entry<Integer,Integer> ele : mp.entrySet()){
            System.out.println(ele.getKey() +  "-->" + ele.getValue());

        }





    }
}