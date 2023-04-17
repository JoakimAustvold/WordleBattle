package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.controller.MainMenuController;
import com.mygdx.game.controller.TutorialController;
import com.mygdx.game.model.FirebaseAPI;
import com.mygdx.game.model.SingletonAPI;
import com.mygdx.game.model.highscore.HighscoreList;
import com.mygdx.game.model.states.TutorialState;

import jdk.tools.jmod.Main;

/**
 * App entrypoint from LibGDX.
 */
public class WordleBattleGame extends ApplicationAdapter {
	SpriteBatch batch;


	FirebaseAPI firebaseAPI;
	HighscoreList highscores;
	TutorialState ts;

	// Do not change, it will break everything!! (wordlists only consist of 5-letter words.)
	public static final int WORD_LENGTH = 5;

    public WordleBattleGame(FirebaseAPI firebaseAPI) {

		this.firebaseAPI = firebaseAPI;
		SingletonAPI.getInstance().setFirebaseAPI(firebaseAPI);
		this.highscores = new HighscoreList(firebaseAPI);
	}


	@Override
	public void create () {
		batch = new SpriteBatch();
		Preferences prefs = Gdx.app.getPreferences("WordleBattleGame Preferences");
		boolean hasPlayed = prefs.getBoolean("hasPlayed");
		if (hasPlayed) {
			ControllerManager.getInstance().push(new MainMenuController());
		} else {
			ControllerManager.getInstance().push(new TutorialController());
		}
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		ControllerManager.getInstance().handleInput();
		ControllerManager.getInstance().update(Gdx.graphics.getDeltaTime());

		batch.begin();
		ControllerManager.getInstance().render(batch);
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
