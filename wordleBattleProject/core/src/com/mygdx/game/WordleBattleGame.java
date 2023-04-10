package com.mygdx.game;

/**
 * App entrypoint from LibGDX.
 */
/*

public class WordleBattleGame extends Game {
	SpriteBatch batch;
	Texture img;
	FirebaseAPI firebaseAPI;
	HighscoreList highscores;

	// Do not change, it will break everything!! (wordlists only consist of 5-letter words.)
	public static final int WORD_LENGTH = 5;

	public WordleBattleGame(FirebaseAPI firebaseAPI) {
		this.firebaseAPI = firebaseAPI;
		this.highscores = new HighscoreList(firebaseAPI);
	}
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		*/
/* Push starting-screen to controller*//*

		setScreen(new MainMenuView(new ScreenController()));
	}
	@Override
	public void render () {
		super.render();

	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
*/


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.controller.MainMenuController;
import com.mygdx.game.model.FirebaseAPI;
import com.mygdx.game.model.highscore.HighscoreList;

/**
 * App entrypoint from LibGDX.
 */
public class WordleBattleGame extends ApplicationAdapter {
	SpriteBatch batch;

	FirebaseAPI firebaseAPI;
	HighscoreList highscores;

	// Do not change, it will break everything!! (wordlists only consist of 5-letter words.)
	public static final int WORD_LENGTH = 5;

	public WordleBattleGame(FirebaseAPI firebaseAPI) {
		this.firebaseAPI = firebaseAPI;
		this.highscores = new HighscoreList(firebaseAPI);
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		//System.out.println(firebaseAPI);

		/* Push starting-screen to controller*/
		ControllerManager.getInstance().push(new MainMenuController());
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
