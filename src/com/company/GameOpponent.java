package com.company;

public class GameOpponent {
    private static String opponentName;

    GameOpponent(Game game) {
        setOpponentName();
        game.setPlayerNameFromInput(this.opponentName, 0);
    }

    public void setOpponentName() {
        this.opponentName = "Admiral BigShot";
        //prepared for multiplayer
    }

    void actionCase(int placement) {
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
                game.setYourTurn();
                gameBoard.disableLeftButtons();
                GameOpponent gameOpponent = new GameOpponent(game);
                placeOpponentsShip();
                infoBox("All your ships have been placed\n\nGame is on!");


            }
        }
    }

}
