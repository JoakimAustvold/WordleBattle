package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
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
            font.draw(spriteBatch, ((PlayState) state).getWord(), Gdx.graphics.getWidth()/(((PlayState) state).getWord().length()-2), Gdx.graphics.getHeight() - 45);
        } else {
            throw new StateException("Wrong state type! Please provide a PlayState.");
        }
    }
}
