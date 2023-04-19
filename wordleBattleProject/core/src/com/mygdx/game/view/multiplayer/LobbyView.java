package     com.mygdx.game.view.multiplayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.states.multiplayer.LobbyInfo;
import com.mygdx.game.model.states.State;
import com.mygdx.game.view.View;

/**
 * The super class for a game lobby
 * Both playerOne/host and player two/joiner sees this view
 * Displays the game lobby code, and all users that has joined
 * Contains a back button
 */
public class LobbyView extends View {

    private BitmapFont font;

    public LobbyView() {
        super();
        font = new BitmapFont();
        setup();
    }

    @Override
    public void setup() {
        createBackButton();
    }

    @Override
    public void render(State state, SpriteBatch spriteBatch) {
        LobbyInfo lobbyState = (LobbyInfo) state;

        font.getData().setScale(6);
        font.draw(spriteBatch, "Game code: " + lobbyState.getCode(),  (float) Gdx.graphics.getWidth()/2 - 400,  (float) (Gdx.graphics.getHeight()*0.8));
        font.draw(spriteBatch, "Users: (max 2)", (float) Gdx.graphics.getWidth()/2 - 400, (float) (Gdx.graphics.getHeight() *0.6));
        if (lobbyState.getPlayerOne() != null) {
            font.draw(spriteBatch, lobbyState.getPlayerOne(), (float) Gdx.graphics.getWidth() / 2 - 400, (float) (Gdx.graphics.getHeight() * 0.55));
        }
        if (lobbyState.getPlayerTwo() != null) {
            font.draw(spriteBatch, lobbyState.getPlayerTwo(), (float) Gdx.graphics.getWidth() / 2 - 400, (float) (Gdx.graphics.getHeight() * 0.5));
        } 

        super.render(state, spriteBatch);
    }


    @Override
    public void dispose() {
        super.dispose();
        font.dispose();
    }
}
