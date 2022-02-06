package com.oleh.chui;

public class BallThread extends Thread {

    private final Ball b;

    public BallThread(Ball ball){
        b = ball;
    }
    @Override
    public void run(){
        try{
            while (true) {
                b.move();
                if (b.inHole()) {
                    b.getCanvas().delete(b);
                    b.getBounceFrame().addOnePointToScore();
                    break;
                }
                Thread.sleep(Config.BALL_SLEEP);
            }
        } catch(InterruptedException ex){
            ex.printStackTrace();
        }
    }

}
