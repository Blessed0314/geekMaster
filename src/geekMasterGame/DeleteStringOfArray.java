package geekMasterGame;
import java.util.Arrays;
public class DeleteStringOfArray {
    public static String[] delete(String[] arr, String str) {
        boolean found = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(str)) {
                arr[i] = null;
                found = true;
                break;
            }
        }
        if (found) {
            String[] newArr = new String[arr.length - 1];
            int j = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] != null) {
                    newArr[j++] = arr[i];
                }
            }
            return newArr;
        }
        return arr;
    }
}