package com.oleh.chui;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Ball {
    private final BallCanvas canvas;
    private final BounceFrame bounceFrame;
    private static final int XSIZE = Config.BALL_WIDTH;
    private static final int YSIZE = Config.BALL_HEIGHT;
    private int x;
    private int y;
    private int dx = Config.BALL_SPEED;
    private int dy = Config.BALL_SPEED;
    private final BallColor ballColor;

    public Ball(BallCanvas c, BounceFrame bounceFrame, BallColor ballColor){
        this.canvas = c;
        this.bounceFrame = bounceFrame;
        this.ballColor = ballColor;

        if(Math.random()<0.5){
            x = new Random().nextInt(this.canvas.getWidth());
            y = 0;
        }else{
            x = 0;
            y = new Random().nextInt(this.canvas.getHeight());
        }

    }

    public void draw (Graphics2D g2) {
        if (ballColor.equals(BallColor.BLUE)) g2.setColor(Color.BLUE);
        else if (ballColor.equals(BallColor.RED)) g2.setColor(Color.RED);
        else if (ballColor.equals(BallColor.GREEN)) g2.setColor(Color.GREEN);
        g2.fill(new Ellipse2D.Double(x,y,XSIZE,YSIZE));
    }

    public void move(){
        x+=dx;
        y+=dy;
        if(x<0){
            x = 0;
            dx = -dx;
        }
        if(x+XSIZE>=this.canvas.getWidth()){
            x = this.canvas.getWidth()-XSIZE;
            dx = -dx;
        }
        if(y<0){
            y=0;
            dy = -dy;
        }
        if(y+YSIZE>=this.canvas.getHeight()){
            y = this.canvas.getHeight()-YSIZE;
            dy = -dy;
        }

        this.canvas.repaint();
    }

    public boolean inHole() {
        return Config.POCKETS_COORDINATES.stream().anyMatch(coordinates ->
                (Math.abs(coordinates.getKey() - x) < Config.POCKET_WIDTH)
                && (Math.abs(coordinates.getValue() - y) < Config.POCKET_HEIGHT));
    }

    public BallCanvas getCanvas() {
        return canvas;
    }

    public BounceFrame getBounceFrame() {
        return bounceFrame;
    }

    public BallColor getBallColor() {
        return ballColor;
    }

    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
