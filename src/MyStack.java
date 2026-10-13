/*
    Name: Jay Wu
    PID:  A19135498
 */

import java.util.EmptyStackException;
import utilities.FullStackException;

/**
 * MyStack Implementation
 * @author Jay Wu
 * @since 2026-10-12
 */
public class MyStack {



    private static final int DEFAULT_CAPACITY = 10;
    private String[] data;
    private int size;

    public MyStack(int capacity) {
        if (capacity < 1){
            throw new IllegalArgumentException();
        }
        data = new String[capacity];
        size = 0;
    }

    /*
     * TODO
     * */
    public MyStack() {
        this(DEFAULT_CAPACITY);
    }

    /*
     * TODO
     * */
    public boolean isEmpty() {
            return size == 0;
    }

    /*
     * TODO
     * */
    public void clear() {
        size = 0;
    }

    /*
     * TODO
     * */
    public int size() {
        return size;
    }

    /*
     * TODO
     * */
    public int capacity() {
        return data.length;
    }

    /*
     * TODO
     * */
    public String peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return data[size - 1];
    }
    /*
     * TODO
     * */
    public void push(String element) {
        if (element==null) {
            throw new IllegalArgumentException();
        }
        if (size == data.length) {
            throw new FullStackException("Your stack is full.");
        }
        data[size] = element;
        size++;
    }

    /*
     * TODO
     * */
    public String pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        size --;
        return data[size];
    }

    /*
     * TODO
     * */
    public void multiPush(String[] elements) {
        if (elements == null) {
            throw new IllegalArgumentException();
        }
        for (String s: elements) {
            push(s);
        }
    }

    /*
     * TODO
     * */
    public String[] multiPop(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException();
        }
        int count = Math.min(amount, size);
        String[] result = new String[count];
        for (int i = 0 ; i < count; i++) {
            result[i] = pop();
        }
        return result;
    }


}