package com.company.controllers;

import com.company.models.Board;
import com.company.models.Cards;

public class ValidateInput {

        //This might have become what class "GetInput" was supposed to be

    public boolean validateYesOrNo(String input){

        if(!(input.equalsIgnoreCase("Yes") || input.equalsIgnoreCase("no"))){
            return false;
        } //else
        return true;
    }

    //This checks to make sure input is an int, and a valid main menu choice
    //Probably a better way to do this
    public boolean validateInt(String input, int limit){
        int x;

        try{
            x = Integer.parseInt(input);
        } catch(NumberFormatException e){
            return false;
        }
        /*
        if(version == 1){               I might remove this, but I will keep it for a bit in case my solution below does not work

            if(!(x == 1 || x == 2 || x == 3)){
                return false;
            }

        }
        if(version == 2){

            if(!(x == 1 || x == 2 || x == 3 || x == 4 || x == 5)){
                return false;
            }

        }

        if(version == 3){

            if(!(x == 1 || x == 2 || x == 3 || x == 4 || x == 5) || x == 6){
                return false;
            }

        }

         */

        for(int i = 1;i <= limit; i++){
            if(i == x){
                return true;
            }
        }
        return false;
    }

    public boolean validateBoardSpace(Board board, String input, Cards card){
        Cards[][] mainBoard = board.getMainBoard();
        Cards[][] specialBoard1 = board.getSpecialBoard1();
        Cards[][] specialBoard2 = board.getSpecialBoard2();

        int x;
        try{
            x = Integer.parseInt(input);
        } catch(NumberFormatException e){
            return false;
        }

        if(card.getFaceCard()){
            if(specialBoard1[board.getLayerCount(x)][x].getIndex() == -1){      //-1 Indicates the space is a blank card
                return true;
            } else if(specialBoard2[board.getLayerCount(x)][x].getIndex() == -1){   //[Row][Column] ; x is the Integer version of the user input and itself was choosing a column to place a card into
                return true;
            } else {
                return false;
            }
        }
        //If the card was a face card then it should not make it past this point

        if(mainBoard[board.getLayerCount(x)][x].getIndex() == -1){
            return true;
        } else{
            return false;
        }
    }

}
