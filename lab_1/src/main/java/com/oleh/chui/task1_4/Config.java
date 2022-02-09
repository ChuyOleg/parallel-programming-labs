package com.oleh.chui.task1_4;

import javafx.util.Pair;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

public class Config {

    private Config() {}

    public final static Integer BOUNCE_FRAME_WIDTH = 500;
    public final static Integer BOUNCE_FRAME_HEIGHT = 400;

    public final static Integer BALL_SPEED = 2;
    public final static Integer BALL_WIDTH = 20;
    public final static Integer BALL_HEIGHT = 20;
    public final static Integer BALL_SLEEP = 5;

    public final static List<Pair<Integer, Integer>> POCKETS_COORDINATES = Arrays.asList(
            new Pair<>(0, 0),
            new Pair<>(0, 260),
            new Pair<>(BOUNCE_FRAME_WIDTH - (2 * BALL_WIDTH), 0),
            new Pair<>(BOUNCE_FRAME_WIDTH - (2 * BALL_WIDTH), 260)
    );

    public final static Integer POCKET_WIDTH = 22;
    public final static Integer POCKET_HEIGHT = 22;
    public final static Color POCKET_COLOR = Color.BLACK;

    public final static Pair<Integer, Integer> EXPERIMENT_COORDINATES = new Pair<>(50, 50);

}
