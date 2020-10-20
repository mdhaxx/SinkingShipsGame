package com.company;

interface Ship {

    void setShipType(String shipType);
    String getShipType();
    int getShipNumber();
    int getShipLength();
    boolean getInitNewShip();
    void setInitNewShip(boolean initial);
}
