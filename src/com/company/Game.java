package com.company;

public class Game {

    private int[][] leftGrid = new int[101][2];
    private int[][] rightGrid = new int[101][2];
    private String[] players = new String[2];
    private final String[] placedAtGridPosition = { "Water", "Carrier", "Battleship", "Cruiser", "Submarine", "Destroyer", "Game On" };

    private boolean yourTurn = true;
    private String placeHolder = "";

    private int yourHit;
    private int yourNoHit;
    private int opponentHit;
    private int opponentNoHit;

    private final GameBoard gameBoard;

    Game() {
        GameData gameData = new GameData();
        gameData.setGame(this);
        gameData.checkIfGameDataToLoadExist();

        gameBoard = new GameBoard(this);
        gameBoard.initGameBoard();

        if(players[0] == null) {
            new GameOpponent(this);
            new GamePlayer(this);
        } else {
            gameData.getWhichPlaceHolder();
            whereToFromSavedGame();
        }
    }

    public int getYourHit() { return this.yourHit; }
    public void setYourHit(int yourHit) { this.yourHit = yourHit; }

    public int getYourNoHit() { return this.yourNoHit; }
    public void setYourNoHit(int yourNoHit) { this.yourNoHit = yourNoHit; }

    public int getOpponentHit() { return this.opponentHit; }
    public void setOpponentHit(int opponentHit) { this.opponentHit = opponentHit; }

    public int getOpponentNoHit() { return this.opponentNoHit; }
    public void setOpponentNoHit(int opponentNoHit) { this.opponentNoHit = opponentNoHit; }

    public String getPlacedAtGridPosition(int index) { return this.placedAtGridPosition[index]; }

    public void setPlayerNameFromInput(String name, int index) { this.players[index] = name; }
    public String getNameOfPlayer(int index) { return this.players[index]; }

    public void setPlaceHolder(String placeHolder) { this.placeHolder = placeHolder; }
    public String getPlaceHolder() { return this.placeHolder; }

    public void setLeftGridValue(int indexI, int indexJ, int value) { this.leftGrid[indexI][indexJ] = value; }
    public int getLeftGridValue(int indexI, int indexJ) { return this.leftGrid[indexI][indexJ]; }

    public int[][] getLeftGrid() { return this.leftGrid; }
    public int[][] getRightGrid() { return this.rightGrid; }

    public void setRightGridValue(int indexI, int indexJ, int value) { this.rightGrid[indexI][indexJ] = value; }
    public int getRightGridValue(int indexI, int indexJ) { return this.rightGrid[indexI][indexJ]; }

    public void setYourTurn() { this.yourTurn = !this.getYourTurn(); }
    public boolean getYourTurn() { return this.yourTurn; }

    /**
     * gets the players name and tugs it away.
     * redirects to the next action to take.
     */
    void redirectFromGamePlayer(GamePlayer gamePlayer, String name) {
        gamePlayer.dispose();
        this.players[1] = name;
        nextAction("");
    }

    /**
     * redirects the game thru to the next step
     * adding new ships or playing the game
     */
    void nextAction(String actionCommand) {
        GameProgress gameprogress = new GameProgress(this, gameBoard);
        if(actionCommand.length() == 0) {
            gameprogress.actionCase(actionCommand);
        } else if(placeHolder.equals("Shooting")) {
            gameprogress.timeToShoot(actionCommand);
        } else if(actionCommand.charAt(0) == 'l') {
            gameprogress.actionCase(actionCommand);
        } else {
            gameprogress.timeToShoot(actionCommand);
        }
    }

    /**
     * analyzes the loaded gamData to determine where to be in the game to be and pick up from there.
     */
    void whereToFromSavedGame() {
        GameProgress gameProgress = new GameProgress(this, gameBoard);
        switch(placeHolder) {
            case "Battleship" -> gameProgress.caseAction(new Battleship(), "");
            case "Cruiser"    -> gameProgress.caseAction(new Cruiser(),    "");
            case "Submarine"  -> gameProgress.caseAction(new Submarine(),  "");
            case "Destroyer"  -> gameProgress.caseAction(new Destroyer(),  "");
            case "Shooting"   -> gameBoard.disableLeftButtons();
        }
    }

    public static void main(String[] args) {
        new Game();
    }

}






