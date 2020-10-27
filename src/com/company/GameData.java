package com.company;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

class GameData {
    private final String fileName = "./gameData/gameData.txt";
    private static Game game;

    public GameData(){  }

    public void setGame(Game game) {
        GameData.game = game;
    }

    public boolean checkIfDataToSaveExist() {
        boolean isData = false;
        for(int i = 1; i <= 100; i++) {
            if(game.getLeftGridValue(i, 0) > 0) {
                isData = true;
                break;
            }
            if(game.getLeftGridValue(i, 1) > 0) {
                isData = true;
                break;
            }
            if(game.getRightGridValue(i, 0) > 0) {
                isData = true;
                break;
            }
            if(game.getRightGridValue(i, 1) > 0) {
                isData = true;
                break;
            }
        }
        return isData;
    }

    public void saveGameData() {
        if(!checkIfDataToSaveExist()) {
            JOptionPane noGame = new JOptionPane();
            JOptionPane.showMessageDialog(noGame, "There is no game to save!", "No game", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane pane = new JOptionPane();
            Object[] options = {"Yes, save", "No, just quit"};
            int choice;
            do {
                choice = JOptionPane.showOptionDialog(pane,
                        "Do you want to save your game?", "Quit game.", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
            } while(choice == JOptionPane.CLOSED_OPTION);
            if(choice == 0) {
                setGameDataToSave();
            } else {
                deleteGameDataFile();
            }
        }
        System.exit(0);
    }

    public void deleteGameDataFile() {
        File file = new File(fileName);
        if(file.exists()) {
            try {
                if(!file.delete()) {
                    System.err.print("Not able to delete file.");
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setGameDataToSave() {
        printStringToFile(getStringFromArrayGridLeftAndArrayGridRight(game.getLeftGrid(), game.getRightGrid()) + getStringFromPlayersArray());
    }

    public String getStringFromArrayGridLeftAndArrayGridRight(int[][] arrayLeftGrid, int[][] arrayRightGrid) {
        StringBuilder contentToWriteToFile = new StringBuilder();

        for(int i = 1; i <=arrayLeftGrid.length-1; i++) {
                contentToWriteToFile.append(arrayLeftGrid[i][0]).append(";");
                contentToWriteToFile.append(arrayLeftGrid[i][1]).append(";");
                contentToWriteToFile.append(arrayRightGrid[i][0]).append(";");
                contentToWriteToFile.append(arrayRightGrid[i][1]).append(";");
        }
        return String.valueOf(contentToWriteToFile).substring(0, contentToWriteToFile.length()-1);
    }

    public String getStringFromPlayersArray() {
        return ";" + game.getNameOfPlayer(0) + ";" + game.getNameOfPlayer(1);
    }

    public void printStringToFile(String stringToPutToFile) {
        try {
            FileWriter inputToFile = new FileWriter(fileName);

            inputToFile.write(stringToPutToFile);
            inputToFile.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void checkIfGameDataToLoadExist() {
        File file = new File(fileName);
        if(file.exists()) {
            JOptionPane pane = new JOptionPane();
            Object[] options = {"Saved game", "New game"};
            int choice;
            do {
                choice = JOptionPane.showOptionDialog(pane,
                        "There is a saved game from last time you played.\nDo you want to continue play or start a new game?", "Load game.", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
            } while(choice == JOptionPane.CLOSED_OPTION);
            if(choice == 0) {
                getGameDataToLoad();
            } else {
                deleteGameDataFile();
                checkIfGameDataToLoadExist();
            }
        }
    }

    public void getGameDataToLoad() {
        getGameDataToArrayPlayers();
        getGameDataToArrayGridLeftAndArrayGridRight();
        getHowManyShots();
        getWhomTurn();
    }

    public void getGameDataToArrayPlayers() {
        String stringReceivedFromFile = readStringFromFile();
        String[] splitStringReceivedFromFile = stringReceivedFromFile.split(";");

        game.setPlayerNameFromInput(splitStringReceivedFromFile[splitStringReceivedFromFile.length-2],0);
        game.setPlayerNameFromInput(splitStringReceivedFromFile[splitStringReceivedFromFile.length-1],1);
    }

    public void getGameDataToArrayGridLeftAndArrayGridRight() {
        String stringReceivedFromFile = readStringFromFile();
        String[] splitStringReceivedFromFile = stringReceivedFromFile.split(";");

        int row = 1;
        for(int i = 0; i <= (splitStringReceivedFromFile.length)-3; i+=4) {
            game.setLeftGridValue(row, 0, Integer.parseInt(splitStringReceivedFromFile[i]));
            game.setLeftGridValue(row, 1, Integer.parseInt(splitStringReceivedFromFile[i+1]));
            game.setRightGridValue(row, 0, Integer.parseInt(splitStringReceivedFromFile[i+2]));
            game.setRightGridValue(row, 1, Integer.parseInt(splitStringReceivedFromFile[i+3]));
            row++;
        }
    }

    public String readStringFromFile() {
        Scanner sc;
        String stringInFile = "";

        try {
            sc = new Scanner(new File(fileName));
            stringInFile = sc.nextLine();
            sc.close();
        } catch(Exception e) {
            System.err.println("Ouch! Something went terribly wrong...");
        }
        return stringInFile;
    }

    public void getWhichPlaceHolder() {
        ArrayList<Integer> whichShips = new ArrayList<>();
        for(int i = 1; i <= 100; i++) {
            if(game.getLeftGridValue(i, 0) > 0) {
                whichShips.add(game.getLeftGridValue(i, 0));
            }
        }
        int whichPlaceHolder = whichShips
                .stream()
                .max(Integer::compare)
                .orElse(0);

        switch(whichPlaceHolder) {
            case 1 -> game.setPlaceHolder("Battleship");
            case 2 -> game.setPlaceHolder("Cruiser");
            case 3 -> game.setPlaceHolder("Submarine");
            case 4 -> game.setPlaceHolder("Destroyer");
            case 5 -> game.setPlaceHolder("Shooting");
        }
    }

    public void getHowManyShots() {
        for(int i = 1; i <= 100; i++){
            if(game.getLeftGridValue(i,1)  == 1 && game.getLeftGridValue(i,0) > 0)   { game.setOpponentHit(game.getOpponentHit()+1); }
            if(game.getLeftGridValue(i,1)  == 1 && game.getLeftGridValue(i,0) == 0)  { game.setOpponentNoHit(game.getOpponentNoHit()+1); }
            if(game.getRightGridValue(i,1) == 1 && game.getRightGridValue(i,0) > 0)  { game.setYourHit(game.getYourHit()+1); }
            if(game.getRightGridValue(i,1) == 1 && game.getRightGridValue(i,0) == 0) { game.setYourNoHit(game.getYourNoHit()+1); }
        }
    }

    public void getWhomTurn() {
        int countShotsYou = 0;
        int countShotsOpp = 0;

        for(int i = 1; i <= 100; i++) {
            if(game.getLeftGridValue(i,1) == 1) {
                countShotsYou++;
            }
            if(game.getRightGridValue(i,1) == 1) {
                countShotsOpp++;
            }
        }
        if(countShotsOpp > countShotsYou) {
            game.setYourTurn();
        }
    }

}
