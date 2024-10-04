package org.example.behavioralPattern.commandPattern;

public class RemoteControl {
    private Command command;

    // Set the command for the remote control
    public void setCommand(Command command) {
        this.command = command;
    }

    // Invoke the command
    public void pressButton() {
        command.execute();
    }
}
