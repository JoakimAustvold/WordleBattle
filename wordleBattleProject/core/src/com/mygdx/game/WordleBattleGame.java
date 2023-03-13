package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class WordleBattleGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	FirebaseAPI firebaseAPI;

	public WordleBattleGame(FirebaseAPI firebaseAPI) {
		this.firebaseAPI = firebaseAPI;
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		System.out.println(firebaseAPI);
		firebaseAPI.updateAPI();
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
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
