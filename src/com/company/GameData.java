package com.company;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

class GameData {

    public void saveGameData(Game game, Ship currentShip) {
        if (currentShip.shipType.equals("Carrier")) {
            JOptionPane noGame = new JOptionPane();
            JOptionPane.showMessageDialog(noGame, "There is no game to save!", "No game", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        } else {


            if (currentShip.shipType.equals("Carrier")) {
                for (int i = 0; i < currentShip.getShipLength(); i++) {
                    if (currentShip.placement[i] == 0) {
                        break;
                    }
                }
            }


            //här ska vi spara ner data till fil.
            System.exit(0);
        }
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


         */
}
