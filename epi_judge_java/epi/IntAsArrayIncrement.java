package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class IntAsArrayIncrement {
  @EpiTest(testDataFile = "int_as_array_increment.tsv")
  public static List<Integer> plusOne(List<Integer> A) {
      int carry = 1; // start as 1 to increment
      for (int i = A.size()-1; i >= 0 && carry == 1; i--) {
          int updated = carry + A.get(i);
          A.set(i, updated % 10);
          carry = updated >= 10 ? 1 : 0;
      }
      // made it to the end and if carry > 0 then add a 1 at start.
      if (carry == 1) {
          A.add(0, 1);
      }
      return A;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsArrayIncrement.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
