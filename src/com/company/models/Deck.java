package com.company.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private static final int deckSize = 54; //52 + 2 jokers

    private Cards[] theDeck = new Cards[54];

    public Deck() {

        String suit;
        boolean faceCard;
        int value;



        for(int index = 0;index < 52; index++){
            value = (index % 13) + 1;

            if(index <= 12){         //cards 0-12
                suit = "hearts";
            } else if (index <= 25){ // cards 13-25
                suit = "diamonds";
            } else if (index <= 38){ // cards 26-38
                suit = "spades";
            } else{              // cards 39-51
                suit = "clubs";
            }

            //10 is not considered a face card
            faceCard = value == 11 || value == 12 || value == 13; //Jack, Queen, and King


            Cards card = new Cards(suit, value, faceCard, index);
            theDeck[index] = card;

            //End For Loop
        }

             //The two jokers
        Cards joker1 = new Cards("Joker", 14, false, 52);
        theDeck[52] = joker1;
        Cards joker2 = new Cards("Joker", 14, false, 53);
        theDeck[53] = joker2;

        //End of Constructor
    }

    public void shuffleDeck(){

        List<Integer> newDeck = new ArrayList<>();
        for (int i = 0; i < 54; i++) {
            newDeck.add(i);
        }
        //"It just Works" -TH
        Collections.shuffle(newDeck);


    }

    //Idk if this belongs here
    public void resetCaravan(int selectedCaravan){}

    //When a card is needing to be drawn to the hand, top card is removed from the deck and sent to the hand.
    //Is called by "drawCard in Hand Class"
    public void removeTopCard(){}





}
