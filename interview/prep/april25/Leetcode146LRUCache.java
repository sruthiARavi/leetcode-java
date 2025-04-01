/*
 * 146. LRU Cache
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 * Implement the LRUCache class:
   ** LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
   ** int get(int key) Return the value of the key if the key exists, otherwise return -1.
   ** void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 * The functions get and put must each run in O(1) average time complexity.
 * Constraints:
   ** 1 <= capacity <= 3000
   ** 0 <= key <= 104
   ** 0 <= value <= 105
   ** At most 2 * 105 calls will be made to get and put.
 */

class Leetcode146LRUCache {

    static class LinkedNode {
        private LinkedNode prev;
        private LinkedNode next;
        private int key;
        private int val;

        public LinkedNode(int key, int val) {
            this.key = key;
            this.val = val;
        }

        public LinkedNode getPrev() {
            return prev;
        }

        public void setPrev(LinkedNode prev) {
            this.prev = prev;
        }

        public LinkedNode getNext() {
            return next;
        }

        public void setNext(LinkedNode next) {
            this.next = next;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            String prevVal, nextVal;
            prevVal = prev == null ? "null" : prev.getVal() + "";
            nextVal = next == null ? "null" : next.getVal() + "";
            return "LinkedNode : key : " + key + ", val : " + val + ", prev : " + prevVal + ", next : " + nextVal;
        }
    }

    LinkedNode head;
    LinkedNode tail;

    //Alternatively, you can have an array of size 10001 & consider index as key and update references when required 
    Map<Integer, LinkedNode> cachedItems = new HashMap<>();
    int capacity;

    public LRUCache(int capacity) {
        head = new LinkedNode(-1, -1);
        tail = new LinkedNode(-1, -1);

        head.setNext(tail);
        tail.setPrev(head);

        this.capacity = capacity;
    }

    public int get(int key) {
        if (!cachedItems.containsKey(key)) {
            return -1;
        }
        LinkedNode node = cachedItems.get(key);
        removeFromList(node);
        addToEndOfList(node);
        return node.getVal();
    }

    public void put(int key, int value) {
        LinkedNode node = new LinkedNode(key, value);

        if (cachedItems.containsKey(key)) {
            LinkedNode oldNode = cachedItems.get(key);
            removeFromList(oldNode);
        }

        while (cachedItems.size() >= capacity) {
            //TODO : Get a lock on the map during cleanup if it's a multi-threaded environment. 
            // The lock has to happen in this method and not removeFromList 
            LinkedNode nodeToBeRemoved = head.getNext();
            removeFromList(nodeToBeRemoved);
        }
        addToEndOfList(node);
    }

    private void addToEndOfList(LinkedNode node) {
        tail.getPrev().setNext(node);

        node.setPrev(tail.getPrev());
        node.setNext(tail);

        tail.setPrev(node);

        cachedItems.put(node.getKey(), node);
    }

    private void removeFromList(LinkedNode node) {
        node = cachedItems.get(node.getKey());
        node.getPrev().setNext(node.getNext());
        node.getNext().setPrev(node.getPrev());
        cachedItems.remove(node.getKey());

    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
