package geekMasterGame;

public class ChangeStringOfArray {

    public static String[] changeString(String[] array, String oldString, String newString) {
        boolean found = false;  // Variable para rastrear si se ha encontrado una coincidencia

        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(oldString) && !found) {
                array[i] = newString;
                found = true;  // Se ha encontrado una coincidencia, se marca como encontrada
            }
        }

        return array;
    }
}