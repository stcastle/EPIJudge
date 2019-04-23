package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
public class TreeLevelOrder {
  @EpiTest(testDataFile = "tree_level_order.tsv")

  public static List<List<Integer>>
  binaryTreeDepthOrder(BinaryTreeNode<Integer> tree) {
//    return binaryTreeDepthOrderRecursive(tree);
    return binaryTreeDepthOrderTwoQueues(tree);
  }

  private static List<List<Integer>> binaryTreeDepthOrderTwoQueues(BinaryTreeNode<Integer> tree) {
    ArrayDeque<BinaryTreeNode<Integer>> q = new ArrayDeque<>();
    ArrayDeque<Integer> depths = new ArrayDeque<>();
    if (tree != null) {
      q.addLast(tree);
      depths.push(0);
    }
    List<List<Integer>> res = new ArrayList<>();
    // can keep track in a s
    while (!q.isEmpty()) {
      BinaryTreeNode<Integer> curr = q.poll();
      int depth = depths.poll();
      while (res.size() < depth + 1) {
        res.add(new LinkedList<>());
      }
      List<Integer> list = res.get(depth);
      list.add(curr.data);
      if (curr.left != null) {
        q.addLast(curr.left);
        depths.addLast(depth + 1);
      }
      if (curr.right != null) {
        q.addLast(curr.right);
        depths.addLast(depth + 1);
      }
    }
    return res;
  }

  private static List<List<Integer>> binaryTreeDepthOrderRecursive(BinaryTreeNode<Integer> tree) {
    List<List<Integer>> res = new ArrayList<>();
    recursiveHelper(tree, 0, res);
    return res;
  }

  private static void recursiveHelper(BinaryTreeNode<Integer> tree, int depth, List<List<Integer>> res) {
    if (tree != null) {
      while (res.size() < depth + 1) {
        res.add(new LinkedList<>());
      }
      List<Integer> list = res.get(depth);
      list.add(tree.data);
      recursiveHelper(tree.left, depth + 1, res);
      recursiveHelper(tree.right, depth + 1, res);
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeLevelOrder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
