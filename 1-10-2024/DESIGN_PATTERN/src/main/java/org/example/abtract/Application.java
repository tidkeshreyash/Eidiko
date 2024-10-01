package org.example.abtract;

public class Application {
    private Button button;
    private Checkbox checkbox;

    public Application(GUIFactory factory) {
        button = factory.createButton();
        checkbox = factory.createCheckbox();
    }

    public void paint() {
        button.paint();
        checkbox.paint();
    }

    public static void main(String[] args) {
        GUIFactory factory;

        // Example: Creating a Windows UI
        factory = new WindowsFactory();
        Application app = new Application(factory);
        app.paint();  // Outputs Windows UI components

        // Example: Creating a MacOS UI
        factory = new MacFactory();
        app = new Application(factory);
        app.paint();
    }
}
