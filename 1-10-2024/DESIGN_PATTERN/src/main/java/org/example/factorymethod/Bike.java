package org.example.factorymethod;

public class Bike implements Vehicle{
    @Override
    public void create() {
        System.out.println("Bike is created");
    }
}
