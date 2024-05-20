import java.util.stream.IntStream;

public class MaxInInterval {

    /**
     * Проверяет наличие максимального элемента массива в заданном интервале.
     * @param array Входной массив целых чисел.
     * @param a Начало интервала (включительно).
     * @param b Конец интервала (включительно).
     * @return true, если максимальный элемент массива находится в заданном интервале, иначе false.
     */
    public static boolean isMaxInInterval(int[] array, int a, int b) {
        if (a < 0 || b >= array.length || a > b) {
            throw new IllegalArgumentException("Invalid interval");
        }

        // Находим максимальный элемент в массиве
        int max = IntStream.of(array).max().orElse(Integer.MIN_VALUE);

        // Проверяем, находится ли максимальный элемент в заданном интервале
        return IntStream.rangeClosed(a, b).anyMatch(i -> array[i] == max);
    }

    public static void main(String[] args) {
        int[] array = {4, 2, 7, 1, 3, 6, 5};
        int a = 2, b = 5;
        // Проверяем, находится ли максимальный элемент массива в заданном интервале
        System.out.println(isMaxInInterval(array, a, b)); // Output: true
    }
}
