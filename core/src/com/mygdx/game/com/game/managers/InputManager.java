package com.mygdx.game.com.game.managers;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.com.game.objects.CentralElement;


public class InputManager {
   public float xTouch, yTouch;
   public float screenHeight;
   public GameManager gameManager;
   public CentralElement[] centralElements;
   public InitRotationManager initRotationManager;
   public boolean isRotateNow;


   public InputManager(GameManager gameManager, InitRotationManager initRotationManager){
       this.initRotationManager = initRotationManager;
       screenHeight = gameManager.getSCREEN_HEIGHT();
       centralElements = gameManager.getCentralElements();
      
   }

   public void handleInput(){

        if(Gdx.input.justTouched() && !initRotationManager.getIsRotateNow()){
            xTouch = Gdx.input.getX();
            yTouch = screenHeight - Gdx.input.getY();
            for(int i = 0; i < centralElements.length; i++){

                initRotationManager.handleTouch(xTouch, yTouch, centralElements[i].cESprite, i);

            }

        }
    }
}
