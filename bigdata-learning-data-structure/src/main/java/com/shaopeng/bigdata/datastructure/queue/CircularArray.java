package com.shaopeng.bigdata.datastructure.queue;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * <p>@author shaopeng</p>
 * <p>{@code @date} 2026/2/16</p>
 */
public class CircularArray<E extends Object> implements Deque<E> {

    private Object[] elements;

    private int head;
    private int tail;

    public CircularArray(int initialCapacity) {
        // 初始容量最小为1，保证能够至少添加一个元素
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException();
        }

        initialCapacity = calculateCapacity(initialCapacity);
        this.elements = new Object[initialCapacity];
        this.head = 0;
        this.tail = 0;
    }

    private int calculateCapacity(int initialCapacity) {
        initialCapacity--;
        initialCapacity |= (initialCapacity >>> 1);
        initialCapacity |= (initialCapacity >>> 2);
        initialCapacity |= (initialCapacity >>> 4);
        initialCapacity |= (initialCapacity >>> 8);
        initialCapacity |= (initialCapacity >>> 16);
        initialCapacity++;

        if (initialCapacity < 0) {
            initialCapacity >>>= 1;
        }
        return initialCapacity;
    }

    public CircularArray() {
        this.elements = new Object[16];
        this.head = 0;
        this.tail = 0;
    }

    @Override
    public int size() {
        return (tail - head) & (elements.length - 1);
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public void addFirst(E e) {
        Objects.requireNonNull(e);

        // head：第一个用户元素的索引
        head = (head - 1) & (elements.length - 1);
        elements[head] = e;

        if (head == tail) {
            doubleCapacity();
        }
    }

    private void doubleCapacity() {
        int newCapacity = elements.length << 1;
        if (newCapacity < 0) {
            throw new IllegalStateException();
        }
        Object[] newElements = new Object[newCapacity];

        /// 数组为满 => head == tail
        // int size = size();
        int size = elements.length;
        System.arraycopy(elements, head, newElements, 0, elements.length - head);
        System.arraycopy(elements, 0, newElements, elements.length - head, head);
        elements = newElements;
        head = 0;
        tail = size;
    }

    @Override
    public void addLast(E e) {
        Objects.requireNonNull(e);

        // tail：next最后一个用户元素的索引
        elements[tail] = e;
        tail = (tail + 1) & (elements.length - 1);

        if (head == tail) {
            doubleCapacity();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public E removeFirst() {
        E ret = (E)elements[head];
        if (ret == null) {
            throw new NoSuchElementException();
        }

        elements[head] = null;
        head = (head + 1) & (elements.length - 1);

        return ret;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E removeLast() {
        int t = (tail - 1) & (elements.length - 1);
        E ret = (E)elements[t];
        if (ret == null) {
            throw new NoSuchElementException();
        }

        elements[t] = null;
        tail = t;

        return ret;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E peekFirst() {
        return (E)elements[head];
    }

    @SuppressWarnings("unchecked")
    @Override
    public E peekLast() {
        int t = (tail - 1) & (elements.length - 1);
        return (E)elements[t];
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        res.append('[');
        int i = head;
        while (i != tail) {
            res.append(elements[i]);

            i = (i + 1) & (elements.length - 1);
            if (i != tail) {
                res.append(", ");
            }
        }
        res.append(']').append(" head = " + head + ", tail = " + tail);

        return res.toString();
    }

}
