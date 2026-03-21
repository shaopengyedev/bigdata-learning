package com.shaopeng.bigdata.datastructure.list;

/**
 * <p>@author shaopeng</p>
 * <p>{@code @date} 2026/3/21</p>
 */
public interface List<E extends Object> {

    int size();

    boolean isEmpty();

    void add(int index, E element);

    void add(E e);

    E remove(int index);

    E set(int index, E element);

    E get(int index);

}
