package geekMasterGame;
/**
 * El modelo de Geek Master, consiste en jugar todos los dados activos y conseguir la mayor cantidad de dados
 * 42 al final, el jugador tiene 5 rondas para obtener 30 puntos o más, si no lo consigue perderá.
 * Por 1 dado 42 => 1 punto
 * Por 2 dados 42 => 3 puntos
 * Por 3 dados 42 => 6 puntos
 * Por 4 dados 42 => 10 puntos
 * Por 5 dados 42 => 15 puntos
 * Por 6 dados 42 => 21 puntos
 * Por 7 dados 42 => 28 puntos
 * Por 8 dados 42 => 36 puntos
 * Por 9 dados 42 => 45 puntos
 * Por 10 dados 42 => 55 puntos
 */

import java.util.Arrays;

public class ModelGame {
    private DadoGeek dice;
    private int point, flag, accumulatedPoints, round, countDragons, countPoints, countSuperHero, countHeart;
    private String stateToString, stateGameText, activedDice;
    private String[] caras, activeDices, inactiveDices;
    private int[] flagsInactiveDices, flagsUsedDices;
    private boolean flagPause, flagDragon;

    public ModelGame(){
        dice = new DadoGeek();

        caras = new String[10];
        activeDices = new String [7];
        inactiveDices = new String [7];

        stateToString = "";
        stateGameText = "";
        activedDice = "";

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
     * Se lanzan los 10 dados y se establecen los 4 arrays que controlaran la partida,
     * activeDices => Contendrá los dados de la zona activa
     * inactiveDices => Contendrá los dados de la zona inactiva
     * flagsUsedDices => Es un array de diez posiciones una por cada dado, que estara en 1 si esta en la zona de usados
     *                   y 0 si no lo está. Al estar en 1 se deshabilitará y no se podrá interactuar con el.
     * flagsInactiveDices => Es un array de diez posiciones, una por cada dado, que estará en 1 si está en la zona de
     *                   dados inactivos y 0 si esta en la zona de dados inactivos, esto con el fin de habilitarlos o
     *                   inhabilitarlos según el poder activo.
     * @return
     */
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

    /**
     * Función para llevar el array que contiene las caras de los 10 dados a la interfaz GUI.
     * @return
     */
    public String[] getFaces(){
        return caras;
    }

    /**
     * Función que se ejecuta solo cuando la flag de control del juego es 0 y cambia esta flag dependiendo del dado
     * seleccionado (diceGame) en la interfaz GUI de ls siguiente forma:
     *  flag = 1 => poder activo Meeple
     *  flag = 2 => poder activo Heart
     *  flag = 3 => poder activo Rocket
     *  flag = 4 => poder activo SuperHero
     * El dado seleccionado es movido a la zona de usados y se inhabilitará.
     * @param diceGame
     */
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
        activedDice = diceGame;
    }

    /**
     * Esta función devuelve un string dependiendo del dado seleccionado, este será mostrado mediante un JoptionPane
     * para dar indicaciones al jugador
     * @param diceGame
     * @return
     */
    public String getStateToString(String diceGame){
        switch (diceGame){
            case "Meeple":  stateToString = "Selecciona otro de los dados activos para lanzarlo nuevamente";
                break;
            case "Heart":   stateToString = "Selecciona uno de los dados inactivos para lanzarlo y agregarlo \na los dados activos";
                break;
            case "Rocket":  stateToString = "Selecciona otro de los dados activos para mandarlo a la zona de \ndados inactivos";
                break;
            case "SuperHero": stateToString = "Selecciona otro de los dados activos para girarlo a su cara \ncontraria";
                break;
        }
        return stateToString;
    }


    /**
     *  Esta función determina si la ronda llego a su fin dependiendo de los dados que queden en el array de
     *  activeDices, y de acuerdo a ello, hace el calculo de puntos de la ronda o activara el flagDragon para
     *  resetear desde el GUI los puntos acumulados.
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
                point = 0;
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
                point = 0;
                flagDragon = true;
                status = true;
            }
        }
        return status;
    }

    /**
     * Esta función es para actualizar los puntos acumulados
     */
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
     * Esta función es para revisar en que ronda se encuentra la partida.
     * @return
     */
    public int getStatusRound (){
        return round;
    }

