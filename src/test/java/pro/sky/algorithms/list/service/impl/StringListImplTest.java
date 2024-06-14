package pro.sky.algorithms.list.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pro.sky.algorithms.list.exception.ArrayIsFullException;
import pro.sky.algorithms.list.exception.NullElemException;
import pro.sky.algorithms.list.service.StringList;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StringListImplTest {

    private StringList stringList;

    @BeforeEach
    void setUp() {
        stringList = new StringListImpl(10);
    }

    @ParameterizedTest
    @MethodSource("provideGetParameters")
    void get(int index, String expected) {
        stringList.add("A");
        stringList.add("B");
        stringList.add("C");

        assertThat(stringList.get(index)).isEqualTo(expected);
    }

    private static Stream<Arguments> provideGetParameters() {
        return Stream.of(
                Arguments.of(0, "A"),
                Arguments.of(1, "B"),
                Arguments.of(2, "C")
        );
    }

    @Test
    void size() {
        stringList.add("A");
        stringList.add("B");
        stringList.add("C");

        assertThat(stringList.size()).isEqualTo(3);
    }

    @Test
    void isEmpty() {
        assertThat(stringList.isEmpty()).isTrue();

        stringList.add("A");

        assertThat(stringList.isEmpty()).isFalse();
    }

    @Test
    void clear() {
        stringList.add("A");
        stringList.add("B");
        stringList.add("C");

        stringList.clear();

        assertThat(stringList.size()).isEqualTo(0);
    }

    @ParameterizedTest
    @MethodSource("provideIndexOfParameters")
    void indexOf(String item, int expected) {
        stringList.add("A");
        stringList.add("B");
        stringList.add("C");

        assertThat(stringList.indexOf(item)).isEqualTo(expected);
    }

    private static Stream<Arguments> provideIndexOfParameters() {
        return Stream.of(
                Arguments.of("A", 0),
                Arguments.of("B", 1),
                Arguments.of("C", 2),
                Arguments.of("D", -1)
        );
    }

    @ParameterizedTest
    @MethodSource("provideLastIndexOfParameters")
    void lastIndexOf(String item, int expected) {
        stringList.add("A");
        stringList.add("B");
        stringList.add("C");
        stringList.add("B");

        assertThat(stringList.lastIndexOf(item)).isEqualTo(expected);
    }

    private static Stream<Arguments> provideLastIndexOfParameters() {
        return Stream.of(
                Arguments.of("A", 0),
                Arguments.of("B", 3),
                Arguments.of("C", 2),
                Arguments.of("D", -1)
        );
    }

    @ParameterizedTest
    @MethodSource("provideContainsParameters")
    void contains(String item, boolean expected) {
        stringList.add("A");
        stringList.add("B");
        stringList.add("C");

        assertThat(stringList.contains(item)).isEqualTo(expected);
    }

    private static Stream<Arguments> provideContainsParameters() {
        return Stream.of(
                Arguments.of("A", true),
                Arguments.of("B", true),
                Arguments.of("C", true),
                Arguments.of("D", false)
        );
    }

    @Test
    void add() {
        stringList.add("A");

        assertThat(stringList.size()).isEqualTo(1);
        assertThat(stringList.get(0)).isEqualTo("A");
    }

    @ParameterizedTest
    @MethodSource("provideAddParameters")
    void add(int index, String item, int expectedSize) {
        stringList.add("A");
        stringList.add("B");

        stringList.add("C");

        stringList.add(index, item);

        assertThat(stringList.size()).isEqualTo(expectedSize);
        assertThat(stringList.get(index)).isEqualTo(item);
    }

    private static Stream<Arguments> provideAddParameters() {
        return Stream.of(
                Arguments.of(0, "D", 4),
                Arguments.of(1, "E", 5),
                Arguments.of(2, "F", 6),
                Arguments.of(3, "G", 7)
        );
    }

    @ParameterizedTest
    @MethodSource("provideSetParameters")
    void set(int index, String item) {
        stringList.add("A");
        stringList.add("B");
        stringList.add("C");

        stringList.set(index, item);

        assertThat(stringList.get(index)).isEqualTo(item);
    }

    private static Stream<Arguments> provideSetParameters() {
        return Stream.of(
                Arguments.of(0, "D"),
                Arguments.of(1, "E"),
                Arguments.of(2, "F")
        );
    }

    @ParameterizedTest
    @MethodSource("provideRemoveParameters")
    void remove(int index, String expected) {
        stringList.add("A");
        stringList.add("B");
        stringList.add("C");

        String removed = stringList.remove(index);

        assertThat(removed).isEqualTo(expected);
        assertThat(stringList.size()).isEqualTo(2);
    }

    private static Stream<Arguments> provideRemoveParameters() {
        return Stream.of(
                Arguments.of(0, "A"),
                Arguments.of(1, "B"),
                Arguments.of(2, "C")
        );
    }

    @Test
    void remove1() {
        stringList.add("A");
        stringList.add("B");
        stringList.add("C");

        String removed = stringList.remove("B");

        assertThat(removed).isEqualTo("B");
        assertThat(stringList.size()).isEqualTo(2);
    }

    @Test
    void equals() {
        StringList otherList = new StringListImpl(10);
        otherList.add("A");
        otherList.add("B");
        otherList.add("C");

        assertThat(stringList.equals(otherList)).isTrue();
    }

    @Test
    void toArray() {
        stringList.add("A");
        stringList.add("B");
        stringList.add("C");

        assertThat(stringList.toArray()).isEqualTo(new String[]{"A", "B", "C"});
    }

    @Test
    void testToString() {
        stringList.add("A");
        stringList.add("B");
        stringList.add("C");

        assertThat(stringList.toString()).isEqualTo("(A, B, C)");
    }

    @Test
    void testValidatePos() {
        assertThatThrownBy(() -> stringList.add("A"))
                .isInstanceOf(ArrayIsFullException.class)
                .hasMessage("Array is full");
    }

    @Test
    void testValidateIndex() {
        assertThatThrownBy(() -> stringList.get(-1))
                .isInstanceOf(IndexOutOfBoundsException.class);

        assertThatThrownBy(() -> stringList.add(4, "A"))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void testValidateItem() {
        assertThatThrownBy(() -> stringList.add(null))
                .isInstanceOf(NullElemException.class)
                .hasMessage("item can not be null!");
    }
}
