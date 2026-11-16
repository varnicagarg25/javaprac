import java.util.ArrayDeque;
import java.util.Deque;

class MinStack {
    private static class Node {
        final int val;
        final int min;

        Node(int val, int min) {
            this.val = val;
            this.min = min;
        }
    }

    private final Deque<Node> stack;

    public MinStack() {
        stack = new ArrayDeque<>();
    }

    public void push(int value) {
        int currentMin = stack.isEmpty() ? value : Math.min(value, stack.peek().min);
        stack.push(new Node(value, currentMin));
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek().val;
    }

    public int getMin() {
        return stack.peek().min;
    }
}