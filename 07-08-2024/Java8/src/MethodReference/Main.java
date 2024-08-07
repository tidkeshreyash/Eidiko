package MethodReference;

interface  Ref{
    void say();
}

public class Main {
    public static  void saySomething(){
        System.out.println("This is static method");
    }
    public static void main(String[] args) {

        Ref rf = Main::saySomething;
        rf.say();


    }
}