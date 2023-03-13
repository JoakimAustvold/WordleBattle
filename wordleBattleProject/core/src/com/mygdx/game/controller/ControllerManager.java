package com.mygdx.game.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class ControllerManager{

    private static final ControllerManager controllerManagerInstance = new ControllerManager();
    private Stack<Controller> controllerStack;

    public static ControllerManager getInstance() {
        return controllerManagerInstance;
    }

    public ControllerManager(){
        controllerStack = new Stack<Controller>();
    }

    public void push(Controller controller){
        controllerStack.push(controller);
    }

    public void pop() {
        controllerStack.pop();
    }

    public void set (Controller controller){
        controllerStack.pop();
        controllerStack.push(controller);
    }

    public void update (){
        controllerStack.peek().update();
    }

    public void render(SpriteBatch spriteBatch){
        controllerStack.peek().render(spriteBatch);
    }

    public void handleInput(){
        controllerStack.peek().handleInput();
    }
}
