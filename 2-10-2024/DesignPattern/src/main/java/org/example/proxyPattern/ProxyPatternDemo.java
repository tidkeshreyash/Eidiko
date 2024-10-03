package org.example.proxyPattern;

public class ProxyPatternDemo {
    public static void main(String[] args) {
        Image image = new ImageProxy("high_resolution_image.jpg");

        // Image will be loaded only when display() is called for the first time
        System.out.println("First time calling display()");
        image.display();

        // Image will not be loaded again, as it has been cached by the proxy
        System.out.println("Second time calling display()");
        image.display();
    }
}
