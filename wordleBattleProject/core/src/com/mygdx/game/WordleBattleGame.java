package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.controller.MainMenuController;
import com.mygdx.game.controller.SingleplayerGameController;
import com.mygdx.game.model.FirebaseAPI;
import com.mygdx.game.model.highscore.HighscoreList;
import com.mygdx.game.model.highscore.Score;
import com.mygdx.game.model.words.WordGenerator;
import com.mygdx.game.model.words.WordValidator;

import jdk.internal.org.jline.utils.Log;

/**
 * App entrypoint from LibGDX.
 */
public class WordleBattleGame extends ApplicationAdapter {

	SpriteBatch batch;

	FirebaseAPI firebaseAPI;
	HighscoreList highscores;

    public WordleBattleGame(FirebaseAPI firebaseAPI) {
    		this.firebaseAPI = firebaseAPI;
			this.highscores = new HighscoreList(firebaseAPI);
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		//System.out.println(firebaseAPI);

		/* Push starting-screen to controller*/
		ControllerManager.getInstance().push(new SingleplayerGameController());

		/* Test Word validator
		*  See println with logcat window.
		*  Remove after testing!!
		*/
		WordValidator wv = new WordValidator(WordGenerator.Language.ENGLISH);
		String word = "shame";
		System.out.println("Valid word " + word + ": " + wv.isValid(word));
		word = "shaml";
		System.out.println("Valid word " + word + ": " + wv.isValid(word));
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
