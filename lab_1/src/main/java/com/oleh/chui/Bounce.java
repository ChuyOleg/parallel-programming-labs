package com.oleh.chui;

import javax.swing.*;

public class Bounce {

    public static void main(String[] args) {
        BounceFrame frame = new BounceFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
        System.out.println("Thread name = " +
                Thread.currentThread().getName());
    }
    
//    public static void main(String[] args) {
//        Thread writer1 = new Thread(() -> System.out.print("-"));
//
//        Thread writer2 = new Thread(() -> System.out.print("|"));
//
//        writer1.start();
//        writer2.start();
//    }

}
