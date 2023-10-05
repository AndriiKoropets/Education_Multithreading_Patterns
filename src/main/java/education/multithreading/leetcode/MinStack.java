package education.multithreading.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

class MinStack {

    Stack<Integer> stack;
    Map<Integer, Integer> map;
    Queue<Integer> queue;

    public MinStack() {
        stack = new Stack<>();
        map = new HashMap<>();
        queue = new PriorityQueue<>();
    }

    public void push(int val) {
        stack.push(val);
        if (map.containsKey(val)) {
            map.replace(val, map.get(val) + 1);
        } else {
            map.put(val, 1);
            queue.add(val);
        }
    }

    public void pop() {
        Integer val = stack.pop();
        if (map.get(val) == 1) {
            map.remove(val);
            queue.remove(val);
        } else {
            map.replace(val, map.get(val) - 1);
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return queue.peek();
    }
}