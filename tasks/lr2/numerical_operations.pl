% max(+X, +Y, +U, -Z)
% Максимум из трех чисел
max(X, Y, U, Z) :-
    TempMax is max(X, max(Y, U)),
    Z = TempMax.

% fact_down(+N, -X)
% Факториал числа, рекурсия вниз
fact_down(0, 1).
fact_down(N, X) :-
    N > 0,
    N1 is N - 1,
    fact_down(N1, X1),
    X is N * X1.

% fact_up(+N, -X)
% Факториал числа, рекурсия вверх
fact_up(N, X) :- fact_up_helper(N, 1, X).
fact_up_helper(0, Acc, Acc).
fact_up_helper(N, Acc, X) :-
    N > 0,
    N1 is N - 1,
    Acc1 is N * Acc,
    fact_up_helper(N1, Acc1, X).

% sum_digits_up(+Number, -Sum)
% Сумма цифр числа, рекурсия вверх
sum_digits_up(0, 0).
sum_digits_up(Number, Sum) :-
    Number > 0,
    NextNumber is Number div 10,
    Digit is Number mod 10,
    sum_digits_up(NextNumber, PartialSum),
    Sum is PartialSum + Digit.

% sum_digits_down(+Number, -Sum)
% Сумма цифр числа, рекурсия вниз
sum_digits_down(Number, Sum) :- sum_digits_down_helper(Number, 0, Sum).
sum_digits_down_helper(0, Acc, Acc).
sum_digits_down_helper(Number, Acc, Sum) :-
    Number > 0,
    NextNumber is Number div 10,
    Digit is Number mod 10,
    NewAcc is Acc + Digit,
    sum_digits_down_helper(NextNumber, NewAcc, Sum).

% is_square_free(+N)
% Проверка числа на свободу от квадратов
is_square_free(1).
is_square_free(N) :-
    N > 1,
    \+ (between(2, N, X), 0 is N mod X^2).

% read_list(-List)
% Чтение списка с клавиатуры
read_list(List) :-
    write('Enter elements, end with "end".: '), nl,
    read_line(List).

read_line([X|List]) :-
    read(X), X \= end,
    !, read_line(List).
read_line([]).

% write_list(+List)
% Вывод списка на экран
write_list([]).
write_list([Head|Tail]) :-
    write(Head), nl,
    write_list(Tail).

% sum_list_down(+List, ?Sum)
% Сумма элементов списка, рекурсия вниз
sum_list_down(List, Sum) :- sum_list_down_helper(List, 0, Sum).
sum_list_down_helper([], Acc, Acc).
sum_list_down_helper([Head|Tail], Acc, Sum) :-
    NewAcc is Acc + Head,
    sum_list_down_helper(Tail, NewAcc, Sum).

% sum_list_up(+List, ?Sum)
% Сумма элементов списка, рекурсия вверх
sum_list_up([], 0).
sum_list_up([Head|Tail], Sum) :-
    sum_list_up(Tail, TailSum),
    Sum is Head + TailSum.

% remove_items_with_digit_sum(+List, +Sum, -Result)
% Удаление элементов, сумма цифр которых равна заданной
remove_items_with_digit_sum(List, Sum, Result) :-
    exclude(has_digit_sum(Sum), List, Result).

has_digit_sum(Sum, Item) :-
    sum_digits_up(Item, ItemSum),
    ItemSum =:= Sum.

% max_digit_down(+Number, -MaxDigit)
% Максимальная цифра в числе, рекурсия вниз
max_digit_down(Number, MaxDigit) :- max_digit_down(Number, 0, MaxDigit).
max_digit_down(0, CurrentMax, CurrentMax) :- !.
max_digit_down(Number, CurrentMax, MaxDigit) :-
    Number > 0,
    NextDigit is Number mod 10,
    NextNumber is Number // 10,
    NewMax is max(CurrentMax, NextDigit),
    max_digit_down(NextNumber, NewMax, MaxDigit).

% max_digit_up(+Number, -MaxDigit)
% Максимальная цифра в числе, рекурсия вверх
max_digit_up(Number, MaxDigit) :- max_digit_up(Number, 0, MaxDigit).
max_digit_up(0, Acc, Acc).
max_digit_up(Number, Acc, MaxDigit) :-
    Number > 0,
    NextDigit is Number mod 10,
    NewAcc is max(Acc, NextDigit),
    NextNumber is Number // 10,
    max_digit_up(NextNumber, NewAcc, MaxDigit).

% Объединение совпадающих предикатов
% min_odd_digit_down(+Number, -MinOddDigit)
% Нахождение минимальной нечетной цифры в числе, рекурсия вниз
min_odd_digit_down(Number, MinOddDigit) :- min_odd_digit_down(Number, -1, MinOddDigit).
min_odd_digit_down(0, CurrentMin, CurrentMin) :- CurrentMin >= 0, !.
min_odd_digit_down(0, _, -1) :- !.  % Если не найдено нечетных цифр
min_odd_digit_down(Number, CurrentMin, MinOddDigit) :-
    Number > 0,
    NextDigit is Number mod 10,
    NextNumber is Number // 10,
    (NextDigit mod 2 =:= 1 -> NewMin = min(CurrentMin, NextDigit); NewMin = CurrentMin),
    min_odd_digit_down(NextNumber, NewMin, MinOddDigit).

% min_odd_digit_up(+Number, -MinOddDigit)
% Нахождение минимальной нечетной цифры в числе, рекурсия вверх
min_odd_digit_up(Number, MinOddDigit) :- min_odd_digit_up_helper(Number, -1, MinOddDigit).
min_odd_digit_up_helper(0, Acc, Acc) :- Acc >= 0, !.
min_odd_digit_up_helper(0, _, -1) :- !.
min_odd_digit_up_helper(Number, Acc, MinOddDigit) :-
    Number > 0,
    NextDigit is Number mod 10,
    NextNumber is Number // 10,
    (NextDigit mod 2 =:= 1 -> NewAcc = min(Acc, NextDigit) ; NewAcc = Acc),
    min_odd_digit_up_helper(NextNumber, NewAcc, MinOddDigit).

% gcd_down(+A, +B, -GCD)
% Нахождение наибольшего общего делителя двух чисел, алгоритм Эвклида, рекурсия вниз
gcd_down(A, 0, A) :- !.
gcd_down(A, B, GCD) :-
    B > 0,
    Remainder is A mod B,
    gcd_down(B, Remainder, GCD).

% gcd_up(+A, +B, -GCD)
% Нахождение наибольшего общего делителя двух чисел, рекурсия вверх
gcd_up(A, B, GCD) :-
    gcd_up_helper(A, B, 1, GCD).

gcd_up_helper(0, B, CurrentGCD, GCD) :-
    !, GCD is max(CurrentGCD, B).
gcd_up_helper(A, 0, CurrentGCD, GCD) :-
    !, GCD is max(CurrentGCD, A).
gcd_up_helper(A, B, CurrentGCD, GCD) :-
    A >= B,
    A1 is A - B,
    gcd_up_helper(A1, B, CurrentGCD, GCD).
gcd_up_helper(A, B, CurrentGCD, GCD) :-
    B > A,
    gcd_up_helper(A, B - A, CurrentGCD, GCD).

