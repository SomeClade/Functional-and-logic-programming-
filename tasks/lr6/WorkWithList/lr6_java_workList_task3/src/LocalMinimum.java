import java.util.OptionalInt;
import java.util.stream.IntStream;

public class LocalMinimum {

    /**
     * Проверяет, является ли элемент по указанному индексу локальным минимумом.
     * @param array Входной массив целых чисел.
     * @param index Индекс элемента для проверки.
     * @return true, если элемент является локальным минимумом, иначе false.
     */
    public static boolean isLocalMinimum(int[] array, int index) {
        if (index < 0 || index >= array.length) {
            throw new IllegalArgumentException("Index out of bounds");
        }

        // Получаем значения соседних элементов (если они существуют)
        OptionalInt left = index > 0 ? OptionalInt.of(array[index - 1]) : OptionalInt.empty();
        OptionalInt right = index < array.length - 1 ? OptionalInt.of(array[index + 1]) : OptionalInt.empty();

        // Проверяем, является ли элемент локальным минимумом
        return (left.isEmpty() || array[index] < left.getAsInt()) &&
                (right.isEmpty() || array[index] < right.getAsInt());
    }

    public static void main(String[] args) {
        int[] array = {4, 2, 7, 1, 3};
        int index = 3;
        // Проверяем, является ли элемент по указанному индексу локальным минимумом
        System.out.println(isLocalMinimum(array, index)); // Output: true
    }
}
