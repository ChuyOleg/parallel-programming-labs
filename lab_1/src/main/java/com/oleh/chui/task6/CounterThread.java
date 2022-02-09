package com.oleh.chui.task6;

public class CounterThread extends Thread {

    private final Counter counter;
    private final boolean isIncrementor;
    private final int LOOP_SIZE;

    public CounterThread(Counter counter, boolean isIncrementor, int loopSize) {
        this.counter = counter;
        this.isIncrementor = isIncrementor;
        this.LOOP_SIZE = loopSize;
    }

    @Override
    public void run() {
        for (int index = 0; index < LOOP_SIZE; index++) {
            // 1 Method
            synchronized (counter) {
                if (isIncrementor) {
                    counter.increment();
                } else {
                    counter.decrement();
                }
            }

            // 2 Method
//            counter.changeCounter(isIncrementor);

            // 3 Method
//            if (isIncrementor) {
//                counter.incrementAtomic();
//            } else {
//                counter.decrementAtomic();
//            }

        }
    }

}
