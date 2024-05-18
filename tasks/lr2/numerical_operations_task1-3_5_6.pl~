% Максимум из трех чисел
max(+X, +Y, +U, -Z) :-
    TempMax is max(X, max(Y, U)),
    Z = TempMax.

% Факториал числа, рекурсия вниз
fact_down(+N, -X) :-
    (N = 0 -> X = 1;
     N > 0, N1 is N - 1, fact_down(N1, X1), X is N * X1).

% Факториал числа, рекурсия вверх
fact_up(+N, -X) :-
    fact_up_helper(N, 1, X).
fact_up_helper(0, Acc, Acc).
fact_up_helper(N, Acc, X) :-
    N > 0, N1 is N - 1, Acc1 is N * Acc, fact_up_helper(N1, Acc1, X).

% Сумма цифр числа, рекурсия вверх
sum_digits_up(+Number, -Sum) :-
    (Number = 0 -> Sum = 0;
     Number > 0, NextNumber is Number div 10, Digit is Number mod 10,
     sum_digits_up(NextNumber, PartialSum), Sum is PartialSum + Digit).

% Сумма цифр числа, рекурсия вниз
sum_digits_down(+Number, -Sum) :-
    sum_digits_down_helper(Number, 0, Sum).
sum_digits_down_helper(0, Acc, Acc).
sum_digits_down_helper(Number, Acc, Sum) :-
    Number > 0, NextNumber is Number div 10, Digit is Number mod 10,
    NewAcc is Acc + Digit, sum_digits_down_helper(NextNumber, NewAcc, Sum).

% Проверка числа на свободу от квадратов
is_square_free(+N) :-
    (N = 1; N > 1, \+ (between(2, N, X), 0 is N mod X^2)).

% Чтение списка и числа с клавиатуры
read_list(-List) :-
    write('Enter elements, end with "end".: '), nl, read_line(List).
read_number(-Number) :-
    write('Введите число: '), read(Number).

read_line([X|List]) :-
    read(X), X \= end, !, read_line(List).
read_line([]).

% Вывод списка и ответов на экран
write_list(+List) :-
    foreach(member(Item, List), (write(Item), nl)).
write_answer(+Count) :-
    write('Количество делителей, не делящихся на 3: '), write(Count), nl.
write_sum(+Sum) :-
    write('Сумма искомых делителей: '), write(Sum), nl.

% Сумма элементов списка, рекурсия вниз
sum_list_down(+List, ?Sum) :-
    sum_list_down_helper(List, 0, Sum).
sum_list_down_helper([], Acc, Acc).
sum_list_down_helper([Head|Tail], Acc, Sum) :-
    NewAcc is Acc + Head, sum_list_down_helper(Tail, NewAcc, Sum).

% Сумма элементов списка, рекурсия вверх
sum_list_up(+List, ?Sum) :-
    (List = [] -> Sum = 0;
     List = [Head|Tail], sum_list_up(Tail, TailSum), Sum is Head + TailSum).

% Удаление элементов, сумма цифр которых равна заданной
remove_items_with_digit_sum(+List, +Sum, -Result) :-
    exclude(has_digit_sum(Sum), List, Result).

has_digit_sum(+Sum, +Item) :-
    sum_digits_up(Item, ItemSum), ItemSum =:= Sum.

% Максимальная и минимальная цифра в числе, рекурсия вниз
max_digit_down(+Number, -MaxDigit) :-
    max_digit_down_helper(Number, 0, MaxDigit).
max_digit_down_helper(0, CurrentMax, CurrentMax).
max_digit_down_helper(Number, CurrentMax, MaxDigit) :-
    Number > 0, NextDigit is Number mod 10, NextNumber is Number // 10,
    NewMax is max(CurrentMax, NextDigit), max_digit_down_helper(NextNumber, NewMax, MaxDigit).

% Нахождение минимальной нечетной цифры в числе, рекурсия вниз
min_odd_digit_down(+Number, -MinOddDigit) :-
    min_odd_digit_down_helper(Number, -1, MinOddDigit).
min_odd_digit_down_helper(0, CurrentMin, CurrentMin) :- CurrentMin >= 0, !.
min_odd_digit_down_helper(0, _, -1) :- !.
min_odd_digit_down_helper(Number, CurrentMin, MinOddDigit) :-
    Number > 0, NextDigit is Number mod 10, NextNumber is Number // 10,
    (NextDigit mod 2 =:= 1 -> NewMin = min(CurrentMin, NextDigit); NewMin = CurrentMin),
    min_odd_digit_down_helper(NextNumber, NewMin, MinOddDigit).

% Нахождение наибольшего общего делителя двух чисел, рекурсия вниз
gcd_down(+A, +B, -GCD) :-
    (B = 0 -> GCD = A; Remainder is A mod B, gcd_down(B, Remainder, GCD)).

% количество делителей числа не делящихся на 3 (+Number, -Count)
count_divisors_not_div_by_3(+Number, -Count) :-
    findall(Divisor, (between(1, Number, Divisor), Number mod Divisor =:= 0, Divisor mod 3 =\= 0), Divisors),
    length(Divisors, Count).

