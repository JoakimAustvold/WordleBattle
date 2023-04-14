package com.mygdx.game.controller;

import com.mygdx.game.model.states.TutorialState;
import com.mygdx.game.view.MainMenuView;

/**
 * Sets the corresponding view for the main menu
 */
public class MainMenuController extends Controller {
    
     public MainMenuController() {
            // Set corresponding state and view here!!!
            this.state = null;
            this.view = new MainMenuView(new TutorialState());
        }
        
        @Override
        public void handleInput() {
    
        }

    @Override
    public void resetView() {

    }
}
