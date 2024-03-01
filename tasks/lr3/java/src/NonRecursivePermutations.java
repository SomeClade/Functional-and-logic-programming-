import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class NonRecursivePermutations {
    // Вспомогательная функция для обмена элементов
    private static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Вспомогательная функция для реверса от i до j
    private static void reverse(char[] arr, int i, int j) {
        while (i < j) {
            swap(arr, i++, j--);
        }
    }

    // Функция для получения следующей перестановки
    private static boolean nextPermutation(char[] arr) {
        int i = arr.length - 2;
        while (i >= 0 && arr[i] >= arr[i + 1]) {
            i--;
        }
        if (i < 0) {
            return false;
        }
        int j = arr.length - 1;
        while (arr[j] <= arr[i]) {
            j--;
        }
        swap(arr, i, j);
        reverse(arr, i + 1, arr.length - 1);
        return true;
    }

    public static void main(String[] args) {
        try (FileWriter writer = new FileWriter("non_recursive_permutations.txt")) {
            char[] chars = {'a', 'b', 'c'};
            Arrays.sort(chars); // Начать с сортированной последовательности
            do {
                writer.write(chars);
                writer.write('\n');
            } while (nextPermutation(chars));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
