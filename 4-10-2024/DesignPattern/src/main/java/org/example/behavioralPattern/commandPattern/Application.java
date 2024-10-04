package org.example.behavioralPattern.commandPattern;

public class Application {
    public static void main(String[] args) {
        Light livingRoomLight = new Light();
        Fan ceilingFan = new Fan();

        // Commands
        Command lightOn = new LightOnCommand(livingRoomLight);
        Command lightOff = new LightOffCommand(livingRoomLight);
        Command fanOn = new FanOnCommand(ceilingFan);
        Command fanOff = new FanOffCommand(ceilingFan);

        // Invoker (Remote control)
        RemoteControl remote = new RemoteControl();

        // Turn on the light
        remote.setCommand(lightOn);
        remote.pressButton();  // Output: Light is ON

        // Turn off the light
        remote.setCommand(lightOff);
        remote.pressButton();  // Output: Light is OFF

        // Turn on the fan
        remote.setCommand(fanOn);
        remote.pressButton();  // Output: Fan is ON

        // Turn off the fan
        remote.setCommand(fanOff);
        remote.pressButton();  // Output: Fan is OFF
    }
}
