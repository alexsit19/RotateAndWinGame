package com.mygdx.game.com.game.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.com.game.objects.CentralElement;
import com.mygdx.game.com.game.objects.Petal;

public class GameManager {
/**
 * класс загружает текстуры игры, создает спрайты
 * и все это отображает
 * фактически создает поле игры
 *
 */

    public Petal[] petals;  //массив для хранения лепестков
    public CentralElement[] centralElements; //массив для хранения центарльных эелементов
    public Texture texture, texture2; //текстуры хранят лепестки и центральные элементы
    public Texture backgroundTexture; //текстура для заднего фона
    public Sprite backgroundSprite; //спрайт заднего фона
    public float SCREEN_HEIGHT; //высота экрана
    public float SCREEN_WIDTH; //ширина экрана
    public float X_CENTER_SCREEN; //центр экрана по X (по ширине)
    public float Y_CENTER_SCREEN; //центр экрана по Y (по высоте)
    public float widthForAllElements, scale; //ширина занимаемая всеми лепестками и центральными элементами в ряду, scale - коэффициент масштабирования
    public float positiveShiftXOrigin, negativeShiftXOrigin, yShiftOrigin; // величины нужные для установки относительных координат для вращения лепестков
    public float positiveShiftXOriginScale, negativeShiftXOriginScale, yShiftOriginScale; //те же величины что и на строчку выше но умноженные на scale
    public float baseWidth, baseShift; // ширина и высота центрального элемента, baseShift равен 1.5 baseWidth - для удобства
    public InitRotationManager initRotationManager; // класс занимается инициализацией вращения


    public void initialize(){
        //метод который создает игровое поле

        SCREEN_HEIGHT = Gdx.graphics.getHeight();//получаем высоту экрана
        SCREEN_WIDTH = Gdx.graphics.getWidth();//получаем ширину экрана
        X_CENTER_SCREEN = SCREEN_WIDTH / 2; //получаем центр экрана по ширине
        Y_CENTER_SCREEN = SCREEN_HEIGHT / 2; //получаем центр экрана по высоте

        texture = new Texture("BigPictures.png"); // загружаем все текстуры для отображения ценитральных элементов и лепестков
        texture2 = new Texture("BigPictures2.png");
        backgroundTexture = new Texture("1680x1050/background_7.png"); //загружаем текстуру заднего фона

        backgroundSprite = new Sprite(backgroundTexture); //создаем спрайт заднего фона
        backgroundSprite.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);//устанавливаем размер спрайта заднего фона
        backgroundSprite.setCenter(X_CENTER_SCREEN, Y_CENTER_SCREEN);//устанавливаем центр спрайта заднего фона в центре экрана

        centralElements = new CentralElement[9]; //создаем массив для хранения центральных элементов

        for (int i = 0; i < centralElements.length; i++){
            //заполняем массив объектами
            centralElements[i] = new CentralElement();
        }

        //создаем спрайт каждого центрального элемента используя конструктор спрайта
        centralElements[0].cESprite = new Sprite(texture, 2, 414, 300, 300);//green
        centralElements[1].cESprite = new Sprite(texture, 2, 716, 300, 300);//blue
        centralElements[2].cESprite = new Sprite(texture, 304, 716, 300, 300);//indigo
        centralElements[3].cESprite = new Sprite(texture, 2, 112, 300, 300);//maroon
        centralElements[4].cESprite = new Sprite(texture, 304, 414, 300, 300);//olive
        centralElements[5].cESprite = new Sprite(texture, 606, 716, 300, 300);//orange
        centralElements[6].cESprite = new Sprite(texture, 304, 112, 300, 300);//purple
        centralElements[7].cESprite = new Sprite(texture, 606, 414, 300, 300);//red
        centralElements[8].cESprite = new Sprite(texture, 606, 112, 300, 300);//yellow



        petals = new Petal[24];//создаем массив лепестков

