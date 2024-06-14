package pro.sky.algorithms.list;

import pro.sky.algorithms.list.service.StringList;
import pro.sky.algorithms.list.service.impl.StringListImpl;

import java.util.StringJoiner;

public class Main {
    public static void main(String[] args) {
        StringList stringList = new StringListImpl(10);
        stringList.add("1");
        stringList.add("2");
        stringList.add("3");
        stringList.add("4");

        stringList.add(2, "вставка в ячейку 2");
        stringList.remove(2);

        System.out.println(stringList);

    }
}