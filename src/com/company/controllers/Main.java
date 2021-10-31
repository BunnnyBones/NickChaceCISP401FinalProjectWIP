package com.company.controllers;

import com.company.models.Deck;
import com.company.models.Board;
import com.company.models.Hand;
import com.company.views.CmdLineView;

public class Main {

    public static void main(String[] args) {

        Board player1Board = new Board();
        Board player2Board = new Board();

        Deck player1Deck = new Deck();
        Deck player2Deck = new Deck();

        Hand player1Hand = new Hand();
        Hand player2Hand = new Hand();

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

           //1) Set-up initial components, hands dealt, board primed
           //2) print out initial board
           //3) Gameplay loop


            String mainMenuInput = view.mainMenu();
           while(!InputChecker.validateInt(mainMenuInput)){
               view.wrongInput();
               mainMenuInput = view.mainMenu();
           }
           //Already been tested above
           int validatedMainMenuInput = Integer.parseInt(mainMenuInput);

           if(validatedMainMenuInput == 1){
                                                                        //Gameplay starts
               while(true){

                   player1Hand.initialDraw(player1Deck);
                   player2Hand.initialDraw(player2Deck);


                    //Need:
                    //Victory condition checks
                    //Loop that asks for player input and cycles between them
                    //Large amount of checks for available moves as according to the rules

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