        for (int i = 0; i < petals.length; i++){
            //заполняем массив объектами
            petals[i] = new Petal();
        }

        //создаем спрайт для каждого лепестка
        petals[0].pSprite = new Sprite(texture, 908, 764, 99, 252);//black_blueV
        petals[1].pSprite = new Sprite(texture, 908, 764, 99, 252);//black_blueH
        petals[2].pSprite = new Sprite(texture, 908, 510, 99, 252);//black_green
        petals[3].pSprite = new Sprite(texture, 908, 256, 99, 252);//black_maroon
        petals[4].pSprite = new Sprite(texture, 908, 2, 99, 252);//black_olive
        petals[5].pSprite = new Sprite(texture2,  2, 256, 99, 252);//black_orange
        petals[6].pSprite = new Sprite(texture2, 2, 2, 99, 252);//black_purpleH
        petals[7].pSprite = new Sprite(texture2, 2, 2, 99, 252);//black_purpleV
        petals[8].pSprite = new Sprite(texture2, 103, 256, 99, 252);//black_redH
        petals[9].pSprite = new Sprite(texture2, 103, 256, 99, 252);//black_redV
        petals[10].pSprite = new Sprite(texture2, 103, 2, 99, 252);//black_yellowH
        petals[11].pSprite = new Sprite(texture2, 103, 2, 99, 252);//black_yellowV
        petals[12].pSprite = new Sprite(texture2,  507, 256, 99, 252);//blue_maroon
        petals[13].pSprite = new Sprite(texture2, 204, 256, 99, 252);//blue_olive
        petals[14].pSprite = new Sprite(texture2, 305, 256, 99, 252);//indigo_green
        petals[15].pSprite = new Sprite(texture2, 305, 2, 99, 252);//indigo_maroon
        petals[16].pSprite = new Sprite(texture2, 406, 256, 99, 252);//indigo_olive
        petals[17].pSprite = new Sprite(texture2, 406, 2, 99, 252);//indigo_orange
        petals[18].pSprite = new Sprite(texture2, 608, 256, 99, 252);//purple_green
        petals[19].pSprite = new Sprite(texture2, 507, 2, 99, 252);//purple_orange
        petals[20].pSprite = new Sprite(texture2,  608, 2, 99, 252);//red_olive
        petals[21].pSprite = new Sprite(texture2, 709, 256, 99, 252);//red_orange
        petals[22].pSprite = new Sprite(texture2, 204, 2, 99, 252);//yellow_green
        petals[23].pSprite = new Sprite(texture2, 709, 2, 99, 252);//yellow_maroon

        //рассчитываем сколько занимает один ряд элементов
        //и далее расчитываем коэффициент масштабирования
        widthForAllElements = centralElements[0].cESprite.getWidth() * 3 + petals[0].pSprite.getWidth();
        scale = SCREEN_WIDTH / widthForAllElements;

        //рассчитываем положительную и отрицательную координату X для относительных вращений лепестков
        positiveShiftXOrigin = centralElements[0].cESprite.getWidth()/2 + petals[0].pSprite.getWidth()/2;
        negativeShiftXOrigin = centralElements[0].cESprite.getWidth()/2 - petals[0].pSprite.getWidth()/2;

        //рассчитываем координату Y для относительных вращений
        yShiftOrigin = petals[0].pSprite.getHeight()/2;

        //вычисляем масштабированные координаты, это понадобится при определении какие координаты какому лепестку присваивать
        //в классе InitRotationManager
        positiveShiftXOriginScale = positiveShiftXOrigin * scale;
        negativeShiftXOriginScale = negativeShiftXOrigin * scale;
        yShiftOriginScale = yShiftOrigin * scale;

        //масштабируем центральные элементы в соответствии с экраном устройства
        for (CentralElement centralElement : centralElements) {
            centralElement.cESprite.setScale(scale);
        }

