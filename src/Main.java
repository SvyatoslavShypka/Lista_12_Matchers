import java.util.List;

public class Main {
    public static void main(String[] args) {
        String text = "ABCDEFGHIJ";
        String pattern = "ABC";

/*
        AutomatonMatcher automaton = new AutomatonMatcher();
        List<Integer> matches = automaton.validShifts(text, pattern);
*/
        KMPMatcher kmpMatcher = new KMPMatcher();
        List<Integer> matches = kmpMatcher.validShifts(text, pattern);

        if (matches.isEmpty()) {
            System.out.println("No matches found.");
        } else {
            System.out.println("Matches found at positions: " + matches);
        }
    }
}
