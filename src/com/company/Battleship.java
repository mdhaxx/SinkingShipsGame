package com.company;

public class Battleship extends Ship {

    public Battleship(String shipType){
        super(shipType);
        this.shipNumber = 2;
        this.shipLength = 4;
        this.placement = new int[shipLength];

    }


}
