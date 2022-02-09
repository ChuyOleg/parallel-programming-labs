package com.oleh.chui.task1_4;

import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class BallCanvas extends JPanel {

    private final List<Pair<Integer, Integer>> pocketsCoordinates = Config.POCKETS_COORDINATES;
    private final Queue<Ball> balls = new ConcurrentLinkedDeque<>();
    private final Queue<BallThread> threads = new ConcurrentLinkedDeque<>();
    private int ballCountInPockets = 0;

    public void addBall(Ball b, BallThread thread){
        this.balls.add(b);
        this.threads.add(thread);
    }

    public synchronized void deleteBall(Ball ball, BallThread thread) {
        this.balls.remove(ball);
        this.threads.remove(thread);
        ballCountInPockets++;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        for (Pair<Integer, Integer> coordinates : pocketsCoordinates) {
            g2.setColor(Config.POCKET_COLOR);
            g2.fill(new Ellipse2D.Double(
                    coordinates.getKey(), coordinates.getValue(), Config.POCKET_WIDTH, Config.POCKET_HEIGHT)
            );
        }
        for (Ball b : balls) {
            b.draw(g2);
        }
    }

    public Queue<BallThread> getThreads() {
        return threads;
    }

    public int getBallCountInPockets() {
        return ballCountInPockets;
    }

}
