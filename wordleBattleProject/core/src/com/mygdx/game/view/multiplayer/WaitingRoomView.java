package com.mygdx.game.view.multiplayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.controller.multiplayer.MultiplayerGameController;
import com.mygdx.game.model.states.State;
import com.mygdx.game.model.states.multiplayer.CurrentPlayer;
import com.mygdx.game.model.states.multiplayer.LobbyInfo;
import com.mygdx.game.view.View;

public class WaitingRoomView extends View {

    private BitmapFont font;

    public WaitingRoomView() {
        font = new BitmapFont();
        font.getData().setScale(5);
        setup();
    }

    @Override
    public void render(State state, SpriteBatch spriteBatch) {

        LobbyInfo lobbyState = (LobbyInfo) state;

        super.render(state, spriteBatch);
        if((lobbyState.getCurrentPlayer() == CurrentPlayer.PLAYERONE) && (lobbyState.getPlayerTwoWordlist().size() == 1) ) {
            ControllerManager.getInstance().push(new MultiplayerGameController(lobbyState.getPlayerTwoWordlist().get(0)));
        } else if((lobbyState.getCurrentPlayer() == CurrentPlayer.PLAYERTWO) && (lobbyState.getPlayerOneWordlist().size() == 1) ) {
           ControllerManager.getInstance().push(new MultiplayerGameController(lobbyState.getPlayerOneWordlist().get(0)));
       }
        //font.draw(spriteBatch,)
        font.draw(spriteBatch, "Waiting for the other player",(float) (Gdx.graphics.getWidth()*0.1), (float) (Gdx.graphics.getHeight() * 0.5));
    }

    @Override
    public void setup() {

    }
}
