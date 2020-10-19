package com.company;

public class Cruiser extends Ship {

    public Cruiser(String shipType){
        super(shipType);
        this.shipNumber = 3;
        this.shipLength = 3;
        this.placement = new int[shipLength];

    }


}