        //масштабируем лепестки в соответствии с экраном устройства
        for (Petal petal : petals){
            petal.pSprite.setScale(scale);
        }

        //вычисляем величины для удобства расстановки спрайтов
        baseWidth = centralElements[0].cESprite.getWidth() * scale;
        baseShift = 1.5f * baseWidth;


        //расставляем спрайты на свои места
        petals[0].pSprite.setCenter(X_CENTER_SCREEN + baseShift, Y_CENTER_SCREEN + baseWidth);//black_blueV
        petals[1].pSprite.setCenter(X_CENTER_SCREEN + baseWidth, Y_CENTER_SCREEN + baseShift);//black_blueH
        petals[2].pSprite.setCenter(X_CENTER_SCREEN, Y_CENTER_SCREEN - baseShift);//black_green
        petals[3].pSprite.setCenter(X_CENTER_SCREEN + baseShift, Y_CENTER_SCREEN);//black_maroon
        petals[4].pSprite.setCenter(X_CENTER_SCREEN, Y_CENTER_SCREEN + baseShift);//black_olive
        petals[5].pSprite.setCenter(X_CENTER_SCREEN - baseShift, Y_CENTER_SCREEN);//black_orange
        petals[6].pSprite.setCenter(X_CENTER_SCREEN - baseWidth, Y_CENTER_SCREEN - baseShift);//black_purpleH
        petals[7].pSprite.setCenter(X_CENTER_SCREEN - baseShift, Y_CENTER_SCREEN - baseWidth);//black_purpleV
        petals[8].pSprite.setCenter(X_CENTER_SCREEN - baseShift, Y_CENTER_SCREEN + baseWidth);//black_redV
        petals[9].pSprite.setCenter(X_CENTER_SCREEN - baseWidth, Y_CENTER_SCREEN + baseShift);//black_redH
        petals[10].pSprite.setCenter(X_CENTER_SCREEN + baseShift, Y_CENTER_SCREEN - baseWidth);//black_yellowV
        petals[11].pSprite.setCenter(X_CENTER_SCREEN + baseWidth, Y_CENTER_SCREEN - baseShift);//black_yellowH
        petals[12].pSprite.setCenter(X_CENTER_SCREEN + baseWidth, Y_CENTER_SCREEN + baseWidth / 2);//blue_maroon
        petals[13].pSprite.setCenter(X_CENTER_SCREEN + baseWidth / 2, Y_CENTER_SCREEN + baseWidth);//blue_olive
        petals[14].pSprite.setCenter(X_CENTER_SCREEN, Y_CENTER_SCREEN - baseWidth / 2);//indigo_green
        petals[15].pSprite.setCenter(X_CENTER_SCREEN + baseWidth / 2, Y_CENTER_SCREEN);//indigo_maroon
        petals[16].pSprite.setCenter(X_CENTER_SCREEN, Y_CENTER_SCREEN + baseWidth / 2);//indigo_olive
        petals[17].pSprite.setCenter(X_CENTER_SCREEN - baseWidth / 2, Y_CENTER_SCREEN);//indigo_orange
        petals[18].pSprite.setCenter(X_CENTER_SCREEN - baseWidth / 2, Y_CENTER_SCREEN - baseWidth);//purple_green
        petals[19].pSprite.setCenter(X_CENTER_SCREEN - baseWidth, Y_CENTER_SCREEN - baseWidth / 2);//purple_orange
        petals[20].pSprite.setCenter(X_CENTER_SCREEN - baseWidth / 2, Y_CENTER_SCREEN + baseWidth);//red_olive
        petals[21].pSprite.setCenter(X_CENTER_SCREEN - baseWidth, Y_CENTER_SCREEN + baseWidth / 2);//red_orange
        petals[22].pSprite.setCenter(X_CENTER_SCREEN + baseWidth / 2, Y_CENTER_SCREEN - baseWidth);//yellow_green
        petals[23].pSprite.setCenter(X_CENTER_SCREEN + baseWidth, Y_CENTER_SCREEN - baseWidth / 2);//yellow_maroon

