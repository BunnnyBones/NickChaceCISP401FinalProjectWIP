package com.company.models;

public class Hand {

    private int handSize = 5;
    private Cards[] hand = new Cards[handSize];
    private int lastCardSlotSelected;

    //Initializes hand with cards from deck
    public void initialDraw(Deck deck){
        for(int i = 0;i < handSize; i++){
            hand[i] = deck.removeTopCard();
        }
    }

    public void drawCard(Deck deck){
        hand[lastCardSlotSelected] = deck.removeTopCard();
    }

    public void removeCard(int card){
        lastCardSlotSelected = card;
        hand[card].setFaceCard(false); //Blank Card Stats, mostly for debugging
        hand[card].setValue(0);        //In reality, when you play a card, the card will be replaced by an
        hand[card].setIndex(-1);       //automatic draw immediately and I just need to update lastCardSlotSelected
        hand[card].setSuit("none");
    }

    public Cards getCardInHand(int index){
        return hand[index];
    }

    //Possible function to push cards down to make new one enter on 5


}
