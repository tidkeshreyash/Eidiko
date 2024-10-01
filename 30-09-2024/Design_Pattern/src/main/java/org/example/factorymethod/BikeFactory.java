package org.example.factorymethod;

public class BikeFactory extends VehicleFactory {
    @Override
    public Vehicle getVehicle() {
        return new Bike();
    }
}
