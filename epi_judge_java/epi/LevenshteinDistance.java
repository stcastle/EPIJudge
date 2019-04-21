package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class LevenshteinDistance {
  @EpiTest(testDataFile = "levenshtein_distance.tsv")

  public static int levenshteinDistance(String A, String B) {
    // Create the distances array and use levenshteinHelper.
    int[][] d = new int[A.length()][B.length()];
    for (int i=0; i<A.length(); i++) {
      for (int j=0; j<B.length(); j++) {
        d[i][j] = -1;
      }
    }
    return levenshteinHelper(A, B, d);
  }

  public static int levenshteinHelper(String A, String B, int[][] distances) {
    int a = A.length();
    int b = B.length();
    // Base Case, where one length is zero.
    if (a == 0) {
      return b;
    }
    if (b == 0) {
      return a;
    }
    // Base case, where we already know the Levenshtein Distance.
    if (distances[a-1][b-1] != -1) {
      return distances[a-1][b-1];
    }

    int res;
    if (A.charAt(a-1) == B.charAt(b-1)) {
      res = levenshteinHelper(A.substring(0, a-1), B.substring(0, b-1), distances);
    } else {
      res = 1 + Math.min(
              levenshteinHelper(A.substring(0, a-1), B.substring(0, b-1), distances),
              Math.min(
                      levenshteinHelper(A.substring(0, a), B.substring(0, b-1), distances),
                      levenshteinHelper(A.substring(0, a-1), B.substring(0, b), distances)
              )
      );
    }

    distances[a-1][b-1] = res;
    return res;
  }


  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LevenshteinDistance.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
