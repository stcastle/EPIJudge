package epi

import epi.test_framework.EpiTest
import epi.test_framework.GenericTest
import kotlin.math.abs
import kotlin.math.max

data class BalanceStatusWithHeight(val isBalanced: Boolean, val height: Int)

object IsTreeBalanced {

    @EpiTest(testDataFile = "is_tree_balanced.tsv")
    @JvmStatic
    fun isBalanced(tree: BinaryTreeNode<Int>?): Boolean {
        return checkBalance(tree).isBalanced
    }

    fun checkBalance(tree: BinaryTreeNode<Int>?): BalanceStatusWithHeight {
        if (tree == null) {
            return BalanceStatusWithHeight(true, -1) // double check this height value
        }

        val leftResult = checkBalance(tree.left)
        if (!leftResult.isBalanced) {
            return leftResult
        }

        val rightResult = checkBalance(tree.right)
        if (!rightResult.isBalanced) {
            return rightResult
        }

        val height = 1 + max(leftResult.height, rightResult.height)
        val isBalanced = abs(leftResult.height - rightResult.height) <= 1
        return BalanceStatusWithHeight(isBalanced, height)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsTreeBalanced.java",
                                object : Any() {

                                }.javaClass.enclosingClass)
                        .ordinal)
    }
}
