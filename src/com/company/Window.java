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
            }
        };

        timer = new Timer(10, taskPerformer);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            label.movePaddle(Paddle.MovementDirection.UP, 2);
            repaint();
        }
        if (key == KeyEvent.VK_DOWN) {
            label.movePaddle(Paddle.MovementDirection.DOWN, 2);
            repaint();
        }
        if (key == KeyEvent.VK_W) {
            label.movePaddle(Paddle.MovementDirection.UP, 1);
            repaint();
        }
        if (key == KeyEvent.VK_S) {
            label.movePaddle(Paddle.MovementDirection.DOWN, 1);
            repaint();
        }
        if (key == KeyEvent.VK_SPACE) {
            if (gameStarted) return;
            gameStarted = true;
            initBall();
            timer.start();
            System.out.println("Paddle 1 Score is " + label.getPaddle1().score);
            System.out.println("Paddle 2 Score is " + label.getPaddle2().score);

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
