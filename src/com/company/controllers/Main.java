package com.company.controllers;

import com.company.models.Cards;
import com.company.models.Deck;
import com.company.models.Board;
import com.company.models.Hand;
import com.company.views.CmdLineView;

public class Main {

    public static void main(String[] args) {

        Board player1Board;
        Board player2Board;

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
           while(!InputChecker.validateInt(mainMenuInput, 3)){
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

                   player1Hand.drawCard(player1Deck);  //5th Card draw                                                              //Player 1 Turn
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
                       validatedPlayer1CardInput -= 1;


                         if(validatedPlayer1TurnInput == 1){                            //Play Card

                             view.printBoard(player1Board, player2Board, player1Name);
                             String player1CardPlacement = view.promptCardPlacement();              //Maybe use a do while loop but IDK

                             while(!InputChecker.validateInt(player1CardPlacement, 3)){    //These while loops that check for validity are bulky, and I wish I could modularize it into one function but sending it to another class doesn't seem like it would work since I think I need the loop here.
                                 view.wrongInput();
                                 player1CardPlacement = view.promptCardPlacement();
                             }
                             int validatedPlayer1CardPlacement;  //The Input is valid but if the space is available needs to be checked

                             while(!InputChecker.validateBoardSpace(player1Board, player1CardPlacement, player1Hand.getCard(validatedPlayer1CardInput))){
                                 view.spaceFull();
                                 player1CardPlacement = view.promptCardPlacement();
                                 while(!InputChecker.validateInt(player1CardPlacement, 3)){
                                     view.wrongInput();
                                     player1CardPlacement = view.promptCardPlacement();
                                 }
                                 validatedPlayer1CardPlacement = Integer.parseInt(player1CardPlacement);  //These updates validated... with the new and correct player1 card placement to avoid an infinite loop in the outer while loop; It would spam view.spaceFull() forever

                             }
                             validatedPlayer1CardPlacement = Integer.parseInt(player1CardPlacement);                                   // Input for Play card is 100% processed and valid
                             validatedPlayer1CardPlacement -= 1;     //To make sure the first pile will be processed as 0 and not 1

                             Cards cardToBePlayed = player1Hand.setCardTo(validatedPlayer1CardInput);
                             Cards playedOn = player1MainBoard[player1Board.getLayerCount(validatedPlayer1CardPlacement)][validatedPlayer1CardPlacement];

                             int layerCount = player1Board.getLayerCount(validatedPlayer1CardPlacement);
                             if(layerCount > 0){     //"layerCount" is for face cards who need to access the previous layer
                                 layerCount -= 1;
                             }

                             if(cardToBePlayed.getFaceCard()){
                                 playedOn = player1MainBoard[layerCount][validatedPlayer1CardPlacement];    //If the card is a face card then played on will reach into the previous layer (except for 0) to be able to properly apply effects
                             }



                             if(cardToBePlayed.getFaceCard()){
                                     if(player1SpecialBoard1[layerCount][validatedPlayer1CardPlacement].getIndex() == -1){  //Card Played if is face card
                                         player1SpecialBoard1[layerCount][validatedPlayer1CardPlacement] = cardToBePlayed;
                                         } else {
                                       player1SpecialBoard2[layerCount][validatedPlayer1CardPlacement] = cardToBePlayed;
                                       }
                             } else {
                                   player1MainBoard[player1Board.getLayerCount(validatedPlayer1CardPlacement)][validatedPlayer1CardPlacement] = cardToBePlayed;           //Card has been Played
                             }


                             int valueOfCard = cardToBePlayed.getValue();


                             //Applying Card Effects

                             if(valueOfCard <= 10){
                                 player1Board.incTotal(validatedPlayer1CardPlacement, valueOfCard);
                                 player1Board.incLayerCount(validatedPlayer1CardPlacement);
                                                                                                                  //Card Affecting the board and applying affects
                             } else if(playedOn.getValue() == null) {      //If the card is a face card it will make it to this point, and if the card under it is blank the nothing should happen, this prevents the game from trying to access a value from the null playedOn card
                                 //Nothing
                             } else if (valueOfCard == 11){

                                 if(validatedPlayer1CardPlacement == 0){
                                     player1Board.decTotal(0, playedOn.getValue());    //Decreasing the corresponding total to account for the card being removed
                                 } else if(validatedPlayer1CardPlacement == 1){
                                     player1Board.decTotal(1, playedOn.getValue());
                                 } else if(validatedPlayer1CardPlacement == 2){
                                     player1Board.decTotal(2, playedOn.getValue());
                                 }


                             } else if (valueOfCard == 12){             //Queens double the value, having immense difficulties with changing the 'order' in Board; Trying to enforce it at least

                                 if(validatedPlayer1CardPlacement == 0){
                                     player1Board.incTotal(0, playedOn.getValue());
                                 } else if(validatedPlayer1CardPlacement == 1){
                                     player1Board.incTotal(1, playedOn.getValue());
                                 } else if(validatedPlayer1CardPlacement == 2){
                                     player1Board.incTotal(2, playedOn.getValue());
                                 }

                             } else if (valueOfCard == 13){     //Tripling the value of the card that it has been played on

                                 if(validatedPlayer1CardPlacement == 0){
                                     player1Board.incTotal(0, (2*playedOn.getValue()));
                                 } else if(validatedPlayer1CardPlacement == 1){
                                     player1Board.incTotal(1, (2*playedOn.getValue()));
                                 } else if(validatedPlayer1CardPlacement == 2){
                                     player1Board.incTotal(2, (2*playedOn.getValue()));
                                 }

                             } else if (valueOfCard == 14){

                                 player1Board.incTotal(validatedPlayer1CardPlacement, valueOfCard);         //This is the Same as the first option, but I am keeping layout consistent

                             } else {
                                 //nothing, cannot be any other value, I like having a default just in case
                             }

                             player1Hand.removeCard(validatedPlayer1CardInput); //Hand refilled



                         } else { // == 2 : Discard that card and draw a new one
                             player1Hand.removeCard(validatedPlayer1CardInput);
                         }


                   } else if (validatedPlayer1TurnInput == 3){                                       //Discard a Caravan

                       view.printBoard(player1Board, player2Board, player1Name);
                       String player1CaravanChoice = view.promptCaravanChoice();

                       while(!InputChecker.validateInt(player1CaravanChoice, 3)){
                           view.wrongInput();
                           player1CaravanChoice = view.promptCardPlacement();
                       }
                       int validatedPlayer1CaravanChoice = Integer.parseInt(player1CaravanChoice);
                       validatedPlayer1CaravanChoice -= 1;

                       player1Board.resetCaravan(validatedPlayer1CaravanChoice);
                                                                                                                        //Resetting values in the discarded Caravan
                       player1Board.setTotal(validatedPlayer1CaravanChoice, 0);
                       player1Board.setLayers(validatedPlayer1CaravanChoice, 0);


                   } else if (validatedPlayer1TurnInput == 4){                                       //Quit
                       break;   //To the main Menu
                   }


                        //Literally Duplicate Code for player 2
                        //It is pretty bad practice and form, but I don't know how I'd modularize this to work for both players without some complete overhaul / rewrite






                   Cards[][] player2MainBoard = player2Board.getMainBoard();
                   Cards[][] player2SpecialBoard1 = player2Board.getSpecialBoard1();
                   Cards[][] player2SpecialBoard2 = player2Board.getSpecialBoard2();

                   player2Hand.drawCard(player2Deck);  //5th Card draw                                                              //Player 2 Turn
                   view.printBoard(player2Board, player1Board, player2Name);
                   view.printHand(player2Hand, player2Name);
                   String player2TurnInput = view.printMiniMenu();

                   while(!InputChecker.validateInt(player2TurnInput, 4)){
                       view.wrongInput();
                       player2TurnInput = view.printMiniMenu();
                   }
                   int validatedPlayer2TurnInput = Integer.parseInt(player2TurnInput);


                   if(validatedPlayer2TurnInput == 1 || validatedPlayer2TurnInput == 2){              //Play a Card || Discard a Card are combined because both will require a player to choose a Card from their hand

                       view.printHand(player2Hand, player2Name);
                       String player2CardInput = view.promptCardSelect();

                       while(!InputChecker.validateInt(player2CardInput, 5)){
                           view.wrongInput();
                           player2CardInput = view.promptCardSelect();
                       }
                       int validatedPlayer2CardInput = Integer.parseInt(player2CardInput);
                       validatedPlayer2CardInput -= 1;


                       if(validatedPlayer2TurnInput == 1){                            //Play Card

                           view.printBoard(player2Board, player1Board, player2Name);
                           String player2CardPlacement = view.promptCardPlacement();              //Maybe use a do while loop but IDK

                           while(!InputChecker.validateInt(player2CardPlacement, 3)){    //These while loops that check for validity are bulky, and I wish I could modularize it into one function but sending it to another class doesn't seem like it would work since I think I need the loop here.
                               view.wrongInput();
                               player2CardPlacement = view.promptCardPlacement();
                           }
                           int validatedPlayer2CardPlacement;  //The Input is valid but if the space is available needs to be checked

                           while(!InputChecker.validateBoardSpace(player2Board, player2CardPlacement, player2Hand.getCard(validatedPlayer2CardInput))){
                               view.spaceFull();
                               player2CardPlacement = view.promptCardPlacement();
                               while(!InputChecker.validateInt(player2CardPlacement, 3)){
                                   view.wrongInput();
                                   player2CardPlacement = view.promptCardPlacement();
                               }
                               validatedPlayer2CardPlacement = Integer.parseInt(player2CardPlacement);  //These updates validated... with the new and correct player1 card placement to avoid an infinite loop in the outer while loop; It would spam view.spaceFull() forever

                           }
                           validatedPlayer2CardPlacement = Integer.parseInt(player2CardPlacement);                                   // Input for Play card is 100% processed and valid
                           validatedPlayer2CardPlacement -= 1;     //To make sure the first pile will be processed as 0 and not 1

                           Cards cardToBePlayed = player2Hand.setCardTo(validatedPlayer2CardInput);
                           Cards playedOn = player2MainBoard[player2Board.getLayerCount(validatedPlayer2CardPlacement)][validatedPlayer2CardPlacement];

                           int layerCount = player2Board.getLayerCount(validatedPlayer2CardPlacement);
                            if(layerCount > 0){     //"layerCount" is for face cards who need to access the previous layer
                               layerCount -= 1;
                           }

                           if(cardToBePlayed.getFaceCard()){
                               playedOn = player2MainBoard[layerCount][validatedPlayer2CardPlacement];    //If the card is a face card then played on will reach into the previous layer (except for 0) to be able to properly apply effects
                           }



                           if(cardToBePlayed.getFaceCard()){
                               if(player2SpecialBoard1[layerCount][validatedPlayer2CardPlacement].getIndex() == -1){  //Card Played if is face card
                                   player2SpecialBoard1[layerCount][validatedPlayer2CardPlacement] = cardToBePlayed;
                               } else {
                                   player2SpecialBoard2[layerCount][validatedPlayer2CardPlacement] = cardToBePlayed;
                               }
                           } else {
                               player2MainBoard[player2Board.getLayerCount(validatedPlayer2CardPlacement)][validatedPlayer2CardPlacement] = cardToBePlayed;           //Card has been Played
                           }


                           int valueOfCard = cardToBePlayed.getValue();


                           //Applying Card Effects

                           if(valueOfCard <= 10){
                               player2Board.incTotal(validatedPlayer2CardPlacement, valueOfCard);
                               player2Board.incLayerCount(validatedPlayer2CardPlacement);
                               //Card Affecting the board and applying affects
                           } else if(playedOn.getValue() == null) {      //If the card is a face card it will make it to this point, and if the card under it is blank the nothing should happen, this prevents the game from trying to access a value from the null playedOn card
                               //Nothing
                           } else if (valueOfCard == 11){

                               if(validatedPlayer2CardPlacement == 0){
                                   player2Board.decTotal(0, playedOn.getValue());    //Decreasing the corresponding total to account for the card being removed
                               } else if(validatedPlayer2CardPlacement == 1){
                                   player2Board.decTotal(1, playedOn.getValue());
                               } else if(validatedPlayer2CardPlacement == 2){
                                   player2Board.decTotal(2, playedOn.getValue());
                               }


                           } else if (valueOfCard == 12){             //Queens double the value, having immense difficulties with changing the 'order' in Board; Trying to enforce it at least

                               if(validatedPlayer2CardPlacement == 0){
                                   player2Board.incTotal(0, playedOn.getValue());
                               } else if(validatedPlayer2CardPlacement == 1){
                                   player2Board.incTotal(1, playedOn.getValue());
                               } else if(validatedPlayer2CardPlacement == 2){
                                   player2Board.incTotal(2, playedOn.getValue());
                               }

                           } else if (valueOfCard == 13){     //Tripling the value of the card that it has been played on

                               if(validatedPlayer2CardPlacement == 0){
                                   player2Board.incTotal(0, (2*playedOn.getValue()));
                               } else if(validatedPlayer2CardPlacement == 1){
                                   player2Board.incTotal(1, (2*playedOn.getValue()));
                               } else if(validatedPlayer2CardPlacement == 2){
                                   player2Board.incTotal(2, (2*playedOn.getValue()));
                               }

                           } else if (valueOfCard == 14){

                               player2Board.incTotal(validatedPlayer2CardPlacement, valueOfCard);         //This is the Same as the first option, but I am keeping layout consistent

                           } else {
                               //nothing, cannot be any other value, I like having a default just in case
                           }

                           player2Hand.removeCard(validatedPlayer2CardInput); //Hand refilled




                       } else { // == 2 : Discard that card and draw a new one
                           player2Hand.removeCard(validatedPlayer2CardInput);
                       }


                   } else if (validatedPlayer2TurnInput == 3){                                       //Discard a Caravan

                       view.printBoard(player2Board, player1Board, player2Name);
                       String player2CaravanChoice = view.promptCaravanChoice();

                       while(!InputChecker.validateInt(player2CaravanChoice, 3)){
                           view.wrongInput();
                           player2CaravanChoice = view.promptCardPlacement();
                       }
                       int validatedPlayer2CaravanChoice = Integer.parseInt(player2CaravanChoice);
                       validatedPlayer2CaravanChoice -= 1;

                       player2Board.resetCaravan(validatedPlayer2CaravanChoice);
                       //Resetting values in the discarded Caravan
                       player2Board.setTotal(validatedPlayer2CaravanChoice, 0);
                       player2Board.setLayers(validatedPlayer2CaravanChoice, 0);


                   } else if (validatedPlayer2TurnInput == 4){                                       //Quit
                       break;   //To the main Menu
                   }








                                                                                //Victory Conditions
                   ConditionCheck victory = new ConditionCheck();

                   if(victory.checkVictory(player1Board, player2Board)){
                       view.printBoard(player1Board, player2Board, player1Name);
                       view.endScreen(player1Name, player2Name);
                       break;
                   }
                   if(victory.checkVictory(player2Board, player1Board)){
                       view.printBoard(player2Board, player1Board, player2Name);
                       view.endScreen(player2Name, player1Name);
                       break;
                   }

                                                                                //Defeat Conditions
                   //Deck size is 54, 0-53 are the cards so 53 should end it
                   if(player1Deck.getCurrentCard() == (player1Deck.getDeckSize() -1)){
                       view.printBoard(player1Board, player2Board, player1Name);
                       view.outOfCards(player1Name);
                       view.endScreen(player2Name, player1Name);
                       break;
                   }

                   if(player2Deck.getCurrentCard() == (player2Deck.getDeckSize() -1)){
                       view.printBoard(player1Board, player2Board, player1Name);
                       view.outOfCards(player2Name);
                       view.endScreen(player1Name, player2Name);
                       break;
                   }
               }

           } else if(validatedMainMenuInput == 2){                                                  //Other Main Menu Options
               view.rulesMessage();
           } else if(validatedMainMenuInput == 3){
               break;
           }


       }




        view.goodbyeMessage();
    }
}
