/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fractals1;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 * @author Mora
 */
public class Fractal_Coha extends JFrame {

    public static int width = 800, height = 600;

    /**
     * Создаёт, настраивает и выводит фрейм для прорисовки кривой Коха.
     */
    public Fractal_Coha() {
        super("Кривая Коха");
        setSize(width, height);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * Формирует цвета и масштаб, рекурсивно вызывает метод
     */
    public void paint(Graphics g) {
        setBackground(Color.WHITE);
        g.setColor(Color.BLACK);

        Point a = new Point(0, 6);
        Point b = new Point(this.getWidth(), 6);

        drawKochLine(g, a, b, 0, 6);
    }

    /**
     * @param g the specified Graphics context
     * @param a начальная точка линии
     * @param b конечная точка линии
     * @param fi угол поворота линии
     * @param n оставшаяся глубина рекурсии
     */
    public void drawKochLine(Graphics g, Point a, Point b, double fi, int n) {

        if (n <= 0) {
            // рисуем прямую, если достигнута необходимая глубина рекурсии.
            g.drawLine(a.x, a.y, b.x, b.y);
        } else {
            // находим длину отрезка (a; b).
            double length = Math.pow(Math.pow(b.y - a.y, 2) + Math.pow(b.x - a.x, 2), 0.5);
            // находим длину 1/3 отрезка (a; b)
            double length1of3 = length / 3;
            // находим т., делящую отрезок как 1:3.
            Point a1 = new Point(a.x + (int) Math.round((length1of3 * Math.cos(fi))),
                    a.y + (int) Math.round((length1of3 * Math.sin(fi))));
            // находим т., делящую отрезок как 2:3.
            Point b1 = new Point(a1.x + (int) Math.round((length1of3 * Math.cos(fi))),
                    a1.y + (int) Math.round((length1of3 * Math.sin(fi))));
            // находим т., которая будет вершиной равностороннего
            // треугольника.
            Point c = new Point(a1.x + (int) Math.round((length1of3 * Math.cos(fi + Math.PI / 3))),
                    a1.y + (int) Math.round((length1of3 * Math.sin(fi + Math.PI / 3))));
            n--;
            drawKochLine(g, a1, c, fi + Math.PI / 3, n);
            drawKochLine(g, c, b1, fi - Math.PI / 3, n);

            n--;
            drawKochLine(g, a, a1, fi, n);
            drawKochLine(g, b1, b, fi, n);
        }
    }
}