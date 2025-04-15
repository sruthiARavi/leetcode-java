/*
 * 23. Merge k Sorted Lists
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 * Merge all the linked-lists into one sorted linked-list and return it.
 * Constraints:
   ** k == lists.length
   ** 0 <= k <= 104
   ** 0 <= lists[i].length <= 500
   ** -104 <= lists[i][j] <= 104
   ** lists[i] is sorted in ascending order.
   ** The sum of lists[i].length will not exceed 104.
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Leetcode23 {
    ListNode mergeLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        ListNode result = new ListNode(Integer.MIN_VALUE);
        ListNode head = result;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                result.next = l1;
                l1 = l1.next;
            } else {
                result.next = l2;
                l2 = l2.next;
            }
            result = result.next;
        }

        if (l1 == null) {
            result.next = l2;
        }

        if (l2 == null) {
            result.next = l1;
        }
        return head.next;
    }

    ListNode mergeKListsHelper(ListNode[] lists, int left, int right) {
        if (left == right) {
            return lists[left];
        }
        int mid = left + (right - left) / 2;
        ListNode l1 = mergeKListsHelper(lists, left, mid);
        ListNode l2 = mergeKListsHelper(lists, mid + 1, right);
        return mergeLists(l1, l2);
    }

    ListNode mergeKListsUsingPQ(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        for (ListNode node : lists) {
            if (node != null) {
                pq.add(node); //adding head to the pq
            }
        }
        ListNode head = new ListNode(0);
        ListNode result = head;
        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            head.next = node;
            head = head.next;
            if(node.next != null) {
                pq.add(node.next); //adding next node to pq
            }
        }
        return result.next;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        //O(klogN)
        return mergeKListsUsingPQ(lists);

        //O(klogN)
        //return mergeKListsHelper(lists, 0, lists.length - 1);

        /*
        O(kN) :
        ListNode result = lists[0];
        int idx = 1;
        while (idx < lists.length) {
            result = mergeLists(result, lists[idx]);
            idx++;
        }
        return result;
         */
    }
}
