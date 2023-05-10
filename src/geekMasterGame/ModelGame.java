package geekMasterGame;

public class ModelGame {
    private DadoGeek dice1, dice2, dice3, dice4, dice5, dice6, dice7, dice8, dice9, dice10;
    private int launch, point, state, flag;
    private String stateToString;
    private String[] caras, activeDices, usedDices, inactiveDices;

    public ModelGame(){
        dice1 = new DadoGeek();
        dice2 = new DadoGeek();
        dice3 = new DadoGeek();
        dice4 = new DadoGeek();
        dice5 = new DadoGeek();
        dice6 = new DadoGeek();
        dice7 = new DadoGeek();
        dice8 = new DadoGeek();
        dice9 = new DadoGeek();
        dice10 = new DadoGeek();
        caras = new String[7];
        stateToString = "";
        flag = 0;
    }

    public void getActiveDices(){
        caras[0] = dice1.getCara();
        caras[1] = dice2.getCara();
        caras[2] = dice3.getCara();
        caras[3] = dice4.getCara();
        caras[4] = dice5.getCara();
        caras[5] = dice6.getCara();
        caras[6] = dice7.getCara();
        activeDices = caras;
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

    public void determineGame (String dice){
        if (dice == "Meeple") {
            activeDices = DeleteStringOfArray.delete(activeDices,"Meeple");
            flag = 1;
        }

    }

    public int getFlag(){
        return flag;
    }

}


