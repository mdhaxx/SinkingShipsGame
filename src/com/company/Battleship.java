package com.company;

public class Battleship implements Ship {

    private String shipType = "Battleship";
    private final int shipNumber = 2;
    private final int shipLength = 4;
    private static String shipAlignment = "";

    public Battleship(){  }

    public String getShipType(){ return this.shipType; }
    public int getShipNumber(){ return this.shipNumber; }
    public int getShipLength(){ return this.shipLength; }
    public String getShipAlignment(){ return this.shipAlignment; }
    public void setShipAlignment(String alignment){ this.shipAlignment = alignment; }



}
