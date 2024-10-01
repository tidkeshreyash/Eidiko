package org.example.prototype;

public class Rectangle implements Prototype{

    private int width;
    private int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    @Override
    public Prototype clone() {
        return new Rectangle(this.width, this.height);
    }


    public void draw() {
        System.out.println("Drawing a rectangle with width: " + width + " and height: " + height);
    }
}
