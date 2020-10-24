package com.company;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

class GameData {
    private final String fileName = "./gameData/gameData.txt";
    private Game game;

    int[][] testArrayL = new int[5][2];
    int[][] testArrayR = new int[5][2];




    public GameData(){
        this.game = game;
/*
        testArrayL[1][0] = 101;
        testArrayL[2][0] = 201;
        testArrayL[3][0] = 301;
        testArrayL[4][0] = 401;

        testArrayL[1][1] = 111;
        testArrayL[2][1] = 211;
        testArrayL[3][1] = 311;
        testArrayL[4][1] = 411;

        testArrayR[1][0] = 102;
        testArrayR[2][0] = 202;
        testArrayR[3][0] = 302;
        testArrayR[4][0] = 402;

        testArrayR[1][1] = 112;
        testArrayR[2][1] = 212;
        testArrayR[3][1] = 312;
        testArrayR[4][1] = 412;

 */


        //printStringToFile(getStringFromArrayGridLeftAndArrayGridRight(testArrayL, testArrayR));
        //getStringFromFileToArrayGridLeftAndArrayGridRight();
    }

    public static void main(String[] args) {
        new GameData();

    }


/*
    public void saveGameData(Game game) {
        if (game.getPlaceHolder().equals("Carrier")) {
            JOptionPane noGame = new JOptionPane();
            JOptionPane.showMessageDialog(noGame, "There is no game to save!", "No game", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // ---------------------------------------
            //  här ska vi spara ner data till fil.
            // ---------------------------------------
        }
        System.exit(0);
    }
        // ---------------------------------------



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

    public boolean checkIfDataExistsForSaving(){

        return false;
    }

    private void initSavedGame() {
        //gameData = new GameData();
        //gameData.loadSavedGameData();
    }





    public openFile(){
        try { //Lagt till en try, flyttat scanner för att kunna använda close i finally.
            Scanner sc = new Scanner(new File(inputFile));
            try {
                String[] dateStrings = getDateStrings(sc);
                LocalDateTime[] dates = getDates(dateStrings);
                System.out.println(getStringMessage() + getTotalFeeCost(dates));
            } finally {
                sc.close(); //close saknades (1)
            }

            //utökat felhanteringen för att ta hand om alla eventualiteter.
        } catch (IOException e) {
            System.err.println("Could not read file " + inputFile);
        } catch (NoSuchElementException e) { //(2)
            System.err.println("No data to process");
        } catch (IllegalArgumentException e) { //(3)
            System.err.println("Wrong date in inputfile!");
        } catch (Exception e) { //(4)
            System.err.println("Ouch! Something went terribly wrong...");
        }
    }

    public String getStringMessage() {
        return "The total fee for the inputfile is ";
    } //Mellanrum saknades(5)

    public String[] getDateStrings(Scanner sc) {
        String[] dateStrings = sc.nextLine().split(", ");
        return dateStrings;
    }
        */
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

        int row = 1;
        for(int i = 0; i <= (splitStringReceivedFromFile.length)-1; i+=4){
            testArrayL[row][0] =  Integer.parseInt(splitStringReceivedFromFile[i]);
            testArrayL[row][1] =  Integer.parseInt(splitStringReceivedFromFile[i+1]);
            testArrayR[row][0] =  Integer.parseInt(splitStringReceivedFromFile[i+2]);
            testArrayR[row][1] =  Integer.parseInt(splitStringReceivedFromFile[i+3]);
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
