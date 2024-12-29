/*
 * 155. Min Stack
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * Implement the MinStack class:
   ** MinStack() initializes the stack object.
   ** void push(int val) pushes the element val onto the stack.
   ** void pop() removes the element on the top of the stack.
   ** int top() gets the top element of the stack.
   ** int getMin() retrieves the minimum element in the stack.
 * You must implement a solution with O(1) time complexity for each function.
 */
public class Leetcode155 {
    //Approach 1 : Modeled on linked list 
    class MinStack {

        Node head;

        public MinStack() {

        }

        public void push(int val) {
            if(head == null) {
                head = new Node(val, val, null);
            } else {
                head = new Node(val, Math.min(val, head.min), head);
            }
        }

        public void pop() {
            head = head.next;
        }

        public int top() {
            return head.val;
        }

        public int getMin() {
            return head.min;
        }
    }

    class Node {
        int val;
        int min;
        Node next;

        public Node(int val, int min, Node next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }

    //Approach 2 : Modeled on maintaining a minVal int and using list 
    class MinStack1 {
        List<Integer> elements;
        Integer minVal;
        //int minValCount; - can use this to reduce itr

        public MinStack() {
            elements = new ArrayList<>();
            minVal = Integer.MAX_VALUE;
        }

        public void push(int val) {
            elements.add(val);
            minVal = val < minVal ? val : minVal;
        }

        public void pop() {
            if (elements.isEmpty()) {
                return;
            }
            int lastEltIdx = elements.size() - 1;
            if (Objects.equals(minVal, elements.get(lastEltIdx))) {
                if (elements.size() == 2) {
                    minVal = elements.get(0);
                } else {
                    minVal = Integer.MAX_VALUE;
                    for (int i = 0; i < lastEltIdx; i++) {
                        int val = elements.get(i);
                        minVal = val < minVal ? val : minVal;
                    }
                }
            }
            elements.remove(lastEltIdx);
        }

        public int top() {
            if (elements.isEmpty()) {
                return -1;
            }
            return elements.get(elements.size() - 1);
        }

        public int getMin() {
            return minVal;
        }
    }

    //Approach 3 : Using list and array dequeue 
    //TODO : Test cases passed but approach needs to be verified 
    class MinStack2 {
        //Assuming thread safety is not required
        List<Integer> elements = new ArrayList<>();
        ArrayDeque<Integer> minElements = new ArrayDeque<>();

        public MinStack() {

        }

        public void push(int val) {
            elements.add(val);
            if (minElements.isEmpty()) {
                minElements.add(val);
            } else if (val <= minElements.getFirst()) {
                minElements.addFirst(val);
            }
        }

        public void pop() {
            if (elements.isEmpty()) {
                return;
            }
            int lastEltIdx = elements.size() - 1;
            if (!minElements.isEmpty() && Objects.equals(elements.get(lastEltIdx), minElements.getFirst())) {
                minElements.removeFirst();
            }
            elements.remove(lastEltIdx);
        }

        public int top() {
            if (elements.isEmpty()) {
                return -1;
            }
            int lastEltIdx = elements.size() - 1;
            return elements.get(lastEltIdx);
        }

        public int getMin() {
            if (minElements.isEmpty()) {
                return -1;
            }
            return minElements.getFirst();
        }
    }

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
}
