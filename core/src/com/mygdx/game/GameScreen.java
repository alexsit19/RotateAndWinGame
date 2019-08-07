package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.com.game.managers.GameManager;
import com.mygdx.game.com.game.managers.InputManager;
import com.mygdx.game.com.game.managers.RotateManager;

public class GameScreen implements Screen {
    public SpriteBatch batch;

    public GameScreen(){
        batch = new SpriteBatch();
        GameManager.initialize();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        GameManager.renderGame(batch);

        batch.end();

        InputManager.handleInput();
        RotateManager.rotate();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        //super.dispose();
        batch.dispose();
        GameManager.dispose();
    }
}
