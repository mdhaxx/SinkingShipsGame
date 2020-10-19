package com.company;

public class Destroyer extends Ship {

    public Destroyer(String shipType){
        super(shipType);
        this.shipNumber = 5;
        this.shipLength = 2;
        this.placement = new int[shipLength];

    }


}
