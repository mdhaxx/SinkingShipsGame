package com.company;


import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.concurrent.TimeUnit;


public class GameProgress implements Runnable {
    private final Game game;
    private final GameBoard gameBoard;
    private Thread thread;
    private String threadName = "doh";
    private static int lastHit;


    GameProgress(Game game, GameBoard gameBoard) {
        this.game = game;
        this.gameBoard = gameBoard;
    }

    /**
     * Initializes each ship
     * @param actionCommand String that holds which button has been pushed
     */
    void actionCase(String actionCommand) {
        Ship currentShip;
        switch (game.getPlaceHolder()) {
            case "GamePlayer" -> {
                gameBoard.enableRightButtons(false);
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
                game.setYourTurn();
                gameBoard.disableLeftButtons();
                game.setPlaceHolder("Carrier");
                GameOpponent gameOpponent = new GameOpponent(game);
                infoBox("All your ships have been placed!");
                placeOpponentsShip();
            }
        }
    }

    /**
     * Decides what action should be done next
     * @param currentShip The ship that is currently being initialized
     * @param actionCommand String that holds which button has been pushed
     */
    void caseAction(Ship currentShip, String actionCommand) {
        if (actionCommand.equals("")) {
            userDialogueInitShip(currentShip);
        } else {
            placeYourShip(currentShip, actionCommand);
        }
    }

    /**
     * Pops up a option pane to chose if to place the current ship vertical or horizontally
     * @param currentShip the ship that is currently being placed
     */
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

    /**
     * Calls different methods depending on what choices have been made regarding ship alignment
     * @param currentShip The ship that is currently being placed
     * @param actionCommand String that holds what button has been pushed
     */
    void placeYourShip(Ship currentShip, String actionCommand) {
        if(currentShip.getShipAlignment().equals("Vertical")) {
            placeYourShipVertically(currentShip, actionCommand);
        } else {
            placeYourShipHorizontally(currentShip, actionCommand);
        }
    }

