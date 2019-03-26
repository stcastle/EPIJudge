package epi

import epi.test_framework.EpiTest
import epi.test_framework.GenericTest

object CountBits {

    @EpiTest(testDataFile = "count_bits.tsv")
    @JvmStatic
    fun countBits(x: Int): Short {
        var numBits: Int = 0
        var y = x
        while (y != 0) {
            numBits += y and 1
            y = y ushr 1
        }
        return numBits.toShort()
    }

    @JvmStatic
    fun main(args: Array<String>) {
        System.out.println("hey")
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "CountBits.kt",
                                object {}.javaClass.enclosingClass)
                        .ordinal)
    }
}