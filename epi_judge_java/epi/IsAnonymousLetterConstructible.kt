package epi

import epi.test_framework.EpiTest
import epi.test_framework.GenericTest

object IsAnonymousLetterConstructible {
    @EpiTest(testDataFile = "is_anonymous_letter_constructible.tsv")
    @JvmStatic
    fun isLetterConstructibleFromMagazine(letterText: String,
                                          magazineText: String): Boolean {
        // Create a hash table from each char in the letter text to the
        // number of times that char appears in the text
        val charsInLetter = HashMap<Char, Int>()
        letterText.forEach { c ->
            if (!charsInLetter.contains(c)) {
                charsInLetter[c] = 1
            } else {
                val prev = charsInLetter[c] ?: 0
                charsInLetter[c] = prev + 1
            }
        }

        // Iterate through chars in magazine, and return true if all
        // chars from letter have been detected
        magazineText.forEach { c ->
            if (charsInLetter.contains(c)) {
                // Decrement the num times in the letter, and remove
                // the key if all uses of c are gone
                val prev = charsInLetter[c] ?: 0
                val updated = prev - 1
                charsInLetter[c] = updated
                if (updated <= 0) {
                    charsInLetter.remove(c)
                    if (charsInLetter.isEmpty()) {
                        return true
                    }
                }
            }
        }

        // Now we have checked the entire magazine. Return false if
        // there are still some chars from letter text which weren't
        // found
        return charsInLetter.isEmpty()
    }

    @JvmStatic
    fun main(args: Array<String>) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsAnonymousLetterConstructible.kt",
                                object : Any() {

                                }.javaClass.enclosingClass)
                        .ordinal)
    }
}
