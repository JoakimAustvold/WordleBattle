package com.mygdx.game.exception;


public class StateException extends IllegalArgumentException {
    public StateException(String errorMessage){
        super(errorMessage);
    }
}
