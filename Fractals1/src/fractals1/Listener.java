/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fractals1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @author Mora
 */
public class Listener implements ActionListener, MouseListener, MouseMotionListener, ChangeListener {

    int x1;
    int y1;
    int x2;
    int y2;
    int x3;
    int y3;
    String action;
    Graphics g;
    int[] X = new int[3];
    int[] Y = new int[3];
    int index = 0;
    int n = 3;

    public void x(int x1, int y1, int x2, int y2, int x3, int y3, int n) {
        index = 0;
        if (n > 0) {
            n--;
            g.drawLine(x1, y1, x2, y2);
            g.drawLine(x3, y3, x2, y2);
            g.drawLine(x1, y1, x3, y3);

            int x4 = (x1 + x3) / 2;
            int y4 = (y1 + y3) / 2;
            int x5 = (x1 + x2) / 2;
            int y5 = (y2 + y1) / 2;
            int x6 = (x3 + x2) / 2;
            int y6 = (y3 + y2) / 2;

            g.drawLine(x4, y4, x5, y5);
            g.drawLine(x6, y6, x5, y5);
            g.drawLine(x4, y4, x6, y6);

            // Итерация
            x(x1, y1, x5, y5, x4, y4, n);
            x(x5, y5, x2, y2, x6, y6, n);
            x(x4, y4, x6, y6, x3, y3, n);
        }
    }

    /**
     *
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (index < 3) {
            // Получить координаты трех вершин
            X[index] = e.getX();
            Y[index] = e.getY();
            System.out.println("Нет." + (index + 1) + "Точка");
            g.fillOval(X[index] - 3, Y[index] - 3, 6, 6);
            index++;
        }

        // Сохраняем координаты в x_i, y_i
        x1 = X[0];
        x2 = X[1];
        x3 = X[2];
        y1 = Y[0];
        y2 = Y[1];
        y3 = Y[2];
    }

    public void actionPerformed(ActionEvent e) {
        action = e.getActionCommand();
        if (action.equals("")) { // цвет
            // Получить источник события
            Object srcObj = e.getSource();
            // Получить кнопку
            JButton srcBtn = (JButton) srcObj;
            // Получить цвет
            Color color = srcBtn.getBackground();
            g.setColor(color);
        } else if ("Очистить экран".equals(action)) {
            g.clearRect(0, 0, 2000, 2000);
        } else if (action.equals("Генерировать")) {
            x(x1, y1, x2, y2, x3, y3, n);
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        // Получить объект слайдера
        Object Ob = e.getSource();
        JSlider Obj = (JSlider) Ob;
        n = Obj.getValue();
        // Очистить экран
        g.clearRect(0, 0, 2000, 2000);
        // нарисовать вершину
        for (int i = 0; i < 3; i++) {
            g.fillOval(X[i] - 3, Y[i] - 3, 6, 6);
        }
        // Рекурсивный
        x(x1, y1, x2, y2, x3, y3, n);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    /**
     *
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub

    }
}