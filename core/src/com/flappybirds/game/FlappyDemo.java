package com.flappybirds.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flappybirds.game.States.GameStateManager;
import com.flappybirds.game.States.MenuState;

public class FlappyDemo extends ApplicationAdapter {

    // GLOBAL VARIABLES
	public static final int WIDTH = 480;            // FINAL MEANS CAN'T BE MODIFIED...
    public static final int HEIGHT = 800;


    public static final String TITLE = "Flappy Bird";
    // we need to make gamestatem update first then render
    private GameStateManager gsm;
    private SpriteBatch batch;                          // should only have one (heavy files)
//	Texture img;                                        // pass spritebatch to diff states only

    private Music music;
    // runs over and over again i think
	@Override
	public void create () {
		batch = new SpriteBatch();
        gsm = new GameStateManager();
        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        gsm.push(new MenuState(gsm));

//		img = new Texture("badlogic.jpg");
	}

    // think this happens all the time
	@Override
	public void render () {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gsm.update(Gdx.graphics.getDeltaTime());  // diff between renders
        gsm.render(batch);

//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.end();
	}

    @Override
    public void dispose() {
        super.dispose();
        music.dispose();
    }

//	@Override
//	public void dispose () {
//		batch.dispose();
//		img.dispose();
//	}
}
