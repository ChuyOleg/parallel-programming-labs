package com.oleh.chui;

import javax.swing.*;
import java.awt.*;

public class BounceFrame extends JFrame {

    private final BallCanvas canvas;
    public static final int WIDTH = Config.BOUNCE_FRAME_WIDTH;
    public static final int HEIGHT = Config.BOUNCE_FRAME_HEIGHT;

    private final JLabel goalsNumberComponent;

    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Bounce program");
        this.canvas = new BallCanvas();
        Container content = this.getContentPane();
        content.add(this.canvas, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);
        JButton buttonAddBlueBall = new JButton("Add blue ball");
        JButton buttonAddRedBall = new JButton("Add red ball");
        JButton buttonStop = new JButton("Stop");

        buttonAddBlueBall.addActionListener(e -> {

            Ball b = new Ball(canvas, BounceFrame.this, BallColor.BLUE);
            canvas.add(b);

            BallThread thread = new BallThread(b);
            thread.setPriority(b.getBallColor().getPriorityValue());
            thread.start();
        });

        buttonAddRedBall.addActionListener(e -> {

            Ball b = new Ball(canvas, BounceFrame.this, BallColor.RED);
            canvas.add(b);

            BallThread thread = new BallThread(b);
            thread.setPriority(b.getBallColor().getPriorityValue());
            thread.start();
        });

        buttonStop.addActionListener(e -> System.exit(0));

        goalsNumberComponent = new JLabel("Score: " + canvas.getBallCountInPockets());

        buttonPanel.add(buttonAddBlueBall);
        buttonPanel.add(buttonAddRedBall);
        buttonPanel.add(buttonStop);
        buttonPanel.add(goalsNumberComponent);

        JPanel experimentsPanel = new JPanel();
        experimentsPanel.setBackground(Color.YELLOW);
        JButton buttonExperiment1 = createButtonExperiment1("Experiment 1", 1, 10);
        JButton buttonExperiment2 = createButtonExperiment1("Experiment 2", 1, 100);
        JButton buttonExperiment3 = createButtonExperiment1("Experiment 3", 1, 1000);
        JButton buttonExperiment4 = createButtonExperiment1("Experiment 4", 1, 2000);
        experimentsPanel.add(buttonExperiment1);
        experimentsPanel.add(buttonExperiment2);
        experimentsPanel.add(buttonExperiment3);
        experimentsPanel.add(buttonExperiment4);

        content.add(buttonPanel, BorderLayout.NORTH);
        content.add(experimentsPanel, BorderLayout.SOUTH);

    }

    public void addOnePointToScore() {
        goalsNumberComponent.setText("Score: " + canvas.getBallCountInPockets());
    }

    public JButton createButtonExperiment1(String name, int redBallsCount, int blueBallsCount) {
        JButton buttonExperiment = new JButton(name);
        buttonExperiment.addActionListener(e -> {

            for (int index = 0; index < redBallsCount; index++) {
                Ball b = new Ball(canvas, BounceFrame.this, BallColor.RED);
                canvas.add(b);
                BallThread thread = new BallThread(b);
                thread.setPriority(b.getBallColor().getPriorityValue());
                thread.start();
                b.setCoordinates(50, 50);
            }

            for (int index = 0; index < blueBallsCount; index++) {
                Ball b = new Ball(canvas, BounceFrame.this, BallColor.BLUE);
                canvas.add(b);
                BallThread thread = new BallThread(b);
                thread.setPriority(b.getBallColor().getPriorityValue());
                thread.start();
                b.setCoordinates(50, 50);
            }

        });
        return buttonExperiment;
    }

}
