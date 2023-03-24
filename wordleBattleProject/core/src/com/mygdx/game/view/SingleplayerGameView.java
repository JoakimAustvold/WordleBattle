package com.mygdx.game.view;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.exception.StateException;
import com.mygdx.game.model.states.PlayState;
import com.mygdx.game.model.states.State;

public class SingleplayerGameView extends View{

    BitmapFont font;

    public SingleplayerGameView(){
        font = new BitmapFont();
        font.getData().setScale(6,6);
    }

    @Override
    public void render(State state, SpriteBatch spriteBatch) {
        if (state instanceof PlayState) {
            font.draw(spriteBatch, ((PlayState) state).getWord(), 450, 1000);
        } else {
            throw new StateException("Wrong state type! Please provide a PlayState.");
        }
    }
}
