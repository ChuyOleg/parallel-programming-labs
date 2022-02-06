package com.oleh.chui;

public enum BallColor {

    BLUE(2),
    RED(10);

    private final int priorityValue;

    BallColor(int priorityValue) {
        this.priorityValue = priorityValue;
    }
    
    public int getPriorityValue() {
        return priorityValue;
    }
     
}
