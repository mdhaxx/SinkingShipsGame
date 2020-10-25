package com.company;


import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

class GameData {
    private final String fileName = "./gameData/gameData.txt";

    int[][] testArrayL = new int[5][2];
    int[][] testArrayR = new int[5][2];

    private static Game game;

    public GameData(){

        //saveGameData();
        //printStringToFile(getStringFromArrayGridLeftAndArrayGridRight(testArrayL, testArrayR));
        //getStringFromFileToArrayGridLeftAndArrayGridRight();
    }


    public void setGame(Game game){
        this.game = game;
    }

    public boolean checkIfDataToSaveExist(){
        boolean isData = false;
        for(int i = 1; i <= 100; i++) {

            if(game.getLeftGridValue(i, 0) > 0){
                isData = true;
                break;
            }
            if(game.getLeftGridValue(i, 1) > 0){
                isData = true;
                break;
            }
            if(game.getRightGridValue(i, 0) > 0){
                isData = true;
                break;
            }
            if(game.getRightGridValue(i, 1) > 0){
                isData = true;
                break;
            }
        }
        return isData;
    }

    public void saveGameData() {
        if(!checkIfDataToSaveExist()){
            JOptionPane noGame = new JOptionPane();
            JOptionPane.showMessageDialog(noGame, "There is no game to save!", "No game", JOptionPane.INFORMATION_MESSAGE);
        } else {
            printStringToFile(getStringFromArrayGridLeftAndArrayGridRight(game.getLeftGrid(), game.getRightGrid()));
        }
        System.exit(0);
    }

    public String getStringFromArrayGridLeftAndArrayGridRight(int[][] arrayLeftGrid, int[][] arrayRightGrid){
        StringBuilder contentToWriteToFile = new StringBuilder();

        for(int i = 1; i <=arrayLeftGrid.length-1; i++){
                contentToWriteToFile.append(arrayLeftGrid[i][0]).append(";");
                contentToWriteToFile.append(arrayLeftGrid[i][1]).append(";");
                contentToWriteToFile.append(arrayRightGrid[i][0]).append(";");
                contentToWriteToFile.append(arrayRightGrid[i][1]).append(";");
        }

        return String.valueOf(contentToWriteToFile).substring(0, contentToWriteToFile.length()-1);
    }

    public void getStringFromFileToArrayGridLeftAndArrayGridRight(){
        String stringReceivedFromFile = readStringFromFile();
        String[] splitStringReceivedFromFile = stringReceivedFromFile.split(";");

        int l = splitStringReceivedFromFile.length;
        int ll = splitStringReceivedFromFile[splitStringReceivedFromFile.length-2].length();
        int ll2 = splitStringReceivedFromFile[splitStringReceivedFromFile.length-1].length();



        int row = 1;
        for(int i = 0; i <= (splitStringReceivedFromFile.length)-3; i+=4){
            game.setLeftGridValue(row, 0, Integer.parseInt(splitStringReceivedFromFile[i]));
            game.setLeftGridValue(row, 1, Integer.parseInt(splitStringReceivedFromFile[i+1]));
            game.setRightGridValue(row, 0, Integer.parseInt(splitStringReceivedFromFile[i+2]));
            game.setRightGridValue(row, 1, Integer.parseInt(splitStringReceivedFromFile[i+3]));
            row++;
        }
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

    public String readStringFromFile(){
        Scanner sc;
        String stringInFile = "";

        try {
            sc = new Scanner(new File(fileName));
            stringInFile = sc.nextLine();
        } catch(Exception e){
            System.err.println("Ouch! Something went terribly wrong...");
        }
        return stringInFile;
    }

}
