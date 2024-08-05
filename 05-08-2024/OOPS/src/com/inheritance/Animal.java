package com.inheritance;

public class Animal {
    String name;
    int age;


    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }


    public void run() {
        System.out.println("running");
    }
}
