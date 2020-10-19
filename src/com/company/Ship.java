package com.company;

public abstract class Ship {
    protected String shipType;
    protected int shipNumber;
    protected int shipLength;
    protected int[] placement = new int[shipLength];

    public Ship(String shipType){ this.shipType = shipType; }

    public String getShipType(){ return this.shipType; }
    public int getShipNumber(){ return this.shipNumber; }
    public int[] getPlacement(){ return this.placement; }
    public int getShipLength(){ return this.shipLength; }
}
