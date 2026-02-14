package com.shaopeng.bigdata.datastructure.list;

/**
 * <p>@author shaopeng</p>
 * <p>{@code @date} 2026/2/14</p>
 */
public class SinglyLinkedListTest {

    public static void main(String[] args) {
        List<Integer> list = new SinglyLinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        System.out.println(list);

        list.add(1, 66);
        System.out.println(list);

        list.remove(1);
        System.out.println(list);

        list.set(8, 88);
        System.out.println(list);

        System.out.println(list.get(8));

        // list.remove(list.size());
        // System.out.println(list);
    }

}
