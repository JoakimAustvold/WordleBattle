package     com.mygdx.game.view.multiplayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.controller.multiplayer.JoinLobbyController;
import com.mygdx.game.model.states.multiplayer.LobbyInfo;
import com.mygdx.game.model.states.State;
import com.mygdx.game.view.View;

/**
 * The view a player joining a lobby will see. They will see the other user's username, but
 * will not see the start game button that the host of the lobby sees.
 */
public class LobbyView extends View {

   // protected Stage stage;
    //protected Skin skin;
    private BitmapFont font;
    //protected TextButton backButton;
    protected EventListener listener;

    public LobbyView() {
       // this.stage = new Stage(new ScreenViewport());
       // this.skin = new Skin(Gdx.files.internal("default/skin/uiskin.json"));
        super();
       // this.skin.getFont("default-font").getData().setScale(4f,4f);
        font = new BitmapFont();
        setup();
    }

    @Override
    public void setup() {
        createBackButton();

        /*
        backButton = new TextButton("Leave", skin);
        stage.addActor(backButton);

        backButton.setPosition(50, (float) (Gdx.graphics.getHeight() * 0.90));
        backButton.setSize((float) (Gdx.graphics.getWidth()*0.2), (float) (Gdx.graphics.getHeight() * 0.05));
        */

    }


    @Override
    public void render(State state, SpriteBatch spriteBatch) {
        LobbyInfo lobbyState = (LobbyInfo) state;

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1/30f));
        stage.draw();

        font.getData().setScale(6);
        font.draw(spriteBatch, lobbyState.getCode(),  (float) Gdx.graphics.getWidth()/2,  (float) (Gdx.graphics.getHeight()*0.8));
        font.draw(spriteBatch, "Users:",(float) Gdx.graphics.getWidth()/2, (float) (Gdx.graphics.getHeight() *0.6));
        if (lobbyState.getPlayerOne() != null) {
            font.draw(spriteBatch, lobbyState.getPlayerOne(), (float) Gdx.graphics.getWidth() / 2, (float) (Gdx.graphics.getHeight() * 0.55));
        }
        if (lobbyState.getPlayerTwo() != null) {
            font.draw(spriteBatch, lobbyState.getPlayerTwo(), (float) Gdx.graphics.getWidth() / 2, (float) (Gdx.graphics.getHeight() * 0.5));
        }
    }


    @Override
    public void dispose() {
        super.dispose();
        font.dispose();
    }
}
