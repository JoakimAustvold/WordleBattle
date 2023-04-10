package com.mygdx.game.controller;

import com.mygdx.game.view.SettingsView;

public class SettingsController extends Controller{

   public SettingsController(){
       this.state = null;
       this.view = new SettingsView();
   }

   @Override
   public void handleInput() {

   }
}
