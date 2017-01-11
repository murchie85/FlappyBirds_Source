package com.flappybirds.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import org.w3c.dom.css.Rect;

import java.awt.TextArea;
import java.util.Random;

/**
 * Created by adammcmurchie on 09/01/2017.
 */
public class Tube {
    public static final int TUBE_WIDTH = 52;
    private static final int FLUCTUATION = 130; // it can move up/down randomly 0 to 130
    private static final int TUBE_GAP = 100; // openings in gap will be 100
    private static final int LOWEST_OPENING = 120; // cant be less than 120 in height
    private Texture topTube;
    private Texture bottomtube;

    //bounding
    private Rectangle boundsTop, boundsBot;
    private Random rand;

    private Vector2 posTopTube, posBotTube;

    public Tube(float x){// x axis where it starts
        topTube     = new Texture("toptube.png");
        bottomtube  = new Texture("bottomtube.png");
        rand        = new Random();

        posTopTube = new Vector2(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBotTube = new Vector2(x, posTopTube.y - TUBE_GAP - bottomtube.getHeight()); // will be drawing below screen

        //set the boundaries then define at reposition
        boundsTop = new Rectangle(posTopTube.x,posTopTube.y, topTube.getWidth(), topTube.getHeight());
        boundsBot = new Rectangle(posBotTube.x,posBotTube.y, bottomtube.getWidth(), bottomtube.getHeight());
        // now pass back info with getters - generate them

    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomtube() {
        return bottomtube;
    }

    public Vector2 getPosTopTube() {return posTopTube;}

    public Vector2 getPosBotTube() {return posBotTube;}


    public void repositon(float x){
        posTopTube.set(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING); // copped pos top tube code
        posBotTube.set(x, posTopTube.y - TUBE_GAP - bottomtube.getHeight());
        boundsTop.setPosition(posTopTube.x,posTopTube.y);
        boundsBot.setPosition(posBotTube.x, posBotTube.y);
    }


    // returns true if player and tube overlap each other
    public boolean collides(Rectangle player){
        return player.overlaps(boundsTop) || player.overlaps(boundsBot);
    }
    public void dispose(){
        topTube.dispose();
        bottomtube.dispose();
    }
}
