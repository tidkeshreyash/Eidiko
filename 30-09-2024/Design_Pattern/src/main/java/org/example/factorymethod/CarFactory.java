package org.example.factorymethod;

public class CarFactory extends VehicleFactory {
    @Override
   public Vehicle getVehicle() {
        return new Car();
    }
}
