package com.oda.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GUI<d> {
    private JFrame frame;
    private int width = 1280;
    private int height = 720;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUI window = new GUI();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public GUI() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0, 0, width, height);
        frame.setTitle("ToeTakTic");

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JLabel label = new JLabel();
        label.setBounds(0, 0, width, height);
        panel.add(label);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    frame.setTitle("GUI");

                    BufferedImage image = new BufferedImage(width,height, 1);

                    Graphics2D g = image.createGraphics();

                    g.drawString("HelloWorld",50,50);

                    label.setIcon(new ImageIcon(image));
                }
            }
        });
        t.start();

    }
}