    /**
     * Places the ship vertically
     * @param currentShip The ship that is currently being placed
     * @param actionCommand String that holds what button has been pushed
     */
    void placeYourShipVertically(Ship currentShip, String actionCommand) {
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
            } else {
                initThread("message");
                 }
        } else {
            initThread("message");
        }
    }

    /**
     * Places the ship horizontally
     * @param currentShip The ship that is currently being placed
     * @param actionCommand String that holds what button has been pushed
     */
    void placeYourShipHorizontally(Ship currentShip, String actionCommand) {
        if(Integer.parseInt(actionCommand.substring(actionCommand.length()-1)) <= ((10-currentShip.getShipLength())+1) && Integer.parseInt(actionCommand.substring(actionCommand.length()-1)) != 0)  {
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
            } else {
                initThread("message");
            }
        } else {
            initThread("message");
        }
    }

    /**
     * Simulates a pushed button from the opponent, while playing against the computer
     * Gets a randomized number between 1-100
     */
    void placeOpponentsShip() {
        int placement = ((int)(100*Math.random())+1);

        actionCaseOpponent(placement);
    }

    /**
     * Initializes each ship of the opponent
     * @param placement int that holds the chosen placement of the current ship
     */
    void actionCaseOpponent(int placement) {
        Ship currentShip;

        switch (game.getPlaceHolder()) {
            case "Carrier" -> {
                currentShip = new Carrier();
                caseActionOpponent(currentShip, placement);
            }
            case "Battleship" -> {
                currentShip = new Battleship();
                caseActionOpponent(currentShip, placement);
            }
            case "Cruiser" -> {
                currentShip = new Cruiser();
                caseActionOpponent(currentShip, placement);
            }
            case "Submarine" -> {
                currentShip = new Submarine();
                caseActionOpponent(currentShip, placement);
            }
            case "Destroyer" -> {
                currentShip = new Destroyer();
                caseActionOpponent(currentShip, placement);
            }
            case "Game On" -> {
                initThread("laugh");
                wait(1100);
                infoBox(game.getNameOfPlayer(0) + " says:\n\n\" I have placed my fleet!\nBest of luck, try to find it...\"\n\nGame is on!!!");
                game.setPlaceHolder("Shooting");
                game.setYourTurn();
                gameBoard.enableRightButtons(true);
                gameBoard.frameRepaint();
            }
        }
    }

    /**
     * Simulates the choice of alignment from the opponent, while playing against the computer
     * @param currentShip The ship that is currently being placed
     * @param placement int that holds the chosen placement of the current ship
     */
    void caseActionOpponent(Ship currentShip, int placement){
        int alignment = (int)Math.round(Math.random());
        if(alignment == 0){
            placeOpponentsShipVertically(currentShip, placement);
        } else {
            placeOpponentsShipHorizontally(currentShip, placement);
        }
    }

    /**
     * While the players turn, takes the shot and acts differently depending on
     * where the shot has been placed
     * @param actionCommand String that holds what button has been pushed
     */
    void timeToShoot(String actionCommand) {
        if(game.getYourTurn()) {
            gameBoard.frameRepaint();
            int indexI = Integer.parseInt(actionCommand.substring(6));
            if(game.getRightGridValue(indexI, 1) == 0) {
                game.setRightGridValue(indexI, 1, 1);
                if(game.getRightGridValue(indexI, 0) == 0) {
                    initThread("nohit");
                    gameBoard.rightB[indexI].setIcon(gameBoard.getNoHit());
                    game.setYourNoHit(game.getYourNoHit()+1);
                    gameBoard.frameRepaint();
                } else {
                    initThread("hit");
                    wait(500);
                    gameBoard.rightB[indexI].setIcon(gameBoard.getHit());
                    gameBoard.rightB[indexI].setBackground(gameBoard.getShipSunk());
                    game.setYourHit(game.getYourHit()+1);
                    gameBoard.frameRepaint();
                    if(game.getYourHit() == 17) {
                        gameOver(1);
                    }
                }
                opponentsShot();
            } else {
                initThread("doh");

            }
        } else {
            //Prepare for multiplayer
            //gameBoard.rightB[placement].doClick();
        }
    }

    /**
     * While the opponents turn, takes the shot and acts differently depending on
     * where the shot has been placed
     */
    void opponentsShot(){
        int indexI = ((int)(100*Math.random())+1);
        game.setYourTurn();
        gameBoard.frameRepaint();
        if(game.getLeftGridValue(indexI, 1) == 0) {
            game.setLeftGridValue(indexI, 1, 1);
            if (game.getLeftGridValue(indexI, 0) == 0) {
                initThread("nohit");
                gameBoard.leftB[indexI].setIcon(gameBoard.getNoHit());
                gameBoard.leftB[indexI].setDisabledIcon(gameBoard.getNoHit());
                game.setOpponentNoHit(game.getOpponentNoHit()+1);
                gameBoard.frameRepaint();
            } else {
                initThread("hit");
                gameBoard.leftB[indexI].setIcon(gameBoard.getHit());
                gameBoard.leftB[indexI].setBackground(gameBoard.getShipSunk());
                gameBoard.leftB[indexI].setDisabledIcon(gameBoard.getHit());
                game.setOpponentHit(game.getOpponentHit()+1);
                gameBoard.frameRepaint();
                lastHit = indexI;
                if(game.getOpponentHit() == 17) {
                    gameOver(0);
                }
            }
            game.setYourTurn();
        } else {
            opponentsShot();
        }
    }

    /**
     * Ends the game when one player has sunk all of its opponents ships
     * @param index int that point to the name of the player that has won
     */
    void gameOver(int index) {
        if(index == 0) {
            infoBox(game.getNameOfPlayer(0) + " says:\n\n\"Muhahahahaahahaha you lost, better luck next time... \"");
        } else {
            infoBox(game.getNameOfPlayer(0) + " says:\n\n\" Congratulations " + game.getNameOfPlayer(1) + ", YOU WON!!! \"");
        }
        GameData gameData = new GameData();
        gameData.deleteGameDataFile();
        System.exit(0);
    }

    /**
     * Places the opponents ship vertically
     * @param currentShip the ship that is currently being placed
     * @param placement int that holds the chosen placement of the current ship
     */
    void placeOpponentsShipVertically(Ship currentShip, int placement) {
        if (placement <= 60 + ((5 - currentShip.getShipLength()) * 10)) {
            boolean empty = true;
            for (int i = placement; i < (placement + (currentShip.getShipLength() * 10)); i = i + 10) {
                if (game.getRightGridValue(i, 0) != 0) {
                    empty = false;
                }
            }
            if (empty) {
                for (int i = placement; i < (placement + (currentShip.getShipLength() * 10)); i = i + 10) {
                    game.setRightGridValue(i, 0, currentShip.getShipNumber());
                }
                game.setPlaceHolder(game.getPlacedAtGridPosition(currentShip.getShipNumber() + 1));
            }
        }
        placeOpponentsShip();
    }

    /**
     * Places the opponents ship horizontally
     * @param currentShip the ship that is currently being placed
     * @param placement int that holds the chosen placement of the current ship
     */
    void placeOpponentsShipHorizontally(Ship currentShip, int placement) {
        int range = placement;
        if(placement > 9){
            range = placement % 10;
        }
        if((range <= ((10-currentShip.getShipLength())+1)) && range != 0)  {
            boolean empty = true;
            for (int i = placement; i < (placement + (currentShip.getShipLength())); i++) {
                if (game.getRightGridValue(i, 0) != 0) {
                    empty = false;
                }
            }
            if (empty) {
                for (int i = placement; i < (placement + (currentShip.getShipLength())); i++) {
                    game.setRightGridValue(i, 0, currentShip.getShipNumber());
                }
                game.setPlaceHolder(game.getPlacedAtGridPosition(currentShip.getShipNumber() + 1));
            }
        }
        placeOpponentsShip();
    }

    /**
     * A infobox that shows a text message
     * @param textMessage String with the chosen text message
     */
    void infoBox(String textMessage) {
        JOptionPane text = new JOptionPane();
        JOptionPane.showMessageDialog(text, textMessage, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Plays a sound
     * @param audioClip String with the name of the sound file.
     */
    void makeSomeNoise(String audioClip){
        try
        {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File("./gameData/sound/" + audioClip)));
            clip.start();
            while (!clip.isRunning())
                thread.sleep(10);
            while (clip.isRunning())
                thread.sleep(10);
            clip.close();
        }
        catch (Exception eAudio) {
            eAudio.printStackTrace();
        }
    }

    /**
     * Specifies what to run when the thread has been initialized
     */
    public void run() {
        makeSomeNoise(this.threadName + ".wav");
    }

    /**
     * Initializes threads to be able to run things simultaneously
     * @param threadName String with the name for the thread
     */
    public void initThread(String threadName) {
        this.threadName = threadName;
        thread = new Thread(this, threadName);
        thread.start();
    }

    /**
     *Adds a delay with the given amount of milliseconds
     * @param timeInterval time in milliseconds
     */
    void wait(int timeInterval){
        try {
            TimeUnit.MILLISECONDS.sleep(timeInterval);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}