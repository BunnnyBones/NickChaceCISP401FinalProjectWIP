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

    public Board() {  //Initializing all the slots in all 3 arrays

        Cards defaultCard = new Cards("none", 0, false, -1);

        for(int i = 0; i < row; i++){
            for(int j = 0; j <column; j++){
                mainBoard[i][j] = defaultCard;
                specialBoard1[i][j] = defaultCard;
                specialBoard2[i][j] = defaultCard;
            }
        }
    }


    public void resetCaravan(int selectedCaravan){

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
}
