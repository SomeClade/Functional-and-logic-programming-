import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ElementsBeforeLastMin {

    /**
     * Возвращает элементы массива, расположенные перед последним минимальным элементом.
     * @param array Входной массив целых чисел.
     * @return Список элементов, расположенных перед последним минимальным элементом.
     */
    public static List<Integer> getElementsBeforeLastMin(int[] array) {
        // Находим индекс последнего минимального элемента
        int lastMinIndex = IntStream.range(0, array.length)
                .reduce((i, j) -> array[j] <= array[i] ? j : i)
                .orElse(-1);

        // Возвращаем элементы, расположенные перед последним минимальным элементом
        return IntStream.range(0, lastMinIndex)
                .mapToObj(i -> array[i])
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        int[] array = {4, 2, 7, 1, 3, 1, 5};
        // Выводим элементы, расположенные перед последним минимальным элементом
        System.out.println(getElementsBeforeLastMin(array)); // Output: [4, 2, 7, 1, 3]
    }
}
