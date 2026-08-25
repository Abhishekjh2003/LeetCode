class CustomStack {

    int[] stack;
    int top;
    int maxSize;

    public CustomStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
        top = -1;
    }

    public void push(int x) {
        if (top == maxSize - 1) {
            return;
        }

        stack[++top] = x;
    }

    public int pop() {
        if (top == -1) {
            return -1;
        }

        return stack[top--];
    }

    public void increment(int k, int val) {
        int limit = Math.min(k, top + 1);

        for (int i = 0; i < limit; i++) {
            stack[i] += val;
        }
    }
}