package com.flappybirds.game.States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by adammcmurchie on 08/01/2017.
 */

public abstract class State  {
    protected OrthographicCamera cam;
    protected Vector3 mouse;                // xyz
    protected GameStateManager gsm;         // manage state


    // MANAGES STATES OF GAMES / PLAY STATE, NEW STATE FOR PAUSE STATE ETC...
    // SO SWITCHING STATES ETC. WORKS WELL WITH A STACK
    protected State(GameStateManager gsm){
        this.gsm = gsm;
        cam = new OrthographicCamera();
        mouse = new Vector3();

    }

    protected abstract void handleInput();
    public abstract void update(float dt);          // dt delta time
    public abstract void render(SpriteBatch sb);    // container for everytyhing we need to render screen
    public abstract void dispose();

}