    /**
     * Esta función es para incrementar la ronda de juego
     */
    public void incRound(){
        round++;
    }

    /**
     * Determina si el jugador ganó o perdió dependiendo de los puntos obtenidos o las rondas jugadas
     * @return
     */
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

    /**
     * Estas funciones ejecutan la habilidad del dado activo de la siguiente forma:
     *  Meeple => Lanza otro de los dados activos y obtiene una nueva cara
     *  Heart => Se toma un dado de la zona de dados inactivos y se lanza, luego se coloca sonbre la zona de dados activos
     *  Rocket => Se lleva uno de los dados de la zona activa (que no sea el mismo) y se lleva a la zona de dados inactivos
     *  SuperHero => Gira un dado de la zona activa que no sea otro SuperHero a su cara contraria.
     *
     * Al finalizar cambia la flag de estado en 0 para permitir seleccionar un nuevo dado
     * @param diceGame
     * @return
     */
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

    /**
     * Verificar el estado de la flag que controla el modo de juego
     * @return
     */
    public int getFlag(){
        return flag;
    }

    /**
     * Retorna el array de dados Inactivos para verificar si el dado esta o no esta en esa zona
     * @return
     */
    public int[] getFlagsInactiveDices(){
        return flagsInactiveDices;
    }

    /**
     * Cambia de de la zona de dados inactivos a dados activos y viceversa del dado seleccionado en el array de dados
     * inactivos.
     * @param inactiveDice
     */
    public void setFlagsInactiveDices(int inactiveDice){
        if (flagsInactiveDices[inactiveDice]==0){
            flagsInactiveDices[inactiveDice] = 1;
        }else{
            flagsInactiveDices[inactiveDice] = 0;
        }
    }

    /**
     * Retorna el array que contiene los dados que fueron usados y los que aun estan en juego.
     * @return
     */
    public int[] getFlagsUsedDices(){
        return flagsUsedDices;
    }

    /**
     * Cambia el status del dado seleccionado cambiandolo de la zona de dados activos a la zona de usados.
     * @param activeDice
     */
    public void setFlagsUsedDices(int activeDice){
        flagsUsedDices[activeDice]=1;
    }

    /**
     * Retorna el status de la flag que controla si el juego esta en espera de que se realice la acción de uno de los dados
     * @return
     */
    public boolean getFlagPause (){
        return flagPause;
    }

    /**
     * Cambia el status de flagPause, estará en true si el juego está en espera de una acción pendiente de un dado y
     * estará en false si el juego está en espera de seleccionar uno de los dados para activar su acción.
     */
    public void setFlagPause (){
        if(flagPause){
            flagPause = false;
        }else{
            flagPause = true;
        }
    }

    /**
     * Retorna el array que contiene las caras de los dados en la zona de dados activos, se utilizó para debuggear el GUI
     * @return
     */
    public String[] getArrayActiveDices(){
        return activeDices;
    }

    public String getStatusText(){
        if ( flag == 0 && round < 5){
            stateGameText = "Actived Dice: on hold" +
                    "\nAction to perform: Seleccione un dado para jugar"  +
                    "\nRound: " + (round + 1) +
                    "\nAccumulated points: " + accumulatedPoints +
                    "\nPoints obtained in the previous round: " + point;
        }else if( round == 5){
            stateGameText = "Actived Dice: N/A" +
                    "\nAction to perform: Presione new Game para jugar nuevamente "  +
                    "\nRound: Juego finalizado"  +
                    "\nFinal score: " + accumulatedPoints +
                    "\nPoints obtained in the previous round: " + point;
        }else{
            stateGameText = "Actived Dice: " + activedDice +
                    "\nAction to perform:\n" + stateToString +
                    "\nRound: " + (round + 1) +
                    "\nAccumulated points: " + accumulatedPoints +
                    "\nPoints obtained in the previous round: " + point;
        }

        return stateGameText;

    }
}


