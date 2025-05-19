/*
 * 3024. Type of Triangle
 * You are given a 0-indexed integer array nums of size 3 which can form the sides of a triangle.
 * A triangle is called equilateral if it has all sides of equal length.
 * A triangle is called isosceles if it has exactly two sides of equal length.
 * A triangle is called scalene if all its sides are of different lengths.
 * Return a string representing the type of triangle that can be formed or "none" if it cannot form a triangle.
 * Constraints:
   ** nums.length == 3
   ** 1 <= nums[i] <= 100
 */
class Leetcode3042 {
    public String triangleType(int[] nums) {
        int a = nums[0], b = nums[1], c = nums[2];
        if ((a + b <= c) || (a + c <= b) || (b + c <= a)) { //check for triangle validity 
            return "none";
        }
        if (a == b && b == c) {
            return "equilateral";
        }
        if (a == b || b == c || a == c) {
            return "isosceles";
        }
        return "scalene";
    }
}
