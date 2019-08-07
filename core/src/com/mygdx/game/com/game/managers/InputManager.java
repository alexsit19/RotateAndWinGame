package com.mygdx.game.com.game.managers;

import com.badlogic.gdx.Gdx;

import static com.mygdx.game.com.game.managers.GameManager.centralElements;
import static com.mygdx.game.com.game.managers.GameManager.initRotationManager;


public class InputManager {
   static float xTouch, yTouch;

    public static void handleInput(){

        if(Gdx.input.justTouched() && !InitRotationManager.isRotateNow){
            xTouch = Gdx.input.getX();
            yTouch = GameManager.SCREEN_HEIGHT - Gdx.input.getY();
            for(int i = 0; i < centralElements.length; i++){

                initRotationManager.handleTouch(xTouch, yTouch, centralElements[i].cESprite, i);

            }

        }
    }
}
