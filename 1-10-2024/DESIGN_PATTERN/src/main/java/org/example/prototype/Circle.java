package org.example.prototype;

public class Circle implements Prototype{

    private int radius;

    public Circle(int radius) {
        this.radius = radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }


    @Override
    public Prototype clone() {
        return new Circle(this.radius);
    }


    public void draw() {
        System.out.println("Drawing a circle with radius: " + radius);
    }
}
