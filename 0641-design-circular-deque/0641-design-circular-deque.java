class MyCircularDeque {
    private int[] deque;
    private int head;
    private int tail;
    private int count;
    private int capacity;

    public MyCircularDeque(int k) {
        this.capacity = k;
        this.deque = new int[k];
        this.head = 0;
        this.tail = 0;
        this.count = 0;
    }
    
    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        head = (head - 1 + capacity) % capacity;
        deque[head] = value;
        count++;
        return true;
    }
    
    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        deque[tail] = value;
        tail = (tail + 1) % capacity;
        count++;
        return true;
    }
    
    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        head = (head + 1) % capacity;
        count--;
        return true;
    }
    
    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        tail = (tail - 1 + capacity) % capacity;
        count--;
        return true;
    }
    
    public int getFront() {
        if (isEmpty()) {
            return -1;
        }
        return deque[head];
    }
    
    public int getRear() {
        if (isEmpty()) {
            return -1;
        }
        return deque[(tail - 1 + capacity) % capacity];
    }
    
    public boolean isEmpty() {
        return count == 0;
    }
    
    public boolean isFull() {
        return count == capacity;
    }
}