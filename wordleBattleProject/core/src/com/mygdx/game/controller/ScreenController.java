package com.mygdx.game.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.view.View;
import com.mygdx.game.view.MainMenuView;
import com.mygdx.game.model.State;

/**
 * @author Marcus Birkeland
 * @version 13.03.2023
 * Empty example implementation of a Controller.
 */
public class ScreenController extends Controller{

    private LoadingScreen loadingScreen;
    private PreferencesScreen preferencesScreen;
    private MenuScreen menuScreen;
    private MainScreen mainScreen;
    private EndScreen endScreen;

    protected State state;
    protected View view;

    public final static int MENU = 0;
    public final static int SETTINGS = 1;
    public final static int GAMESCREEN = 2;
    public final static int ENDGAME = 3;


    public void changeScreen(int screen) {
        switch (screen) {
            case MENU:
                if(menuScreen == null) menuScreen = new MenuScreen(this); // added (this)
                this.setScreen(menuScreen);
                break;

            case SETTINGS:
                if(preferencesScreen == null) preferencesScreen = new PreferencesScreen(this); // added (this)
                this.setScreen(preferencesScreen);
                break;

            case GAMESCREEN:
                if(mainScreen == null) mainScreen = new MainScreen(this); //added (this)
                this.setScreen(mainScreen);
                break;

            case ENDGAME:
                if(endScreen == null) endScreen = new EndScreen(this);  // added (this)
                this.setScreen(endScreen);
                break;
        }
    }



    public MainMenuController() {
        // Set corresponding state and view here!!!
        this.state = new State();
        this.view = new MainMenuView();
    }

    /**
     * Run the render function on the view.
     */
    public void render(SpriteBatch spriteBatch){
        //super.render(spriteBatch);
        view.render(state, spriteBatch);
    }

    /**
     * Run the update function in the model.
     */

    @Override
    public void update(float deltaTime){
        //super.update();

        state.update(deltaTime);
    }

    /**
     * Handle input.
     */
    @Override
    public abstract void handleInput(int changeEvent) {


    };

    @Override
    public void show() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }


    @Override
    public void rezise(int width, int height) {

    }

    /*public void render( float delta) {
        Gdx.gl.glClearColor(.1f, .12f, .16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }*/


    /*   public MainMenu(WordleBattle WordleBattleGame) {
           this.WordleBattleGame = WordleBattleGame;
           width = WordleBattleGame.getWidth();
           height = WordleBattleGame.getHeight();
           skin = WordleBattleGame.getSkin();
       }*/
}
