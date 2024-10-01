package org.example.prototype;

public class Application {
    public static void main(String[] args) {
        Rectangle originalRectangle = new Rectangle(10, 20);
        Circle originalCircle = new Circle(15);

        // Clone the original objects
        Rectangle clonedRectangle = (Rectangle) originalRectangle.clone();
        Circle clonedCircle = (Circle) originalCircle.clone();

        // Modify the cloned objects
        clonedRectangle.setWidth(30);  // Change the width of the cloned rectangle
        clonedCircle.setRadius(25);    // Change the radius of the cloned circle

        // Draw both original and cloned shapes
        System.out.println("Original Objects:");
        originalRectangle.draw();  // Output: Drawing a rectangle with width: 10 and height: 20
        originalCircle.draw();     // Output: Drawing a circle with radius: 15

        System.out.println("Cloned Objects:");
        clonedRectangle.draw();    // Output: Drawing a rectangle with width: 30 and height: 20
        clonedCircle.draw();
    }
}
