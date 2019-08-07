package com.mygdx.game.com.game.managers;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import static com.mygdx.game.com.game.managers.GameManager.negativeShiftXOrigin;
import static com.mygdx.game.com.game.managers.GameManager.negativeShiftXOriginScale;
import static com.mygdx.game.com.game.managers.GameManager.petals;
import static com.mygdx.game.com.game.managers.GameManager.positiveShiftXOrigin;
import static com.mygdx.game.com.game.managers.GameManager.positiveShiftXOriginScale;
import static com.mygdx.game.com.game.managers.GameManager.yShiftOrigin;

public class InitRotationManager {

    public static float degCount; //счетчик поворотов, при 2 градусах degCount = 45, при 3 градусах degCount = 30 и так далее
    public static boolean isRotateNow; //проверка вращается ли что то сейчас. если вращается то касания не обрабатываются
    public static boolean directionRotateRight; //направление вращения по часовой стрелке если true, если нет то против
    public float centerX; //координата x центра центрального элемента
    public float centerY; //координата y центра центрального элемента
    public static int id; //id - индекс массива сeRotationCount который содержит индексы элементов которые вращаются сейчас
    public static int[] overlapsRectIdArray; // массив содержит индексы лепестков которые будут сейчас вращаться
    public static int j; //счетчик лепестков для массива overlapsRectIdArray
    public Rectangle rect, petalRect; //прямоугольник ограничивающий центральный элемент, и прямоугольник ограничивающий лепесток
    public static int[] ceRotationCount; //массив для хранения индексов вращающегося центрального элемента
    public float X1, Y1;//координаты левого нижнего угла лепестков
    public float deltaX, deltaY; //расстояние между координатами левого нижнего угла и центром лепестка

    public Vector2 v = new Vector2(); // двумерный вектор для получения двуменрых координат из прямоугольников движка

    InitRotationManager(){

        isRotateNow = false;//
        directionRotateRight = false;
        overlapsRectIdArray = new int[4];
        j = 0;
        ceRotationCount = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};

    }

    public void handleTouch(float xTouch, float yTouch, Sprite cESprite, int i) {
        rect = cESprite.getBoundingRectangle();
        centerX = rect.getCenter(v).x;
        centerY = rect.getCenter(v).y;

        if (xTouch > rect.x && xTouch < rect.x + rect.width) {
            if (yTouch > rect.y && yTouch < rect.y + rect.width) {
                degCount = 45;
                isRotateNow = true;
                id = i;
                petalsOverlaps();
                if (xTouch > centerX) {
                    directionRotateRight = true;

                }
            }
        }
    }

    public void petalsOverlaps() {
        for (int i = 0; i < petals.length; i++) {
            petalRect = petals[i].pSprite.getBoundingRectangle();
            if (rect.overlaps(petalRect)) {
                overlapsRectIdArray[j] = i;
                j++;
            }
            if (j == 4) {
                j = 0;
                break;
            }
        }

        petalSetOriginAndBasedOrigin();


    }

    public void petalSetOriginAndBasedOrigin() {
        ceRotationCount[id]++;

        if (ceRotationCount[id] == 1) {

            for (int i = 0; i < overlapsRectIdArray.length; i++) {
                X1 = petals[overlapsRectIdArray[i]].pSprite.getVertices()[SpriteBatch.X1];
                Y1 = petals[overlapsRectIdArray[i]].pSprite.getVertices()[SpriteBatch.Y1];
                deltaX = centerX - X1;
                deltaY = centerY - Y1;

                if (Math.abs(deltaX) > negativeShiftXOriginScale - 3 && Math.abs(deltaX) < negativeShiftXOriginScale + 3) {
                    petals[overlapsRectIdArray[i]].pSprite.setOrigin(-negativeShiftXOrigin, yShiftOrigin);
                    petals[overlapsRectIdArray[i]].pSprite.setOriginBasedPosition(centerX, centerY);


                }

                if (Math.abs(deltaX) > positiveShiftXOriginScale - 3 && Math.abs(deltaX) < positiveShiftXOriginScale + 3) {
                    petals[overlapsRectIdArray[i]].pSprite.setOrigin(positiveShiftXOrigin, yShiftOrigin);
                    petals[overlapsRectIdArray[i]].pSprite.setOriginBasedPosition(centerX, centerY);


                }

                if (Math.abs(deltaY) > negativeShiftXOriginScale - 3 && Math.abs(deltaY) < negativeShiftXOriginScale + 3) {
                    petals[overlapsRectIdArray[i]].pSprite.setOrigin(-negativeShiftXOrigin, yShiftOrigin);
                    petals[overlapsRectIdArray[i]].pSprite.setOriginBasedPosition(centerX, centerY);

                }

                if (Math.abs(deltaY) > positiveShiftXOriginScale - 3 && Math.abs(deltaY) < positiveShiftXOriginScale + 3) {
                    petals[overlapsRectIdArray[i]].pSprite.setOrigin(positiveShiftXOrigin, yShiftOrigin);
                    petals[overlapsRectIdArray[i]].pSprite.setOriginBasedPosition(centerX, centerY);


                }

            }

        }
        for (int i = 0; i < ceRotationCount.length; i++) {
            if (i == id) {
                continue;
            }

            ceRotationCount[i] = 0;

        }
    }
}
