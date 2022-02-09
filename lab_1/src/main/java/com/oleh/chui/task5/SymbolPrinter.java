package com.oleh.chui.task5;

public class SymbolPrinter {

    private int symbolCounter = 0;
    private int lineCounter = 0;
    private final int MAX_SYMBOL_COUNTER;
    private final int MAX_LINE_COUNTER;
    private String symbol;

    public SymbolPrinter(int max_symbol_counter, int max_line_counter) {
        MAX_SYMBOL_COUNTER = max_symbol_counter;
        MAX_LINE_COUNTER = max_line_counter;
    }

    public void printSymbol(String symbol) {
        System.out.print(symbol);
        symbolCounter++;

        if (symbolCounter > MAX_SYMBOL_COUNTER) {
            System.out.println();
            symbolCounter = 0;
            lineCounter++;
        }
    }

    public void changeSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getLineCounter() {
        return lineCounter;
    }

    public int getMAX_LINE_COUNTER() {
        return MAX_LINE_COUNTER;
    }

    public String getSymbol() {
        return symbol;
    }

}
