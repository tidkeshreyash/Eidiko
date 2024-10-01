package org.example.factory;

public class EmailNotification implements N{
    @Override
    public void notifyUser() {
        System.out.println("Sending an Email Notification");
    }
}
