package org.example.builder;

public class Application {
    public static void main(String[] args) {
        Car car1 = new Car.Builder("Tesla Model S")
                .engineType("Electric")
                .color("Red")
                .hasSunroof(true)
                .build();

        Car car2 = new Car.Builder("Ford Mustang")
                .engineType("Gasoline")
                .color("Blue")
                .hasSunroof(false)
                .build();

        // Print the car details
        System.out.println(car1);
        System.out.println(car2);

    }
}
