package geekMasterGame;

import java.util.Arrays;

public class ModelGame {
    private DadoGeek dice;
    private int point, flag, accumulatedPoints;
    private String stateToString;
    private String[] caras, activeDices, inactiveDices;
    private int[] flagsInactiveDices, flagsUsedDices;
    private boolean flagPause;

    public ModelGame(){
        dice = new DadoGeek();

        caras = new String[10];
        activeDices = new String [7];
        inactiveDices = new String [7];

        stateToString = "";

        flagPause = true;
        flag = 0;
        flagsInactiveDices = new int[10];
        flagsUsedDices = new int[10];

        accumulatedPoints = 0;

    }

    public boolean getStateGame(){
        int countDragons = RepeatStringInArray.repeatCount(activeDices,"Dragon");
        int countPoints = RepeatStringInArray.repeatCount(activeDices,"Point");
        int countSuperHero = RepeatStringInArray.repeatCount(activeDices,"SuperHero");
        boolean status;

        if ((countPoints + countDragons) == activeDices.length || countDragons == activeDices.length){
            accumulatedPoints = 0;
            status = true;
        } else if (countPoints == activeDices.length) {
            point = (countPoints * (countPoints + 1)) / 2;
            accumulatedPoints += point;
            status = true;
        } else if (activeDices.length==1 && (activeDices[0]=="Meeple" || activeDices[0]=="Rocket" )) {
            point = 0;
            status = true;
        } else if (countSuperHero == activeDices.length) {
            point = 0;
            status = true;
        }else{
            status = false;
        }
        return status;
    }

    public void getActiveDices(){
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
        return newFace;
    }

    public String heartPower(String diceGame){
        String newFace = dice.getCara();
        activeDices = PushStringToArray.pushString(activeDices,newFace);
        inactiveDices = DeleteStringOfArray.delete(inactiveDices,diceGame);
        return newFace;
    }

    public void rocketPower(String diceGame){
        activeDices = DeleteStringOfArray.delete(activeDices,diceGame);
        inactiveDices = PushStringToArray.pushString(inactiveDices,diceGame);
    }

    public String superHeroPower (String diceGame){
        String result = "";
        switch (diceGame){
            case "Meeple":
                activeDices = ChangeStringOfArray.changeString(activeDices,diceGame,"Rocket");
                result = "Rocket";
                break;
            case "Heart":
                activeDices = ChangeStringOfArray.changeString(activeDices,diceGame,"Point");
                result = "Point";
                break;
            case "Rocket":
                activeDices = ChangeStringOfArray.changeString(activeDices,diceGame,"Meeple");
                result = "Meeple";
                break;
            case "Point":
                activeDices = ChangeStringOfArray.changeString(activeDices,diceGame,"Heart");
                result = "Heart";
                break;
            case "Dragon":
                activeDices = ChangeStringOfArray.changeString(activeDices,diceGame,"SuperHero");
                result = "SuperHero";
                break;
            case "SuperHero":
                result = "No se puede girar otro superHero, escoge otro dado";
                break;
        }
        return result;
    }

    public int getFlag(){
        return flag;
    }

    public int resetFlag(){
        return flag=0;
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
}


