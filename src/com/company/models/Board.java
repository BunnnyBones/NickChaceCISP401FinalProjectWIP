package com.company.models;

public class Board {

    private final int row = 6;
    private final int column = 3;

    private final Cards[][] mainBoard = new Cards[6][3];  //Rows 0-5 and Columns 0-2

    private final Cards[][] specialBoard1 = new Cards[6][3];  //slot 1 for special cards
    private final Cards[][] specialBoard2 = new Cards[6][3];  //slot 2 for special cards

    private int total1;
    private int total2;
    private int total3;

    private int layerCount1 = 0;  //This is to keep track of what "layer" or row is the next available for player input and based on number of columns that should be the same for all 3 boards (NOPE)
    private int layerCount2 = 0;
    private int layerCount3 = 0;

    private final Cards defaultCard = new Cards("", null, false, -1);

    public Board() {  //Initializing all the slots in all 3 arrays

        for(int i = 0; i < row; i++){
            for(int j = 0; j <column; j++){
                mainBoard[i][j] = defaultCard;
                specialBoard1[i][j] = defaultCard;
                specialBoard2[i][j] = defaultCard;
            }
        }
    }


    public void resetCaravan(int selectedCaravan){
        for(int i = 0; i < column; i++){
            mainBoard[selectedCaravan][i] = defaultCard;
            specialBoard1[selectedCaravan][i] = defaultCard;
            specialBoard2[selectedCaravan][i] = defaultCard;
        }
    }

    public Cards[][] getMainBoard() {
        return mainBoard;
    }

    public Cards[][] getSpecialBoard1() {
        return specialBoard1;
    }

    public Cards[][] getSpecialBoard2() {
        return specialBoard2;
    }

    public int getTotal1() {
        return total1;
    }

    public int getTotal2() {
        return total2;
    }

    public int getTotal3() {
        return total3;
    }

    public int getRows(){return row;}

    public int getCol(){return column;}

    public int getLayerCount(int whichOne){
        if(whichOne == 0){
            return layerCount1;
        }
        if(whichOne == 1){
            return layerCount2;
        }
        if(whichOne == 2){
            return layerCount3;
        }
        //Else
        return -1;
    }

    public void setTotal(int whichOne, int value){
        if(whichOne == 0){
            total1 = value;
        }
        if(whichOne == 1){
            total2 = value;
        }
        if(whichOne == 2){
            total3 = value;
        }
    }
    public void setLayers(int whichOne, int value) {
        if (whichOne == 0) {
            layerCount1 = value;
        }
        if (whichOne == 1) {
            layerCount2 = value;
        }
        if (whichOne == 2) {
            layerCount3 = value;
        }
    }

    public void incTotal(int whichOne, int value) {
        if (whichOne == 0) {
            total1 += value;
        }
        if (whichOne == 1) {
            total2 += value;
        }
        if (whichOne == 2) {
            total3 += value;
        }
    }

    public void decTotal(int whichOne, int value) {
        if (whichOne == 0) {
            total1 -= value;
        }
        if (whichOne == 1) {
            total2 -= value;
        }
        if (whichOne == 2) {
            total3 -= value;
        }
    }

    public void incLayerCount(int whichOne){
        if (whichOne == 0) {
            layerCount1 += 1;
        }
        if (whichOne == 1) {
            layerCount2 += 1;
        }
        if (whichOne == 2) {
            layerCount3 += 1;
        }
    }
}
