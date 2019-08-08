package com.mygdx.game.com.game.managers;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class InitRotationManager {

    public float degCount; //счетчик поворотов, при 2 градусах degCount = 45, при 3 градусах degCount = 30 и так далее
    public boolean isRotateNow; //проверка вращается ли что то сейчас. если вращается то касания не обрабатываются
    public boolean directionRotateRight; //направление вращения по часовой стрелке если true, если нет то против
    public float centerX; //координата x центра центрального элемента
    public float centerY; //координата y центра центрального элемента
    public int indexRotatingCE; //id - индекс массива сeRotationCount который содержит индексы элементов которые вращаются сейчас
    public int[] overlapsRectIdArray; // массив содержит индексы лепестков которые будут сейчас вращаться
    public int j; //счетчик лепестков для массива overlapsRectIdArray
    public Rectangle rect, petalRect; //прямоугольник ограничивающий центральный элемент, и прямоугольник ограничивающий лепесток
    public int[] ceRotationCount; //массив для хранения индексов вращающегося центрального элемента
    public float X1, Y1;//координаты левого нижнего угла лепестков
    public float deltaX, deltaY; //расстояние между координатами левого нижнего угла и центром лепестка
    public GameManager gameManager;
    public Vector2 v; // двумерный вектор для получения двуменрых координат из прямоугольников движка
    public float negativeShiftXOriginScale, negativeShiftXOrigin, yShiftOrigin;
    public float positiveShiftXOriginScale, positiveShiftXOrigin;


    public InitRotationManager(GameManager gameManager){
        this.gameManager = gameManager;
        v = new Vector2();
        positiveShiftXOriginScale = gameManager.getPositiveShiftXOriginScale();
        positiveShiftXOrigin = gameManager.getPositiveShiftXOrigin();
        negativeShiftXOrigin = gameManager.getNegativeShiftXOrigin();
        negativeShiftXOriginScale = gameManager.getNegativeShiftXOriginScale();
        yShiftOrigin = gameManager.getYShiftOrigin();
        isRotateNow = false;//
        directionRotateRight = false;
        overlapsRectIdArray = new int[4];
        j = 0;
        ceRotationCount = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        indexRotatingCE = 0;

    }

    public boolean getIsRotateNow(){
        return isRotateNow;
    }

    public void setIsRotateNow(boolean isRotateNow){
        this.isRotateNow = isRotateNow;

    }

    public void setDegCount(float degCount){
        this.degCount = degCount;
    }

    public float getDegCount(){
        return degCount;
    }

    public boolean getDirectionRotateRight(){
        return directionRotateRight;
    }

    public void setDirectionRotateRight(boolean directionRotateRight){
        this.directionRotateRight = directionRotateRight;
    }

    public void decrementDegCount(){
        degCount--;
    }

    public int getIndexRotatingCE(){
        return indexRotatingCE;
    }

    public int[] getOverlapsRectIdArray(){
        return overlapsRectIdArray;
    }

    public void handleTouch(float xTouch, float yTouch, Sprite cESprite, int i) {

        rect = cESprite.getBoundingRectangle();
        centerX = rect.getCenter(v).x;
        centerY = rect.getCenter(v).y;

        if (xTouch > rect.x && xTouch < rect.x + rect.width) {
            if (yTouch > rect.y && yTouch < rect.y + rect.width) {
                setDegCount(45);
                setIsRotateNow(true);
                indexRotatingCE = i;
                petalsOverlaps();
                if (xTouch > centerX) {
                    setDirectionRotateRight(true);

                }
            }
        }
    }

    public void petalsOverlaps() {
        for (int i = 0; i < gameManager.petals.length; i++) {
            petalRect = gameManager.petals[i].pSprite.getBoundingRectangle();
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
        ceRotationCount[indexRotatingCE]++;

        if (ceRotationCount[indexRotatingCE] == 1) {

            for (int i = 0; i < overlapsRectIdArray.length; i++) {
                X1 = gameManager.petals[overlapsRectIdArray[i]].pSprite.getVertices()[SpriteBatch.X1];
                Y1 = gameManager.petals[overlapsRectIdArray[i]].pSprite.getVertices()[SpriteBatch.Y1];
                deltaX = centerX - X1;
                deltaY = centerY - Y1;

                if (Math.abs(deltaX) > negativeShiftXOriginScale - 3 && Math.abs(deltaX) < negativeShiftXOriginScale + 3) {
                    gameManager.petals[overlapsRectIdArray[i]].pSprite.setOrigin(-negativeShiftXOrigin, yShiftOrigin);
                    gameManager.petals[overlapsRectIdArray[i]].pSprite.setOriginBasedPosition(centerX, centerY);


                }

                if (Math.abs(deltaX) > positiveShiftXOriginScale - 3 && Math.abs(deltaX) < positiveShiftXOriginScale + 3) {
                    gameManager.petals[overlapsRectIdArray[i]].pSprite.setOrigin(positiveShiftXOrigin, yShiftOrigin);
                    gameManager.petals[overlapsRectIdArray[i]].pSprite.setOriginBasedPosition(centerX, centerY);


                }

                if (Math.abs(deltaY) > negativeShiftXOriginScale - 3 && Math.abs(deltaY) < negativeShiftXOriginScale + 3) {
                    gameManager.petals[overlapsRectIdArray[i]].pSprite.setOrigin(-negativeShiftXOrigin, yShiftOrigin);
                    gameManager.petals[overlapsRectIdArray[i]].pSprite.setOriginBasedPosition(centerX, centerY);

                }

                if (Math.abs(deltaY) > positiveShiftXOriginScale - 3 && Math.abs(deltaY) < positiveShiftXOriginScale + 3) {
                    gameManager.petals[overlapsRectIdArray[i]].pSprite.setOrigin(positiveShiftXOrigin, yShiftOrigin);
                    gameManager.petals[overlapsRectIdArray[i]].pSprite.setOriginBasedPosition(centerX, centerY);


                }

            }



        }
        for (int i = 0; i < ceRotationCount.length; i++) {
            if (i == indexRotatingCE) {
                continue;
            }

            ceRotationCount[i] = 0;

        }
    }
}
