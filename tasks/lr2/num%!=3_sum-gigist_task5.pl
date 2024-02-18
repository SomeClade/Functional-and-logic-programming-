read_number(Number) :-
    write('Введите число: '), read(Number).

% количество делитей числа не делящихся на 3 (+Number, -Count)
% Number - число, для которого ищем количество делителей.
% Count - количество делителей, не делящихся на 3.
count_divisors_not_div_by_3(Number, Count) :-
    findall(Divisor, (between(1, Number, Divisor), Number mod Divisor =:= 0, Divisor mod 3 =\= 0), Divisors),
    length(Divisors, Count).

write_answer(Count) :-
    write('Количество делителей, не делящихся на 3: '), write(Count), nl.
