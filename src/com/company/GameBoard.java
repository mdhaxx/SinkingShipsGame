package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class GameBoard implements ActionListener {

    private final JFrame frame = new JFrame();
    final JButton[] leftB = new JButton[101];
    final JButton[] rightB = new JButton[101];
    final JLabel leftHL = new JLabel("Your field");
    final JLabel rightHL = new JLabel("Opponents field");
    private final JLabel midLine = new JLabel();
    private final JLabel[] leftTL = new JLabel[10];
    private final JLabel[] leftSL = new JLabel[10];
    private final JLabel[] rightTL = new JLabel[10];
    private final JLabel[] rightSL = new JLabel[10];

    private final int frameWidth = 1110;
    private final int frameHeight = 591;

    private final Font fontHeader = new Font("Verdana", Font.BOLD, 14);
    private final Font fontText = new Font("Verdana", Font.PLAIN, 12);

    private final Border blackLine = BorderFactory.createLineBorder(Color.black);

    private final Color shipFloating = new Color(45, 149, 63, 255);
    private final Color shipSunk = new Color(179, 11, 11);

    private final ImageIcon hit = new ImageIcon("gameData/images/hit3.png");
    private final ImageIcon noHit = new ImageIcon("gameData/images/drop.png");

    private final Game game;

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
        printLeftGridTopLabels();
        printLeftGridSideLabels();
        printLeftGridButtons();
        printMidLine();
        printRightGridHeadLabel();
        printRightGridTopLabels();
        printRightGridSideLabels();
        printRightGridButtons();

        frame.repaint();
        game.setPlaceHolder("gameBoard");

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                GameData gameData = new GameData();
                gameData.saveGameData();
            }
        });
    }

    /**
     * Disables the left grid buttons while playing
     */
    void disableLeftButtons() {
        for(int i = 1; i <= 100; i++) {
            leftB[i].setEnabled(false);
        }
    }

    /**
     * Enables or disables the right grid buttons
     * @param state boolean value, true for enable, false for disable
     */
    void enableRightButtons(boolean state) {
        for(int i = 1; i <= 100; i++) {
            rightB[i].setEnabled(state);
        }
    }

    /**
     * Sets the frame properties
     */
    void printFrame() {
        frame.setBounds(200, 150, frameWidth, frameHeight);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    /**
     * Sets the properties of the left grid head label(Your field) and adds it to the frame
     */
    void printLeftGridHeadLabel() {
        leftHL.setBounds((frameWidth / 4) - 40, 5, 150, 16);
        leftHL.setFont(new Font("Verdana", Font.BOLD, 14));
        frame.add(leftHL);
    }

    /**
     * Sets the properties of the left grid top labels(A-J) and adds it to the frame
     */
    void printLeftGridTopLabels() {
        int width = 79;
        for (int i = 0; i < 10; i++) {
            leftTL[i] = new JLabel(Character.toString((char) 65 + i));
            leftTL[i].setBounds(width, 35, 44, 44);
            leftTL[i].setBorder(blackLine);
            leftTL[i].setHorizontalAlignment(JLabel.CENTER);
            leftTL[i].setVerticalAlignment(JLabel.CENTER);
            leftTL[i].setFont(fontText);
            frame.add(leftTL[i]);
            width = width + 44;
        }
    }

    /**
     * Sets the properties of the left grid side labels(1-10) and adds it to the frame
     */
    void printLeftGridSideLabels() {
        int width = 35;
        int height = 79;
        for (int i = 0; i < 10; i++) {
            leftSL[i] = new JLabel("" + (i + 1));
            leftSL[i].setBounds(width, height, 44, 44);
            leftSL[i].setBorder(blackLine);
            leftSL[i].setHorizontalAlignment(JLabel.CENTER);
            leftSL[i].setVerticalAlignment(JLabel.CENTER);
            leftSL[i].setFont(fontText);
            frame.add(leftSL[i]);
            height = height + 44;
        }
    }

    /**
     * Sets the properties of the left grid buttons(1-100) and adds it to the frame
     */
    void printLeftGridButtons() {
        int width = 79;
        int height = 79;
        int x = 1;
        for(int i = 0; i < 100; i = i + 10) {
            for(int j = 1; j <= 10; j++) {
                leftB[j + i] = new JButton();
                leftB[j + i].setBounds(width, height, 44, 44);
                leftB[j + i].setBackground(new Color(0, 0, 180 - (15 * x)));
                leftB[j + i].setForeground(new Color(0, 0, 180 - (15 * x)));
                if(game.getLeftGridValue(j+i,0) > 0) {
                    leftB[j + i].setEnabled(false);
                    if(game.getLeftGridValue(j+i,1) > 0) {
                        leftB[j + i].setBackground(shipSunk);
                        leftB[j + i].setForeground(shipSunk);
                        leftB[j + i].setIcon(hit);
                        leftB[j + i].setDisabledIcon(hit);
                    } else {
                        leftB[j + i].setBackground(shipFloating);
                        leftB[j + i].setForeground(shipFloating);
                    }
                } else {
                    if(game.getLeftGridValue(j+i,1) > 0) {
                        leftB[j + i].setIcon(noHit);
                        leftB[j + i].setDisabledIcon(noHit);
                    }
                    leftB[j + i].setEnabled(true);
                }
                leftB[j + i].setText("leftB" + (i + j));
                frame.add(leftB[i + j]);
                leftB[j + i].addActionListener(this);
                width = width + 44;
            }
            width = 79;
            height = height + 44;
            x++;
        }
    }

    /**
     * Sets the properties of the mid line between the grids and adds it to the frame
     */
    void printMidLine() {
        midLine.setBounds(frameWidth / 2, 0, 1, frameHeight - 36);
        midLine.setBorder(blackLine);
        frame.add(midLine);
    }

    /**
     * Sets the properties of the right grid head label(Opponents field) and adds it to the frame
     */
    void printRightGridHeadLabel() {
        rightHL.setBounds((frameWidth / 4) * 3 - 54, 5, 150, 18);
        rightHL.setFont(fontHeader);
        frame.add(rightHL);
    }

    /**
     * Sets the properties of the right grid top labels(A-J) and adds it to the frame
     */
    void printRightGridTopLabels() {
        int width = (frameWidth / 2) + 79;
        for (int i = 0; i < 10; i++) {
            rightTL[i] = new JLabel(Character.toString((char) 65 + i));
            rightTL[i].setBounds(width, 35, 44, 44);
            rightTL[i].setBorder(blackLine);
            rightTL[i].setHorizontalAlignment(JLabel.CENTER);
            rightTL[i].setVerticalAlignment(JLabel.CENTER);
            rightTL[i].setFont(fontText);
            frame.add(rightTL[i]);
            width = width + 44;
        }
    }

    /**
     * Sets the properties of the right grid side labels(1-10) and adds it to the frame
     */
    void printRightGridSideLabels() {
        int width = (frameWidth / 2) + 35;
        int height = 79;
        for(int i = 0; i < 10; i++) {
            rightSL[i] = new JLabel("" + (i + 1));
            rightSL[i].setBounds(width, height, 44, 44);
            rightSL[i].setBorder(blackLine);
            rightSL[i].setHorizontalAlignment(JLabel.CENTER);
            rightSL[i].setVerticalAlignment(JLabel.CENTER);
            rightSL[i].setFont(fontText);
            frame.add(rightSL[i]);
            height = height + 44;
        }
    }

    /**
     * Sets the properties of the right grid buttons(1-100) and adds it to the frame
     */
    void printRightGridButtons() {
        int width = (frameWidth / 2) + 79;
        int height = 79;
        int x = 1;
        for(int i = 0; i < 100; i = i + 10) {
            for(int j = 1; j <= 10; j++) {
                rightB[i + j] = new JButton();
                rightB[i + j].setBounds(width, height, 44, 44);
                rightB[j + i].setEnabled(true);
                rightB[j + i].setBackground(new Color(0, 0, 180 - (15 * x)));
                rightB[j + i].setForeground(new Color(0, 0, 180 - (15 * x)));
                if(game.getRightGridValue(j+i,0) > 0) {
                    if(game.getRightGridValue(j+i,1) > 0) {
                        rightB[j + i].setBackground(shipSunk);
                        rightB[j + i].setForeground(shipSunk);
                        rightB[j + i].setIcon(hit);
                    }
                } else {
                    if(game.getRightGridValue(j+i,1) > 0) {
                        rightB[j + i].setIcon(noHit);
                    }
                }
                rightB[i + j].setText("rightB" + (i + j));
                rightB[i + j].addActionListener(this);
                frame.add(rightB[i + j]);
                width = width + 44;
            }
            width = (frameWidth / 2) + 79;
            height = height + 44;
            x++;
        }
    }

    /**
     * Repaints the frame, to update the graphics
     */
    void frameRepaint(){
        frame.repaint();
    }

    /**
     * Receives an ActionEvent from the ActionListener when a button has been pushed.
     * Sends a string with the name of the button to the next action
     * @param e The ActionEvent object
     */
    @Override
    public void actionPerformed(ActionEvent e) {
            game.nextAction(e.getActionCommand());
    }

}