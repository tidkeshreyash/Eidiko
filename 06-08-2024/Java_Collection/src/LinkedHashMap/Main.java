package LinkedHashMap;

import java.util.*;

class Book{
    String name,author,publisher;
    int quantity;

    public Book(String name, String author, String publisher, int quantity) {
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.quantity = quantity;
    }

}

public class Main {
    public static void main(String[] args) {
        LinkedHashMap<Integer,Book> lhm = new LinkedHashMap<>();
        Book b1 = new Book("Social Science","Shreyash","Marvel",10);
        Book b2 = new Book("Physics","ST","UNO",12);
        Book b3 = new Book("Chemistry","SST","DC",20);
        Book b4 = new Book("Maths","SS","KK",100);


        lhm.put(1,b1);
        lhm.put(2,b2);
        lhm.put(3,b3);
        lhm.put(5,b4);

        for(Map.Entry<Integer,Book> ele : lhm.entrySet()){
            int k = ele.getKey();
            Book b = ele.getValue();
            System.out.println(k+" Details:");
            System.out.println(b.name+" "+b.author+" "+b.publisher+" "+b.quantity);
        }

    }
}
