package com.mygdx.game.controller;

import com.badlogic.gdx.Game;
import com.mygdx.game.model.states.State;
import com.mygdx.game.view.MainMenuView;
import com.mygdx.game.view.SettingsScreenView;
import com.mygdx.game.view.View;

/**
 * @author Marcus Birkeland
 * @version 13.03.2023
 * Empty example implementation of a Controller.
 */
public class ScreenController extends Game {

    //private LoadingScreen loadingScreen;
    //private PreferencesScreen preferencesScreen;
    private MainMenuView menuScreen;
    private SettingsScreenView settingsScreen;
    //private MainScreen mainScreen;
   // private EndScreen endScreen;

    protected State state;
    protected View view;

    public final static int MENU = 0;
    public final static int SETTINGS = 1;
    public final static int GAMESCREEN = 2;
    public final static int ENDGAME = 3;


    public void changeScreen(int screen) {
        switch (screen) {
            case MENU:
                if(menuScreen == null) menuScreen = new MainMenuView(this); // added (this)
                this.setScreen(menuScreen);
                break;
            case SETTINGS:
                if(settingsScreen == null) settingsScreen = new SettingsScreenView(this); // added (this)
                this.setScreen(settingsScreen);
                break;


            case GAMESCREEN:
                //if(mainScreen == null) mainScreen = new MainScreen(this); //added (this)
                     ControllerManager.getInstance().push(new SingleplayerGameController());

             break;

            /**
            case ENDGAME:
                if(endScreen == null) endScreen = new EndScreen(this);  // added (this)
                this.setScreen(endScreen);
                break;
             */
        }
    }

    /**
     * Run the render function on the view.
     */
    public void render(){
        super.render();
        //view.render(state, spriteBatch);
    }

    /**
     * Run the update function in the model.
     */

    /**
    @Override
    public void update(){
        //super.update();

        state.update(deltaTime);
    }
    */

    @Override
    public void create() {
        changeScreen(MENU);
    }

}
