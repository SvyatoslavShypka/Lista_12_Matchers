import java.util.ArrayList;
import java.util.List;

public class AutomatonMatcher implements IStringMatcher {
    private final int alphabetSize = 256;
    private int[][] transitionTable;
    private String pattern;

    @Override
    public List<Integer> validShifts(String textToSearch, String patternToFind) {
        pattern = patternToFind;
        transitionTable = buildTransitionTable();
        // TODO: Zaimplementuj algorytm dopasowywania napisów oparty na automacie skończonym
        List<Integer> matches = new ArrayList<>();

        int n = textToSearch.length();
        int currentState = 0;

        for (int i = 0; i < n; i++) {
            currentState = transitionTable[currentState][textToSearch.charAt(i)];

            if (currentState == pattern.length()) {
                matches.add(i - pattern.length() + 1);
            }
        }

        return matches;
    }

    private int[][] buildTransitionTable() {
        int m = pattern.length();
        int[][] table = new int[m + 1][alphabetSize];

        for (int state = 0; state <= m; state++) {
            for (int ch = 0; ch < alphabetSize; ch++) {
                int nextState = computeNextState(pattern, m, state, ch);
                table[state][ch] = nextState;
            }
        }

        return table;
    }

    private int computeNextState(String pattern, int m, int state, int ch) {
        if (state < m && ch == pattern.charAt(state)) {
            return state + 1;
        }

        for (int nextState = state; nextState > 0; nextState--) {
            if (pattern.charAt(nextState - 1) == ch) {
                int i;
                for (i = 0; i < nextState - 1; i++) {
                    if (pattern.charAt(i) != pattern.charAt(state - nextState + 1 + i)) {
                        break;
                    }
                }
                if (i == nextState - 1) {
                    return nextState;
                }
            }
        }
        return 0;
    }
}
