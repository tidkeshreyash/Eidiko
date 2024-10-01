package org.example.factorymethod;

abstract class VehicleFactory {
    public abstract Vehicle getVehicle();

    public void buildVehicle(){
        Vehicle vh = getVehicle();
        vh.create();
    }

}
