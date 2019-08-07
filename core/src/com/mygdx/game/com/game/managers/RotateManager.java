package com.mygdx.game.com.game.managers;

public class RotateManager {

    public static void rotate(){
        //isRotateNow - проверяет вращается ли что либо сейчас
        if(InitRotationManager.isRotateNow){
            InitRotationManager.degCount--;
                                            //счетчик вращения вместе с минимальным углом поворота deg
                                            //обеспечивает поворот на 90 градусов
                                            //т.е. если deg = 2 грудуса то degCount = 45

            //directionRotateRight если true то вращение будет по часовой стрелке
            if(InitRotationManager.directionRotateRight) {
                GameManager.centralElements[InitRotationManager.id].rotate(-2);
                GameManager.petals[InitRotationManager.overlapsRectIdArray[0]].rotate(-2);
                GameManager.petals[InitRotationManager.overlapsRectIdArray[1]].rotate(-2);
                GameManager.petals[InitRotationManager.overlapsRectIdArray[2]].rotate(-2);
                GameManager.petals[InitRotationManager.overlapsRectIdArray[3]].rotate(-2);

            }

            else {
                //в противном случае вращение против часовой стрелки
                GameManager.centralElements[InitRotationManager.id].rotate(2);
                GameManager.petals[InitRotationManager.overlapsRectIdArray[0]].rotate(2);
                GameManager.petals[InitRotationManager.overlapsRectIdArray[1]].rotate(2);
                GameManager.petals[InitRotationManager.overlapsRectIdArray[2]].rotate(2);
                GameManager.petals[InitRotationManager.overlapsRectIdArray[3]].rotate(2);
            }

            if(InitRotationManager.degCount == 0){
                InitRotationManager.isRotateNow = false;
                InitRotationManager.directionRotateRight = false;

            }
        }

    }


}
