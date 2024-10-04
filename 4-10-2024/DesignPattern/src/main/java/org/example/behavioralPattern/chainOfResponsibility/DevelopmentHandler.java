package org.example.behavioralPattern.chainOfResponsibility;

public class DevelopmentHandler extends SupportHandler{
    @Override
    public void handleRequest(String request) {
        if (request.equals("complex")) {
            System.out.println("Development: Handling complex request.");
        } else {
            if (nextHandler != null) {
                nextHandler.handleRequest(request);
            } else {
                System.out.println("Request cannot be handled.");
            }
        }
    }
}
