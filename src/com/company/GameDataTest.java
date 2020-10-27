package com.company;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class GameDataTest{

    private final int[][] testArrayL = new int[3][2];
    private final int[][] testArrayR = new int[3][2];

    GameDataTest() {  }

    @Test
    @DisplayName("test to put arrays from both grids into string")
    void testGettingStringFromTwo2DArrays(){

        testArrayL[1][0] = 101;
        testArrayL[2][0] = 201;

        testArrayL[1][1] = 111;
        testArrayL[2][1] = 211;

        testArrayR[1][0] = 102;
        testArrayR[2][0] = 202;

        testArrayR[1][1] = 112;
        testArrayR[2][1] = 212;

        String expOutput = "101;111;102;112;201;211;202;212";
        GameData gameData = new GameData();

        assertEquals(expOutput, gameData.getStringFromArrayGridLeftAndArrayGridRight(testArrayL, testArrayR), "not the same output");
    }

    @Test
    @DisplayName("test to write string to file")
    void testToWriteStringToFile(){

        String fileName = "./gameData/gameData.txt";
        GameData gameData = new GameData();

        gameData.printStringToFile("Test write and read to file.");

        Scanner sc;
        String stringInFile = "";

        try {
            sc = new Scanner(new File(fileName));
            stringInFile = sc.nextLine();
        } catch(Exception e){
            System.err.println("Ouch! Something went terribly wrong...");
        }

        assertEquals("Test write and read to file.", stringInFile, "not the same string in file as expected");
    }

    @Test
    @DisplayName("test to read string from file")
    void testToReadStringFromFile(){

        String fileName = "./gameData/gameData.txt";

        String inputStringToFile = "Test reading from file.";

        try {
            FileWriter inputToFile = new FileWriter(fileName);

            inputToFile.write(inputStringToFile);
            inputToFile.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

        GameData gameData = new GameData();

        String actStringFromFile = gameData.readStringFromFile();

        assertEquals(inputStringToFile, actStringFromFile, "not the same string in file as expected");

    }

    @Test
    @DisplayName("test to create ship and get right shipType")
    void testToGetCorrectShipType() {

        Ship testShip = new Destroyer();

        assertEquals("Destroyer", testShip.getShipType(), "shipType does not match");

    }

    @Test
    @DisplayName("test to create ship and get right shipLength")
    void testToGetCorrectShipLength() {

        Ship testShip = new Destroyer();

        assertEquals(2, testShip.getShipLength(), "shipType does not match");

    }

}
