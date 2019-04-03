package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class EvaluateRpn {
  @EpiTest(testDataFile = "evaluate_rpn.tsv")

  public static int eval(String expression) {
    // TODO - you fill in here.
    // validate input

      // Stack vs ArrayDeque for a stack implementation?
    Deque<Integer> deque = new ArrayDeque<>();
    for (String s : expression.split(",")) {
      if (isOperator(s)) {
        // do the operation with the top two on the stack
        int op2 = deque.removeFirst();
        int op1 = deque.removeFirst();
        int res;
        switch (s) {
          case "X":
          case "x":
            res = op1 * op2;
            break;
          case "+":
            res = op1 + op2;
            break;
          case "-":
            res = op1 - op2;
            break;
          case "/":
            res = op1 / op2;
            break;
          default:
            res = 0;
        }
        deque.addFirst(res);
      } else {
        deque.addFirst(Integer.valueOf(s));
      }
    }

    return deque.removeFirst();
  }

  // 1, 2, +, 2, 5, +, *
  // deque: 21
  // op1:
  // op2:


  public static boolean isOperator(String s) {
    return (s.equals("X") || s.equals("x") || s.equals("+") || s.equals("-") || s.equals("/"));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "EvaluateRpn.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
