package geekMasterGame;

import java.util.Arrays;

public class ModelGame {
    private DadoGeek dice;
    private int launch, point, state, flag;
    private String stateToString;
    private String[] caras, activeDices, inactiveDices;
    private int[] flagsInactiveDices;

    public ModelGame(){
        dice = new DadoGeek();
        caras = new String[10];
        activeDices = new String [7];
        inactiveDices = new String [7];
        stateToString = "";
        flag = 0;
        flagsInactiveDices = new int[]{0,0,0};
        
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
        switch (inactiveDice){
            case 0:
                 flagsInactiveDices[0]= 1;
                break;
            case 1:
                flagsInactiveDices[1]= 1;
                break;
            case 2:
                flagsInactiveDices[2] = 1;
                break;
        }
    }


}


