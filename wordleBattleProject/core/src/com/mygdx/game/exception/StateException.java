package com.mygdx.game.exception;

import java.security.InvalidParameterException;

public class StateException extends IllegalArgumentException {
    public StateException(String errorMessage){
        super(errorMessage);
    }
}
