package com.mygdx.game.model.keyboard;

public class KeyboardInput {
    private StringBuilder text = new StringBuilder();

    public void appendText(String newText) {
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
