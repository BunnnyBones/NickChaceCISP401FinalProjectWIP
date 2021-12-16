package com.company.views;

import com.company.models.Board;
import com.company.models.Cards;
import com.company.models.Hand;

import java.util.Random;
import java.util.Scanner;

public class CmdLineView {

    private final Scanner in = new Scanner(System.in);

    public void rulesMessage(){
        System.out.println("Here are the rules:");

        System.out.print("\n");

        System.out.println("You are going to have 3 piles or \"Caravans\" of cards that you are trying to sell");
        System.out.println("You are playing against an opponent who also has 3 Caravans that line up with yours");

        System.out.print("\n");

        System.out.println("Your goal is to sell your Caravans before your opponent does and here is how you do it:");
        System.out.println("To \"Sell\" you goods your Caravan must hold at least a value of 20");
        System.out.println("Be careful though, as a Caravan of 27 or higher has \"busted\" and needs to be discarded");
        System.out.println("If you can get 2 Caravans sold before your opponent gets even 1 corresponding Caravan sold then you win");
        System.out.println("(I.E. Your Caravan 1 and Caravan 3 are sold while the opponent's 1 & 3 are not; Two does not matter in this case)");
        System.out.println("However, if your opponent manages to \"sell\" one of their corresponding Caravans then you enter Sudden Death ");
        System.out.println("Now, The objective is to sell ALL 3 Caravans and have your Caravans be higher than your opponents or equal to 26");
        System.out.println("(I.E If your opponent has their Caravans at 20, 21, and 25 respectively then yours need to be at least 21,22, and 26 respectfully");

        System.out.println("\nThere is a catch in your caravan. Instead of using 1 deck, each player uses their own separate deck of 54 (52 +2 Jokers)");
        System.out.println("\nBe careful though because if you run out of cards then you lose");
        System.out.println("Tip: A guaranteed way to win is to be the first to get all Caravans to 26, Exactly!");

        System.out.println("\n\nEach player has their own advantages.\nPlayer 1 gets the first move but Player 2 has an extra move to try and tie or win the game.");


        System.out.print("\n\n");

        System.out.println("How the cards work: ");
        System.out.println("Cards in Caravan work a little differently than in regular playing card games");
        System.out.println("(Regular Cards)");
        System.out.println("Aces are 1");
        System.out.println("2-10 are their respective values");
        System.out.println("(Special / Face Cards)");

        System.out.println("Jacks remove the value of a card from the total. Great for fixing mistakes");
        System.out.println("Queens double the value of a card");
        System.out.println("Kings triple the value of a card");
        System.out.println("Jokers have a value of 14 but cannot be played on their own like regular cards");
        System.out.print("\n");
        System.out.println("The Face Cards are cool but you can only place up to 2 special / face cards on 1 regular card");
        System.out.println("If you don't play them on a regular card then nothing will happen and they become \"dead\"");
        System.out.println("Even if you place a card on your \"dead\" card, it will not apply any effects");

        System.out.println("\nIf you have ever played Caravan from Fallout: New Vegas, You might realize this is a modified version of the game\n");

        System.out.println("Lastly, Cards will appear in an abbreviated form. So, a 4 of Clubs will be \"4C\", a queen of diamonds will be \"QD\", and a Joker would be \"XX\"");

    }

    public void welcomeMessage(){
        System.out.println("Welcome to Caravan!");
        System.out.println("Author: Nick Chace");
        System.out.println("Purpose: CISP 401 Fall 2021 Final Project\n");
    }

    public String promptRules(){
        System.out.println("\n" +
                            "Would you like to hear the rules? (yes/no)");
        System.out.print("Your Input: ");

        return in.nextLine();
    }

    public void wrongInput(){
        System.out.println("\nThat input was not valid. \nPlease try again");
    }

    public String mainMenu(){
        System.out.print("\n");
        System.out.println("  Main Menu ");
        System.out.println("=============");

        System.out.println("1) Play Game");
        System.out.println("2) Rules");
        System.out.println("3) Quit Game");
        System.out.print("Your Input: ");

        return in.nextLine();
    }

    public void goodbyeMessage(){
        System.out.println("\n\nThank you for Playing!");
    }

    public void outOfCards(String loser){
        System.out.println("\n\n");

        System.out.println("===================");
        System.out.println("= CRITICAL DEFEAT =");
        System.out.println("===================");

        System.out.println("\n"+loser+" has run out of cards to draw!!!!");
    }

    //Maybe not supposed to be in a View but keeps this function modular, plus data handled is arbitrary
    public void endScreen(String winner, String loser){
        Random rand = new Random();
        int randomMessage = rand.nextInt(3) +1;
        System.out.println("\n================");
        System.out.println("=  GAME OVER  =");
        System.out.println("================\n");

        System.out.print(loser + " has lost ");
        if(randomMessage == 1){
            System.out.println("in a heartbreaking defeat");
        } else if(randomMessage == 2){
            System.out.println(":(");
        } else {
            System.out.println("with dignity");
        }

        randomMessage = rand.nextInt(3) +1;

        System.out.print("\n"+winner + " has ");
        if(randomMessage == 1){
            System.out.println("risen to the top as the \"Cream of the Crop\"\n");
        } else if(randomMessage == 2){
            System.out.println("blown the competition away\n");
        } else {
            System.out.println("defeated their greatest foe!\n");
        }

        System.out.println("====================");
        System.out.println("=See You Next Time!=");
        System.out.println("====================\n\n\n");

    }

