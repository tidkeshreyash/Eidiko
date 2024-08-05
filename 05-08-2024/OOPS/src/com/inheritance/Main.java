package com.inheritance;

public class Main{

    public static void main(String[] args) {
        Dog myDog = new Dog("Buddy", 3, "Golden Retriever");


        System.out.println("Name: " + myDog.name);
        System.out.println("Age: " + myDog.age);
        System.out.println("Breed: " + myDog.breed);
        myDog.run();
        myDog.sound();
        myDog.fetch();
    }
}