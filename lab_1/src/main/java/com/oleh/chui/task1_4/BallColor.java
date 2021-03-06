package com.oleh.chui.task1_4;

public enum BallColor {

    BLUE(2),
    RED(10),
    GREEN(10);

    private final int priorityValue;

    BallColor(int priorityValue) {
        this.priorityValue = priorityValue;
    }
    
    public int getPriorityValue() {
        return priorityValue;
    }
     
}
