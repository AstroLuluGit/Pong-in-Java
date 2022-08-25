package com.company;

import javax.swing.*;
import java.awt.*;

public class Paddle extends JPanel {
    int x, y, width, height, id, score;
    Rectangle pad;

    public Paddle(int id, int x, int y, int width, int height, int score) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.height = height;
        this.width = width;
        this.score = score;
    }

    public void drawPaddle(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g);

        pad = new Rectangle(x, y, width, height);
        g2d.setColor(Color.BLACK);
        g2d.fill(pad);
    }
    public void drawScore(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        if (id == 1) {
            g2d.drawString("Points: " +score, 10, 20);
        } else {
            g2d.drawString("Points " +  score, 630, 20);
        }

        g2d.drawString("Press Space to Start Playing", 270, 630);
        g2d.drawString("W and S for Left Paddle Movement, Arrow Up and Down for Right Paddle Movement", 130, 650);
    }

    public boolean checkIntersection(int x, int y, int width, int height) {
        if (pad != null) return pad.intersects(x, y, width, height);
        return true;
    }

    public void movePaddle(MovementDirection direction) {
        switch (direction) {
            case UP:
                y -= 10;
                if (y < 0) {
                    y = 0;
                }
                break;
            case DOWN:
                y += 10;
                if (y > 560) {
                    y = 560;
                }
                break;
            default:
                break;
        }
    }

    public enum MovementDirection {
        UP,
        DOWN
    }
}
