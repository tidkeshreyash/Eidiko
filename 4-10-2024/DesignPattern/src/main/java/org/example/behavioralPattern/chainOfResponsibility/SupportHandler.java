package org.example.behavioralPattern.chainOfResponsibility;

abstract class SupportHandler {
    protected SupportHandler nextHandler;

    // Set the next handler in the chain
    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    // Handle the request or pass it to the next handler
    public abstract void handleRequest(String request);
}
