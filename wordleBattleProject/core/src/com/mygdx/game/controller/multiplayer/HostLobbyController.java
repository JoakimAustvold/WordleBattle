package com.mygdx.game.controller.multiplayer;

import com.mygdx.game.controller.Controller;
import com.mygdx.game.model.multiplayer.LobbyInfo;
import com.mygdx.game.model.states.HostLobbyState;
import com.mygdx.game.view.multiplayer.HostLobbyView;

public class HostLobbyController extends Controller {


    public HostLobbyController() {
        //this.state = LobbyInfo.getInstance();
        this.state = new HostLobbyState();
        this.view = new HostLobbyView();
    }

    @Override
    public void handleInput() {

    }


    public void setUsername(String username) {
        HostLobbyState lobbyState = (HostLobbyState) state;
        lobbyState.setUsername(username);
    }
    /*
    public void setCode(int code) {
        LobbyInfo.getInstance().setCode(code);
    }
    */


}
