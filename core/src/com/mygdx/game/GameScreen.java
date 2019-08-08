package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.com.game.managers.GameManager;
import com.mygdx.game.com.game.managers.InitRotationManager;
import com.mygdx.game.com.game.managers.InputManager;
import com.mygdx.game.com.game.managers.RotateManager;

public class GameScreen implements Screen {
    public SpriteBatch batch;
    public GameManager gameManager;
    public InputManager inputManager;
    public RotateManager rotateManager;
    public InitRotationManager initRotationManager;

    public GameScreen(){
        batch = new SpriteBatch();
        gameManager = new GameManager();
        gameManager.initialize();
        initRotationManager = new InitRotationManager(gameManager);
        inputManager = new InputManager(gameManager, initRotationManager);
        rotateManager = new RotateManager(gameManager, initRotationManager);


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        gameManager.renderGame(batch);

        batch.end();

        inputManager.handleInput();
        rotateManager.rotate();
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
        gameManager.dispose();
    }
}
