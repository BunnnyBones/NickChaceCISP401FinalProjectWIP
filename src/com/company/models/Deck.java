package com.company.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private static final int deckSize = 54; //52 + 2 jokers
    private int currentCard;

    private Cards[] theDeck = new Cards[54];

    public Deck() {

        String suit;
        boolean faceCard;
        int value;
        currentCard = 0;


        for(int index = 0;index < 52; index++){
            value = (index % 13) + 1;

            if(index <= 12){         //cards 0-12
                suit = "H";  //Hearts
            } else if (index <= 25){ // cards 13-25
                suit = "D";  //Diamonds
            } else if (index <= 38){ // cards 26-38
                suit = "S";  //Spades
            } else{              // cards 39-51
                suit = "C";  //Clubs
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

        shuffleDeck();
    }

    public void shuffleDeck(){

        List<Integer> newDeck = new ArrayList<>();
        for (int i = 0; i < 54; i++) {
            newDeck.add(i);
        }
        //"It just Works" -TH
        Collections.shuffle(newDeck);

        Cards[] tempDeck = new Cards[54];
        //Converting ArrayList to a temp. array as not to damage "theDeck"/original
        for(int i = 0; i < newDeck.size();i++){
            //This should make tempDeck's index equal to the index number of theDeck AT the randomized index in newDeck
            //I.E. tempDeck[0] should be the value of theDeck[random Location]
            tempDeck[i].setIndex(theDeck[newDeck.get(i)].getIndex());
            tempDeck[i].setSuit(theDeck[newDeck.get(i)].getSuit());
            tempDeck[i].setValue(theDeck[newDeck.get(i)].getValue());
            tempDeck[i].setFaceCard(theDeck[newDeck.get(i)].getFaceCard());
        }

        //Updating original Array
        for(int i = 0; i < deckSize; i++){
            theDeck[i].setIndex(tempDeck[i].getIndex());
            theDeck[i].setSuit(tempDeck[i].getSuit());
            theDeck[i].setValue(tempDeck[i].getValue());
            theDeck[i].setFaceCard(tempDeck[i].getFaceCard());
        }
    }


    //Once the limit has been reached (53) then that player loses
    public Cards removeTopCard(){
        Cards card = theDeck[currentCard];

        currentCard += 1;
        return card;
    }

    public void setCurrentCard(int x){
        currentCard = x;
    }

    public int getCurrentCard(){return currentCard;}

    public int getDeckSize(){return deckSize;}






}
