package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class MyLabel extends JLabel {
    int randomRotation;
    private Paddle paddle1;
    private Paddle paddle2;
    private Ball ball;
    private Window window;

    public MyLabel() {
        this.paddle1 = new Paddle(1, 20, 280, 10, 100, 0);
        this.paddle2 = new Paddle(2, 655, 280, 10, 100, 0);
        this.ball = new Ball(350, 350, 10, 10, newBallRotation());
    }

    public void getWindow(Window newWindow) {
        window = newWindow;
    }

    public void movePaddle(Paddle.MovementDirection direction, int id) {
        if (id == 1) {
            paddle1.movePaddle(direction);
            repaint();
        } else {
            paddle2.movePaddle(direction);
            repaint();
        }

    }

    public void resetGame() {
        window.gameStarted = false;
        window.timer.stop();
        ball.resetBall();

        paddle2.resetPaddle();

        this.ball.setRotation(200);
    }
    public int newBallRotation() {
        return ThreadLocalRandom.current().nextInt(10, 340 +1);
    }

    @Override
    public void paintComponent(Graphics g) {
        paddle1.drawPaddle(g);
        paddle1.drawScore(g);
        paddle2.drawPaddle(g);
        paddle2.drawScore(g);
        ball.drawBall(g);
    }

    public Paddle getPaddle1() {
        return paddle1;
    }

    public Paddle getPaddle2() {
        return paddle2;
    }

    public Ball getBall() {
        return ball;
    }
}
