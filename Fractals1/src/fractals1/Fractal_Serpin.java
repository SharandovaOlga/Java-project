/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fractals1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

/**
 * @author Mora
 */
public class Fractal_Serpin extends JFrame {

    public Fractal_Serpin() {
        init();
    }

    private void init() {
        // Создать окно для рисования
        JFrame jf = new JFrame("Треугольник Серпинского");

        // Создать объект слушателя
        Listener ls = new Listener();

        // Установить свойства панели
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(800, 700); // Установить размер 800 * 700
        jf.setLocationRelativeTo(null);// Установить отображение по центру
        jf.setLayout(new BorderLayout());// Установить макет границы (JFrame по умолчанию использует макет границы)

        // Создать панель (а Panel представляет собой потоковый макет)
        JPanel jp1 = new JPanel();
        JPanel jp2 = new JPanel();
        JPanel jp3 = new JPanel();
        JPanel jp4 = new JPanel();
        JPanel jp5 = new JPanel();
        jp1.setLayout(new FlowLayout());
        jp2.setLayout(new FlowLayout());
        jp1.setPreferredSize(new Dimension(130, 100)); //Для кнопок
        jp2.setPreferredSize(new Dimension(40, 100)); //Для иконок цвета
        jp5.setPreferredSize(new Dimension(100, 20)); //Для задания
        jf.add(jp1, BorderLayout.WEST); //кнопки
        jf.add(jp2, BorderLayout.EAST); //цвета
        jf.add(jp3, BorderLayout.CENTER); //область для треугольника
        jf.add(jp4, BorderLayout.SOUTH); //слайдер
        jf.add(jp5, BorderLayout.NORTH); //задание
        jp3.addMouseMotionListener(ls);
        jp3.addMouseListener(ls);

        // Создать слайдер
        JSlider JS = new JSlider();
        // Добавить слайдер
        jp4.add(JS);
        JS.setValue(3);
        JS.setSnapToTicks(true);
        JS.setMaximum(6);
        JS.setMinimum(0);
        JS.addChangeListener(ls);

        //Создание области для пояснения
        JLabel JL = new JLabel("Укажите три рандомные точки. Сделали? Теперь кнопку 'Генерировать'.");
        //Добавить пояснение
        jp5.add(JL);

        // Создание кнопок с массивами и циклами и добавление слушателей. Формат: type [] array name = {"variable", "variable" ...};
        String[] ShapeBtn = {"Генерировать", "Очистить экран"};
        for (String ShapeBtn1 : ShapeBtn) {
            JButton shape = new JButton(ShapeBtn1);
            jp1.add(shape);
            shape.addActionListener(ls);
        }

        // Создать цветную кнопку
        Color[] ColorBtn = {Color.BLUE, Color.GREEN, Color.RED, Color.ORANGE, Color.GRAY, Color.BLACK};
        Dimension colorBtnSize = new Dimension(30, 30); // Устанавливаем размер цветного квадрата
        for (Color ColorBtn1 : ColorBtn) {
            JButton color = new JButton(); // Создать цвет кнопки объекта
            color.setBackground(ColorBtn1); // Установить цвет фона кнопки
            color.setPreferredSize(colorBtnSize);
            jp2.add(color);
            color.addActionListener(ls);
        }

        // Установить окно видимым
        jf.setVisible(true);

        // Получить холст формы и передать его объекту слушателя (должен быть виден после установки окна)
        ls.g = jp3.getGraphics();

    }
}