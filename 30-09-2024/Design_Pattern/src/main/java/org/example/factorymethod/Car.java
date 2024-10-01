package org.example.factorymethod;

public class Car implements Vehicle {

    @Override
    public void create() {
        System.out.println("Car is created");
    }
}
