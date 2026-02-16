package com.shaopeng.bigdata.datastructure.queue;

/**
 * <p>@author shaopeng</p>
 * <p>{@code @date} 2026/2/16</p>
 */
public class CircularArrayTest {

    public static void main(String[] args) {
        Deque<Integer> list = new CircularArray<>(1);
        for (int i = 0; i < 6; i++) {
            list.addLast(i);
        }
        System.out.println(list);

        list.addFirst(7);
        System.out.println(list);

        list.removeLast();
        System.out.println(list);

        list.removeFirst();
        System.out.println(list);
    }

}
