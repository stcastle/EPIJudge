package epi

import epi.test_framework.EpiTest
import epi.test_framework.GenericTest

object CountBits {

    @EpiTest(testDataFile = "count_bits.tsv")
    @JvmStatic
    fun countBits(x: Int): Short {
        // TODO - you fill in here.
        return 0
    }

    @JvmStatic
    fun main(args: Array<String>) {
        System.out.println("hey")
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "CountBits.kt",
                                object : Any() {

                                }.javaClass.enclosingClass)
                        .ordinal)
    }
}