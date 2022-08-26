package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Window extends JFrame implements KeyListener {
    public MyLabel label = new MyLabel();
    public int width = 700, height = 700;
    public boolean gameStarted = false;
    public Timer timer;
    private boolean leftUpPressed = false;
    private boolean leftDownPressed = false;
    private boolean rightUpPressed = false;
    private boolean rightDownPressed = false;

    public void drawWindow(String title) {
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setResizable(false);
        setVisible(true);
        setFocusable(true);
        addKeyListener(this);
        getContentPane().add(label);
    }

    public void initBall() {
        Window that = this;
        label.getWindow(that);
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                label.getBall().setSpeed(5);
                label.getBall().initLabel(label);
                label.getBall().moveBall(that);

                if (rightUpPressed) {
                    label.movePaddle(Paddle.MovementDirection.UP, 2);
                }
                if (rightDownPressed) {
                    label.movePaddle(Paddle.MovementDirection.DOWN, 2);
                }
                if (leftUpPressed) {
                    label.movePaddle(Paddle.MovementDirection.UP, 1);
                }
                if (leftDownPressed) {
                    label.movePaddle(Paddle.MovementDirection.DOWN, 1);
                }

            }
        };

        timer = new Timer(10, taskPerformer);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            rightUpPressed = true;
        }
        if (key == KeyEvent.VK_DOWN) {
            rightDownPressed = true;
        }
        if (key == KeyEvent.VK_W) {
            leftUpPressed = true;
        }
        if (key == KeyEvent.VK_S) {
            leftDownPressed = true;
        }
        if (key == KeyEvent.VK_SPACE) {
            if (gameStarted) { label.resetGame();} else {

                gameStarted = true;
                initBall();
                timer.start();
            }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            rightUpPressed = false;
        }
        if (key == KeyEvent.VK_DOWN) {
            rightDownPressed = false;
        }
        if (key == KeyEvent.VK_W) {
            leftUpPressed = false;
        }
        if (key == KeyEvent.VK_S) {
            leftDownPressed = false;
        }
    }
}
