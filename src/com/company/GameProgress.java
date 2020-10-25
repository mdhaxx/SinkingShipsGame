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
    private static int yourHit;
    private static int yourNoHit;
    private static int opponentHit;
    private static int opponentNoHit;



    GameProgress(Game game, GameBoard gameBoard) {
        this.game = game;
        this.gameBoard = gameBoard;
    }

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
            placeYourShipVertically(currentShip, actionCommand);
        } else {
            placeYourShipHorizontally(currentShip, actionCommand);
        }
    }

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

    void placeOpponentsShip() {
        int placement = ((int)(100*Math.random())+1);

        actionCaseOpponent(placement);
    }

    void caseActionOpponent(Ship currentShip, int placement){
        int alignment = (int)Math.round(Math.random());

        if(alignment == 0){
            placeOpponentsShipVertically(currentShip, placement);
        } else {
            placeOpponentsShipHorizontally(currentShip, placement);
        }
    }



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
                gameBoard.rightHL.setForeground(new Color(0,0,0));
                gameBoard.leftHL.setForeground(new Color(218,218,218));
                gameBoard.frameRepaint();
            }
        }
    }

    void timeToShoot(String actionCommand) {
        if(game.getYourTurn()) {

            gameBoard.rightHL.setForeground(new Color(0,0,0));
            gameBoard.leftHL.setForeground(new Color(218,218,218));
            gameBoard.frameRepaint();
            int indexI = Integer.parseInt(actionCommand.substring(6));
            if(game.getRightGridValue(indexI, 1) == 0) {
                game.setRightGridValue(indexI, 1, 1);
                if(game.getRightGridValue(indexI, 0) == 0) {
                    initThread("blob");
                    gameBoard.rightB[indexI].setIcon(gameBoard.getNoHit());
                    yourNoHit++;
                    gameBoard.frameRepaint();
                } else {
                    initThread("e");
                    wait(500);
                    gameBoard.rightB[indexI].setIcon(gameBoard.getHit());
                    gameBoard.rightB[indexI].setBackground(gameBoard.getShipSunk());
                    yourHit++;
                    gameBoard.frameRepaint();
                    if(yourHit == 17) {
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

    void opponentsShot(){
        int indexI = opponentsNextShot();
        game.setYourTurn();
        gameBoard.rightHL.setForeground(new Color(218,218,218));
        gameBoard.leftHL.setForeground(new Color(0,0,0));
        gameBoard.frameRepaint();
        //infoBox(game.getNameOfPlayer(0) + " says:\n\n\" My turn... \"");
        if(game.getLeftGridValue(indexI, 1) == 0) {
            game.setLeftGridValue(indexI, 1, 1);
            if (game.getLeftGridValue(indexI, 0) == 0) {
                initThread("blob");
                gameBoard.leftB[indexI].setIcon(gameBoard.getNoHit());
                opponentNoHit++;
                gameBoard.frameRepaint();
            } else {
                initThread("e");
                gameBoard.leftB[indexI].setIcon(gameBoard.getHit());
                gameBoard.leftB[indexI].setBackground(gameBoard.getShipSunk());
                opponentHit++;
                gameBoard.frameRepaint();
                lastHit = indexI;
                if(opponentHit == 17) {
                    gameOver(0);
                }
            }

            //infoBox("Your turn!");
            game.setYourTurn();
        } else {
            opponentsShot();
        }


    }

    void gameOver(int index) {
        if(index == 0) {
            infoBox(game.getNameOfPlayer(0) + " says:\n\n\"Muhahahahaahahaha you lost, better luck next time... \"");
        } else {
            infoBox(game.getNameOfPlayer(0) + " says:\n\n\" Congratulations " + game.getNameOfPlayer(1) + ", YOU WON!!! \"");
        }
        System.exit(0);
    }

    int opponentsNextShot() {
       return ((int)(100*Math.random())+1);
        //if(lastHit > 0)
        //lastHit+1
    }

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
                    //gameBoard.rightB[i].setBackground(gameBoard.getShipFloating());
                    //remove color later
                }
                game.setPlaceHolder(game.getPlacedAtGridPosition(currentShip.getShipNumber() + 1));
            }
        }
        placeOpponentsShip();
    }

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
                    gameBoard.rightB[i].setBackground(gameBoard.getShipFloating());
                }
                game.setPlaceHolder(game.getPlacedAtGridPosition(currentShip.getShipNumber() + 1));
            }
        }
        placeOpponentsShip();
    }

    void infoBox(String textMessage) {
        JOptionPane text = new JOptionPane();
        JOptionPane.showMessageDialog(text, textMessage, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

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
        catch (Exception eAudio)
        {
            System.err.print(eAudio);
        }
    }

    public void run() {
        makeSomeNoise(this.threadName + ".wav");
    }

    public void initThread(String threadName) {
        this.threadName = threadName;
        thread = new Thread(this, threadName);
        thread.start();
    }

    void wait(int timeInterval){
        try {
            TimeUnit.MILLISECONDS.sleep(timeInterval);
        } catch (InterruptedException e) {
            System.err.print(e);
        }
    }
}