package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.controller.MainMenuController;
import com.mygdx.game.model.FirebaseAPI;
import com.mygdx.game.model.highscore.HighscoreList;
import com.mygdx.game.model.highscore.Score;
import com.mygdx.game.model.words.WordGenerator;

/**
 * App entrypoint from LibGDX.
 */
public class WordleBattleGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	FirebaseAPI firebaseAPI;
	HighscoreList highscores;

	BitmapFont font;
	String word;

    public WordleBattleGame(FirebaseAPI firebaseAPI) {
    		this.firebaseAPI = firebaseAPI;
			this.highscores = new HighscoreList(firebaseAPI);
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		//System.out.println(firebaseAPI);

		/* Push starting-screen to controller*/
		//ControllerManager.getInstance().push(new MainMenuController());

		font = new BitmapFont();
		font.getData().setScale(6,6);

		WordGenerator wg = new WordGenerator(WordGenerator.Language.ENGLISH);
		word = wg.generateWord();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		//ControllerManager.getInstance().handleInput();
		//ControllerManager.getInstance().update(Gdx.graphics.getDeltaTime());
		//ControllerManager.getInstance().render(batch);


		batch.begin();
		font.draw(batch, word, 450, 1000);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
