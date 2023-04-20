package com.mygdx.game.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Singleton class containing a stack of controllers.
 * Each controller is coupled with a view and a state class.
 * To change a game-screen, push a new controller to the stack!
 */
public class ControllerManager{
    private static final ControllerManager controllerManagerInstance = new ControllerManager();
    private Stack<Controller> controllerStack;

    public static ControllerManager getInstance() {
        return controllerManagerInstance;
    }

    public ControllerManager(){
        controllerStack = new Stack<>();
    }

    public void push(Controller controller){
        controllerStack.push(controller);
        controllerStack.peek().getView().setup();
    }

    public void pop() {
         // TODO: Add dispose and setup to all methods in a correct manner
        controllerStack.peek().getView().dispose();
        controllerStack.pop();
        controllerStack.peek().getView().setup();
    }

    public void set (Controller controller){
        controllerStack.pop();
        controllerStack.push(controller);
    }


    public void render(SpriteBatch spriteBatch){
        controllerStack.peek().render(spriteBatch);
    }

    public Controller peek() {
        return controllerStack.peek();
    }
}
