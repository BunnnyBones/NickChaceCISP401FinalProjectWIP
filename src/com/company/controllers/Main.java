package com.company.controllers;

import com.company.models.Cards;
import com.company.models.Deck;
import com.company.models.Board;
import com.company.models.Hand;
import com.company.views.CmdLineView;

public class Main {

    public static void main(String[] args) {

        //BIG NOTE:
        //I was looking into adding another class to help with the load of the main game loop but apparently java only passes by value and kinda-sorta-technically passes by reference,
        //but it is a whole rabbit hole I do not entirely get. I am not entirely sure how a second class would work because I would need to return/ make changes to multiple variables and have them be returned to main
        //and not just change inside the class/object and then not return because I can only return 1 variable.

        Board player1Board = new Board();
        Board player2Board = new Board();

        Deck player1Deck;
        Deck player2Deck;

        Hand player1Hand;
        Hand player2Hand;

        ValidateInput InputChecker = new ValidateInput();
        CmdLineView view = new CmdLineView();

        //Cards defaultCard = new Cards("",null,false,-1);

                                                                //Welcome Message
        view.welcomeMessage();
        String rulesInput = view.promptRules();
                                                                //Rules
        while(!InputChecker.validateYesOrNo(rulesInput)){
            view.wrongInput();
            rulesInput = view.promptRules();
        }

        if(rulesInput.equalsIgnoreCase("yes")){
            view.rulesMessage();
        }

        String player1Name = view.gatherNames(1);
        String player2Name = view.gatherNames(2);

                                                                    //Main Menu Loop
       while (true){
                                                    //Resetting Decks, hands, and boards for future games
           player1Deck = new Deck();
           player2Deck = new Deck();

           player1Hand = new Hand(player1Deck);
           player2Hand = new Hand(player2Deck);

           player1Board = new Board();
           player2Board = new Board();


            String mainMenuInput = view.mainMenu();
           while(!InputChecker.validateInt(mainMenuInput, 4)){
               view.wrongInput();
               mainMenuInput = view.mainMenu();
           }
           //Already been tested directly above in the Input Checker
           int validatedMainMenuInput = Integer.parseInt(mainMenuInput);

           if(validatedMainMenuInput == 1){
                                                                        //Gameplay starts
               while(true){

                   Cards[][] player1MainBoard = player1Board.getMainBoard();
                   Cards[][] player1SpecialBoard1 = player1Board.getSpecialBoard1();
                   Cards[][] player1SpecialBoard2 = player1Board.getSpecialBoard2();

                   player1Hand.drawCard(player1Deck);                                                                //Player 1 Turn
                   view.printBoard(player1Board, player2Board, player1Name);
                   view.printHand(player1Hand, player1Name);
                   String player1TurnInput = view.printMiniMenu();

                   while(!InputChecker.validateInt(player1TurnInput, 4)){
                       view.wrongInput();
                       player1TurnInput = view.printMiniMenu();
                   }
                   int validatedPlayer1TurnInput = Integer.parseInt(player1TurnInput);


                   if(validatedPlayer1TurnInput == 1 || validatedPlayer1TurnInput == 2){              //Play a Card || Discard a Card are combined because both will require a player to choose a Card from their hand

                       view.printHand(player1Hand, player1Name);
                       String player1CardInput = view.promptCardSelect();

                       while(!InputChecker.validateInt(player1CardInput, 5)){
                           view.wrongInput();
                           player1CardInput = view.promptCardSelect();
                       }
                       int validatedPlayer1CardInput = Integer.parseInt(player1CardInput);


                         if(validatedPlayer1TurnInput == 1){                            //Play Card

                             view.printBoard(player1Board, player2Board, player1Name);
                             String player1CardPlacement = view.promptCardPlacement();              //Maybe use a do while loop but IDK

                             while(!InputChecker.validateInt(player1CardPlacement, 3)){    //These while loops that check for validity are bulky and I wish I could modularize it into one function but sending it to another class doesn't seem like it would work since I think I need the loop here.
                                 view.wrongInput();
                                 player1CardPlacement = view.promptCardPlacement();
                             }
                             int validatedPlayer1CardPlacement = Integer.parseInt(player1CardPlacement);  //The Input is valid but if the space is available needs to be checked

                             while(!InputChecker.validateBoardSpace(player1Board, player1CardPlacement, player1Hand.getCard(validatedPlayer1CardInput))){
                                 view.spaceFull();
                                 while(!InputChecker.validateInt(player1CardPlacement, 3)){
                                     view.wrongInput();
                                     player1CardPlacement = view.promptCardPlacement();
                                 }

                             }
                             validatedPlayer1CardPlacement = Integer.parseInt(player1CardPlacement);                    // Input for Play card is 100% processed and valid

                             Cards cardToBePlayed = player1Hand.getCard(validatedPlayer1CardInput);

                             if(cardToBePlayed.getFaceCard() == true){
                                 if(player1SpecialBoard1[player1Board.getLayerCount(validatedPlayer1CardPlacement)][validatedPlayer1CardPlacement].getIndex() == -1){  //Card Played if is face card
                                     player1SpecialBoard1[player1Board.getLayerCount(validatedPlayer1CardPlacement)][validatedPlayer1CardPlacement] = cardToBePlayed;
                                 } else {
                                     player1SpecialBoard2[player1Board.getLayerCount(validatedPlayer1CardPlacement)][validatedPlayer1CardPlacement] = cardToBePlayed;
                                 }
                             } else {
                                 player1MainBoard[player1Board.getLayerCount(validatedPlayer1CardPlacement)][validatedPlayer1CardPlacement] = cardToBePlayed;           //Card has been Played
                             }

                             player1Hand.removeCard(validatedPlayer1CardInput); //Hand refilled
                             player1Hand.drawCard(player1Deck);

                             int valueOfCard = cardToBePlayed.getValue();

                            //Take the "checkCardEffects" function in the 'Cards' class and literally put it here.
                             //Need to update 'Board' object with the changes the card made to it.
                             //After that, the 'Play a Card' option should be finished and much of the game is complete, next is testing for bugs
                             //Possible find more optimal way to have while loop-input checks and be able to reuse turn code for both players


                         } else { // == 2 : Discard that card and draw a new one
                             player1Hand.removeCard(validatedPlayer1CardInput);
                             player1Hand.drawCard(player1Deck);
                         }


                   } else if (validatedPlayer1TurnInput == 3){                                       //Discard a Caravan

                       view.printBoard(player1Board, player2Board, player1Name);
                       String player1CaravanChoice = view.promptCaravanChoice();

                       while(!InputChecker.validateInt(player1CaravanChoice, 3)){
                           view.wrongInput();
                           player1CaravanChoice = view.promptCardPlacement();
                       }
                       int validatedPlayer1CaravanChoice = Integer.parseInt(player1CaravanChoice);

                       player1Board.resetCaravan(validatedPlayer1CaravanChoice);

                       player1Board.setMainBoard(player1MainBoard);
                       player1Board.setSpecialBoard1(player1SpecialBoard1);
                       player1Board.setSpecialBoard2(player1SpecialBoard2);
                                                                                                                        //Resetting values in the discarded Caravan
                       player1Board.setOrder(validatedPlayer1CaravanChoice, false);  //False / descending as a default might need to change
                       player1Board.setTotal(validatedPlayer1CaravanChoice, 0);
                       player1Board.setLayers(validatedPlayer1CaravanChoice, 0);


                   } else if (validatedPlayer1TurnInput == 4){                                       //Quit
                       break;   //To the main Menu
                   }


                        //Literally Duplicate Code for player 2; Modularizing the turns into a single class seems like the go-to, but I can't work out the logistics in my head and doesn't seem possible as per the BIG NOTE at the start



                        //Possible adjustments in case time runs out:
                        //1- Remove Jokers and the much complexity with them
                        //2- restrict actions like choosing the order your cards can be played (ascending/descending) and force it to one unless there's a queen
                        //3- Simplify Cards and effects






                                                                                //Victory Conditions
                   ConditionCheck victory = new ConditionCheck();

                   if(victory.checkVictory(player1Board, player2Board) == true){
                       view.endScreen(player1Name, player2Name);
                       break;
                   }
                   if(victory.checkVictory(player2Board, player1Board) == true){
                       view.endScreen(player2Name, player1Name);
                       break;
                   }

                                                                                //Defeat Conditions
                   //Deck size is 54, 0-53 are the cards so 53 should end it
                   if(player1Deck.getCurrentCard() == (player1Deck.getDeckSize() -1)){
                       view.endScreen(player2Name, player1Name);
                       break;
                   }

                   if(player2Deck.getCurrentCard() == (player2Deck.getDeckSize() -1)){
                       view.endScreen(player1Name, player2Name);
                       break;
                   }
               }

           } else if(validatedMainMenuInput == 2){
               view.rulesMessage();
           } else if(validatedMainMenuInput == 3){
               break;
           }

           view.goodbyeMessage();
       }





    }
}
