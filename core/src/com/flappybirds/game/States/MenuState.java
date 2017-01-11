package com.flappybirds.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flappybirds.game.FlappyDemo;

/**
 * Created by adammcmurchie on 08/01/2017.
 */


// IMPORTANT RIGHT CLICK ON MENUSTATE OVERRIDE METHODS SELECT FIRST FOUR
public class MenuState extends State {

    private Texture background;
    private Texture playBtn;
    public MenuState(GameStateManager gsm) {
        super(gsm);
        // COMMENT FOR DESKTOP
        //cam.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
        background = new Texture("bg.png");
        playBtn = new Texture("playbtn.png");
    }

    // we dispose individual textures to free up memory
    @Override
    public void handleInput() {
        if(Gdx.input.justTouched())
        {
            gsm.set(new PlayState(gsm)); //sets playstate class

        }
    }

    //handle input to check if user did anything
    @Override
    public void update(float dt) {
        handleInput();
    }
    // bit like a container open it, put everything in then close and it uses contents to render
    @Override
    public void render(SpriteBatch sb) {
        //sb.setProjectionMatrix(cam.combined); // COMMENT FOR DESKTOP
        sb.begin();

        sb.draw(background, 0,0,FlappyDemo.WIDTH, FlappyDemo.HEIGHT);//add FlappyDemo.WIDTH, FlappyDemo.HEIGHT for desktop
        sb.draw(playBtn, (FlappyDemo.WIDTH /2) - (playBtn.getWidth()/2),FlappyDemo.HEIGHT/2); // DESKTOP
       // sb.draw(playBtn, cam.position.x - playBtn.getWidth() /2, cam.position.y );//ANDROID
        sb.end();

    }

    // call this to transition state
    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        System.out.println("Menu State Disposed");
    }
}
