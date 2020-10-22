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

}
