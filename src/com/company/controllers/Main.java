package com.company.controllers;

import com.company.models.Cards;
import com.company.models.Deck;
import com.company.models.Board;
import com.company.models.Hand;
import com.company.views.CmdLineView;

public class Main {

    public static void main(String[] args) {

        Board player1Board = new Board();
        Board player2Board = new Board();

        Deck player1Deck;
        Deck player2Deck;

        Hand player1Hand;
        Hand player2Hand;

        ValidateInput InputChecker = new ValidateInput();
        CmdLineView view = new CmdLineView();

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
           while(!InputChecker.validateInt(mainMenuInput, 1)){
               view.wrongInput();
               mainMenuInput = view.mainMenu();
           }
           //Already been tested above
           int validatedMainMenuInput = Integer.parseInt(mainMenuInput);

           if(validatedMainMenuInput == 1){
                                                                        //Gameplay starts
               while(true){

                   player1Hand.drawCard(player1Deck);                           //Player 1 Turn
                   view.printBoard(player1Board, player2Board, player1Name);                    //Need loop in case player decision is bad, so they do not lose their turn
                   String player1TurnInput = view.printHand(player1Hand, player1Name);

                   String player1Row = view.promptRow();
                   String player1Col = view.promptCol();

                   Cards[][] player1BoardMain = player1Board.getMainBoard();
                   Cards[][] player1BoardSpecial1 = player1Board.getSpecialBoard1();
                   Cards[][] player1BoardSpecial2 = player1Board.getSpecialBoard2();

                   while(!InputChecker.validateInt(player1TurnInput, 2)){
                       view.wrongInput();
                       player1TurnInput = view.printHand(player1Hand, player1Name);
                   }


                   while(!InputChecker.validateInt(player1Row, 3)){
                       view.wrongInput();
                       player1Row = view.promptRow();
                   }
                   int player1RowTested = Integer.parseInt(player1Row); //Tested above


                   while(!InputChecker.validateInt(player1Col, 1)){
                       view.wrongInput();
                       player1Col = view.promptCol();
                   }
                   int player1ColTested = Integer.parseInt(player1Col); //Tested above

                   while(!(player1BoardMain[player1RowTested][player1ColTested].getIndex() == -1)){   //If the index is -1 then it is a blank card, otherwise there is a card there, and you cannot put it there
                       view.wrongInput();                                                               //Looping again until card chosen is an empty one

                       view.printBoard(player1Board, player2Board, player1Name);
                       player1TurnInput = view.printHand(player1Hand, player1Name);

                       while(!InputChecker.validateInt(player1TurnInput, 2)){
                           view.wrongInput();
                           player1TurnInput = view.printHand(player1Hand, player1Name);
                       }


                       while(!InputChecker.validateInt(player1Row, 3)){
                           view.wrongInput();
                           player1Row = view.promptRow();
                       }
                       player1RowTested = Integer.parseInt(player1Row); //Tested above


                       while(!InputChecker.validateInt(player1Col, 1)){
                           view.wrongInput();
                           player1Col = view.promptCol();
                       }
                       player1ColTested = Integer.parseInt(player1Col); //Tested above
                   }


                   //Need Player 2's turn which is just a repeat of Player 1
                   //Need to update board with the correct/valid input
                   //Need to distingush between facecard and not face card for placement as rules vary
                   //Need to update loop that checks for correct placement to account for faceCards

                    //Future plan: only allow user to input what Caravan pile they wish to select. Only allow them to play
                   //speical cards on the topmost card in the Caravan (should help balance out the power) and only requires
                   //an input of 1 - 3 instead of relying on the user for good input; Only allow them to give good input.


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
