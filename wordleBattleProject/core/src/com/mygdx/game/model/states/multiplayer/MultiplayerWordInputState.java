package com.mygdx.game.model.states.multiplayer;

import com.mygdx.game.model.input.KeyboardInput;
import com.mygdx.game.model.states.State;

public class MultiplayerWordInputState extends State {
    private final KeyboardInput keyboardInput;

    public MultiplayerWordInputState() {
        keyboardInput = new KeyboardInput();
    }

    public KeyboardInput getKeyboardInput(){
        return this.keyboardInput;
    }
}