    public String gatherNames(int playerNum){
        System.out.println("\nWhat is Player "+playerNum+"'s name?");
        System.out.print("Your Input: ");
        return in.nextLine();
    }


      //Prints out the game board
    public void printBoard(Board player, Board opponent, String name) {
        System.out.println("\n  " + name + "'s turn");            //Turn Header
        System.out.print("==");
        for(int i = 0; i < name.length();i++){
            System.out.print("=");
        }
        System.out.println("=========");

        System.out.println("\n  " + name + "'s Caravans:");
        System.out.print("===============");
        for(int i = 0; i < name.length();i++){
            System.out.print("=");
        }
        System.out.print("\n");


        Cards[][] playerBoard1 = player.getMainBoard();
        Cards[][] playerBoard2 = player.getSpecialBoard1();
        Cards[][] playerBoard3 = player.getSpecialBoard2();


        for(int i = 0; i < player.getRows();i++){  //6 rows

            for(int j = 0; j < player.getCol();j++){  //3 columns
                String playerBoard2Value = convertToString(String.valueOf(playerBoard2[i][j].getValue()));
                String playerBoard3Value = convertToString(String.valueOf(playerBoard3[i][j].getValue()));

                System.out.print(playerBoard1[i][j].getValue() + playerBoard1[i][j].getSuit());  //Value is Integer set to null and suit is "" when initialized so empty slots should show as empty
                System.out.print(" (" + playerBoard2Value + playerBoard2[i][j].getSuit() + ")"); //Reminder, when removing card, set it to default one in Board
                System.out.print(" (" + playerBoard3Value + playerBoard3[i][j].getSuit() + ")");
                System.out.print("        ");
            }
            System.out.print("\n");
        }
        System.out.println("Total 1: "+player.getTotal1() + "               Total 2: " + player.getTotal2() + "                Total 3: " + player.getTotal3() + "\n");


        Cards[][] opponentBoard1 = opponent.getMainBoard();
        Cards[][] opponentBoard2 = opponent.getSpecialBoard1();
        Cards[][] opponentBoard3 = opponent.getSpecialBoard2();

        System.out.println("\n  Opponent's Caravans:");
        System.out.print    ("=========================");
        System.out.print("\n");

        for(int i = 0; i < opponent.getRows();i++){  //6 rows

            for(int j = 0; j < opponent.getCol();j++){  //3 columns
                String opponentBoard2Value = convertToString(String.valueOf(opponentBoard2[i][j].getValue()));
                String opponentBoard3Value = convertToString(String.valueOf(opponentBoard3[i][j].getValue()));

                System.out.print(opponentBoard1[i][j].getValue() + opponentBoard1[i][j].getSuit());  //Value is Integer set to null and suit is "" when initialized so empty slots should show as empty
                System.out.print(" (" + opponentBoard2Value + opponentBoard2[i][j].getSuit() + ")"); //Reminder, when removing card, set it to default one in Board
                System.out.print(" (" + opponentBoard3Value + opponentBoard3[i][j].getSuit() + ")");
                System.out.print("        ");
            }
            System.out.print("\n");
        }
        System.out.println("Total 1: "+opponent.getTotal1() + "                Total 2: " + opponent.getTotal2() + "                Total 3: " + opponent.getTotal3());

    }

     //Prints out the player's hand
    public void printHand(Hand hand, String name) {

        System.out.println("\n  " + name + "'s Hand:");
        for (int i = 0; i < name.length(); i++) {
            System.out.print("=");
        }
        System.out.println("=============");

        for (int i = 1; i < hand.getHandSize() + 1; i++) {
            String cardValue = String.valueOf(hand.getCard(i-1).getValue());
            if (cardValue.equals("11")) {
                cardValue = "J";
            } else if (cardValue.equals("12")) {
                cardValue = "Q";
            } else if (cardValue.equals("13")) {
                cardValue = "K";
            } else if (cardValue.equals("14")) {
                cardValue = "XX   ";
            }
            System.out.println(i + ") " + cardValue + hand.getCard(i-1).getSuit());
        }

    }
    public String printMiniMenu(){
        System.out.println("\n  Select an Action");
        System.out.println  ("====================");
        System.out.println  ("1) Play a Card");
        System.out.println  ("2) Discard a Card");
        System.out.println  ("3) Discard a Caravan");
        System.out.println  ("4) Quit Game");
        System.out.print("Your Input: ");
        return in.nextLine();
    }

    public String promptCardSelect(){
        System.out.println("Select a Card from your Hand");
        System.out.print("Your Input: ");
        return in.nextLine();
    }

    public String promptCardPlacement(){
        System.out.println("\nSelect a Caravan to place your card");
        System.out.println("1) Left");
        System.out.println("2) Center");
        System.out.println("3) Right");
        System.out.print("Your Input: ");
        return in.nextLine();
    }

    public String promptCaravanChoice(){
        System.out.println("\nSelect a Caravan to Discard");
        System.out.println("1) Left");
        System.out.println("2) Center");
        System.out.println("3) Right");
        System.out.print("Your Input: ");
        return in.nextLine();
    }

    public void spaceFull(){
        System.out.println("\nThe space you selected is already occupied.");
        System.out.println("Try playing your card on a different Caravan, discarding a caravan, or placing a new regular card to free up more special card slots");
    }

    public String convertToString(String input){

        if(input.equals("11")){
            return "J";
        } else if(input.equals("12")){
            return "Q";
        } else if(input.equals("13")){
            return "K";
        } else if(input.equals("14")){
            return "XX";
        }

      return input; //No change for things that aren't face cards
    }

}



