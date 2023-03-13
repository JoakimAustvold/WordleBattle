package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Stack;

public class GameStateManager {
    private Stack<State> states;
    private static GameStateManager instance = new GameStateManager();

    private GameStateManager(){
        states = new Stack<State>();
    }

    public static GameStateManager getInstance() {
        return instance;
    }

    public void push(State state){
        states.push(state);
    }

    public void pop(){
        states.pop();
    }

    public void set(State state){
        states.pop();
        states.push(state);
    }

    public void update(float dt){ // Argument: delta time
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb){ // Argument: the sprite batch, everything we want to render
        states.peek().render(sb);
    }
}