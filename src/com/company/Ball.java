package com.company;

import java.awt.*;

public class Ball {
    private int x, y, width, height, rotation;
    private double speed;

    private MyLabel label;

    public Ball(int x, int y, int width, int height, int rotation) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rotation = rotation;
    }

    public void drawBall(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.RED);
        g2d.fillOval(x, y, width, height);
    }

    public void moveBall(Window window) {
        double velocityX, velocityY;

        double radian = Math.PI / 180;
        velocityX = speed * Math.cos(rotation * radian);
        velocityY = speed * Math.sin(rotation * radian);
        checkCollision();
        x += velocityX;
        y += velocityY;
        window.repaint();
    }

    public void resetBall() {
        x = 350;
        y = 350;
    }

    public void initLabel(MyLabel newLabel) {
        label = newLabel;
    }

    public void checkCollision() {
        Paddle paddle1 = label.getPaddle1();
        Paddle paddle2 = label.getPaddle2();
        boolean paddle1Collision = paddle1.checkIntersection(x, y, width, height);
        boolean paddle2Collision = paddle2.checkIntersection(x, y, width, height);
        if (paddle1Collision) {
            calculateNewRotation("paddle1");
            x += 10;
        }
        if (paddle2Collision) {
            calculateNewRotation("paddle2");
            x -= 10;
        }
        if (y <= 5) {
            calculateNewRotation("wall");
            y += 10;
        }
        if (y >= 640) {
            calculateNewRotation("wall");
            y -= 11;
            System.out.println("Hit bottom wall");
        }

        if (x <= 10) {
            label.resetGame();
            resetBall();
            paddle2.score += 1;
        }
        if (x > 690) {
            label.resetGame();
            resetBall();
            paddle1.score += 1;
        }
    }

    public void calculateNewRotation(String place) {
        if (place == "paddle1") {
            int relativeHitPosition = (label.getPaddle1().y - y) * (-1);
            if ((relativeHitPosition >= -10) && (relativeHitPosition <= 45)) {
                rotation = 310;
            } else if ((relativeHitPosition > 45) && (relativeHitPosition <= 55)) {
                rotation = 0;
            } else if ( relativeHitPosition > 55 && relativeHitPosition <= 110) {
                rotation = 45;
            } else {
                rotation = 180 - rotation;
            }
        }
        if (place == "paddle2") {
            int relativeHitPosition = (label.getPaddle2().y - y) * (-1);
            if ((relativeHitPosition >= -10) && (relativeHitPosition <= 45)) {
                rotation = 200;
            } else if ((relativeHitPosition > 45) && (relativeHitPosition <= 55)) {
                rotation = 180;
            } else if ( relativeHitPosition > 55 && relativeHitPosition <= 110) {
                rotation = 130;
            } else {
                rotation = 180 - rotation;
            }
        }
        if (place == "wall") {
            rotation = 0 - rotation;
        }
    }

    public double setSpeed(double newSpeed) {
        return speed = newSpeed;
    }

    public int setRotation(int newRotation) {
        return rotation = newRotation;
    }
}
