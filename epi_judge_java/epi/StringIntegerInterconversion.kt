package epi

import epi.test_framework.EpiTest
import epi.test_framework.GenericTest
import epi.test_framework.TestFailure
import kotlin.math.absoluteValue

object StringIntegerInterconversion {

    val charToDigitMap = hashMapOf<Char, Int>(
            '0' to 0,
            '1' to 1,
            '2' to 2,
            '3' to 3,
            '4' to 4,
            '5' to 5,
            '6' to 6,
            '7' to 7,
            '8' to 8,
            '9' to 9
    )

    fun mapDigitToChar(x: Int): Char = when(x) {
        0 -> '0'
        1 -> '1'
        2 -> '2'
        3 -> '3'
        4 -> '4'
        5 -> '5'
        6 -> '6'
        7 -> '7'
        8 -> '8'
        9 -> '9'
        else -> throw Exception("Int $x is not a single digit!")
    }

    fun mapCharToDigit(x: Char): Int {
        return charToDigitMap[x] ?: 0
    }

    fun stringToInt(s: String): Int {
        // TODO: Validate string input that it corresponds to a valid Int

        // If current char is not a digit, i.e. it is "," or ".", skip to next char.

        // Base case
        if (s.length <= 0) {
            return 0
        } else if (s.length == 1) {
            if (s[0] == '-' || s[0] == '+') {
                return 0
            } else {
                return mapCharToDigit(s[0])
            }
        } else {
            var multiplier = 1
            if (s[0] == '-') {
                multiplier = -1
            }
            return multiplier * mapCharToDigit(s[s.length-1]) +
                    10 * stringToInt(s.substring(0, s.length-1))
        }
    }

    fun intToString(x: Int): String {

        val tens = x / 10
        val onesDigit = (x % 10).absoluteValue

        if (tens == 0) {
            var prefix = ""
            if (x < 0) prefix = "-"
            return prefix + mapDigitToChar(onesDigit)
        } else {
            return intToString(tens) + mapDigitToChar(onesDigit)
        }

    }

    @EpiTest(testDataFile = "string_integer_interconversion.tsv")
    @Throws(TestFailure::class)
    @JvmStatic
    fun wrapper(x: Int, s: String) {
        if (intToString(x) != s) {
            throw TestFailure("Int to string conversion failed")
        }
        if (stringToInt(s) != x) {
            throw TestFailure("String to int conversion failed")
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "StringIntegerInterconversion.kt",
                                object : Any() {

                                }.javaClass.enclosingClass)
                        .ordinal)
    }
}
