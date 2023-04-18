package com.mygdx.game.view.letters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

public class LetterMap {

    private HashMap<String, Texture> textureMap;

    public LetterMap(){
        textureMap = new HashMap<>();
        String charsString = "abcdefghijklmnopqrstuvxyz";
        for(int i = 0; i < charsString.length(); i++){
            char c = charsString.charAt(i);
            textureMap.put(c+"", new Texture(Gdx.files.internal("textures/letters/"+c+".png")));
        }
    }

    public Texture getTexture(String c){
        return textureMap.get(c);
    }
}
