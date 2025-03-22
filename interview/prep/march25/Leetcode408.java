/*
 * 408. Valid Word Abbreviation
 * A string can be abbreviated by replacing any number of non-adjacent, non-empty substrings with their lengths. 
 * The lengths should not have leading zeros.
 * For example, a string such as "substitution" could be abbreviated as (but not limited to):
   ** "s10n" ("s ubstitutio n")
   ** "sub4u4" ("sub stit u tion")
   ** "12" ("substitution")
   ** "su3i1u2on" ("su bst i t u ti on")
   ** "substitution" (no substrings replaced)
 * The following are not valid abbreviations:
   ** "s55n" ("s ubsti tutio n", the replaced substrings are adjacent)
   ** "s010n" (has leading zeros)
   ** "s0ubstitution" (replaces an empty substring)
 * Given a string word and an abbreviation abbr, return whether the string matches the given abbreviation.
 * A substring is a contiguous non-empty sequence of characters within a string.
 */
class Leetcode408 {
    public boolean validWordAbbreviation(String word, String abbr) {
        int wordLength = word.length();
        int abbrLength = abbr.length();

        // abbr len can't be greater than word len
        if (abbrLength > wordLength) {
            return false;
        }

        // ptrs for traversing the word and the abbr
        int wordPointer = 0;
        int abbrPointer = 0;

        while (wordPointer < wordLength && abbrPointer < abbrLength) {
            char wordChar = word.charAt(wordPointer);
            char abbrChar = abbr.charAt(abbrPointer);

            // move fwd if both are equal
            if (wordChar == abbrChar) {
                wordPointer++;
                abbrPointer++;
                continue;
            }

            // don't allow leading 0s or mismatched char
            if (abbrChar == '0' || !Character.isDigit(abbrChar)) {
                return false;
            }

            // find num by which word ptr has to be incremented
            int num = 0;
            while (abbrPointer < abbrLength && Character.isDigit(abbr.charAt(abbrPointer))) {
                num = 10 * num + (abbr.charAt(abbrPointer) - '0');
                abbrPointer++;
            }

            wordPointer += num;
        }
        // at the end, the pointers should have been incremented to match length and the
        // chars should have matched
        return wordPointer == wordLength && abbrPointer == abbrLength;
    }
}
