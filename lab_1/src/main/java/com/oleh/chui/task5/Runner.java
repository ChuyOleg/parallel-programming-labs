package com.oleh.chui.task5;

public class Runner {

    public static void main(String[] args) {
        SymbolPrinter symbolPrinter = new SymbolPrinter(99, 100);

        Thread threadOne = new SymbolThread(symbolPrinter, "|");
        Thread threadTwo = new SymbolThread(symbolPrinter, "-");

        threadOne.start();
        threadTwo.start();
    }

}
