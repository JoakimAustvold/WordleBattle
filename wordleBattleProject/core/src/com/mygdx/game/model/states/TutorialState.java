package com.mygdx.game.model.states;

public class TutorialState extends State{
    private boolean hasPlayed;

    public TutorialState() {
        this.hasPlayed = false;
    }

    @Override
    public void update(float dt) {

    }

    public boolean getHasPlayed() {
        return hasPlayed;
    }

    public void setHasPlayed(boolean hasPlayed) {
        this.hasPlayed = hasPlayed;
    }
}
