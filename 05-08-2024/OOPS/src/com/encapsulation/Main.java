package com.encapsulation;
class Student{
    private int age;
    private String name;

    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
public class Main {
    public static void main(String[] args) {
        Student std = new Student(21,"Shreyash");
        System.out.println("Name: " + std.getName());
        System.out.println("Age: " + std.getAge());

        std.setName("ST");
        std.setAge(35);

        System.out.println("Updated Name: " + std.getName());
    }
}
