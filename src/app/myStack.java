
package app;

public class myStack {
    private int top;
    private int max;
    private message mes[];

    public myStack(int max) {
        this.max = max;
        mes = new message[max];
        top = -1;
    }

    public void push(message x) {
        top++;
        if (top >= max) {
            System.out.println("Stack is full, can't insert");
            top--;
            return;
        }
        mes[top] = x;
    }

    public message pop() throws Exception {
        if (isEmpty()) {
            throw new Exception("The linked list is empty");
        }
        message tmp = mes[top];
        top--;
        return tmp;
    }

    public void clear() {
        top = -1;
    }

    private boolean isEmpty() {
        if (top == -1) {
            return true;
        }
        return false;
    }

    public int getTopNum() {
        return top;
    }
}