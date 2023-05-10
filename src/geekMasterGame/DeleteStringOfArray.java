package geekMasterGame;
import java.util.Arrays;
public class DeleteStringOfArray {
    public static String[] delete(String[] arr, String str) {
        // Busca el índice del primer elemento que coincida con el string
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(str)) {
                index = i;
                break;
            }
        }

        // Si se encuentra el elemento, borra el elemento y devuelve el nuevo arreglo
        if (index != -1) {
            String[] newArr = new String[arr.length - 1];
            for (int i = 0, j = 0; i < arr.length; i++) {
                if (i != index) {
                    newArr[j++] = arr[i];
                }
            }
            return newArr;
        }

        // Si no se encuentra el elemento, devuelve el arreglo original
        return arr;
    }
}