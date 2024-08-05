package com.polymorphism;

public class Main {
    public static void main(String[] args) {
        Animal an = new Animal();
        Animal dog = new Dog();
        Animal cat = new Cat();

        an.makeSound();
        dog.makeSound();
        cat.makeSound();

    }
}
