package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class Wordgrid {
    private Table gridTable;
    private Skin skin;

    public Wordgrid() {
        skin = new Skin();
        this.gridTable = new Table();
        this.gridTable.setSize(5, 6);
        //TODO: Style table
        //this.TestTable();
    }

    public void TestTable() {
        Label label = new Label("E", skin);
        this.gridTable.add(label).pad(10).center();
    }

}
