package com.mygdx.game.view.multiplayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.view.View;

/**
* The view a user is taken to when clicking "Multiplayer" in the main menu. 
* This view contains two buttons: one for hosting a lobby, and one for joining an already existing lobby.
*/
public class MultiplayerMenuView extends View {

    public TextButton hostGameButton;
    public TextButton joinGameButton;

    public MultiplayerMenuView() {
        super();
        hostGameButton = new TextButton("Host Game", skin);
        joinGameButton = new TextButton("Join Game", skin);
        setup();
    }

    /**
     * A method used to add and position elements to the stage
     * Used to make the view responsive again after a stack-pop
     */
    @Override
    public void setup() {

        

        Gdx.input.setInputProcessor(stage);
        createBackButton();

        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(false);
        stage.addActor(table);

        // Add the buttons to the table
        table.row().pad(50, 0, 50, 0);
        table.add(hostGameButton).fill().uniform();
        table.row().pad(50, 0, 50, 0);
        table.add(joinGameButton).fill().uniform();
    }

}
