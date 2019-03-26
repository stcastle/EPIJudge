package epi

import epi.test_framework.EpiTest
import epi.test_framework.GenericTest

object Parity {
    @EpiTest(testDataFile = "parity.tsv")
    @JvmStatic
    fun parity(x: Long): Short {
        var y = x
        y = y xor (y ushr 32)
        y = y xor (y ushr 16)
        y = y xor (y ushr 8)
        y = y xor (y ushr 4)
        y = y xor (y ushr 2)
        y = y xor (y ushr 1)
        return (y and 0x1).toShort()
    }

    @JvmStatic
    fun main(args: Array<String>) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "Parity.kt",
                                object : Any() {

                                }.javaClass.enclosingClass)
                        .ordinal)
    }
}
