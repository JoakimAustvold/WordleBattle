package com.mygdx.game.model.input;

// This class keeps track of which keys the user clicks on the keyboard and saves it in variable text
public class KeyboardInput {
    private StringBuilder text = new StringBuilder();

    public void appendChar(String newText) {
        text.append(newText);
    }

    public void deleteLastChar() {
        if (text.length() > 0) {
            text.deleteCharAt(text.length() - 1);
        }
    }

    public String getCurrentText() {
        return text.toString();
    }
}
