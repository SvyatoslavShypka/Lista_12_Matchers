import java.util.ArrayList;
import java.util.List;

public class KMPMatcher implements IStringMatcher {
    private String pattern;
    private int[] lps;

    @Override
    public List<Integer> validShifts(String textToSearch, String patternToFind) {
        // TODO: Zaimplementuj algorytm dopasowywania napisów Knutha-Morrisa-Pratta
        pattern = patternToFind;
        lps = computeLPSArray(pattern);
        List<Integer> matches = new ArrayList<>();

        int n = textToSearch.length();
        int m = pattern.length();
        int i = 0; // indeks w tekście
        int j = 0; // indeks w wzorcu

        while (i < n) {
            if (textToSearch.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                if (j == m) {
                    matches.add(i - m);
                    j = lps[j - 1];
                }
            } else {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }

        return matches;
    }
    private int[] computeLPSArray(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        int len = 0; // długość najdłuższego prefiksu-sufiksu (Longest Proper Prefix which is also Suffix)

        int i = 1;
        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }
}
