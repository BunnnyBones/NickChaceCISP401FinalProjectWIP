package com.company.models;

public class Board {

    private final int row = 6;
    private final int column = 3;

    private Cards[][] mainBoard = new Cards[6][3];  //Rows 0-5 and Columns 0-2

    private Cards[][] specialBoard1 = new Cards[6][3];  //slot 1 for special cards
    private Cards[][] specialBoard2 = new Cards[6][3];  //slot 2 for special cards

    private int total1;
    private int total2;
    private int total3;

    private boolean order1;  //These are to determine the direction of the Caravan. You can only increase or decrease in numbers unless you have a queen
    private boolean order2;  //May change to String but true = increase && false = decrease should work
    private boolean order3;

    private Cards defaultCard = new Cards("", null, false, -1);

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
        for(int i = 0; i < row; i++){
            mainBoard[selectedCaravan][i] = defaultCard;
            specialBoard1[selectedCaravan][i] = defaultCard;
            specialBoard2[selectedCaravan][i] = defaultCard;
        }
    }

    public Cards[][] getMainBoard() {
        return mainBoard;
    }

    public void setMainBoard(Cards[][] mainBoard) {
        this.mainBoard = mainBoard;
    }

    public Cards[][] getSpecialBoard1() {
        return specialBoard1;
    }

    public void setSpecialBoard1(Cards[][] specialBoard1) {
        this.specialBoard1 = specialBoard1;
    }

    public Cards[][] getSpecialBoard2() {
        return specialBoard2;
    }

    public void setSpecialBoard2(Cards[][] specialBoard2) {
        this.specialBoard2 = specialBoard2;
    }

    public int getTotal1() {
        return total1;
    }

    public void setTotal1(int total1) {
        this.total1 = total1;
    }

    public int getTotal2() {
        return total2;
    }

    public void setTotal2(int total2) {
        this.total2 = total2;
    }

    public int getTotal3() {
        return total3;
    }

    public void setTotal3(int total3) {
        this.total3 = total3;
    }

    public int getRows(){return row;}

    public int getCol(){return column;}

    public boolean getOrder1() {
        return order1;
    }

    public void setOrder1(boolean order1) {
        this.order1 = order1;
    }

    public boolean getOrder2() {
        return order2;
    }

    public void setOrder2(boolean order2) {
        this.order2 = order2;
    }

    public boolean getOrder3() {
        return order3;
    }

    public void setOrder3(boolean order3) {
        this.order3 = order3;
    }
}
