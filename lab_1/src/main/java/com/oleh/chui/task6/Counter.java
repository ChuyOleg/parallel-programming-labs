package com.oleh.chui.task6;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {

    private int counter = 0;
    private AtomicInteger atomicCounter = new AtomicInteger(0);

    public void increment() {
        counter++;
    }

    public void incrementAtomic() {
        atomicCounter.incrementAndGet();
    }

    public void decrement() {
        counter--;
    }

    public void decrementAtomic() {
        atomicCounter.decrementAndGet();
    }

    public synchronized void changeCounter(boolean isIncrementor) {
        if (isIncrementor) {
            increment();
        } else {
            decrement();
        }
    }

    public int getCounter() {
        return counter;
    }

    public int getAtomicCounter() {
        return atomicCounter.get();
    }

}
