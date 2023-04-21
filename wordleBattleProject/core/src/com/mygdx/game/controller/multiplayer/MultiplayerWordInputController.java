package com.mygdx.game.controller.multiplayer;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.game.controller.Controller;
import com.mygdx.game.controller.ControllerManager;
import com.mygdx.game.model.SingletonAPI;
import com.mygdx.game.model.states.multiplayer.LobbyInfo;
import com.mygdx.game.model.words.Language;
import com.mygdx.game.model.words.WordValidator;
import com.mygdx.game.view.multiplayer.MultiplayerWordInputView;

public class MultiplayerWordInputController extends Controller {

    public MultiplayerWordInputController() {
       // this.state = new MultiplayerWordInputState();
        this.state = LobbyInfo.getInstance();
        this.view = new MultiplayerWordInputView();

        final LobbyInfo lobbyInfo = (LobbyInfo) state;

        final WordValidator wordValidator = new WordValidator(Language.ENGLISH);
        final MultiplayerWordInputView multiplayerWordInputView = (MultiplayerWordInputView) view;

        multiplayerWordInputView.submitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String submittedWord = multiplayerWordInputView.wordField.getText();
                if (submittedWord.length() != 5) {
                    multiplayerWordInputView.setFeedbackMessage("Word is too short");
                }
                else if (!wordValidator.isValid(submittedWord)) {
                    multiplayerWordInputView.setFeedbackMessage("Word not in list");
                } else{
                    multiplayerWordInputView.setFeedbackMessage("Waiting for player 2...");

                    // Submit word
                    SingletonAPI.getInstance().submitWord(lobbyInfo.getCode(), lobbyInfo.getCurrentPlayer().label, submittedWord);


                    ControllerManager.getInstance().push(new WaitingRoomController());
                }
            }
        });
    }

    @Override
    public void resetView() {

    }
}
