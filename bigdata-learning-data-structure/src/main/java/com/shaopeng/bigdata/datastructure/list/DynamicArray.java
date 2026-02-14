package com.shaopeng.bigdata.datastructure.list;

/**
 * <p>@author shaopeng</p>
 * <p>{@code @date} 2026/2/14</p>
 */
public class DynamicArray<E extends Object> implements List<E> {

    private Object[] elementData;

    private int size;

    public DynamicArray(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException();
        }

        this.elementData = new Object[initialCapacity];
        this.size = 0;
    }

    public DynamicArray() {
        this(10);
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
        rangeCheckForAdd(index);

        int minCapacity = size + 1;
        if (minCapacity - elementData.length > 0) {
            grow(minCapacity);
        }

        // index - size-1 -> index+1 - size
        // for (int i = size - 1; i >= index; i--) {
        //     elementData[i + 1] = elementData[i];
        // }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;
        size++;
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    // minCapacity：最小需求容量
    private void grow(int minCapacity) {
        if (minCapacity < 0) {
            throw new OutOfMemoryError();
        }

        int newCapacity = elementData.length + (elementData.length >> 1);
        // 保证：新数组容量 >= 最小需求容量
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }
        // 新数组容量过大：新数组容量 = Integer.MAX_VALUE;
        if (newCapacity < 0) {
            newCapacity = Integer.MAX_VALUE;
        }
        Object[] newElementData = new Object[newCapacity];

        System.arraycopy(elementData, 0, newElementData, 0, size);
        elementData = newElementData;
    }

    @Override
    public void add(E e) {
        add(size, e);
    }

    @Override
    public E remove(int index) {
        E ret = get(index);

        // index+1 - size-1 -> index - size-2
        // for (int i = index + 1; i < size; i++) {
        //     elementData[i - 1] = elementData[i];
        // }
        System.arraycopy(elementData, index + 1, elementData, index, size - (index + 1));
        elementData[size - 1] = null;
        size--;

        return ret;
    }

    @Override
    public E set(int index, E element) {
        E ret = get(index);
        elementData[index] = element;
        return ret;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        rangeCheck(index);

        return (E)elementData[index];
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        res.append('[');
        int i = 0;
        while (i < size) {
            res.append(elementData[i]);

            i++;
            if (i < size) {
                res.append(", ");
            }
            /// 没有进入循环，不会输出']'
            // else {
            //     res.append(']');
            // }
        }
        res.append(']');

        return res.toString();
    }

}
