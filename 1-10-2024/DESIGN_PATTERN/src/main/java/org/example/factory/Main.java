package org.example.factory;

public class Main {
    public static void main(String[] args) {
        NotificationFactory factory = new NotificationFactory();

        // Step 2: Get the desired notification type
        N notification = factory.createNotification("email");

        // Step 3: Use the notification object to notify the user
        notification.notifyUser();  // Output: Sending an Email Notification

        // Example of getting another notification type
        N smsNotification = factory.createNotification("sms");
        smsNotification.notifyUser();  // Output: Sending an SMS Notification
    }
}
