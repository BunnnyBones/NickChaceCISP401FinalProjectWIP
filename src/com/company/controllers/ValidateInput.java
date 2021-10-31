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
    public boolean validateInt(String input){
        int x;

        try{
            x = Integer.parseInt(input);
        } catch(NumberFormatException e){
            return false;
        }

        if(!(x == 1 || x == 2 || x == 3)){
            return false;
        }
        return true;
    }

}
