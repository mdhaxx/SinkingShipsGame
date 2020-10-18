package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


class GameBoard {
    private JFrame frame = new JFrame();
    private JLabel[] label = new JLabel[4];
    private JPanel[] panel = new JPanel[2];
    private JButton[] button = new JButton[6];
    private JSplitPane splitPane;
    private final int frameWidth = 1110;
    private final int frameHeight = 591;

    private final Font fontHeader = new Font("Verdana", Font.BOLD, 14);
    private final Font fontText = new Font("Verdana", Font.PLAIN, 12);

    private final Border blackline = BorderFactory.createLineBorder(Color.black);

    private final Color[] buttonColors = new Color[10];
    private final Color shipFloating = new Color(45, 149, 63, 255);
    private final Color shipSunk = new Color(179, 11, 11);

    /*

    private static final JButton[] leftB = new JButton[101];
    private static final JButton[] rightB = new JButton[101];
    private final ImageIcon hit = new ImageIcon("progData/images/hit3.png");
    private final ImageIcon noHit = new ImageIcon("progData/images/dropp.png");
    private int[][] leftGridArray = new int[101][2];
    private int[][] rightGridArray = new int[101][2];
*/

    GameBoard(){
        label();
        button();
        panel();
        splitPane();
        frame();
    }

    private void label() {
        label[0] = new JLabel("Your field");
        label[0].setBounds((frameWidth/4)-40, 100, 150, 18);
        label[0].setFont(fontHeader);
        label[0].setBorder(blackline);
        label[0].setVisible(true);

        label[1] = new JLabel("Opponents field");
        label[1].setBounds((frameWidth/4)*3-54, 5, 150, 18);
        label[1].setFont(fontHeader);
        label[1].setBorder(blackline);
        label[1].setVisible(true);


        label[3] = new JLabel(Character.toString((char) 65));
        label[3].setBounds(0, 35, 44, 44);
        label[3].setBorder(blackline);
        label[3].setHorizontalAlignment(JLabel.CENTER);
        label[3].setVerticalAlignment(JLabel.CENTER);
        label[3].setFont(fontText);

    }

    private void button() {

    }

    private void panel() {
        panel[0] = new JPanel();
        panel[0].setBackground(shipFloating);
        panel[0].setVisible(true);
        panel[0].add(label[0]);


        panel[1] = new JPanel();
        panel[1].setBackground(shipFloating);
        panel[1].setVisible(true);
        panel[1].add(label[1]);


    }

    private void splitPane() {
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel[0], panel[1]);
        //splitPane.setDividerSize(1);
        //splitPane.setResizeWeight(0.5);

    }

    private void frame() {
        frame.setSize(frameWidth, frameHeight);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                //quitGame("Gameboard");
            }
        });

        frame.add(panel[0]);

        //frame.repaint();
    }

}