% сумма и произведение цифр числа, Наибольший общий делитель
sum_digits(+Number, -Sum), prod_digits(+Number, -Product), gcd(+A, +B, -GCD) :-
    sum_digits(0, 0), prod_digits(0, 1), gcd(A, 0, A).

% Сумма цифр числа (+number -Sum) 
sum_digits(Number, Sum) :-
    Number > 0,
    NextDigit is Number mod 10,
    RestNumber is Number // 10,
    sum_digits(RestNumber, RestSum),
    Sum is NextDigit + RestSum.

%Произведение цифр числа (+Number -Product
prod_digits(Number, Product) :-
    Number > 0,
    NextDigit is Number mod 10,
    RestNumber is Number // 10,
    prod_digits(RestNumber, RestProduct),
    Product is NextDigit * RestProduct.

%НОД двух чисел (+A +B -GCD)
gcd(A, B, GCD) :-
    Remainder is A mod B,
    gcd(B, Remainder, GCD).

% сумма делителей числа, взаимно простых с суммой и произведением цифр числа (+Number, -Sum)
sum_divisors_coprime(Number, Sum) :-
    sum_digits(Number, SumDigits),
    prod_digits(Number, ProdDigits),
    findall(Divisor, (between(1, Number, Divisor), Number mod Divisor =:= 0, coprime(Divisor, SumDigits), \+ coprime(Divisor, ProdDigits)), Divisors),
    sum_list(Divisors, Sum).
% Проверка на взаимную простоту чисел (+A +B)
coprime(A, B) :-
    gcd(A, B, GCD),
    GCD =:= 1.

% Нахождение пропущенных чисел в числовой последовательности (+List, -Missing)
find_missing_numbers(List, Missing) :-
    min_list(List, Min),
    max_list(List, Max),
    numlist(Min, Max, FullRange),
    findall(Num, (member(Num, FullRange), \+ member(Num, List)), Missing).

% Нахождение элементов между первым и последним максимальным элементами в списке (+List, -Elements)
elements_between_maxes(List, Elements) :-
    max_list(List, Max),
    prefix_suffix(Max, List, _, Suffix),
    reverse(Suffix, RevSuffix),
    prefix_suffix(Max, RevSuffix, _, RevBetween),
    reverse(RevBetween, Elements).

% Вспомогательные предикаты для нахождения префикса и суффикса в списке (+Element, +List, -Prefix, -Suffix)
prefix_suffix(Element, List, Prefix, Suffix) :-
    append(Prefix, [Element|Suffix], List), !.

% Пример использования всех предикатов:
% Пример чтения списка, вывода его на экран, нахождения и вывода суммы его элементов.
example :-
    read_list(List),
    write('Введенный список: '), write_list(List),
    sum_list_up(List, Sum),
    write('Сумма элементов списка: '), write(Sum), nl,
    find_missing_numbers(List, Missing),
    write('Пропущенные числа в последовательности: '), write_list(Missing),
    elements_between_maxes(List, Elements),
    write('Элементы между первым и последним максимальным элементами: '), write_list(Elements),
    read_number(Number),
    count_divisors_not_div_by_3(Number, Count),
    write_answer(Count),
    sum_divisors_coprime(Number, SumDivisors),
    write_sum(SumDivisors).

% Для запуска примера выполните команду:
% ?- example.


% Предикат для расчета степени цифры (+Number, -Sum, +Power)
digit_power_sum(0, 0, _).
digit_power_sum(Number, Sum, Power) :-
    Number > 0,
    LastDigit is Number mod 10,
    Rest is Number // 10,
    digit_power_sum(Rest, RestSum, Power),
    Sum is RestSum + LastDigit^Power.

% Проверка, удовлетворяет ли число условию (+Number, +Power)
is_valid_number(Number, Power) :-
    digit_power_sum(Number, Sum, Power),
    Number == Sum.

% Перебор чисел в заданном диапазоне и вывод удовлетворяющих условию find_numbers(+Power, +Limit) find_numbers(+Number, +Power, +Limit, -AccSum, -TotalSum).
find_numbers(Power, Limit) :-
    find_numbers(2, Power, Limit, 0, TotalSum),
    write('Total Sum: '), write(TotalSum), nl.

find_numbers(Number, Power, Limit, AccSum, TotalSum) :-
    Number =< Limit,
    (   is_valid_number(Number, Power)
    ->  write(Number), nl,
        NewAccSum is AccSum + Number
    ;   NewAccSum is AccSum
    ),
    NextNumber is Number + 1,
    find_numbers(NextNumber, Power, Limit, NewAccSum, TotalSum).

find_numbers(Number, _, Limit, TotalSum, TotalSum) :-
    Number > Limit.

% Запуск поиска чисел, удовлетворяющих условию для пятых степеней
solve :-
    find_numbers(5, 200000). % Предел задан с запасом
