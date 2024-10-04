package org.example.behavioralPattern.chainOfResponsibility;

public class TechnicalSupportHandler extends SupportHandler{
    @Override
    public void handleRequest(String request) {
        if (request.equals("technical")) {
            System.out.println("Technical Support: Handling technical request.");
        } else {
            if (nextHandler != null) {
                nextHandler.handleRequest(request);
            }
        }
    }
}
