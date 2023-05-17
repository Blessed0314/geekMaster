package geekMasterGame;

import java.util.Arrays;

public class ModelGame {
    private DadoGeek dice;
    private int point, flag, accumulatedPoints, round, countDragons, countPoints, countSuperHero, countHeart;
    private String stateToString;
    private String[] caras, activeDices, inactiveDices;
    private int[] flagsInactiveDices, flagsUsedDices;
    private boolean flagPause, flagDragon;

    public ModelGame(){
        dice = new DadoGeek();

        caras = new String[10];
        activeDices = new String [7];
        inactiveDices = new String [7];

        stateToString = "";

        flagPause = true;
        flagDragon = false;
        flag = 0;
        flagsInactiveDices = new int[10];
        flagsUsedDices = new int[10];

        accumulatedPoints = 0;
        point = 0;
        round = 0;

    }

    /**
     *  Esta función determina si la ronda llego a su fin dependiendo de los dados que queden en el array de
     *  activeDices, y de acuerdo a ello, hace el calculo de puntos de la ronda o resetea los puntos acumulados
     * @return
     */
    public boolean getStateGame(){
        countDragons = RepeatStringInArray.repeatCount(activeDices,"Dragon");
        countPoints = RepeatStringInArray.repeatCount(activeDices,"Point");
        countSuperHero = RepeatStringInArray.repeatCount(activeDices,"SuperHero");
        countHeart = RepeatStringInArray.repeatCount(activeDices,"Heart");
        boolean status = false;

        if (flag == 0){
            if ((countDragons > 0 && (countPoints + countDragons) == activeDices.length) || countDragons == activeDices.length ){
                flagDragon = true;
                status = true;
            } else if ((countPoints == activeDices.length)||(countHeart > 0 && countHeart + countPoints == activeDices.length && inactiveDices.length == 0)) {
                point = (countPoints * (countPoints + 1)) / 2;
                status = true;
            } else if (activeDices.length==1 && (activeDices[0]=="Meeple" || activeDices[0]=="Rocket" )) {
                point = 0;
                status = true;
            } else if ((countSuperHero == activeDices.length) || (countHeart == activeDices.length && inactiveDices.length == 0)) {
                point = 0;
                status = true;
            } else if (activeDices.length==0) {
                point = 0;
                status = true;
            } else if(countDragons > 0 && countHeart > 0 && countDragons + countHeart + countPoints == activeDices.length && inactiveDices.length == 0){
                flagDragon = true;
                status = true;
            }
        }
        return status;
    }

    public void setScore(){
        if (flagDragon == true){
            accumulatedPoints = 0;
            flagDragon = false;
        }else{
            accumulatedPoints += point;
        }
    }

    public int getAcumPoints(){
        return accumulatedPoints;
    }


    /**
     * Determina si el jugador ganó o perdió dependiendo de los puntos obtenidos o las rondas jugadas
     * @return
     */
    public int getStatusRound (){
        return round;
    }

    public void incRound(){
        round++;
    }
    public String validateGame(){
        if(accumulatedPoints >=  30){
            return "Haz ganado, obtuviste 30 puntos o más";
        }else{
            return "Haz perdido, no obtuviste los puntos necesarios";
        }
    }

    /**
     * Resetea el modelGame a sus valores iniciales
     */

    public void resetGame(){
        accumulatedPoints = 0;
        point = 0;
        round = 0;
        flagPause = true;
        flagDragon = false;
        flag = 0;
    }


    public void newLaunch(){
        caras[0] = dice.getCara();
        caras[1] = dice.getCara();
        caras[2] = dice.getCara();
        caras[3] = dice.getCara();
        caras[4] = dice.getCara();
        caras[5] = dice.getCara();
        caras[6] = dice.getCara();
        caras[7] = dice.getCara();
        caras[8] = dice.getCara();
        caras[9] = dice.getCara();
        activeDices = Arrays.copyOfRange(caras,0,7);
        inactiveDices = Arrays.copyOfRange(caras,7,10);
        flagsUsedDices = new int[]{0,0,0,0,0,0,0,0,0,0};
        flagsInactiveDices = new int[]{1,1,1,1,1,1,1,0,0,0};
    }

    public String[] getFaces(){
        return caras;
    }

    public String getStateToString(String dice){
        switch (dice){
            case "Meeple":  stateToString = "Selecciona otro de los dados activos para lanzarlo nuevamente";
                            break;
            case "Heart":   stateToString = "Selecciona uno de los dados inactivos para lanzarlo y agregarlo a los dados activos";
                            break;
            case "Rocket":  stateToString = "Selecciona otro de los dados activos para mandarlo a la zona de dados inactivos";
                            break;
            case "SuperHero": stateToString = "Selecciona otro de los dados activos para girarlo a su cara contaria";
                              break;
        }
        return stateToString;
    }


    public void activeToUsed (String diceGame){

        activeDices = DeleteStringOfArray.delete(activeDices,diceGame);
        switch (diceGame){
            case "Meeple":
                flag = 1;
                break;
            case "Heart":
                flag = 2;
                break;
            case "Rocket":
                flag = 3;
                break;
            case "SuperHero":
                flag = 4;
                break;
        }
    }

    public String meeplePower(String diceGame){
        String newFace = dice.getCara();
        activeDices = ChangeStringOfArray.changeString(activeDices,diceGame,newFace);
        flag = 0;
        return newFace;
    }

    public String heartPower(String diceGame){
        String newFace = dice.getCara();
        activeDices = PushStringToArray.pushString(activeDices,newFace);
        inactiveDices = DeleteStringOfArray.delete(inactiveDices,diceGame);
        flag = 0;
        return newFace;
    }

    public void rocketPower(String diceGame){
        activeDices = DeleteStringOfArray.delete(activeDices,diceGame);
        inactiveDices = PushStringToArray.pushString(inactiveDices,diceGame);
        flag = 0;
    }

    public String superHeroPower (String diceGame){
        String result = "";
        switch (diceGame){
            case "Meeple":
                result = "Rocket";
                break;
            case "Heart":
                result = "Point";
                break;
            case "Rocket":
                result = "Meeple";
                break;
            case "Point":
                result = "Heart";
                break;
            case "Dragon":
                result = "SuperHero";
                break;
            case "SuperHero":
                result = "No se puede girar otro superHero, escoge otro dado";
                break;
        }
        if (result.length() < 10){
            activeDices = ChangeStringOfArray.changeString(activeDices,diceGame,result);
            flag = 0;
        }

        return result;
    }

    public int getFlag(){
        return flag;
    }



    public int[] getFlagsInactiveDices(){
        return flagsInactiveDices;
    }

    public void setFlagsInactiveDices(int inactiveDice){
        if (flagsInactiveDices[inactiveDice]==0){
            flagsInactiveDices[inactiveDice] = 1;
        }else{
            flagsInactiveDices[inactiveDice] = 0;
        }

    }

    public int[] getFlagsUsedDices(){
        return flagsUsedDices;
    }

    public void setFlagsUsedDices(int activeDice){
        flagsUsedDices[activeDice]=1;
    }

    public boolean getFlagPause (){
        return flagPause;
    }
    public void setFlagPause (){
        if(flagPause){
            flagPause = false;
        }else{
            flagPause = true;
        }
    }

    public String[] getArrayActiveDices(){
        return activeDices;
    }
}


