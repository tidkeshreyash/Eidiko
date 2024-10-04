package org.example.behavioralPattern.commandPattern;

public class LightOffCommand implements Command{

    public Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }
}
