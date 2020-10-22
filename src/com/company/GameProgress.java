package com.company;


import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;


public class GameProgress {
    private final Game game;
    private final GameBoard gameBoard;

    GameProgress(Game game, GameBoard gameBoard) {
        this.game = game;
        this.gameBoard = gameBoard;
    }

    void actionCase(String actionCommand) {
        Ship currentShip;
        switch (game.getPlaceHolder()) {
            case "GamePlayer" -> {
                game.setPlaceHolder("Carrier");
                currentShip = new Carrier();
                userDialogueInitShip(currentShip);
            }
            case "Carrier" -> {
                currentShip = new Carrier();
                caseAction(currentShip, actionCommand);
            }
            case "Battleship" -> {
                currentShip = new Battleship();
                caseAction(currentShip, actionCommand);
            }
            case "Cruiser" -> {
                currentShip = new Cruiser();
                caseAction(currentShip, actionCommand);
            }
            case "Submarine" -> {
                currentShip = new Submarine();
                caseAction(currentShip, actionCommand);
            }
            case "Destroyer" -> {
                currentShip = new Destroyer();
                caseAction(currentShip, actionCommand);
            }
            case "Game On" -> {
            }
        }
    }

    void caseAction(Ship currentShip, String actionCommand) {
        if (actionCommand.equals("")) {
            userDialogueInitShip(currentShip);
        } else {
            placeYourShip(currentShip, actionCommand);

        }

    }

    void userDialogueInitShip(Ship currentShip) {
        JOptionPane pane = new JOptionPane();
        Object[] options = {"Vertical", "Horizontal"};
        int choice;
        do {
            choice = JOptionPane.showOptionDialog(pane,
                    "How do you want to place your " + currentShip.getShipType() + "?\n"
                            + "Size: " + currentShip.getShipLength() + " squares. \n"
                            + "Please, position your ships starting point on your field!", "Welcome " + game.getNameOfPlayer(1), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
        } while (choice == JOptionPane.CLOSED_OPTION);
        if (choice == 0) {
            currentShip.setShipAlignment("Vertical");
        } else {
            currentShip.setShipAlignment("Horizontal");
        }
    }

    void placeYourShip(Ship currentShip, String actionCommand) {
        if(currentShip.getShipAlignment().equals("Vertical")) {
            if(Integer.parseInt(actionCommand.substring(5)) <= 60 + ((5-currentShip.getShipLength())*10)) {
                boolean empty = true;
                for (int i = Integer.parseInt(actionCommand.substring(5)); i < (Integer.parseInt(actionCommand.substring(5)) + (currentShip.getShipLength() * 10)); i = i + 10) {
                    if (game.getLeftGridValue(i, 0) != 0) {
                        empty = false;
                    }
                }
                if (empty) {
                    for (int i = Integer.parseInt(actionCommand.substring(5)); i < (Integer.parseInt(actionCommand.substring(5)) + (currentShip.getShipLength() * 10)); i = i + 10) {
                        game.setLeftGridValue(i, 0, currentShip.getShipNumber());
                        gameBoard.leftB[i].setBackground(gameBoard.getShipFloating());
                        gameBoard.leftB[i].setEnabled(false);
                    }
                    game.setPlaceHolder(game.getPlacedAtGridPosition(currentShip.getShipNumber() + 1));
                    actionCase("");
                }
            } else { makeSomeNoise("meep.wav"); }
        } else {
            if(Integer.parseInt(actionCommand.substring(actionCommand.length()-1)) <= ((10-currentShip.getShipLength())+1)) {
                boolean empty = true;
                for (int i = Integer.parseInt(actionCommand.substring(5)); i < (Integer.parseInt(actionCommand.substring(5)) + (currentShip.getShipLength())); i++) {
                    if (game.getLeftGridValue(i, 0) != 0) {
                        empty = false;
                    }
                }
                if (empty) {
                    for (int i = Integer.parseInt(actionCommand.substring(5)); i < (Integer.parseInt(actionCommand.substring(5)) + (currentShip.getShipLength())); i++) {
                        game.setLeftGridValue(i, 0, currentShip.getShipNumber());
                        gameBoard.leftB[i].setBackground(gameBoard.getShipFloating());
                        gameBoard.leftB[i].setEnabled(false);
                    }
                    game.setPlaceHolder(game.getPlacedAtGridPosition(currentShip.getShipNumber() + 1));
                    actionCase("");
                }
            }
        }
    }

    void makeSomeNoise(String audioClip){
        try
        {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File("./gameData/sound/" + audioClip)));
            clip.start();
            while (!clip.isRunning())
                Thread.sleep(10);
            while (clip.isRunning())
                Thread.sleep(10);
            clip.close();
        }
        catch (Exception eAudio)
        {
            System.out.println(eAudio);;
        }
    }

}