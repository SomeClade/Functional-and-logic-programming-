import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ElementsAfterFirstMax {

    /**
     * Возвращает элементы массива, расположенные после первого максимального элемента.
     * @param array Входной массив целых чисел.
     * @return Список элементов, расположенных после первого максимального элемента.
     */
    public static List<Integer> getElementsAfterFirstMax(int[] array) {
        // Находим индекс первого максимального элемента
        int firstMaxIndex = IntStream.range(0, array.length)
                .reduce((i, j) -> array[j] > array[i] ? j : i)
                .orElse(-1);

        // Возвращаем элементы, расположенные после первого максимального элемента
        return IntStream.range(firstMaxIndex + 1, array.length)
                .mapToObj(i -> array[i])
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        int[] array = {4, 2, 7, 1, 3, 7, 5};
        // Выводим элементы, расположенные после первого максимального элемента
        System.out.println(getElementsAfterFirstMax(array)); // Output: [1, 3, 7, 5]
    }
}
