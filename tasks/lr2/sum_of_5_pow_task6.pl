% Предикат для расчета степени цифры
digit_power_sum(0, 0, _).
digit_power_sum(Number, Sum, Power) :-
    Number > 0,
    LastDigit is Number mod 10,
    Rest is Number // 10,
    digit_power_sum(Rest, RestSum, Power),
    Sum is RestSum + LastDigit^Power.

% Проверка, удовлетворяет ли число условию
is_valid_number(Number, Power) :-
    digit_power_sum(Number, Sum, Power),
    Number == Sum.

% Перебор чисел в заданном диапазоне и вывод удовлетворяющих условию
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
