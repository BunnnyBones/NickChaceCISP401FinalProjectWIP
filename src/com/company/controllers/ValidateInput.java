package com.company.controllers;

public class ValidateInput {

        //This might have become what class "GetInput" was supposed to be

    public boolean validateYesOrNo(String input){

        if(!(input.equalsIgnoreCase("Yes") || input.equalsIgnoreCase("no"))){
            return false;
        } //else
        return true;
    }

    //This checks to make sure input is an int, and a valid main menu choice
    //Probably a better way to do this
    public boolean validateInt(String input, int version){
        int x;

        try{
            x = Integer.parseInt(input);
        } catch(NumberFormatException e){
            return false;
        }
        if(version == 1){

            if(!(x == 1 || x == 2 || x == 3)){
                return false;
            }

        }
        if(version == 2){

            if(!(x == 1 || x == 2 || x == 3 || x == 4 || x == 5)){
                return false;
            }

        }

        if(version == 3){

            if(!(x == 1 || x == 2 || x == 3 || x == 4 || x == 5) || x == 6){
                return false;
            }

        }

        return true;
    }

}
