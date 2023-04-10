package com.mygdx.game.view.multiplayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.model.multiplayer.LobbyCode;
import com.mygdx.game.model.states.State;
import com.mygdx.game.view.View;

public class HostGameView extends View {

    private Stage stage;
    private Skin skin;
    private BitmapFont font;
    private LobbyCode lobbyCode;

    public HostGameView() {
        //this.stage = new Stage(new ScreenViewport());
        this.skin = new Skin(Gdx.files.internal("default/skin/uiskin.json"));
        font = new BitmapFont();
        setup();
    }

     public void setup() {
            lobbyCode = new LobbyCode();
         }

    @Override
    public void render(State state, SpriteBatch spriteBatch) {
        font.getData().setScale(6);
        font.draw(spriteBatch, String.valueOf(lobbyCode.getCode()), Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
    }

    @Override
    public void dispose() {
        font.dispose();
    }



}
