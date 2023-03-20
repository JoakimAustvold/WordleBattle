package com.mygdx.game.model.states;

/**
 * @author Marcus Birkeland
 * @version 13.03.2023
 *
 * State classes is a container for the model of any game-screen.
 * Only business logic should be handled in a State-subclass.
 */
public abstract class State {

    public abstract void update(float deltaTime);
}
