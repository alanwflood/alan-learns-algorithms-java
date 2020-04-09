package Stacks;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack {

}

class ArrayStack {
    private String[] stack;
    private int top;

    public ArrayStack(int capacity) {
        stack = new String[capacity];
    }

    public void push(String newString) {
        if (top == stack.length) {
            // Need to resize array
            String[] newArray = new String[stack.length * 2];
            System.arraycopy(stack, 0, newArray,0, stack.length);
        }

        stack[top++] = newString;
    }

    public String pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        String string = stack[--top];
        stack[top] = null;
        return string;
    }

    public String peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        String string = stack[top];
        return string;
    }

    public int Size() {
        return top;
    }

    public boolean isEmpty() {
        return top == 0;
    }

    public void printStack() {
        for (int i = top - 1; i >= 0; i--) {
            System.out.println(stack[i]);
        }
    }
}
