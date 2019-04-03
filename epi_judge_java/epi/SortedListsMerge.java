package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SortedListsMerge {
  @EpiTest(testDataFile = "sorted_lists_merge.tsv")
  //@include
  public static ListNode<Integer> mergeTwoSortedLists(ListNode<Integer> L1,
                                                      ListNode<Integer> L2) {

    // Book solution
    ListNode<Integer> dummyHead = new ListNode<>(0, null);
    ListNode<Integer> curr = dummyHead;

    while (L1 != null && L2 != null) {
      if (L1.data <= L2.data) {
        curr.next = L1;
        L1 = L1.next;
      } else {
        curr.next = L2;
        L2 = L2.next;
      }
      curr = curr.next;
    }

    curr.next = (L1 != null) ? L1 : L2;

    return dummyHead.next;
    /*
    ListNode<Integer> sortedHead = new ListNode<>(0, null);

    // Check for null input
    if (L1 == null) return L2;
    if (L2 == null) return L1;

    if (L1.data <= L2.data) {
      sortedHead.data = L1.data;
      L1 = L1.next;
    } else {
      sortedHead.data = L2.data;
      L2 = L2.next;
    }

    ListNode<Integer> curr = sortedHead;


    // Merge two sorted lists
    // Assume neither is null straight away
    while (L1 != null || L2 != null) {
      curr.next = new ListNode<>(0, null);
      curr = curr.next;
      if (L1 == null) {
        // copy data from L2 if L1 is null
        curr.data = L2.data;
        L2 = L2.next;
      } else if (L2 == null || L1.data <= L2.data) {
        curr.data = L1.data;
        L1 = L1.next;
      } else { // L2.data > L1.data
        curr.data = L2.data;
        L2 = L2.next;
      }
    }
    // Get the first element
    return sortedHead;
  */
  }



  // sortedHead 1 -> 2 -> 3 -> 4 -> 5 -> null
  // curr: 5 -> null
  // L1:
  // L2:

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SortedListsMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
