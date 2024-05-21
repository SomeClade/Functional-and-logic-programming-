import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("один", "два слова", "три слова тут", "слова", "четыре", "Пять слов тут однако поидеи");

        // Создаем компаратор для сортировки по количеству слов
        Comparator<String> wordCountComparator = Comparator.comparingInt(s -> s.split("\\s+").length);

        // Преобразуем список в отсортированное двоичное дерево
        BinarySearchTree bst = BinarySearchTree.fromList(strings, wordCountComparator);

        // Преобразуем двоичное дерево обратно в отсортированный список
        List<String> sortedList = bst.toSortedList();

        // Выводим отсортированный список
        sortedList.forEach(System.out::println);
    }
}
