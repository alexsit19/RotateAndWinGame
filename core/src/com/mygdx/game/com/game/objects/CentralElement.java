package com.mygdx.game.com.game.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CentralElement {
/**
 * класс контейнер включает в себя спрайт и поэтому имеет методы render и rotate
 * заведует центральными вращающимися частями
 * */
    public Sprite cESprite;//объявление спрайта


    public void render(SpriteBatch batch){
        // метод для рендеринга срайта принимает Спрайт батч это то что рисует спрайты
        cESprite.draw(batch);

    }



    public void rotate(float deg){
        //метод вращения спрайта, принимает минимальный угол вращения deg в нашем случае так как вызывается
        //многократно чтобы общий угол поворота был 90 градусов
        cESprite.rotate(deg);
    }


    }


