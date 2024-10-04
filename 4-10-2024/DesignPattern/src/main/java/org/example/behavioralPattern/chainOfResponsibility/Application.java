package org.example.behavioralPattern.chainOfResponsibility;

public class Application {
    public static void main(String[] args) {
        SupportHandler customerService = new CustomerServiceHandler();
        SupportHandler technicalSupport = new TechnicalSupportHandler();
        SupportHandler development = new DevelopmentHandler();

        // Set up the chain
        customerService.setNextHandler(technicalSupport);
        technicalSupport.setNextHandler(development);

        // Client sends requests
        System.out.println("Request 1: simple");
        customerService.handleRequest("simple");

        System.out.println("\nRequest 2: technical");
        customerService.handleRequest("technical");

        System.out.println("\nRequest 3: complex");
        customerService.handleRequest("complex");

        System.out.println("\nRequest 4: unknown");
        customerService.handleRequest("unknown");
    }
}
