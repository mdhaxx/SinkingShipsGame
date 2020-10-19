package com.company;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Game {

    private int[][] leftGrid = new int[101][2];
    private int[][] rightGrid = new int[101][2];
    private String[] players = {"Admiral BigShot", ""};
    private boolean yourTurn = true;

    private boolean choseToSaveGame;
    private boolean previousGameIsSaved;
    private Scanner scan;
    private File file;
    private FileWriter fileWriter;


    private Game() {

        GameBoard gameBoard = new GameBoard(this);
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


    public void returnFromGamePlayer(GamePlayer gamePlayer, Game game) {
        gamePlayer.dispose();
        whereAreWe("");
    }

    public void whereAreWe(String actionCommand){
        if(!actionCommand.equals("")){
            placeYourShip("Carrier");
        } else {


        }
    }
    public boolean checkIfDataExistsForSaving(){

        return false;
    }

    public void placeYourShip(String placeHolder){

        Ship currentShip = new Carrier(placeHolder);
        if(userDialogueShip(currentShip) == 0){ //chose 'OK'
            //start placing ship
        } else {
           quitGame(currentShip);
        }


    }

    public int userDialogueShip(Ship currentShip) {
        JOptionPane pane = new JOptionPane();
        return JOptionPane.showConfirmDialog(pane, "Place your " + currentShip.shipType + "\n Vertically or horizontally only\n Size: " + currentShip.getShipLength() + " squares", "Place Battleships", JOptionPane.OK_CANCEL_OPTION);
        //if (choice == 2 || choice == JOptionPane.CLOSED_OPTION) {
        //    quitGame(currentShip);
        //} else {
        //    placeYourShip(currentShip.getShipType());
        //}
    }

    void quitGame(Ship currentShip) {
        JOptionPane quitGame = new JOptionPane();
        Object[] options = {"Yes, quit and save", "No, continue playing"};
        int choice = JOptionPane.showOptionDialog(quitGame, "Do you want to exit the game", "Quit game", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
        if (choice == 0) {
            GameData gameData = new GameData();
            gameData.saveGameData(this, currentShip);
        } else {
            if(currentShip.getShipType().equals("Carrier")){
                userDialogueShip(currentShip);
            }
        }
    }

    private void initSavedGame() {
        //gameData = new GameData();
        //gameData.loadSavedGameData();
    }


    public static void main(String[] args) {
        new Game();
    }

}






