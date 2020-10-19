package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private int[][] leftGrid = new int[101][2];
    private int[][] rightGrid = new int[101][2];
    private String[] players = {"Admiral BigShot", ""};
    private String[] placedAtGridPosition = { "Water", "Carrier", "Battleship", "Cruiser", "Submarine", "Destroyer", "Game On" };

    private boolean yourTurn = true;
    private String placeHolder = "";

    private boolean choseToSaveGame;
    private boolean previousGameIsSaved;
    private Scanner scan;
    private File file;
    private FileWriter fileWriter;


    private Game() {

        GameBoard gameBoard = new GameBoard(this);
        gameBoard.initGameBoard();
        GamePlayer gameplayer = new GamePlayer(this);



        //initPlayers(gameplay);
        //initGamePlay();
        //if(previousGameIsSaved) { initSavedGame(); }
    }

    public void setInputPlayer(String name, int index){ this.players[index] = name;}
    public String getInputPlayer(int index){ return this.players[index];}

    public void setLeftGridValue(int indexI, int indexJ, int value){ this.leftGrid[indexI][indexJ] = value;}
    public int setLeftGridValue(int indexI, int indexJ){ return this.leftGrid[indexI][indexJ];}

    public void setRightGridValue(int indexI, int indexJ, int value){ this.leftGrid[indexI][indexJ] = value;}
    public int setRightGridValue(int indexI, int indexJ){ return this.leftGrid[indexI][indexJ];}

    public void setYourTurn(){ this.yourTurn = !this.getYourTurn(); }
    public boolean getYourTurn(){ return this.yourTurn; }


    void returnFromGamePlayer(GamePlayer gamePlayer, Game game) {
        gamePlayer.dispose();
        whereAreWe("");
    }

    void whereAreWe(String actionCommand) {
        if (actionCommand.equals("")) {
            Ship currentShip = new Carrier(placedAtGridPosition[1]);
            initCurrentShip(currentShip);
        } else if(actionCommand.charAt(0) == 'l'){
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
            leftGrid[Integer.parseInt(actionCommand.substring(5))][0] = currentShip.shipNumber;
            ArrayList<Integer> listCounter = new ArrayList<>();
            for(int i = 0; i <= 100; i++){
                if(leftGrid[i][0] == currentShip.shipNumber){
                    listCounter.add(leftGrid[i][0]);
                }
            }
            if(listCounter.size() == currentShip.shipLength) {
                placeHolder = placedAtGridPosition[currentShip.shipNumber + 1];
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
        }
    }

    void test(GameBoard gameBoard){
        gameBoard.disableLeftButtons();

    }

    void initCurrentShip(Ship currentShip) {
        if(placeHolder.equals("Game On")){
            //-----------------------------
            // opponentsTurn();
            //-----------------------------
        }else {
            currentShip.placement[0] = -1;
            placeYourShip(currentShip);
        }
    }


    void placeYourShip(Ship currentShip){
        if(currentShip.placement[0] < 0){
            userDialogueShip(currentShip);
        }
        else {
            //placeHolder = currentShip.getShipType();
            // standby for userInput. (currentShip seized to exist)
        }


    }

    void userDialogueShip(Ship currentShip) {
        JOptionPane pane = new JOptionPane();
        int choice = JOptionPane.showConfirmDialog(pane, "Place your " + currentShip.shipType + "\n Vertically or horizontally only\n Size: " + currentShip.getShipLength() + " squares", "Welcome " + players[1], JOptionPane.OK_CANCEL_OPTION);
        if (choice == 2 || choice == JOptionPane.CLOSED_OPTION) {
            quitGame(currentShip);
        } else {
            currentShip.placement[0] = 0;
            placeYourShip(currentShip);
        }
    }

    void quitGame(Ship currentShip) {
        JOptionPane quitGame = new JOptionPane();
        Object[] options = {"Yes, quit and save", "No, continue playing"};
        int choice = JOptionPane.showOptionDialog(quitGame, "Do you want to exit the game", "Quit game", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
        if (choice == 0) {
            GameData gameData = new GameData();
            gameData.saveGameData(this, currentShip);
        } else {
                placeYourShip(currentShip);
        }
    }

    void opponentsTurn(){





    }








    public static void main(String[] args) {
        new Game();
    }

}






