/*
 * 339. Nested List Weight Sum
 * You are given a nested list of integers nestedList. 
 * Each element is either an integer or a list whose elements may also be integers or other lists.

 * The depth of an integer is the number of lists that it is inside of. 
 * For example, the nested list [1,[2,2],[[3],2],1] has each integer's value set to its depth.
 
 * Return the sum of each integer in nestedList multiplied by its depth.
  * Constraints:
   ** 1 <= nestedList.length <= 50
   ** The values of the integers in the nested list is in the range [-100, 100].
   ** The maximum depth of any integer is less than or equal to 50.
 */


/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 * // Constructor initializes an empty nested list.
 * public NestedInteger();
 *
 * // Constructor initializes a single integer.
 * public NestedInteger(int value);
 *
 * // @return true if this NestedInteger holds a single integer, rather than a
 * nested list.
 * public boolean isInteger();
 *
 * // @return the single integer that this NestedInteger holds, if it holds a
 * single integer
 * // The result is undefined if this NestedInteger holds a nested list
 * public Integer getInteger();
 *
 * // Set this NestedInteger to hold a single integer.
 * public void setInteger(int value);
 *
 * // Set this NestedInteger to hold a nested list and adds a nested integer to
 * it.
 * public void add(NestedInteger ni);
 *
 * // @return the nested list that this NestedInteger holds, if it holds a
 * nested list
 * // The result is undefined if this NestedInteger holds a single integer
 * public List<NestedInteger> getList();
 * }
 */
class Leetcode339 {
    public int depthSum(List<NestedInteger> nestedList) {
        return dfsDepthSum(nestedList, 1);
    }

    private int dfsDepthSum(List<NestedInteger> nestedList, int depth) {
        int result = 0;
        for (NestedInteger ni : nestedList) {
            if (ni.isInteger()) {
                result += (ni.getInteger() * depth);
            } else {
                result += dfsDepthSum(ni.getList(), depth + 1);
            }
        }
        return result;
    }
}
