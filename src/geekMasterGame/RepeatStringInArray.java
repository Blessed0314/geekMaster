package geekMasterGame;
public class RepeatStringInArray {

    public static int repeatCount(String[] arr, String str) {
        int count = 0;
        for (String s : arr) {
            if (s.equals(str)) {
                count++;
            }
        }
        return count;
    }
}