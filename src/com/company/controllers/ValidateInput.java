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

    public boolean validateInt(String input, int limit){
        int x;

        try{
            x = Integer.parseInt(input);
        } catch(NumberFormatException e){
            return false;
        }

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

        x -= 1;  //Input is still 1-3 while code needs 0-2

        int layerCount = board.getLayerCount(x);
        if(x == 0){                                 //The current layer to place cards is always going to be the top one with no cards played
            layerCount = 0;                         //The layer needs to be 1 lower so that the face cards are played on the previously played card
        } else if(layerCount > 0){
            layerCount -= 1;
        }

        if(card.getFaceCard()){
            if(specialBoard1[layerCount][x].getIndex() == -1){      //-1 Indicates the space is a blank card
                return true;
            } else if(specialBoard2[layerCount][x].getIndex() == -1){   //[Row][Column] ; x is the Integer version of the user input and itself was choosing a column to place a card into
                return true;
            } else {
                return false;
            }
        }
        //If the card was a face card then it should not make it past this point

        if(board.getLayerCount(x) == 6){
            return false;
        }

        if(mainBoard[board.getLayerCount(x)][x].getIndex() == -1){
            return true;
        } else{
            return false;
        }
    }

}
