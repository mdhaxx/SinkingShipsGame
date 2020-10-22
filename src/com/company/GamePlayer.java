package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GamePlayer extends Frame implements ActionListener {
    private JTextField yourName;
    private Game game;

    GamePlayer(Game game) {
        this.game = game;
        setYourNameDialogue();
    }

    void setYourNameDialogue() {
        JTextArea textArea = new JTextArea("text");
        textArea.setText("Hello!\nMy name is " + game.getNameOfPlayer(0) + "\nand I will be your opponent\nfor this game.\n\nAnd who might you be?");
        textArea.setFont(new Font("Verdana", Font.BOLD, 18));
        textArea.setBounds(10, 10, 100, 160);

        yourName = new JTextField("Enter your name, please!",10);
        yourName.setBounds(10, 170, 100, 40);
        yourName.addActionListener(this);


        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(20,220,800, 80);
        submitButton.addActionListener(this);

        setSize(400, 400);
        setLayout(new BorderLayout(0,5));
        setLocationRelativeTo(null);

        add(textArea, BorderLayout.NORTH);
        add(yourName, BorderLayout.CENTER);
        add(submitButton, BorderLayout.SOUTH);

        pack();
        setVisible(true);

        repaint();
        game.setPlaceHolder("GamePlayer");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!yourName.getText().equals("Enter your name, please!")) {
            game.redirectFromGamePlayer(this, yourName.getText());
        }

    }
}
