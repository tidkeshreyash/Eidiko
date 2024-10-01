package org.example.abtract;

public class WindowsCheckbox implements Checkbox{
    @Override
    public void paint() {
        System.out.println("Rendering a checkbox in Windows style.");
    }
}
