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

    /**
     * Method for adding the game-object to gameData().
     * @param game Game as object.
     */
    public void setGame(Game game) {
        GameData.game = game;
    }

    /**
     * Checks if there has been any events in the game so far yet
     */
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

    /**
     * checks whether there is data to save and asks
     * the user to save or not and take action accordingly.
     */
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

    /**
     * if there is a savedGame file... delete it
     */
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

    /**
     * gather all data to be printed to file and sets just that in motion.
     */
    public void setGameDataToSave() {
        printStringToFile(getStringFromArrayGridLeftAndArrayGridRight(game.getLeftGrid(), game.getRightGrid()) + getStringFromPlayersArray());
    }

    /**
     * takes all info in the arrays from left- and rightside of the gamefield
     * @param arrayLeftGrid points to the left side of the gamefield
     * @param arrayRightGrid points to the right side of the gamefield
     * @return String with all info appended.
     */
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

    /**
     * gather the players names.
     * @return String with players names appended.
     */
    public String getStringFromPlayersArray() {
        return ";" + game.getNameOfPlayer(0) + ";" + game.getNameOfPlayer(1);
    }

    /**
     * Writes all gameData gathered in a String to the gameData-file.
     */
    public void printStringToFile(String stringToPutToFile) {
        try {
            FileWriter inputToFile = new FileWriter(fileName);

            inputToFile.write(stringToPutToFile);
            inputToFile.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * is there any gameData from previous game.
     * and if so asks the user to load or start a new game.
     *redirect you accordingly...
     */
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

    /**
     * initiates the loading of a previous game.
     * gather the gameData to add into the game.
     */
    public void getGameDataToLoad() {
        getGameDataToArrayPlayers();
        getGameDataToArrayGridLeftAndArrayGridRight();
        getHowManyShots();
        getWhomTurn();
    }

    /**
     * retrieves the players names from savedData-file and adds them into the game.
     */
    public void getGameDataToArrayPlayers() {
        String stringReceivedFromFile = readStringFromFile();
        String[] splitStringReceivedFromFile = stringReceivedFromFile.split(";");

        game.setPlayerNameFromInput(splitStringReceivedFromFile[splitStringReceivedFromFile.length-2],0);
        game.setPlayerNameFromInput(splitStringReceivedFromFile[splitStringReceivedFromFile.length-1],1);
    }

    /**
     * retrieves the gameData from savedData-file and adds it into the game.
     */
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

    /**
     * retrieves the intel from savedData-file.
     * @return String with the content of gameData.
     */
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

    /**
     * analyses the gamedata to determine the placeholder.
     * or where in the games progress we are in.
     */
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

    /**
     * analyses the gamedata to determine how many shots have been fired by the players.
     */
    public void getHowManyShots() {
        for(int i = 1; i <= 100; i++){
            if(game.getLeftGridValue(i,1)  == 1 && game.getLeftGridValue(i,0) > 0)   { game.setOpponentHit(game.getOpponentHit()+1); }
            if(game.getLeftGridValue(i,1)  == 1 && game.getLeftGridValue(i,0) == 0)  { game.setOpponentNoHit(game.getOpponentNoHit()+1); }
            if(game.getRightGridValue(i,1) == 1 && game.getRightGridValue(i,0) > 0)  { game.setYourHit(game.getYourHit()+1); }
            if(game.getRightGridValue(i,1) == 1 && game.getRightGridValue(i,0) == 0) { game.setYourNoHit(game.getYourNoHit()+1); }
        }
    }

    /**
     * analyses the gamedata to determine how's turn it is.
     */
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
