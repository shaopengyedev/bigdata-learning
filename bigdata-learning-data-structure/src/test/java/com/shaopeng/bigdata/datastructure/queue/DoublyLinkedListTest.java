package com.shaopeng.bigdata.datastructure.queue;

import com.shaopeng.bigdata.datastructure.list.List;

/**
 * <p>@author shaopeng</p>
 * <p>{@code @date} 2026/2/14</p>
 */
public class DoublyLinkedListTest {

    public static void main(String[] args) {
        List<Integer> list = new DoublyLinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        System.out.println(list);

        list.add(1, 66);
        System.out.println(list);

        list.remove(1);
        System.out.println(list);

        list.add(8, 88);
        System.out.println(list);

        list.remove(8);
        System.out.println(list);

        list.set(8, 888);
        System.out.println(list);

        System.out.println(list.get(8));
    }

}
