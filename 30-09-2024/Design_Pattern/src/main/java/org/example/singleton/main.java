package org.example.singleton;

public class main {
    public static void main(String[] args) {
        A firstInstance = A.getA();
        A secondInstance = A.getA();

        // Check if both are the same instance
        if (firstInstance == secondInstance) {
            System.out.println("Both are the same instance");
        }
    }
}
