package com.company.models;

public class Cards {

    private boolean faceCard;
    private String suit;
    private int value;
    private int index;

    public Cards(String suit, int value, boolean faceCard, int index) {
        this.suit = suit;
        this.value = value;
        this.faceCard = faceCard;
        this.index = index;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean getFaceCard() {
        return faceCard;
    }

    public void setFaceCard(boolean faceCard) {
        this.faceCard = faceCard;
    }

    public void setIndex(int index){this.index = index;}

    public int getIndex(){return index;}

    //The comments probably do not belong here, but I am keeping this here as a note
    public void checkCardEffect(){

        if(value <= 10){
            //nothing happens
        } else if (value == 11){
            //Remove card that it is played on, and any additionally played face cards
        } else if (value == 12){
            //Changes the suit of the Caravan played on to that of the queen, and reverses direction
        } else if (value == 13){
            //Doubles the value of card played on
        } else if (value == 14){
            //if played on an Ace, removes all non-face cards of the suit of the ace from play (all hearts)
            //if played 2-10, removes all of said card from play (all 7s)
        } else {
            //nothing, cannot be any other value
        }

    }
    //-1 = blank card, 1-10 = not face cards, 11 = Jack, 12 = Queen, 13 = King, 14 = Joker

}
