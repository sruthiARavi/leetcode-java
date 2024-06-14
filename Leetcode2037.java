/*
 * Minimum Number of Moves to Seat Everyone
 * There are n seats and n students in a room. You are given an array seats of length n, where seats[i] is the position of the ith seat. 
 * You are also given the array students of length n, where students[j] is the position of the jth student.
 * You may perform the following move any number of times:
 * * Increase or decrease the position of the ith student by 1 (i.e., moving the ith student from position x to x + 1 or x - 1)
 * Return the minimum number of moves required to move each student to a seat such that no two students are in the same seat.
 * Note that there may be multiple seats or students in the same position at the beginning.
 */

class Leetcode2037 {
    public int minMovesToSeat(int[] seats, int[] students) {
        // Counting sort approach
        int result = 0;

        int maxSeats = findMaxNum(seats);
        int maxStudents = findMaxNum(students);
        int maxPosition = maxSeats > maxStudents ? maxSeats : maxStudents;

        int[] differences = new int[maxPosition];

        for (int position : seats) {
            differences[position - 1]++; // positive integer
        }

        for (int position : students) {
            differences[position - 1]--; // negative integer
        }

        int moves = 0, unmatched = 0;
        for (int difference : differences) {
            moves += Math.abs(unmatched);
            unmatched += difference;
        }

        return moves;

        // Greedy approach (sorting)
        /*
         * int result = 0;
         * 
         * Arrays.sort(seats);
         * Arrays.sort(students);
         * 
         * for (int i = 0; i < seats.length; i++) {
         * result += Math.abs(seats[i] - students[i]);
         * }
         * 
         * return result;
         */
    }

    private int findMaxNum(int[] arr) {
        int maxNum = 0;
        for (int value : arr) {
            maxNum = maxNum > value ? maxNum : value;
        }
        return maxNum;
    }
}
