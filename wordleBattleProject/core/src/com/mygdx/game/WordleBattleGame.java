package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.controller.MainMenuController;

import com.mygdx.game.model.FirebaseAPI;
import com.mygdx.game.model.SingletonAPI;
import com.mygdx.game.model.highscore.HighscoreList;

/**
 * App entrypoint from LibGDX.
 */
public class WordleBattleGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private FirebaseAPI firebaseAPI;
	private HighscoreList highscores;

	// Do not change, it will break everything!! (wordlists only consist of 5-letter words.)
	public static final int WORD_LENGTH = 5;

    public WordleBattleGame(FirebaseAPI firebaseAPI) {

		this.firebaseAPI = firebaseAPI;
		SingletonAPI.getInstance().setFirebaseAPI(firebaseAPI);
		this.highscores = new HighscoreList();
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		ControllerManager.getInstance().push(new MainMenuController());
	}
	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);

		batch.begin();
		ControllerManager.getInstance().render(batch);
		batch.end();
	}
	@Override
	public void dispose () {
		batch.dispose();
	}
}
