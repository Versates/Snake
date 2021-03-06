package onpu.oop.kurs;

import java.awt.*;
import java.util.ArrayList;

public class Snake {

    public static final int DIR_PAUSE = 0;
    public static final int DIR_UP = 1;
    public static final int DIR_RIGHT = 2;
    public static final int DIR_DOWN = 3;
    public static final int DIR_LEFT = 4;

    private ArrayList<Point> body = new ArrayList<Point>();
    private int bodySize;
    private int direction = DIR_PAUSE;

    Snake(int x0, int y0, int size) {
        bodySize = size;
        int x = x0 * size + 2;
        int y = y0 * size + 2;
        for (int i = 0; i < 1; i++) {
            body.add(new Point(x, y));
        }
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public ArrayList<Point> getBody() {
        return body;
    }

    public void paint(Graphics2D g2) {
        for (Point p : body) {
            g2.setColor(Color.blue);
            g2.fillArc(p.x, p.y, bodySize, bodySize, 0, 360);
            g2.setColor(Color.magenta);
            g2.drawArc(p.x, p.y, bodySize, bodySize, 0, 360);
        }
        Point p = body.get(body.size() - 1);
        g2.setColor(Color.black);
        g2.fillArc(p.x + bodySize / 2 - 2, p.y + bodySize / 2 - 2, 4, 4, 0, 360);
        g2.setColor(Color.white);
        g2.fillArc(p.x + bodySize / 2 - 1, p.y + bodySize / 2 - 1, 2, 2, 0, 360);
    }

    public Point move() {
        Point last = body.get(body.size() - 1);
        Point pp = last;
        switch (direction) {
            case DIR_PAUSE:
                break;
            case DIR_UP:
                body.remove(0);
                pp = new Point(last.x, last.y - bodySize);
                body.add(pp);
                delete();
                break;
            case DIR_RIGHT:
                body.remove(0);
                pp = new Point(last.x + bodySize, last.y);
                body.add(pp);
                delete();
                break;
            case DIR_DOWN:
                body.remove(0);
                pp = new Point(last.x, last.y + bodySize);
                body.add(pp);
                delete();
                break;
            case DIR_LEFT:
                body.remove(0);
                pp = new Point(last.x - bodySize, last.y);
                body.add(pp);
                delete();
                break;
        }
        return pp;
    }

    public int getSpeed() {
        return Data.getSpeed();
    }

    public void expand() {
        body.add(0, new Point(body.get(0).x, body.get(0).y));
    }

    public void delete() {
        for (int i = 1; i < body.size(); i++) {
            if (body.get(i).equals(body.get(0))) {
                body.removeAll(body.subList(i, body.size()));
            }
        }
    }
}