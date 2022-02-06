package com.oleh.chui;

import java.util.Queue;

public class BallThread extends Thread {

    private final Queue<BallThread> threadQueue;
    private final Ball activeBall;

    public BallThread(Ball ball, Queue<BallThread> threadQueue){
        this.activeBall = ball;
        this.threadQueue = threadQueue;
    }

    @Override
    public void run(){
        try{
            while (true) {
                if (!activeBall.getBallColor().equals(BallColor.GREEN)) {
                    threadQueue.stream()
                            .filter(ballThread -> ballThread.getActiveBall().getBallColor().equals(BallColor.GREEN))
                            .forEach(ballThread -> {
                                try {
                                    ballThread.join();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            });
                }
                activeBall.move();
                if (activeBall.inHole()) {
                    activeBall.getCanvas().deleteBall(activeBall, this);
                    activeBall.getBounceFrame().addOnePointToScore();
                    break;
                }
                Thread.sleep(Config.BALL_SLEEP);
            }
        } catch(InterruptedException ex){
            ex.printStackTrace();
        }
    }

    public Ball getActiveBall() {
        return activeBall;
    }
}
