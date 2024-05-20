import java.util.stream.IntStream;

public class LocalMaximaCount {

    /**
     * Считает количество локальных максимумов в массиве.
     * @param array Входной массив целых чисел.
     * @return Количество локальных максимумов.
     */
    public static long countLocalMaxima(int[] array) {
        // Считаем количество локальных максимумов
        return IntStream.range(1, array.length - 1)
                .filter(i -> array[i] > array[i - 1] && array[i] > array[i + 1])
                .count();
    }

    public static void main(String[] args) {
        int[] array = {4, 2, 7, 1, 3, 6, 5};
        // Выводим количество локальных максимумов
        System.out.println(countLocalMaxima(array)); // Output: 3
    }
}
