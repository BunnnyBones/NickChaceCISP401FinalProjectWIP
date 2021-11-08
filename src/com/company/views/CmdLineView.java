package com.company.views;

import com.company.controllers.ValidateInput;
import com.company.models.Board;
import com.company.models.Cards;
import com.company.models.Hand;

import java.util.Random;
import java.util.Scanner;

public class CmdLineView {

    private Scanner in = new Scanner(System.in);

    public void rulesMessage(){
        System.out.println("Here are the rules:");

        System.out.print("\n");

        System.out.println("You are a business tycoon and control multiple trade routes");
        System.out.println("You are going to have 3 piles or \"Caravans\" of cards or \"Goods\" that you are trying to sell");
        System.out.println("You are playing against an opponent who also has 3 Caravans that line up with yours");

        System.out.print("\n");

        System.out.println("Your goal is to sell your Caravans before your opponent does and here is how you do it:");
        System.out.println("To \"Sell\" you goods your Caravan must hold at least a value of 20");
        System.out.println("Be careful though, as a Caravan of 27 or higher is too valuable and will be robbed");
        System.out.println("If you can get 2 Caravans sold before your opponent gets even 1 corresponding Caravan sold then you win");
        System.out.println("If you aren't quick enough then it is sudden death. ");
        System.out.println("Whoever gets all three Caravans sold with higher values than all the opponent's corresponding Caravans win");
        System.out.println("There is a catch in your caravan can only increase or decrease in value unless you have a queen. ");

        System.out.print("\n");

        System.out.println("Tip: A guaranteed way to win is to be the first to get all Caravans to 26, Exactly!");

        System.out.print("\n\n");

        System.out.println("How the cards work: ");
        System.out.println("Cards in Caravan work a little differently than in regular playing card games");
        System.out.println("Ace is 1");
        System.out.println("2-10 are their respective values");
        System.out.println("Then we have special effect cards");

        System.out.println("Jacks remove the card they are played on and any other special effect cards on said card");
        System.out.println("Queens reverse the direction of your Caravan");
        System.out.println("Kings double the value of a card");
        System.out.println("In Caravan you do use 2 jokers, and they have 2 effects");
        System.out.println("1) If played on an ace, it removes all regular cards (Ace, 2-10) of the suit it is played on from the cards in play");
        System.out.println("2) If played on a 2-10, it removes all of that card from the cards in play");
        System.out.print("\n");
        System.out.println("Additionally, you can only place up to 2 special cards on 1 regular card");

    }

    public void welcomeMessage(){
        System.out.println("Welcome to Caravan!");
        System.out.println("Author: Nick Chace");
        System.out.println("Purpose: CISP 401 Fall 2021 Final Project\n");
    }

    public String promptRules(){
        System.out.println("\n" +
                            "Would you like to hear the rules?");
        System.out.print("Your Input: ");

        return in.nextLine();
    }

    public void wrongInput(){
        System.out.print("That input was not valid. \nPlease try again\nYour Input: ");
    }

    public String mainMenu(){
        System.out.println("  Main Menu ");
        System.out.println("=============");

        System.out.println("1) Play Game");
        System.out.println("2) Rules");
        System.out.println("3) Quit Game");
        //Potential option to customize cards included in your deck if time permits

        return in.nextLine();
    }

    public void goodbyeMessage(){
        System.out.println("\n\nThank you for Playing!");
    }

    //Maybe not supposed to be in a View but keeps this function modular, plus data handled is arbitrary
    public void endScreen(String winner, String loser){
        Random rand = new Random();
        int randomMessage = rand.nextInt(3) +1;

        System.out.print(loser + " has lost ");
        if(randomMessage == 1){
            System.out.println("in a heartbreaking defeat");
        } else if(randomMessage == 2){
            System.out.println(":(");
        } else {
            System.out.println("with dignity");
        }

        randomMessage = rand.nextInt(3) +1;

        System.out.println("\n"+winner + " has ");
        if(randomMessage == 1){
            System.out.println("risen to the top as the Cream of the Crop");
        } else if(randomMessage == 2){
            System.out.println("blown the competition away");
        } else {
            System.out.println("defeated their greatest foe!");
        }

    }

