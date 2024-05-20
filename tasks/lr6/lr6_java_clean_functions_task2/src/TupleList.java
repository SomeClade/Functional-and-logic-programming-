import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TupleList {

    /**
     * Создает список кортежей длины 3 из трех списков, сортируя каждый список по определенным правилам.
     * @param listA Первый список, сортируемый по убыванию.
     * @param listB Второй список, сортируемый по возрастанию суммы цифр элементов.
     * @param listC Третий список, сортируемый по убыванию количества делителей элементов.
     * @return Список кортежей длины 3.
     */
    public static List<int[]> createTuples(List<Integer> listA, List<Integer> listB, List<Integer> listC) {
        // Сортируем первый список по убыванию
        List<Integer> sortedA = listA.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        // Сортируем второй список по возрастанию суммы цифр, при равенстве суммы по убыванию абсолютного значения
        List<Integer> sortedB = listB.stream()
                .sorted(Comparator.comparingInt(TupleList::sumDigits)
                        .thenComparing(Comparator.reverseOrder()))
                .collect(Collectors.toList());

        // Сортируем третий список по убыванию количества делителей, при равенстве количества по убыванию абсолютного значения
        List<Integer> sortedC = listC.stream()
                .sorted(Comparator.comparingInt(TupleList::countDivisors)
                        .reversed()
                        .thenComparing(Comparator.reverseOrder()))
                .collect(Collectors.toList());

        // Объединяем три отсортированных списка в список кортежей (массивов длины 3)
        return IntStream.range(0, Math.min(listA.size(), Math.min(listB.size(), listC.size())))
                .mapToObj(i -> new int[]{sortedA.get(i), sortedB.get(i), sortedC.get(i)})
                .collect(Collectors.toList());
    }

    /**
     * Вычисляет сумму цифр числа.
     * @param number Число, сумма цифр которого вычисляется.
     * @return Сумма цифр числа.
     */
    private static int sumDigits(int number) {
        return String.valueOf(Math.abs(number))
                .chars()
                .map(Character::getNumericValue)
                .sum();
    }

    /**
     * Подсчитывает количество делителей числа.
     * @param number Число, количество делителей которого подсчитывается.
     * @return Количество делителей числа.
     */
    private static int countDivisors(int number) {
        return (int) IntStream.rangeClosed(1, Math.abs(number))
                .filter(i -> number % i == 0)
                .count();
    }

    public static void main(String[] args) {
        List<Integer> listA = List.of(1, 2, 3, 4, 5);
        List<Integer> listB = List.of(10, 20, 30, 40, 50);
        List<Integer> listC = List.of(6, 7, 8, 9, 10);

        List<int[]> tuples = createTuples(listA, listB, listC);
        // Выводим результат
        tuples.forEach(tuple -> System.out.println("(" + tuple[0] + ", " + tuple[1] + ", " + tuple[2] + ")"));
    }
}
