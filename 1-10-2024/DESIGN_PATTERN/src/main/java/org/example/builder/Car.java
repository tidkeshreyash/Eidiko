package org.example.builder;

public class Car {
    private String model;
    private String engineType;
    private String color;
    private boolean hasSunroof;

    // Private constructor to enforce the use of the builder
    private Car(Builder builder) {
        this.model = builder.model;
        this.engineType = builder.engineType;
        this.color = builder.color;
        this.hasSunroof = builder.hasSunroof;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", engineType='" + engineType + '\'' +
                ", color='" + color + '\'' +
                ", hasSunroof=" + hasSunroof +
                '}';
    }

    // Builder class
    public static class Builder {
        private String model;
        private String engineType;
        private String color;
        private boolean hasSunroof;

        public Builder(String model) {
            this.model = model; // Required parameter
        }

        public Builder engineType(String engineType) {
            this.engineType = engineType;
            return this; // Return the builder for method chaining
        }

        public Builder color(String color) {
            this.color = color;
            return this; // Return the builder for method chaining
        }

        public Builder hasSunroof(boolean hasSunroof) {
            this.hasSunroof = hasSunroof;
            return this; // Return the builder for method chaining
        }

        // Build method to create the Car object
        public Car build() {
            return new Car(this);
        }
    }
}
