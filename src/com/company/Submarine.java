package com.company;

public class Submarine extends Ship {

    public Submarine(String shipType){
        super(shipType);
        this.shipNumber = 4;
        this.shipLength = 3;
        this.placement = new int[shipLength];

    }


}
