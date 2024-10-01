package org.example.factory;

public class SMSNotification implements N{
    @Override
    public void notifyUser() {
        System.out.println("Sending an SMS Notification");

    }
}
