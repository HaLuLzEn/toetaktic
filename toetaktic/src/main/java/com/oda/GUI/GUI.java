package com.oda.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class GUI<d> {
    private JFrame frame;
    private int width = 1280;
    private int height = 720;
    public static byte[][] board = new byte[5][5];
    private static List<ClickFieldEvent> listeners = new ArrayList<>();
    public static void setBoard(byte[][] newBoard){
        board = newBoard;
    }
    public static void addListener(ClickFieldEvent e){
        listeners.add(e);
    }
    public static byte[][] getBoard(){
        return board;
    }

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
        frame.setBounds(0, 0, width, height + 39);
        frame.setTitle("ToeTakTic");

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JLabel label = new JLabel();
        label.setBounds(0, 0, width, height);
        panel.add(label);

        frame.show();

        panel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for(ClickFieldEvent ce : listeners){
                    ce.onClickEvent(0,0,0);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        Thread t = new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            while (true) {
                frame.setTitle("GUI");

                BufferedImage image = new BufferedImage(width,height,1);

                Graphics2D g = image.createGraphics();

                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g.setColor(Color.WHITE);

                double w = 720.0/board.length;

                for(int row = 1; row < board.length; row++){
                    g.drawLine(0, (int) (w*row),720, (int) (w*row));
                }

                for(int column = 1; column < board.length; column++){
                    g.drawLine((int) (w*column),0, (int) (w*column),720);
                }

                g.setColor(Color.BLUE);

                int offset = (int) (w / 10);

                for(int x = 0; x < board.length; x++){
                    for(int y = 0; y < board.length; y++){
                        switch (board[x][y]){
                            case 0:{
                                break;
                            }
                            case 1:{
                                g.drawLine((int) ((x*w) + offset), (int) ((y*w) + offset), (int) ((x*w+w) - offset), (int) ((y*w+w) - offset));
                                g.drawLine((int) ((x*w) + offset), (int) ((y*w+w) - offset), (int) ((x*w+w) - offset), (int) ((y*w) + offset));
                                break;
                            }
                            case 2:{
                                g.drawOval((int) ((x*w) + offset), (int) ((y*w) + offset), (int) w - 2 * offset, (int) w - 2 * offset);
                                break;
                            }
                        }
                    }
                }
                label.setIcon(new ImageIcon(image));
            }
        });
        t.start();
    }
}
