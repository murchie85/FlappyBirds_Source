package com.flappybirds.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import static com.badlogic.gdx.utils.JsonValue.ValueType.array;


/**
 * Created by adammcmurchie on 10/01/2017.
 */

public class Animation {
    private Array<TextureRegion> frames;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount;
    private int frame;      // current frame



    public Animation(TextureRegion region, int frameCount, float cycleTime){
        frames = new Array<TextureRegion>();
        int frameWidth = region.getRegionWidth() / frameCount; //gets width of single frame
        for(int i = 0; i < frameCount; i++){
            frames.add(new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight())); // cut into splices
            // cuts the three sprites up
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0; // this is where wee start
    }
    // update
    public void update(float dt){
        currentFrameTime += dt;
        if(currentFrameTime > maxFrameTime){
            frame++;
            currentFrameTime = 0;
        }
        //if over eddge of frame
        if(frame >= frameCount){
            frame =0;
        }
    }

    public TextureRegion getFrame(){
        return frames.get(frame);
    }
}
