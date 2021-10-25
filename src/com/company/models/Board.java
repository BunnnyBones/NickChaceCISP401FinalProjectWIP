package com.company.models;

public class Board {

    private final int row = 6;
    private final int column = 3;

    private Cards[][] mainBoard = new Cards[6][3];  //Rows 0-5 and Columns 0-2

    private Cards[][] specialBoard1 = new Cards[6][3];  //slot 1 for special cards
    private Cards[][] specialBoard2 = new Cards[6][3];  //slot 2 for special cards

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
}
