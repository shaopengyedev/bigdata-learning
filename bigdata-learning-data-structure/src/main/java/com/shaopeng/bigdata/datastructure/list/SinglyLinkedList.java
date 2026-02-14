package com.shaopeng.bigdata.datastructure.list;

/**
 * <p>@author shaopeng</p>
 * <p>{@code @date} 2026/2/14</p>
 */
public class SinglyLinkedList<E extends Object> implements List<E> {

    // 静态内部类
    private static class Node<e extends Object> {

        e e;

        Node<e> next;

        Node(e e) {
            this.e = e;
            this.next = null;
        }

        @Override
        public String toString() {
            return e.toString();
        }

    }

    private Node<E> head;

    private int size;

    public SinglyLinkedList() {
        this.head = null;
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

        Node<E> prev = null;
        Node<E> next = head;
        for (int i = 1; i <= index; i++) {
            prev = next;
            next = next.next;
        }

        Node<E> node = new Node<>(element);
        node.next = next;
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
    public E remove(int index) {
        checkElementIndex(index);

        Node<E> prev = null;
        Node<E> node = head;
        for (int i = 1; i <= index; i++) {
            prev = node;
            node = node.next;
        }

        E ret = node.e;
        node.e = null;
        Node<E> next = node.next;
        node.next = null;
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
    public E set(int index, E element) {
        checkElementIndex(index);

        Node<E> node = head;
        for (int i = 1; i <= index; i++) {
            node = node.next;
        }

        E ret = node.e;
        node.e = element;
        return ret;
    }

    @Override
    public E get(int index) {
        checkElementIndex(index);

        Node<E> node = head;
        for (int i = 1; i <= index; i++) {
            node = node.next;
        }

        return node.e;
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
