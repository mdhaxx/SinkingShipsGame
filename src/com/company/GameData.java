package com.company;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Scanner;

class GameData {
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

        /*

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

    public boolean checkIfDataExistsForSaving(){

        return false;
    }

    private void initSavedGame() {
        //gameData = new GameData();
        //gameData.loadSavedGameData();
    }


         *//*


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
}
