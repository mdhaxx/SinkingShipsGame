package com.company;

public class GameOpponent {
    private static String opponentName;

    GameOpponent(Game game) {
        setOpponentName();
        game.setPlayerNameFromInput(opponentName, 0);
    }

    /**
     * to add opponents name into the game.
     * prepared for multifunction purpose.
     */
    public void setOpponentName() {
        opponentName = "Admiral BigShot";
    }

}
