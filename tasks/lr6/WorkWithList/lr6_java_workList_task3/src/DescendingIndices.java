import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DescendingIndices {

    /**
     * Возвращает индексы массива в порядке убывания значений элементов.
     * @param array Входной массив целых чисел.
     * @return Список индексов в порядке убывания значений элементов массива.
     */
    public static List<Integer> getDescendingIndices(int[] array) {
        // Создаем поток индексов массива, сортируем их по значениям элементов в убывающем порядке
        return IntStream.range(0, array.length)
                .boxed()
                .sorted(Comparator.comparingInt((Integer i) -> array[i]).reversed())
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        int[] array = {4, 2, 7, 1, 3};
        // Выводим индексы элементов массива в порядке убывания значений
        System.out.println(getDescendingIndices(array)); // Output: [2, 0, 4, 1, 3]
    }
}
