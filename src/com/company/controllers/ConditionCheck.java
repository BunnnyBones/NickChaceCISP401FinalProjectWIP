package com.company.controllers;

import com.company.models.Board;

public class ConditionCheck {

    //Separate place for the victory win condition because it is beefy

    //The 'Caravans' are the three corresponding piles each player has. If 2/3 of the piles are "sold" (between 20-26)
    //...and the opponent's opposite piles are not sold then that 1st player wins.
    //The other condition is if all 3 of your Caravans are sold, and all 3 are higher than your opponents you win
    public boolean checkVictory(Board board1, Board board2){

        int individualCaravanCheck = 0;
        int competingCaravanCheck = 0;
                                                                        //Caravan #1 Check 1
        if(isSold(board1.getTotal1())){
            individualCaravanCheck += 1;
            if(!isSold(board2.getTotal1())){
                competingCaravanCheck += 1;
            }
        }
                                                                        //Caravan #2 Check 1
        if(isSold(board1.getTotal2())){
            individualCaravanCheck += 1;
            if(!isSold(board2.getTotal2())){
                competingCaravanCheck += 1;
            }
        }
                                                                         //Caravan #3 Check 1
        if(isSold(board1.getTotal3())){
            individualCaravanCheck += 1;
            if(!isSold(board2.getTotal3())){
                competingCaravanCheck += 1;
            }
        }

        if(competingCaravanCheck >= 2){
            return true; //The first player that was passed over has won
        }

        //The first condition is false, meaning either the opponent has some caravans "sold" that are now competing with
        //the first player or the first player does not have enough/ any sold. The former could still result in a victory
        if(individualCaravanCheck == 3){
            if((board1.getTotal1() > board2.getTotal1())
                    && (board1.getTotal2() > board2.getTotal2())
                    && (board1.getTotal3() > board2.getTotal3())){
                return true;                                                    //Player1's 3 Caravans are all higher and sold than opponent.
            }

            if((board1.getTotal1() == 26) && (board1.getTotal2() == 26) && (board1.getTotal3() == 26)){
                return true;                                                    //Final Check to see if all 3 Caravans are at 26. Ends a close game with 1st to 26 winning.
            }
        }

        return false; //If no conditions have been met

    }

    public boolean isSold(int x){
        return x >= 20 && x <= 26;
    }

}
