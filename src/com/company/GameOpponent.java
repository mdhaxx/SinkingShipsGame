package com.company;

public class GameOpponent {
    private static String opponentName;

    GameOpponent(Game game) {
        setOpponentName();
        game.setPlayerNameFromInput(opponentName, 0);
    }

    public void setOpponentName() {
        opponentName = "Admiral BigShot";
        //prepared for multiplayer
    }

}
