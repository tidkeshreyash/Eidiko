package org.example.structuralPattern.proxyPattern;

public class ImageProxy implements Image{

    private RealImage realImage;
    private String fileName;

    public ImageProxy(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName);  // Loads the image only when needed
        }
        realImage.display();
    }
}
