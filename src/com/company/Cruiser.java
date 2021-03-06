package com.company;

class Cruiser implements Ship {

    private final String shipType = "Cruiser";
    private final int shipNumber = 3;
    private final int shipLength = 3;
    private static String shipAlignment = "";

    public Cruiser(){  }

    public String getShipType(){ return this.shipType; }
    public int getShipNumber(){ return this.shipNumber; }
    public int getShipLength(){ return this.shipLength; }
    public String getShipAlignment(){ return shipAlignment; }
    public void setShipAlignment(String alignment){ shipAlignment = alignment; }

}
