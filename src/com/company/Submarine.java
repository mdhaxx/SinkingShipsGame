package com.company;

public class Submarine implements Ship {

    private final String shipType = "Submarine";
    private final int shipNumber = 4;
    private final int shipLength = 3;
    private static String shipAlignment = "";

    public Submarine(){  }

    public String getShipType(){ return this.shipType; }
    public int getShipNumber(){ return this.shipNumber; }
    public int getShipLength(){ return this.shipLength; }
    public String getShipAlignment(){ return shipAlignment; }
    public void setShipAlignment(String alignment){ shipAlignment = alignment; }

}
