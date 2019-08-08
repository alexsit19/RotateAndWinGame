package com.mygdx.game.com.game.managers;

public class RotateManager {
    public int indexRotatingCE;
    public GameManager gameManager;
    public InitRotationManager initRotationManager;

    public RotateManager(GameManager gameManager, InitRotationManager initRotationManager){
        this.initRotationManager = initRotationManager;
        this.gameManager = gameManager;

    }

    public void rotate(){

        //isRotateNow - проверяет вращается ли что либо сейчас
        if(initRotationManager.getIsRotateNow()){
            indexRotatingCE = initRotationManager.getIndexRotatingCE();

            initRotationManager.decrementDegCount();

                                            //счетчик вращения вместе с минимальным углом поворота deg
                                            //обеспечивает поворот на 90 градусов
                                            //т.е. если deg = 2 грудуса то degCount = 45

            //directionRotateRight если true то вращение будет по часовой стрелке
            if(initRotationManager.getDirectionRotateRight()) {
                gameManager.centralElements[indexRotatingCE].rotate(-2);
                gameManager.petals[initRotationManager.overlapsRectIdArray[0]].rotate(-2);
                gameManager.petals[initRotationManager.overlapsRectIdArray[1]].rotate(-2);
                gameManager.petals[initRotationManager.overlapsRectIdArray[2]].rotate(-2);
                gameManager.petals[initRotationManager.overlapsRectIdArray[3]].rotate(-2);


            }

            else {
                //в противном случае вращение против часовой стрелки
                gameManager.centralElements[indexRotatingCE].rotate(2);
                gameManager.petals[initRotationManager.overlapsRectIdArray[0]].rotate(2);
                gameManager.petals[initRotationManager.overlapsRectIdArray[1]].rotate(2);
                gameManager.petals[initRotationManager.overlapsRectIdArray[2]].rotate(2);
                gameManager.petals[initRotationManager.overlapsRectIdArray[3]].rotate(2);

            }

            if(initRotationManager.getDegCount() == 0){
                initRotationManager.setIsRotateNow(false);
                initRotationManager.setDirectionRotateRight(false);

            }
        }

    }


}
