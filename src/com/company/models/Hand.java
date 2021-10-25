package com.company.models;

public class Hand {

    private int handSize = 5;
    private Cards[] hand = new Cards[handSize];

    public void initialDraw(){}

    public void drawCard(){}

    public void removeCard(int card){}

    public Cards getCard(int index){
        return hand[index];
    }


}
