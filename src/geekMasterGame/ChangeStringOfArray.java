package geekMasterGame;

public class ChangeStringOfArray {

    public static String[] changeString(String[] array, String oldString, String newString) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(oldString)) {
                array[i] = newString;
                break;
            }
        }
        return array;
    }
}