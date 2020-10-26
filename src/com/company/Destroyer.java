package com.company;

public class Destroyer implements Ship {

    private final String shipType = "Destroyer";
    private final int shipNumber = 5;
    private final int shipLength = 2;
    private static String shipAlignment = "";

    public Destroyer(){  }

    public String getShipType(){ return this.shipType; }
    public int getShipNumber(){ return this.shipNumber; }
    public int getShipLength(){ return this.shipLength; }
    public String getShipAlignment(){ return shipAlignment; }
    public void setShipAlignment(String alignment){ shipAlignment = alignment; }

}
