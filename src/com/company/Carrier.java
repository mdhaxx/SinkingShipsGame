package com.company;

public class Carrier extends Ship {

    public Carrier(String shipType){
        super(shipType);
        this.shipNumber = 1;
        this.shipLength = 5;
        this.placement = new int[shipLength];

    }


}
