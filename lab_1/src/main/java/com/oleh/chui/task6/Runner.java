package com.oleh.chui.task6;

public class Runner {

    public static void main(String[] args) throws InterruptedException {
        final int LOOP_SIZE = 1000000;
        Counter counter = new Counter();

        Thread incrementThread = new CounterThread(counter, true, LOOP_SIZE);
        Thread decrementThread = new CounterThread(counter, false, LOOP_SIZE);

        incrementThread.start();
        decrementThread.start();

        incrementThread.join();
        decrementThread.join();

        System.out.println(counter.getCounter());
//        System.out.println(counter.getAtomicCounter());

    }

}
