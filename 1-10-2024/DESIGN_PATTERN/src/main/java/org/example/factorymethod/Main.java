package org.example.factorymethod;

public class Main {
    public static void main(String[] args) {
        VehicleFactory car = new CarFactory();
        car.buildVehicle();
    }
}
