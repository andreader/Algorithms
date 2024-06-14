package pro.sky.algorithms.list.service.impl;
import pro.sky.algorithms.list.exception.ArrayIsFullException;
import pro.sky.algorithms.list.exception.NullElemException;
import pro.sky.algorithms.list.service.StringList;
import java.util.Arrays;


public class StringListImpl implements StringList {
    private final String[] array;
    private int pos;

    public StringListImpl(int size) {
        this.array = new String[size];
    }

    @Override
    public String get(int index) {
        validateIndex(index);
        return array[index];
    }

    @Override
    public int size() {
        return pos;
    }

    @Override
    public boolean isEmpty() {
        return pos == 0;
    }

    @Override
    public void clear() {
        pos = 0;
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i < pos; i++) {
            if (array[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        for (int i = pos; i >= 0; i--) {
            if (array[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(String item) {
        return indexOf(item) != -1;
    }

    @Override
    public String add(String item) {
        validateItem(item);
        validatePos();
        array[pos++] = item;
        return item;
    }

    @Override
    public String add(int index, String item) {
        validatePos();
        validateIndex(index);
        validateItem(item);
        if (pos == index) {
            array[pos++] = item;
            return item;
        }
        System.arraycopy(array, index, array, index + 1, pos - index);
        array[index] = item;
        pos++;
        return item;
    }

    @Override
    public String set(int index, String item) {
        validateIndex(index);
        validateItem(item);
        array[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        validateItem(item);
        return remove(indexOf(item));
    }

    @Override
    public String remove(int index) {
        validateIndex(index);
        String deleted = array[index];
        if (pos != index) {
            System.arraycopy(array, index + 1, array, index, pos - index);
        }
        pos--;
        return deleted;
    }

    @Override
    public boolean equals(StringList otherList) {
        Arrays.equals(this.array, otherList.toArray());
        return false;
    }


    private void validatePos() {
        if (pos == array.length) {
            throw new ArrayIsFullException("Array is full");
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index > array.length) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void validateItem(String item) {
        if (item == null) {
            throw new NullElemException("item can not be null!");
        }
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(array, pos);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        for (int i = 0; i < pos; i++) {
            sb.append(array[i]);
            if (i == pos - 1) {
                return sb.append(')').toString();
            }
            sb.append(',').append(' ');
        }
        return sb.toString();
    }
}
