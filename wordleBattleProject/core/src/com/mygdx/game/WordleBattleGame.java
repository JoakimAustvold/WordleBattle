package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.controller.MainMenuController;
import com.mygdx.game.model.FirebaseAPI;
import com.mygdx.game.model.highscore.HighscoreList;
import com.mygdx.game.model.highscore.Score;

/**
 * App entrypoint from LibGDX.
 */
public class WordleBattleGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	FirebaseAPI firebaseAPI;
	HighscoreList highscores;

    public WordleBattleGame(FirebaseAPI firebaseAPI) {
    		this.firebaseAPI = firebaseAPI;
			this.highscores = new HighscoreList(firebaseAPI);
    	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		System.out.println(firebaseAPI);
        firebaseAPI.updateAPI();

		/* Push starting-screen to controller*/
		// TODO:
		//ControllerManager.getInstance().push(new MainMenuController());

		highscores.fetchHighscores();
        highscores.submitHighscore("John", 3600);
        highscores.fetchHighscores();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
//		ControllerManager.getInstance().handleInput();
//		ControllerManager.getInstance().update(Gdx.graphics.getDeltaTime());
//		ControllerManager.getInstance().render(batch);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
