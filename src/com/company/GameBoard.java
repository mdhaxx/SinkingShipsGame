package com.company;


import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;


public class GameBoard implements ActionListener {

    private final JFrame frame = new JFrame();
    final JButton[] leftB = new JButton[101];
    final JButton[] rightB = new JButton[101];
    private final JLabel leftHL = new JLabel("Your field");
    private final JLabel rightHL = new JLabel("Opponents field");
    private final JLabel midLine = new JLabel();
    private final JLabel[] leftTL = new JLabel[10];
    private final JLabel[] leftSL = new JLabel[10];
    private final JLabel[] rightTL = new JLabel[10];
    private final JLabel[] rightSL = new JLabel[10];

    private final int frameWidth = 1110;
    private final int frameHeight = 591;

    private final Font fontHeader = new Font("Verdana", Font.BOLD, 14);
    private final Font fontText = new Font("Verdana", Font.PLAIN, 12);

    private final Border blackline = BorderFactory.createLineBorder(Color.black);

    private final Color[] buttonColors = new Color[10];
    private final Color shipFloating = new Color(45, 149, 63, 255);
    private final Color shipSunk = new Color(179, 11, 11);
    //private final Color shipSunk = new Color(0, 50, 135);

    private final ImageIcon hit = new ImageIcon("gameData/images/hit3.png");
    private final ImageIcon noHit = new ImageIcon("gameData/images/drop.png");

    private Game game;


    GameBoard(Game game) {
        this.game = game;


    }

    Color getShipFloating() { return shipFloating; }
    Color getShipSunk() { return shipSunk; }


    public ImageIcon getNoHit() { return this.noHit; }
    public ImageIcon getHit() { return this.hit; }


