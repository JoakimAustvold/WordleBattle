package com.mygdx.game.controller.multiplayer;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.controller.Controller;
import com.mygdx.game.model.words.Language;
import com.mygdx.game.model.words.WordValidator;
import com.mygdx.game.view.multiplayer.MultiplayerWordInputView;

public class MultiplayerWordInputController extends Controller {

    public MultiplayerWordInputController() {
        this.view = new MultiplayerWordInputView();

        final WordValidator wordValidator = new WordValidator(Language.ENGLISH);
        final MultiplayerWordInputView multiplayerWordInputView = (MultiplayerWordInputView) view;

        multiplayerWordInputView.submitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String submittedWord = multiplayerWordInputView.wordField.getText();
                if (submittedWord.length() != 5) {
                    multiplayerWordInputView.setErrormessage("Word is too short");
                }
                else if (!wordValidator.isValid(submittedWord)) {
                    multiplayerWordInputView.setErrormessage("Word not in list");
                } else{
                    multiplayerWordInputView.setErrormessage("Starting game...");
                }
                //TODO: Submit word to firebase and open the Multiplayer Game View
            }
        });
    }
}
