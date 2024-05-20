import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.TreeSet;
import java.util.regex.Pattern;

// Класс Passport реализует Comparable для возможности сравнения по дате выдачи
public class Passport implements Comparable<Passport> {
    // Регулярные выражения для проверки формата серии и номера паспорта
    private static final Pattern SERIES_PATTERN = Pattern.compile("\\d{4}");
    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d{6}");

    private String series;
    private String number;
    private LocalDate issueDate;

    // Конструктор с проверкой формата полей
    public Passport(String series, String number, LocalDate issueDate) {
        if (!SERIES_PATTERN.matcher(series).matches()) {
            throw new IllegalArgumentException("Invalid series format");
        }
        if (!NUMBER_PATTERN.matcher(number).matches()) {
            throw new IllegalArgumentException("Invalid number format");
        }
        this.series = series;
        this.number = number;
        this.issueDate = issueDate;
    }

    // Геттеры для доступа к полям
    public String getSeries() {
        return series;
    }

    public String getNumber() {
        return number;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    // Переопределение метода equals для сравнения по серии и номеру
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passport passport = (Passport) o;
        return series.equals(passport.series) && number.equals(passport.number);
    }

    // Переопределение метода hashCode для использования в коллекциях
    @Override
    public int hashCode() {
        return Objects.hash(series, number);
    }

    // Метод compareTo для сравнения по дате выдачи
    @Override
    public int compareTo(Passport o) {
        return this.issueDate.compareTo(o.issueDate);
    }

    // Метод toString для удобного вывода информации о паспорте
    @Override
    public String toString() {
        return "Passport{" +
                "series='" + series + '\'' +
                ", number='" + number + '\'' +
                ", issueDate=" + issueDate +
                '}';
    }

    // Статический метод для сравнения по серии и номеру
    public static int compareBySeriesAndNumber(Passport p1, Passport p2) {
        int seriesComparison = p1.series.compareTo(p2.series);
        if (seriesComparison != 0) {
            return seriesComparison;
        }
        return p1.number.compareTo(p2.number);
    }

    public static void main(String[] args) {
        Passport passport1 = new Passport("1234", "567890", LocalDate.of(2020, 1, 1));
        Passport passport2 = new Passport("1234", "567891", LocalDate.of(2019, 5, 15));
        Passport passport3 = new Passport("1234", "567892", LocalDate.of(2021, 8, 20));

        // Выводим паспорт на экран
        System.out.println(passport1);

        // Проверка равенства документов по серии и номеру
        System.out.println(passport1.equals(new Passport("1234", "567890", LocalDate.now()))); // true
        System.out.println(passport1.equals(passport2)); // false

        // Создание HashSet и TreeSet для проверки корректности работы поиска элементов
        HashSet<Passport> hashSet = new HashSet<>();
        hashSet.add(passport1);
        hashSet.add(passport2);
        hashSet.add(passport3);
        System.out.println("HashSet: " + hashSet);

        TreeSet<Passport> treeSetByDate = new TreeSet<>();
        treeSetByDate.add(passport1);
        treeSetByDate.add(passport2);
        treeSetByDate.add(passport3);
        System.out.println("TreeSet by date: " + treeSetByDate);

        // TreeSet с использованием компаратора для сортировки по серии и номеру
        TreeSet<Passport> treeSetBySeriesAndNumber = new TreeSet<>(Passport::compareBySeriesAndNumber);
        treeSetBySeriesAndNumber.add(passport1);
        treeSetBySeriesAndNumber.add(passport2);
        treeSetBySeriesAndNumber.add(passport3);
        System.out.println("TreeSet by series and number: " + treeSetBySeriesAndNumber);
    }
}
