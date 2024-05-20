import java.time.LocalDate
import java.util.*
import java.util.regex.Pattern

class Passport(val series: String, val number: String, val issueDate: LocalDate) : Comparable<Passport> {

    init {
        require(SERIES_PATTERN.matcher(series).matches()) { "Invalid series format" }
        require(NUMBER_PATTERN.matcher(number).matches()) { "Invalid number format" }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val passport = other as Passport
        return series == passport.series && number == passport.number
    }

    override fun hashCode(): Int {
        return Objects.hash(series, number)
    }

    override fun compareTo(other: Passport): Int {
        return this.issueDate.compareTo(other.issueDate)
    }

    override fun toString(): String {
        return "Passport(series='$series', number='$number', issueDate=$issueDate)"
    }

    companion object {
        private val SERIES_PATTERN = Pattern.compile("\\d{4}")
        private val NUMBER_PATTERN = Pattern.compile("\\d{6}")

        fun compareBySeriesAndNumber(p1: Passport, p2: Passport): Int {
            val seriesComparison = p1.series.compareTo(p2.series)
            if (seriesComparison != 0) {
                return seriesComparison
            }
            return p1.number.compareTo(p2.number)
        }
    }
}

fun main() {
    val passport1 = Passport("1234", "567890", LocalDate.of(2020, 1, 1))
    val passport2 = Passport("1234", "567891", LocalDate.of(2019, 5, 15))
    val passport3 = Passport("1234", "567892", LocalDate.of(2021, 8, 20))

    // Выводим паспорт на экран
    println(passport1)

    // Проверка равенства документов по серии и номеру
    println(passport1 == Passport("1234", "567890", LocalDate.now())) // true
    println(passport1 == passport2) // false

    // Создание HashSet и TreeSet для проверки корректности работы поиска элементов
    val hashSet = HashSet<Passport>()
    hashSet.add(passport1)
    hashSet.add(passport2)
    hashSet.add(passport3)
    println("HashSet: $hashSet")

    val treeSetByDate = TreeSet<Passport>()
    treeSetByDate.add(passport1)
    treeSetByDate.add(passport2)
    treeSetByDate.add(passport3)
    println("TreeSet by date: $treeSetByDate")

    // TreeSet с использованием компаратора для сортировки по серии и номеру
    val treeSetBySeriesAndNumber = TreeSet(Comparator(Passport::compareBySeriesAndNumber))
    treeSetBySeriesAndNumber.add(passport1)
    treeSetBySeriesAndNumber.add(passport2)
    treeSetBySeriesAndNumber.add(passport3)
    println("TreeSet by series and number: $treeSetBySeriesAndNumber")
}
