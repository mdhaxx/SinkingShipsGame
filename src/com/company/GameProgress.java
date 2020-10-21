package com.company;

import javafx.scene.media.AudioClip;

import javax.sound.sampled.*;
import javax.swing.*;
import java.beans.Beans;
import java.io.File;
import java.io.File.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

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
            actionCase("");
        }

    }

        /*
        if (actionCommand.equals("")) {
            Ship currentShip = new Carrier(placedAtGridPosition[1]);
            initCurrentShip(currentShip);
        } else if (actionCommand.charAt(0) == 'l') {
            Ship currentShip = new Carrier(placeHolder);
            switch (placeHolder) {
                case "Carrier":
                    break;
                case "Battleship":
                    currentShip = new Battleship(placeHolder);
                    break;
                case "Cruiser":
                    currentShip = new Cruiser(placeHolder);
                    break;
                case "Submarine":
                    currentShip = new Submarine(placeHolder);
                    break;
                case "Destroyer":
                    currentShip = new Destroyer(placeHolder);
                    break;
            }
            leftGrid[Integer.parseInt(actionCommand.substring(5))][0] = currentShip.getShipNumber();
            ArrayList<Integer> listCounter = new ArrayList<>();
            for (int i = 0; i <= 100; i++) {
                if (leftGrid[i][0] == currentShip.getShipNumber()) {
                    listCounter.add(leftGrid[i][0]);
                }
            }
            if (listCounter.size() == currentShip.getShipLength()) {
                placeHolder = placedAtGridPosition[currentShip.getShipNumber() + 1];
                currentShip = new Carrier(placeHolder);
                switch (placeHolder) {
                    case "Carrier":
                        break;
                    case "Battleship":
                        currentShip = new Battleship(placeHolder);
                        break;
                    case "Cruiser":
                        currentShip = new Cruiser(placeHolder);
                        break;
                    case "Submarine":
                        currentShip = new Submarine(placeHolder);
                        break;
                    case "Destroyer":
                        currentShip = new Destroyer(placeHolder);
                        break;
                    case "Game On":
                        yourTurn = false;
                        break;
                }
                initCurrentShip(currentShip);
            }
        }*/


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

                }
            } else {

                try {
                    File audioFile = new File("C:\\Users\\Maria Hermansson\\Dev\\ProjectFiles\\IdeaProjects\\Labs\\SinkingShipsGame\\gameData\\sound\\cantPlaceShipHere.wav");
                    AudioClip sound = new AudioClip("" + audioFile.toURI());
                    sound.play();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //placeYourShip(currentShip, actionCommand);

        } else {
            //horizontal
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
}