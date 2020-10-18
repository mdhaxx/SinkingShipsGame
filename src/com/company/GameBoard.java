package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Spliterators;

class GameBoard {
    private final JFrame[] frame = new JFrame[3];
    private final Font font = new Font("Verdana", Font.PLAIN, 12);
    private final Border blackline = BorderFactory.createLineBorder(Color.black);
    private final int frameWidth = 1110;
    private final int frameHeight = 591;

    private final JLabel[] label = new JLabel[2];
    private final JPanel panel = new JPanel();
    private final JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, frame[1], frame[2]);

    private static final JButton[] leftB = new JButton[101];
    private static final JButton[] rightB = new JButton[101];
    private static final JButton button = new JButton();
    private final Color[] colors = new Color[10];
    private final Color shipFloating = new Color(45, 149, 63, 255);
    private final Color shipSunk = new Color(179, 11, 11);
    private final ImageIcon hit = new ImageIcon("progData/images/hit3.png");
    private final ImageIcon noHit = new ImageIcon("progData/images/dropp.png");


    private int[][] leftGridArray = new int[101][2];
    private int[][] rightGridArray = new int[101][2];


    //JFrame frame = new JFrame();
    //JLabel label;

    //JComponent component;
    //JButton button;
    //JPanel panel;
    //JOptionPane optionPane;
    //JDialog dialog;
    JList list;
    JSeparator separator;
    //JSplitPane splitPane;
    JTextArea textArea;
    JTextField textField;
    JToggleButton toggleButton;
    JWindow window;

    GameBoard(){
        frame();
        label();
        panel();
    }

    private void frame() {
        //frame.setBounds(200, 150, frameWidth, frameHeight);
        frame[0] = new JFrame();
        frame[0].setSize(frameWidth, frameHeight);
        frame[0].setLocationRelativeTo(null);
        frame[0].setLayout(null);
        frame[0].setVisible(true);
        frame[0].repaint();
        frame[0].setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame[0].addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                //quitGame("Gameboard");
            }
        });


        //splitPane.setLeftComponent(frame[1]);
        //splitPane.setRightComponent(frame[1]);
        //splitPane.setVisible(true);
        //frame[0].add(splitPane);


        frame[1] = new JFrame();
        frame[1].setSize(500, 300);
        frame[1].setLocationRelativeTo(null);
        frame[1].setLayout(null);
        frame[1].setVisible(false);
        frame[1].setBackground(shipFloating);
        frame[1].repaint();
        frame[1].setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame[1].addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                //quitGame("Gameboard");
            }
        });

        frame[2] = new JFrame();
        frame[2].setSize(frameWidth, frameHeight);
        frame[2].setLocationRelativeTo(null);
        frame[2].setLayout(null);
        frame[2].setVisible(false);
        frame[2].repaint();
        frame[2].setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame[2].addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                //quitGame("Gameboard");
            }
        });

    }

    private void label() {
        label[0] = new JLabel("Your field");
        label[0].setBounds((frameWidth/4)-40, 5, 150, 16);
        label[0].setFont(new Font("Verdana", Font.BOLD, 14));
       // frame[0].add(label[0]);

        label[1] = new JLabel("Opponents field");
        label[1].setBounds((frameWidth/4)*3-54, 5, 150, 18);
        label[1].setFont(new Font("Verdana", Font.BOLD, 14));
        //frame[0].add(label[1]);

    }

    private void panel() {
       // panel.add(button);
        panel.setVisible(true);
        panel.setBackground(shipSunk);
        panel.add(label[0]);
        frame[0].add(panel);
        frame[0].toBack();
    }


}