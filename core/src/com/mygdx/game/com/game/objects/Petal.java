package com.mygdx.game.com.game.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Petal {
/** класс содержит спрайт для создания лепестков
*/

    public Sprite pSprite;

    public void render(SpriteBatch batch){
        // метод для рендеринга срайта принимает Спрайт батч это то что рисует спрайты
        pSprite.draw(batch);
    }

    public void rotate(float deg){
        //метод вращения спрайта, принимает минимальный угол вращения  deg в нашем случае так как вызывается
        //многократно чтобы общий угол поворота был 90 градусов
        pSprite.rotate(deg);

    }
}