        //придаем нужным лепесткам правильный поворот
        petals[1].pSprite.rotate(270);//black_blueH
        petals[0].pSprite.rotate(180);//black_blueV
        petals[6].pSprite.rotate(90);//black_purpleH
        petals[11].pSprite.rotate(90);//black_yellowH
        petals[4].pSprite.rotate(270);//black_olive
        petals[2].pSprite.rotate(90);//black_green
        petals[3].pSprite.rotate(180);//black_maroon
        petals[9].pSprite.rotate(270);//black_redH
        petals[10].pSprite.rotate(180);//black_yellowV
        petals[12].pSprite.rotate(270);//blue_maroon
        petals[13].pSprite.rotate(180);//blue_olive
        petals[14].pSprite.rotate(270);//indigo_green
        petals[17].pSprite.rotate(180);//indigo_orange
        petals[16].pSprite.rotate(90);// indigo_olive
        petals[21].pSprite.rotate(270);//red_orange
        petals[22].pSprite.rotate(180);//yellow_green
        petals[23].pSprite.rotate(90);//yellow_maroon
        petals[19].pSprite.rotate(90);//purple_orange

        //располагаем центральные лепестки по координатам
        centralElements[2].cESprite.setCenter(X_CENTER_SCREEN, Y_CENTER_SCREEN);//indigo
        centralElements[4].cESprite.setCenter(X_CENTER_SCREEN, Y_CENTER_SCREEN + baseWidth);//olive
        centralElements[0].cESprite.setCenter(X_CENTER_SCREEN, Y_CENTER_SCREEN - baseWidth);//green
        centralElements[5].cESprite.setCenter(X_CENTER_SCREEN - baseWidth, Y_CENTER_SCREEN);//orange
        centralElements[3].cESprite.setCenter(X_CENTER_SCREEN + baseWidth, Y_CENTER_SCREEN);//maroon
        centralElements[7].cESprite.setCenter(X_CENTER_SCREEN - baseWidth, Y_CENTER_SCREEN + baseWidth);//red
        centralElements[1].cESprite.setCenter(X_CENTER_SCREEN + baseWidth, Y_CENTER_SCREEN + baseWidth);//blue
        centralElements[6].cESprite.setCenter(X_CENTER_SCREEN - baseWidth, Y_CENTER_SCREEN - baseWidth);//purple
        centralElements[8].cESprite.setCenter(X_CENTER_SCREEN + baseWidth, Y_CENTER_SCREEN - baseWidth);//yellow

    }

    public float getPositiveShiftXOrigin(){
        return positiveShiftXOrigin;
    }

    public float getNegativeShiftXOrigin(){
        return negativeShiftXOrigin;
    }

    public float getYShiftOrigin(){
        return yShiftOrigin;
    }

    public float getNegativeShiftXOriginScale(){
        return negativeShiftXOriginScale;
    }

    public float getPositiveShiftXOriginScale(){
        return positiveShiftXOriginScale;
    }

    public Petal[] getPetals(){
        return petals;
    }

    public CentralElement[] getCentralElements(){
        return centralElements;
    }

    public float getSCREEN_HEIGHT(){
        return SCREEN_HEIGHT;
    }

    public void renderGame(SpriteBatch batch){

        backgroundSprite.draw(batch);//отображаем спрайт заднего фона

        for (CentralElement centralElement : centralElements){
            //отображаем центральные элементы
            centralElement.cESprite.draw(batch);
        }

        for (Petal petal : petals){
            //отображаем лепестки
            petal.pSprite.draw(batch);
        }


    }

    public void dispose(){
        backgroundTexture.dispose();//уничтожаем текстуры
        texture2.dispose();
        texture.dispose();
    }
}
