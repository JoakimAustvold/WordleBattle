package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.controller.Controller;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.controller.MainMenuController;

public class WordleBattleGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		/* Push starting-screen to controller*/
		// TODO: instantiate starting screen, for example MainMenuController.
		ControllerManager.getInstance().push(new MainMenuController());
	}
	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		ControllerManager.getInstance().handleInput();
		ControllerManager.getInstance().update();
		ControllerManager.getInstance().render(batch);
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
