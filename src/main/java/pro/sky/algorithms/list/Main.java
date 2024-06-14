package pro.sky.algorithms.list;

import pro.sky.algorithms.list.service.StringList;
import pro.sky.algorithms.list.service.impl.StringListImpl;

public class Main {
    public static void main(String[] args) {
        StringList stringList = new StringListImpl(0);
        stringList.add("1");
        stringList.add("2");
        stringList.add("3");
        stringList.add("4");
        stringList.add("5");
        stringList.add("6");


/*
        stringList.add(2, "вставка в ячейку 2");
*/

        System.out.println(stringList);

    }
}
