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

}
