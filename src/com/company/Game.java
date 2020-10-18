package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Game {
    private GameBoard gameBoard;
    private Player[] players;
    private GamePlay gamePlay;
    private GameData gameData;

    private boolean choseToSaveGame;
    private boolean previousGameIsSaved;
    private Scanner scan;
    private File file;
    private FileWriter fileWriter;


    private Game() {
        initGameBoard();
        initPlayers();
        initGamePlay();
        if(previousGameIsSaved) { initSavedGame(); }
    }
    private void initGameBoard() {
        gameBoard = new GameBoard();
    }

    private void initPlayers() {
        players = new Player[2];
        players[0] = new Player("InputName");
        players[1] = new Player("Opponent");
    }

    private void initGamePlay() {
        gamePlay = new GamePlay();

    }

    private void initSavedGame() {
        gameData = new GameData();
        gameData.loadSavedGameData();
    }

    class GameData {

        private void saveGameData(GameData currentGameData) {
            if(choseToSaveGame) {
                try{
                    //Antingen outputstream/inputstream eller scanner/filewriter?
                    //DataOutputStream
                    fileWriter = new FileWriter(File.createTempFile("SavedGameData", "txt", new File("gameData")));
                    //scan = new Scanner(currentGameData);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally { scan.close(); }
            }
        }

        private void loadSavedGameData() {
            try {
                scan = new Scanner(new File("gameData/savedGameData.txt"));
                if (file.exists())
                    while (scan.hasNext()) {
                        //DataInputStream
                    }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                scan.close();
            }
        }
    }


    public static void main(String[] args) { new Game(); }

}






