package com.shaopeng.bigdata.datastructure.queue;

/**
 * <p>@author shaopeng</p>
 * <p>{@code @date} 2026/3/24</p>
 */
public interface Deque<E extends Object> {

    int size();

    boolean isEmpty();

    void addFirst(E e);

    void addLast(E e);

    E removeFirst();

    E removeLast();

    E peekFirst();

    E peekLast();

}
