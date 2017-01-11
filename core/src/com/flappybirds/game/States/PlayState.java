package com.flappybirds.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.flappybirds.game.FlappyDemo;
import com.flappybirds.game.sprites.Bird;
import com.flappybirds.game.sprites.Tube;

import java.awt.TextArea;

import static java.awt.SystemColor.text;

/**
 * Created by adammcmurchie on 09/01/2017.
 */

// we right click to generate all methods and one constructor
    //creates bird object from picture and renders
public class PlayState extends State {
    // TUBES
    private static  final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    // ARRAY OF TUBES GDX ARRAY
    private Array<Tube> tubes;

   //SPRITES and ground
    private Bird bird;
    private Texture bg;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;
    private static final int GROUND_Y_OFFSET = -50;



    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50, 300); // start position
        cam.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2); // zooms in (false = start from 0,0 btm left)
        bg = new Texture("bg.png");
        ground = new Texture("ground.png");
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth/2, GROUND_Y_OFFSET); // shunt ground to the left side
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth/2)+ ground.getWidth(), GROUND_Y_OFFSET); // add on to end of ground

        tubes = new Array<Tube>();

        for(int i =1; i <= TUBE_COUNT; i++){
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        bird.update(dt);
        cam.position.x = bird.getPosition().x + 80; // keep camera on bird off by 80


        //cycles through tubes
        for (int i = 0; i < tubes.size; i++){   //tubes.size = array size
            Tube tube = tubes.get(i);
            //if tube is off left of screen we execute this to reset it to the right
            if(cam.position.x -(cam.viewportWidth /2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()){
                tube.repositon(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }
            //boundary check
            if(tube.collides(bird.getBounds())){
                gsm.set(new PlayState(gsm));
            }
        }

        if(bird.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET){
            gsm.set(new PlayState(gsm));
        }

        cam.update(); // need to call the cam to keep on bird

    }




    /// RENDER
    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined); // tells us where in the world we are to draw it
        sb.begin();
        sb.draw(bg, cam.position.x - (cam.viewportWidth /2 ), 0);
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);


        for (Tube tube : tubes){
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomtube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }

        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);

        sb.end();
    }

    // DISPOSEMETHOD
    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        ground.dispose();
        for(Tube tube : tubes){
            tube.dispose();
            System.out.println("play state disposed");
        }
    }
    //update ground to check if camera is passed where ground is
    private void updateGround(){
        if(cam.position.x - (cam.viewportWidth / 2 ) > groundPos1.x + ground.getWidth()){
            groundPos1.add(ground.getWidth() * 2, 0);
        }
        if(cam.position.x - (cam.viewportWidth / 2 ) > groundPos2.x + ground.getWidth()){
            groundPos2.add(ground.getWidth() * 2, 0);
        }
    }
}
