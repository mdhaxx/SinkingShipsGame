package com.company;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Game {

    private int[][] leftGrid = new int[101][2];
    private int[][] rightGrid = new int[101][2];
    private String[] players = new String[2];
    private String[] placedAtGridPosition = { "Water", "Carrier", "Battleship", "Cruiser", "Submarine", "Destroyer", "Game On" };

    private boolean yourTurn = true;
    private String placeHolder = "";

    private boolean choseToSaveGame;
    private boolean previousGameIsSaved;
    private Scanner scan;
    private File file;
    private FileWriter fileWriter;

    private final GameBoard gameBoard;

    Game() {

        gameBoard = new GameBoard(this);
        gameBoard.initGameBoard();
        GameOpponent gameOpponent = new GameOpponent(this);
        GamePlayer gameplayer = new GamePlayer(this);


    }
    public String getPlacedAtGridPosition(int index) { return this.placedAtGridPosition[index]; }

    public void setPlayerNameFromInput(String name, int index){ this.players[index] = name;}
    public String getNameOfPlayer(int index){ return this.players[index];}

    public void setPlaceHolder(String placeHolder){ this.placeHolder = placeHolder;}
    public String getPlaceHolder(){ return this.placeHolder;}

    public void setLeftGridValue(int indexI, int indexJ, int value){ this.leftGrid[indexI][indexJ] = value;}
    public int getLeftGridValue(int indexI, int indexJ){ return this.leftGrid[indexI][indexJ];}

    public void setRightGridValue(int indexI, int indexJ, int value){ this.rightGrid[indexI][indexJ] = value;}
    public int getRightGridValue(int indexI, int indexJ){ return this.rightGrid[indexI][indexJ];}

    public void setYourTurn(){ this.yourTurn = !this.getYourTurn(); }
    public boolean getYourTurn(){ return this.yourTurn; }


    void redirectFromGamePlayer(GamePlayer gamePlayer, String name) {
        gamePlayer.dispose();
        this.players[1] = name;
        nextAction("");
    }

    void nextAction(String actionCommand) {
        GameProgress gameprogress = new GameProgress(this, gameBoard);
        if(actionCommand.length() == 0){
            gameprogress.actionCase(actionCommand);
        } else if(placeHolder.equals("Shooting")) {
            gameprogress.timeToShoot(actionCommand);
        } else if(actionCommand.charAt(0) == 'l') {
            gameprogress.actionCase(actionCommand);
        } else {
            gameprogress.timeToShoot(actionCommand);
        }
    }

    public static void main(String[] args) { new Game(); }

}






