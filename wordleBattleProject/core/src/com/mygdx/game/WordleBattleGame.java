package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.controller.ScreenController;
import com.mygdx.game.view.MainMenuView;

/**
 * App entrypoint from LibGDX.
 */
public class WordleBattleGame extends Game {
	SpriteBatch batch;
	Texture img;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		/* Push starting-screen to controller*/
		// TODO:
		//ControllerManager.getInstance().push(new MainMenuController());
		setScreen(new MainMenuView(new ScreenController()));
	}
	@Override
	public void render () {
		/*
		ScreenUtils.clear(0, 0, 0, 1);
		ControllerManager.getInstance().handleInput();
		ControllerManager.getInstance().update(Gdx.graphics.getDeltaTime());
		ControllerManager.getInstance().render(batch);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();

		 */
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
