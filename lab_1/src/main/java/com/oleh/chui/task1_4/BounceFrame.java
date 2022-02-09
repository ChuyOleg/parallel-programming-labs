package com.oleh.chui.task1_4;

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
        JButton buttonAddGreenBall = new JButton("Add green ball");
        JButton buttonStop = new JButton("Stop");

        buttonAddBlueBall.addActionListener(e -> createBallAndSetPriorityAndStartThread(BallColor.BLUE));

        buttonAddRedBall.addActionListener(e -> createBallAndSetPriorityAndStartThread(BallColor.RED));

        buttonAddGreenBall.addActionListener(e -> createBallAndSetPriorityAndStartThread(BallColor.GREEN));

        buttonStop.addActionListener(e -> System.exit(0));

        goalsNumberComponent = new JLabel("Score: " + canvas.getBallCountInPockets());

        buttonPanel.add(buttonAddBlueBall);
        buttonPanel.add(buttonAddRedBall);
        buttonPanel.add(buttonAddGreenBall);
        buttonPanel.add(buttonStop);
        buttonPanel.add(goalsNumberComponent);

        JPanel experimentsPanel = new JPanel();
        experimentsPanel.setBackground(Color.YELLOW);
        createButtonExperimentAndAddToPanel("Experiment 1", 1, 10, experimentsPanel);
        createButtonExperimentAndAddToPanel("Experiment 2", 1, 100, experimentsPanel);
        createButtonExperimentAndAddToPanel("Experiment 3", 1, 1000, experimentsPanel);
        createButtonExperimentAndAddToPanel("Experiment 4", 1, 2000, experimentsPanel);

        content.add(buttonPanel, BorderLayout.NORTH);
        content.add(experimentsPanel, BorderLayout.SOUTH);

    }

    public void addOnePointToScore() {
        goalsNumberComponent.setText("Score: " + canvas.getBallCountInPockets());
    }

    public void createButtonExperimentAndAddToPanel(String name, int redBallsCount, int blueBallsCount, JPanel panel) {
        JButton buttonExperiment = new JButton(name);
        buttonExperiment.addActionListener(e -> {

            for (int index = 0; index < redBallsCount; index++) {
                Ball ball = createBallAndSetPriorityAndStartThread(BallColor.RED);
                ball.setCoordinates(Config.EXPERIMENT_COORDINATES.getKey(), Config.EXPERIMENT_COORDINATES.getValue());
            }

            for (int index = 0; index < blueBallsCount; index++) {
                Ball ball = createBallAndSetPriorityAndStartThread(BallColor.BLUE);
                ball.setCoordinates(Config.EXPERIMENT_COORDINATES.getKey(), Config.EXPERIMENT_COORDINATES.getValue());
            }

        });
        panel.add(buttonExperiment);
    }

    private Ball createBallAndSetPriorityAndStartThread(BallColor ballColor) {
        Ball ball = new Ball(canvas, BounceFrame.this, ballColor);
        BallThread thread = new BallThread(ball, canvas.getThreads());
        thread.setPriority(ball.getBallColor().getPriorityValue());
        canvas.addBall(ball, thread);
        thread.start();
        return ball;
    }

}
