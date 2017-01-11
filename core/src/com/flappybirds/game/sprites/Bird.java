package com.flappybirds.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;





/**
 * Created by adammcmurchie on 09/01/2017.
 */


// DFINE FEATURE, CONSTRUCT FEATURE, UDPATE FEATURE, return values



// bird neesd position
    // velocity and stuff
public class Bird {
    private static final int Gravity = -15;
    private static final int MOVEMENT = 100;
    private Vector3 position; //xyz
    private Vector3 velocity;
    private Rectangle bounds;
    private Animation birdAnimation;
    private Texture texture;
    private Sound flap;

    private Texture bird;

    // constructor - the instant of Bird not bird
    public Bird(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);

        texture = new Texture("birdanimation.png");
        birdAnimation = new Animation(new TextureRegion(texture), 3, 0.5f);//(texture) from above, 3 different frames, 0.5 time per frame
        bounds= new Rectangle(x, y, texture.getWidth() / 3, texture.getHeight());// construct bounds next update it
        flap = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));

    }



    //sends delta time to birdclass to allow it to do the maths to reset its position in game
    public void update(float dt){
        birdAnimation.update(dt);
        if(position.y >0)
            velocity.add(0,Gravity,0); //constantly adds gravity

        // this is because we do this in relation to change in time, so we scale by change in time
        velocity.scl(dt); // multiplies everything by delta time
        position.add(MOVEMENT * dt, velocity.y, 0); // we add velocity.y
        if(position.y < 0){
            position.y = 0;
        }

        velocity.scl(1/dt);// turns it back so we can add it on next frame reverses scale
        bounds.setPosition(position.x,position.y);

    }

    // right click GENERATE GETTER FOR GETPOS GETBIRD  To get info back ot our playstate
    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture(){return birdAnimation.getFrame();  }

    public void jump(){
        velocity.y = 250;
        flap.play(0.3f);
    }


    public Rectangle getBounds(){return bounds;}

    public void dispose(){
        texture.dispose();
        flap.dispose(); }
}
