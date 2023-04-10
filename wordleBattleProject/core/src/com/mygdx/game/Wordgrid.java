package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class Wordgrid {
    private Table gridTable;
    private Skin skin;

    public Wordgrid() {
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("style.atlas"));
        skin = new Skin();
        skin.addRegions(atlas);
        this.gridTable = new Table();
        this.gridTable.setSize(5, 6);
        //TODO: Style table
        this.TestTable();
    }

    public void TestTable() {
        Label label = new Label("E", skin);
        this.gridTable.add(label).width(100);
    }

}
