package com.abstraction;

abstract class Animal {
    // Abstract method (does not have a body)
    public abstract void makeSound();


    public void sleep() {
        System.out.println("sleeping");
    }
}

class Dog extends Animal {
    public void makeSound() {
        System.out.println("Woof");
    }
}

class Cat extends Animal {
    public void makeSound() {
        System.out.println("Meow");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();
        Cat cat = new Cat();

        dog.makeSound();
        dog.sleep();

        cat.makeSound();
        cat.sleep();
    }
}

