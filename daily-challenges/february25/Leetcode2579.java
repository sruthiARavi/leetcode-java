/*
 * https://leetcode.com/problems/count-total-number-of-colored-cells/description/?envType=daily-question&envId=2025-03-05
 * 2579. Count Total Number of Colored Cells
 * There exists an infinitely large two-dimensional grid of uncolored unit cells. 
 * You are given a positive integer n, indicating that you must do the following routine for n minutes:
   ** At the first minute, color any arbitrary unit cell blue.
   ** Every minute thereafter, color blue every uncolored cell that touches a blue cell.
 * Below is a pictorial representation of the state of the grid after minutes 1, 2, and 3.
 */
class Leetcode2579 {
    public long coloredCells(int n) {
        // 1, 4, 8, 12,.... - multiples of 4
        long numBlueCells = 1; // n is atleast 1 based on inout constraints
        long addend = 4;

        while (--n > 0) {
            numBlueCells += addend;
            addend += 4;
        }

        return numBlueCells;
    }
}
