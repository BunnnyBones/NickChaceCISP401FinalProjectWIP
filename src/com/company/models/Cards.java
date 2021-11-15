package com.company.models;

public class Cards {

    private boolean faceCard;
    private String suit;
    private Integer value;  //Changed value to type: Integer from int, so in View, I can print it out and have it not show up if it is null
    private int index;

    public Cards(String suit, Integer value, boolean faceCard, int index) {
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

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
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
    public void checkCardEffect(Cards selectedCard, Cards playedOn, Board board, int caravan){  //caravan will be sent over as the user input that will determine which Caravan to choose for the queen


        if(value <= 10){
            //Add value to the total in Board
        } else if (value == 11){
            Cards defaultCard = new Cards("",null, false, -1); //Resets both cards to empty
            selectedCard = playedOn = defaultCard;
        } else if (value == 12){
            board.setOrder1(!board.getOrder1());
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
