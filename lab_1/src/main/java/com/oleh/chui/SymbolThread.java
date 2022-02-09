package com.oleh.chui;

public class SymbolThread extends Thread {

    private final SymbolPrinter symbolPrinter;
    private final String symbol;

    public SymbolThread(SymbolPrinter symbolPrinter, String symbol) {
        this.symbolPrinter = symbolPrinter;
        this.symbol = symbol;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (symbolPrinter) {
                while (symbol.equals(symbolPrinter.getSymbol())) {
                    try {
                        symbolPrinter.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (symbolPrinter.getLineCounter() >= symbolPrinter.getMAX_LINE_COUNTER()) {
                    symbolPrinter.changeSymbol(symbol);
                    symbolPrinter.notifyAll();
                    break;
                }

                symbolPrinter.changeSymbol(symbol);
                symbolPrinter.printSymbol(symbol);
                symbolPrinter.notifyAll();
            }
        }
    }
}
