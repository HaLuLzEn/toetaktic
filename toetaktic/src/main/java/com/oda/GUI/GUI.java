package com.oda.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GUI<d> {
    private JFrame frame;
    private int width = 1280;
    private int height = 720;
    public static byte[][] board = new byte[3][3];

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

                    g.setColor(Color.WHITE);

                    int w = 720/board.length;

                    for(int row = 1; row < board.length; row++){
                        g.drawLine(0,w*row,720,w*row);
                    }

                    for(int column = 1; column < board.length; column++){
                        g.drawLine(w*column,0,w*column,720);
                    }

                    g.setColor(Color.BLUE);

                    int offset = 10;

                    for(int x = 0; x < board.length; x++){
                        for(int y = 0; y < board.length; y++){
                            switch (board[x][y]){
                                case 0:{
                                    g.drawLine(x*w,y*w ,x*w+w,y*w+w);
                                    break;
                                }
                                case 1:{
                                    break;
                                }
                                case 2:{
                                    break;
                                }
                            }
                        }
                    }
                    label.setIcon(new ImageIcon(image));
                }
            }
        });
        t.start();

    }
}