    public String gatherNames(int player){
        System.out.println("What is Player "+player+"'s name?");
        System.out.print("Your Input: ");
        return in.nextLine();
    }


      //Prints out the game board
    public void printBoard(Board player, Board opponent, String name) {
        System.out.println("  " + name + "'s turn");            //Turn Header
        System.out.print("==");
        for(int i = 0; i < name.length();i++){
            System.out.print("=");
        }
        System.out.println("=========");

        System.out.println("\n  " + name + "'s Caravans:");
        System.out.print("=============");
        for(int i = 0; i < name.length();i++){
            System.out.print("=");
        }
        System.out.print("\n");


        Cards[][] playerBoard1 = player.getMainBoard();
        Cards[][] playerBoard2 = player.getSpecialBoard1();
        Cards[][] playerBoard3 = player.getSpecialBoard2();

        for(int i = 0; i < player.getRows();i++){  //6 rows

            for(int j = 0; j < player.getCol();j++){  //3 columns
                System.out.print(playerBoard1[i][j].getValue() + playerBoard1[i][j].getSuit());  //Value is Integer set to null and suit is "" when initialized so empty slots should show as empty
                System.out.print("(" + playerBoard2[i][j].getValue() + playerBoard2[i][j].getSuit() + ")"); //Reminder, when removing card, set it to default one in Board
                System.out.print("(" + playerBoard3[i][j].getValue() + playerBoard3[i][j].getSuit() + ")");
                System.out.print("  ");
            }
            System.out.print("\n");
        }
        System.out.println(player.getTotal1() + "  " + player.getTotal2() + "  " + player.getTotal3() + "\n");


        Cards[][] opponentBoard1 = player.getMainBoard();
        Cards[][] opponentBoard2 = player.getSpecialBoard1();
        Cards[][] opponentBoard3 = player.getSpecialBoard2();

        System.out.println("\n  Opponent's Caravans:");
        System.out.print    ("=========================");
        System.out.print("\n");

        for(int i = 0; i < opponent.getRows();i++){  //6 rows

            for(int j = 0; j < opponent.getCol();j++){  //3 columns
                System.out.print(opponentBoard1[i][j].getValue() + opponentBoard1[i][j].getSuit());  //Value is Integer set to null and suit is "" when initialized so empty slots should show as empty
                System.out.print("(" + opponentBoard2[i][j].getValue() + opponentBoard2[i][j].getSuit() + ")"); //Reminder, when removing card, set it to default one in Board
                System.out.print("(" + opponentBoard3[i][j].getValue() + opponentBoard3[i][j].getSuit() + ")");
                System.out.print("  ");
            }
            System.out.print("\n");
        }
        System.out.println(opponent.getTotal1() + "  " + opponent.getTotal2() + "  " + opponent.getTotal3() + "\n");

    }

     //Prints out the player's hand
    public String printHand(Hand hand, String name){

        System.out.println("  " + name + "'s Hand:");
        for(int i = 0; i < name.length();i++){
            System.out.print("=");
        }
        System.out.println("=============");

        for(int i = 1; i <hand.getHandSize() + 1; i++){
            System.out.println(i + ") " + hand.getCard(i).getValue() + hand.getCard(i).getSuit());
        }

        System.out.println("Select a card to play");
        System.out.print("Your Input: ");
        return in.nextLine();
    }

    public String promptRow(){
        System.out.println("What Row would you like to place your card in?");
        System.out.print("Your Input: ");
        return in.nextLine();
    }

    public String promptCol(){
        System.out.println("What Column would you like to place your card in?");
        System.out.print("Your Input: ");
        return in.nextLine();
    }

}



