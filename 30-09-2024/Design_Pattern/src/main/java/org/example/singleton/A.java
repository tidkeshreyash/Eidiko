package org.example.singleton;

public class A {
    private static A obj = new A();

    private A() {
    }

    public static synchronized A getA(){
        if (obj == null)
            obj = new A();
        return obj;
    }

    public static void main(String args[]){

    }

}
