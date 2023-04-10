package com.mygdx.game.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * @author Marcus Birkeland
 * @version 13.03.2023
 *
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

    public void update (float deltaTime){
        System.err.println("Found: " + controllerStack.peek());
        controllerStack.peek().update(deltaTime);
    }

    public void render(SpriteBatch spriteBatch){
        controllerStack.peek().render(spriteBatch);
    }

    public void handleInput(){
        controllerStack.peek().handleInput();
    }
}