    void initGameBoard() {

        printFrame();
        printLeftGridHeadLabel();
        printLeftGridTopLabels();  //vår planhalva
        printLeftGridSideLabels();
        printLeftGridButtons();
        printMidLine();
        printRightGridHeadLabel();
        printRightGridTopLabels(); //motståndarens
        printRightGridSideLabels();
        printRightGridButtons();
        //gameProgress.setColors();

        frame.repaint();
        game.setPlaceHolder("gameBoard");

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                //gameProgress.quitGame("Gameboard");
            }
        });
    }

    void disableLeftButtons() {
        for(int i = 1; i <= 100; i++) {
            leftB[i].setEnabled(false);
        }
    }

    void enableRightButtons(boolean state) {
        for(int i = 1; i <= 100; i++) {
            rightB[i].setEnabled(state);
        }
    }

    public int whichRowColor(int index) {
        if ((index % 10) == 0) {
            return (index / 10) - 1;
        } else {
            return ((int) (Math.round((index / 10) + 0.5)) * 10) / 10 - 1;
        }
    }

    static void changeToImageWhenHit() {
    }


    void printFrame() {
        frame.setBounds(200, 150, frameWidth, frameHeight);
        frame.setLayout(null);
        frame.setVisible(true);
        //setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    void printLeftGridHeadLabel() {
        leftHL.setBounds((frameWidth / 4) - 40, 5, 150, 16);
        leftHL.setFont(new Font("Verdana", Font.BOLD, 14));
        frame.add(leftHL);
    }

    void printLeftGridTopLabels() {
        int width = 79;

        for (int i = 0; i < 10; i++) {
            leftTL[i] = new JLabel(Character.toString((char) 65 + i));
            leftTL[i].setBounds(width, 35, 44, 44);
            leftTL[i].setBorder(blackline);
            leftTL[i].setHorizontalAlignment(JLabel.CENTER);
            leftTL[i].setVerticalAlignment(JLabel.CENTER);
            leftTL[i].setFont(fontText);
            frame.add(leftTL[i]);
            width = width + 44;
        }
    }

    void printLeftGridSideLabels() {
        int width = 35;
        int height = 79;

        for (int i = 0; i < 10; i++) {
            leftSL[i] = new JLabel("" + (i + 1));
            leftSL[i].setBounds(width, height, 44, 44);
            leftSL[i].setBorder(blackline);
            leftSL[i].setHorizontalAlignment(JLabel.CENTER);
            leftSL[i].setVerticalAlignment(JLabel.CENTER);
            leftSL[i].setFont(fontText);
            frame.add(leftSL[i]);
            height = height + 44;
        }
    }

    void printLeftGridButtons() {
        int width = 79;
        int height = 79;
        int x = 1;
/*
        for (int height = 0; height < 10; height++) {
            for (int width = 1; width <= 10; width++) {
                knappNr = (height * 10) + width;
            }
        }

*/



        for (int i = 0; i < 100; i = i + 10) {
            for (int j = 1; j <= 10; j++) {
                leftB[j + i] = new JButton();
                leftB[j + i].setBounds(width, height, 44, 44);
                leftB[j + i].setEnabled(true);
                leftB[j + i].setBackground(new Color(0, 0, 180 - (15 * x)));
                leftB[j + i].setText("leftB" + (i + j));
                leftB[j + i].setForeground(new Color(0, 0, 180 - (15 * x)));
                frame.add(leftB[i + j]);
                leftB[j + i].addActionListener(this);
                width = width + 44;
            }
            width = 79;
            height = height + 44;
            x++;
        }
    }

    void printMidLine() {
        midLine.setBounds(frameWidth / 2, 0, 1, frameHeight - 36);
        midLine.setBorder(blackline);
        frame.add(midLine);
    }

    void printRightGridHeadLabel() {
        rightHL.setBounds((frameWidth / 4) * 3 - 54, 5, 150, 18);
        rightHL.setFont(fontHeader);

        frame.add(rightHL);
    }

    void printRightGridTopLabels() {
        int width = (frameWidth / 2) + 79;

        for (int i = 0; i < 10; i++) {
            rightTL[i] = new JLabel(Character.toString((char) 65 + i));
            rightTL[i].setBounds(width, 35, 44, 44);
            rightTL[i].setBorder(blackline);
            rightTL[i].setHorizontalAlignment(JLabel.CENTER);
            rightTL[i].setVerticalAlignment(JLabel.CENTER);
            rightTL[i].setFont(fontText);
            frame.add(rightTL[i]);
            width = width + 44;
        }
    }

    void printRightGridSideLabels() {
        int width = (frameWidth / 2) + 35;
        int height = 79;

        for (int i = 0; i < 10; i++) {
            rightSL[i] = new JLabel("" + (i + 1));
            rightSL[i].setBounds(width, height, 44, 44);
            rightSL[i].setBorder(blackline);
            rightSL[i].setHorizontalAlignment(JLabel.CENTER);
            rightSL[i].setVerticalAlignment(JLabel.CENTER);
            rightSL[i].setFont(fontText);
            frame.add(rightSL[i]);
            height = height + 44;

        }
    }

    void printRightGridButtons() {
        int width = (frameWidth / 2) + 79;
        int height = 79;
        int x = 1;

        for (int i = 0; i < 100; i = i + 10) {
            for (int j = 1; j <= 10; j++) {
                rightB[i + j] = new JButton();
                rightB[i + j].setBounds(width, height, 44, 44);
                rightB[i + j].setEnabled(true);
                rightB[i + j].setBackground(new Color(0, 0, 180 - (15 * x)));
                rightB[i + j].setText("rightB" + (i + j));
                rightB[i + j].setForeground(new Color(0, 0, 180 - (15 * x)));
                rightB[i + j].addActionListener(this);
                /*
                rightB[i + j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        //gameProgress.setPushedButton(e.getActionCommand());
                        //gameProgress.placeShip(gameProgress.getCurrentShip());
                    }
                });

                 */
                frame.add(rightB[i + j]);
                width = width + 44;
            }
            width = (frameWidth / 2) + 79;
            height = height + 44;
            x++;
        }
    }

    public void frameRepaint(){
        frame.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

            game.nextAction(e.getActionCommand());
    }

}