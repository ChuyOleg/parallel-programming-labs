package com.oleh.chui;

public class Bounce {

//    public static void main(String[] args) {
//        BounceFrame frame = new BounceFrame();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        frame.setVisible(true);
//        System.out.println("Thread name = " +
//                Thread.currentThread().getName());
//    }
    
    public static void main(String[] args) {
        SymbolPrinter symbolPrinter = new SymbolPrinter(99, 100);

        Thread threadOne = new SymbolThread(symbolPrinter, "|");
        Thread threadTwo = new SymbolThread(symbolPrinter, "-");

        threadOne.start();
        threadTwo.start();
    }

}
