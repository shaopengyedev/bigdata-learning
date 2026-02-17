package com.shaopeng.bigdata.datastructure.queue;

import com.shaopeng.bigdata.datastructure.list.List;
import java.util.Objects;

/**
 * <p>@author shaopeng</p>
 * <p>{@code @date} 2026/2/14</p>
 */
public class DoublyLinkedList<E extends Object> implements List<E>, Deque<E> {

    private static class Node<e extends Object> {

        e e;

        Node<e> prev;
        Node<e> next;

        Node(e e) {
            this.e = e;
            this.prev = null;
            this.next = null;
        }

        @Override
        public String toString() {
            return e.toString();
        }

    }

    private Node<E> head;
    private Node<E> tail;

    private int size;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void add(int index, E element) {
        checkPositionIndex(index);
        // Objects.requireNonNull(element);

        Node<E> prev;
        Node<E> next;
        // 0 - size
        int mid = size >> 1;
        if (index <= mid) {
            prev = null;
            next = head;
            for (int i = 1; i <= index; i++) {
                prev = next;
                next = next.next;
            }
        }
        else {
            next = null;
            prev = tail;
            for (int i = size - 1; i >= index; i--) {
                next = prev;
                prev = prev.prev;
            }
        }

        Node<E> node = new Node<>(element);
        node.prev = prev;
        node.next = next;
        // next == null => prev == tail
        if (next == null) {
            tail = node;
        }
        else {
            next.prev = node;
        }
        // prev == null => next == head
        if (prev == null) {
            head = node;
        }
        else {
            prev.next = node;
        }
        size++;
    }

    private void checkPositionIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public void add(E e) {
        add(size, e);
    }

    @Override
    public void addFirst(E e) {
        add(0, e);
    }

    @Override
    public void addLast(E e) {
        add(size, e);
    }

    @Override
    public E remove(int index) {
        checkElementIndex(index);

        Node<E> node;
        // 0 - size-1
        int mid = (size - 1) >> 1;
        if (index <= mid) {
            node = head;
            for (int i = 1; i <= index; i++) {
                node = node.next;
            }
        }
        else {
            node = tail;
            for (int i = size - 2; i >= index; i--) {
                node = node.prev;
            }
        }

        E ret = node.e;
        node.e = null;
        Node<E> prev = node.prev;
        node.prev = null;
        Node<E> next = node.next;
        node.next = null;
        // next == null => node == tail
        if (next == null) {
            tail = prev;
        }
        else {
            next.prev = prev;
        }
        // prev == null => node == head
        if (prev == null) {
            head = next;
        }
        else {
            prev.next = next;
        }
        size--;
        return ret;
    }

    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public E removeFirst() {
        return remove(0);
    }

    @Override
    public E removeLast() {
        return remove(size - 1);
    }

    @Override
    public E set(int index, E element) {
        checkElementIndex(index);
        // Objects.requireNonNull(element);

        Node<E> node;
        // 0 - size-1
        int mid = (size - 1) >> 1;
        if (index <= mid) {
            node = head;
            for (int i = 1; i <= index; i++) {
                node = node.next;
            }
        }
        else {
            node = tail;
            for (int i = size - 2; i >= index; i--) {
                node = node.prev;
            }
        }

        E ret = node.e;
        node.e = element;
        return ret;
    }

    @Override
    public E get(int index) {
        checkElementIndex(index);

        Node<E> node;
        // 0 - size-1
        int mid = (size - 1) >> 1;
        if (index <= mid) {
            node = head;
            for (int i = 1; i <= index; i++) {
                node = node.next;
            }
        }
        else {
            node = tail;
            for (int i = size - 2; i >= index; i--) {
                node = node.prev;
            }
        }

        return node.e;
    }

    @Override
    public E peekFirst() {
        return get(0);
    }

    @Override
    public E peekLast() {
        return get(size - 1);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        Node<E> node = head;
        while (node != null) {
            res.append(node).append("-> ");

            node = node.next;
        }
        res.append("null");

        return res.toString();
    }

}
