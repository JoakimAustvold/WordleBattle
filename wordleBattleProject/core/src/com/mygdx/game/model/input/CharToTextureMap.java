package com.mygdx.game.model.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

/**
 *  Maps char to corresponding letter texture
 */

public class CharToTextureMap {
    public static String CharToTexture (char character) {
        switch (character) {
            case 'a': return "textures/letters/a.png";
        }
        return "textures/letters/a.png";
    }
}
