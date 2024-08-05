package com.inheritance;

public class Dog extends Animal {
    String breed;


    public Dog(String name, int age, String breed) {
        super(name, age);
        this.breed = breed;
    }


    public void sound(){
        System.out.println("Bark");
    }


    public void fetch() {
        System.out.println(name + " is fetching the ball");
    }
}
