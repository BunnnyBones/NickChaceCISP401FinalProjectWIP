package com.company.models;

public class Hand {

    private final int handSize = 5;
    private final Cards[] hand = new Cards[handSize];
    private int lastCardSlotSelected = 4;

    public Hand(Deck deck){
        initialDraw(deck);
    }

    //Initializes hand with cards from deck
    public void initialDraw(Deck deck){
        for(int i = 0;i < handSize -1; i++){
            hand[i] = deck.removeTopCard();
        }
    }

    public void drawCard(Deck deck){
        hand[lastCardSlotSelected] = deck.removeTopCard();
    }

    public void removeCard(int card){
        lastCardSlotSelected = card;
        hand[lastCardSlotSelected].setFaceCard(false); //Blank Card Stats, mostly for debugging
        hand[lastCardSlotSelected].setValue(0);        //In reality, when you play a card, the card will be replaced by an
        hand[lastCardSlotSelected].setIndex(-1);       //automatic draw immediately and I just need to update lastCardSlotSelected
        hand[lastCardSlotSelected].setSuit("none");
    }


    public int getHandSize(){return handSize;}

    public Cards getCard(int x){return hand[x];}

    public Cards setCardTo( int whichOne){       //I think pointers are messing up my game, so I am trying to avoid all played Cards resulting as "0none" which only happens with "hand.removeCard"
        int value = hand[whichOne].getValue();
        int index = hand[whichOne].getIndex();
        boolean face = hand[whichOne].getFaceCard();
        String suit;
        suit = hand[whichOne].getSuit();

        Cards newCard = new Cards("",-14,false,-14);

        newCard.setSuit(suit);
        newCard.setFaceCard(face);
        newCard.setIndex(index);
        newCard.setValue(value);

        return newCard;
    }

}
