package geekMasterGame;
import java.util.Arrays;

public class PushStringToArray {

    public static String[] pushString(String[] array, String stringToAdd) {
        array = Arrays.copyOf(array, array.length + 1);
        array[array.length - 1] = stringToAdd;
        return array;
    }
}