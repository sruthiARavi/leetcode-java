/*
 * Jump Game
 * You are given an integer array nums. 
 * You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.
 * Return true if you can reach the last index, or false otherwise.
 */
class Leetcode55 {
    public boolean canJump(int[] nums) {
        // Greedy solution : https://www.youtube.com/watch?v=Yan0cv2cLy8

        // Edge case : if nums array has size 1, it is already at the goal
        if (nums.length == 1) {
            return true;
        }

        int goal_post = nums.length - 1; // Consider the goal to be the end of the array
        int current_index = goal_post - 1; // We work backwards to see if we can reach the starting idx

        while (current_index >= 0 && goal_post >= 0) {
            /*
             * What's the maximum you can jump from the current position ?
             * If that value - goal position is 0 or greater, then
             * that automatically means I should be able to jump to the goal post
             * using some x number of jumps. Therefore, we can conclude that
             * reaching the goal is possible. In this case, shift the goal to the
             * current index position and continue
             */
            int max_jump = current_index + nums[current_index] - goal_post;
            goal_post = max_jump >= 0 ? current_index : goal_post;
            if (goal_post == 0) { // We've reached starting idx
                return true;
            }
            current_index--;
        }

        return false;
    }
}
