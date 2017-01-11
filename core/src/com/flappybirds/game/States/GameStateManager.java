package com.flappybirds.game.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by adammcmurchie on 08/01/2017.
 */
    //states is class name
    // state is stack name
    //State is classifier
public class GameStateManager {

    private Stack<State> states;

    // constructor
    public GameStateManager(){
        states = new Stack<State>();
    }


    // methods for stack management

    public void push(State state){
        states.push(state);
    }

    public void pop(){
        states.pop().dispose();
    } // free up memory after pop



    // set - if we want ot immediately replace it
    public void set(State state){
        states.pop().dispose();
        states.push(state);
    }

    // peak looks at top state
    // updates the time between two renders
    public void update(float dt){

        states.peek().update(dt);
    }

    // takes spritebatch which is everything
    public void render(SpriteBatch sb){

        states.peek().render(sb);
    }
}
