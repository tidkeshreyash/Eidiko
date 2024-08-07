package Collectors;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class Demo{
    int id;
    String name;

    public Demo(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Demo> list= new ArrayList<>();

        list.add(new Demo(1,"SST"));
        list.add(new Demo(2,"SS"));
        list.add(new Demo(3,"ST"));

        Set<String> set = list.stream().map(x -> x.name).collect(Collectors.toSet());
        System.out.println(set);

    }
}
