package com.company;

public class Submarine implements Ship {

    private String shipType;
    private final int shipNumber = 4;
    private final int shipLength = 3;
    private boolean initNewShip = true;

    public Submarine(String shipType){  }

    public void setShipType(String shipType){ this.shipType = shipType; }
    public String getShipType(){ return this.shipType; }
    public int getShipNumber(){ return this.shipNumber; }
    public int getShipLength(){ return this.shipLength; }
    public boolean getInitNewShip(){ return this.initNewShip; }
    public void setInitNewShip(boolean initial){ this.initNewShip = initial; }
}
