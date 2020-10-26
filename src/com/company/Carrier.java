package com.company;

class Carrier implements Ship {

    private final String shipType = "Carrier";
    private final int shipNumber = 1;
    private final int shipLength = 5;
    private static String shipAlignment = "";

    public Carrier(){  }

    public String getShipType(){ return this.shipType; }
    public int getShipNumber(){ return this.shipNumber; }
    public int getShipLength(){ return this.shipLength; }
    public String getShipAlignment(){ return shipAlignment; }
    public void setShipAlignment(String alignment){ shipAlignment = alignment; }

}
