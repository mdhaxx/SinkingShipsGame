package com.company;

import java.io.File;
import java.io.FileWriter;
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

    private final GameBoard gameBoard;

    private Game() {

        gameBoard = new GameBoard(this);
        gameBoard.initGameBoard();
        GamePlayer gameplayer = new GamePlayer(this);


    }
    public String getPlacedAtGridPosition(int index) { return this.placedAtGridPosition[index]; }

    public void setPlayerNameFromInput(String name, int index){ this.players[index] = name;}
    public String getNameOfPlayer(int index){ return this.players[index];}

    public void setPlaceHolder(String placeHolder){ this.placeHolder = placeHolder;}
    public String getPlaceHolder(){ return this.placeHolder;}

    public void setLeftGridValue(int indexI, int indexJ, int value){ this.leftGrid[indexI][indexJ] = value;}
    public int getLeftGridValue(int indexI, int indexJ){ return this.leftGrid[indexI][indexJ];}

    public void setRightGridValue(int indexI, int indexJ, int value){ this.leftGrid[indexI][indexJ] = value;}
    public int getRightGridValue(int indexI, int indexJ){ return this.leftGrid[indexI][indexJ];}

    public void setYourTurn(){ this.yourTurn = !this.getYourTurn(); }
    public boolean getYourTurn(){ return this.yourTurn; }


    void returnFromGamePlayer(GamePlayer gamePlayer, String name) {
        gamePlayer.dispose();
        this.players[1] = name;
        nextAction("");
    }

    void nextAction(String actionCommand) {
        GameProgress gameprogress = new GameProgress(this, gameBoard);
        gameprogress.actionCase(actionCommand);
    }

/*
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
            leftGrid[Integer.parseInt(actionCommand.substring(5))][0] = currentShip.getShipNumber();
            ArrayList<Integer> listCounter = new ArrayList<>();
            for(int i = 0; i <= 100; i++){
                if(leftGrid[i][0] == currentShip.getShipNumber()){
                    listCounter.add(leftGrid[i][0]);
                }
            }
            if(listCounter.size() == currentShip.getShipLength()) {
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
        }
    }


 */
    void test(GameBoard gameBoard){
        gameBoard.disableLeftButtons();

    }

    void initCurrentShip(Ship currentShip) {
        if(placeHolder.equals("Game On")){
            //-----------------------------
            // opponentsTurn();
            //-----------------------------
        }else {
            //currentShip.setInitNewShip(true);
            //placeYourShip(currentShip);
        }
    }


    void opponentsTurn(){





    }








    public static void main(String[] args) {
        new Game();
    }


}






