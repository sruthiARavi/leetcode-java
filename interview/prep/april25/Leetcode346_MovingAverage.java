/*
 * 346. Moving Average from Data Stream
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 * Implement the MovingAverage class:
   ** MovingAverage(int size) Initializes the object with the size of the window size.
   ** double next(int val) Returns the moving average of the last size values of the stream.
 * Constraints:
   ** 1 <= size <= 1000
   ** -105 <= val <= 105
   ** At most 104 calls will be made to next.
 * Example 1:
   ** Input
     *** ["MovingAverage", "next", "next", "next", "next"]
     *** [[3], [1], [10], [3], [5]]
   ** Output
     *** [null, 1.0, 5.5, 4.66667, 6.0]
   ** Explanation
     *** MovingAverage movingAverage = new MovingAverage(3);
     *** movingAverage.next(1); // return 1.0 = 1 / 1
     *** movingAverage.next(10); // return 5.5 = (1 + 10) / 2
     *** movingAverage.next(3); // return 4.66667 = (1 + 10 + 3) / 3
     *** movingAverage.next(5); // return 6.0 = (10 + 3 + 5) / 3
 */
class Leetcode346_MovingAverage {

    Queue<Integer> window = new LinkedList<Integer>();
    int windowSize = -1;
    double currSum = 0.0; 

    public MovingAverage(int size) {
        this.windowSize = size;
    }

    public double next(int val) {
        window.offer(val);
        currSum += val; 
        while (window.size() > windowSize) {            
            currSum -= window.poll();
        }        
        return currSum / window.size();
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
