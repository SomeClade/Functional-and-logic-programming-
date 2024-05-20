import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SquareElements {

    /**
     * Подсчитывает количество элементов в списке, которые могут быть квадратом какого-то другого элемента из списка.
     * @param list Входной список целых чисел.
     * @return Количество элементов, которые могут быть квадратом какого-то другого элемента.
     */
    public static long countSquares(List<Integer> list) {
        // Создаем множество квадратов элементов из исходного списка
        Set<Integer> squares = list.stream()
                .map(x -> x * x)
                .collect(Collectors.toSet());

        // Фильтруем исходный список, оставляя только те элементы, которые содержатся в множестве квадратов
        return list.stream()
                .filter(squares::contains)
                .count();
    }

    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 9, 16, 25);
        // Ожидаемый результат: 3 (поскольку 1, 9 и 16 являются квадратами 1, 3 и 4 соответственно)
        System.out.println(countSquares(list)); // Output: 3
    }
}
