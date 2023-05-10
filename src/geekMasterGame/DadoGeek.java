package geekMasterGame;

import java.util.Random;
/**
 * Class DadoGeek generate a Random value between 1 and 6 and depending on the value the function is assigned
 * @author Christian Villegas
 * version v.1.0.0 date 02/05/2023
 */
public class DadoGeek {
    private String cara;
    private int temp;

    /**
     * Method that generate an random value to cara
     * @return number between (1,6)
     */

    public String getCara() {
        Random aleatorio = new Random();
        temp = aleatorio.nextInt(6)+1;
        switch (temp){
            case 1: cara = "Meeple";
                    break;
            case 2: cara = "Dragon";
                    break;
            case 3: cara = "Heart";
                    break;
            case 4: cara = "Point";
                    break;
            case 5: cara = "SuperHero";
                    break;
            case 6: cara = "Rocket";
                    break;
        }
        return cara;
    }
}
