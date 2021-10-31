package com.company.controllers;

import com.company.models.Cards;
import com.company.models.Hand;

public class GetInput {

    private int userInput;   //I originally had this as a string but with a menu it
                            // will be easier to use numbers for processing and actually playing the game
    private Cards targetCard;

    public int getUserInput(){
        return userInput;
    }

    public void setUserInput(int userInput){
        this.userInput = userInput;
    }

    public Cards convertInputToCard(Hand hand){
        hand.getCardInHand(userInput);

        return targetCard;
    }

}
