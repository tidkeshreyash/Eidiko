package org.example.behavioralPattern.chainOfResponsibility;

public class CustomerServiceHandler extends SupportHandler{

    @Override
    public void handleRequest(String request) {
        if (request.equals("simple")) {
            System.out.println("Customer Service: Handling simple request.");
        } else {
            if (nextHandler != null) {
                nextHandler.handleRequest(request);
            }
        }
    }
}
