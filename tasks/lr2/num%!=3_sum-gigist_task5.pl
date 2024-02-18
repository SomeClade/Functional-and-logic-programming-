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


% предикат для нахождения суммы всех делителей числа, взаимно простых с суммой цифр числа и не взаимно простых с произведением цифр числа

% сумма цифр +Number, -Sum
sum_digits(0, 0).
sum_digits(Number, Sum) :-
    Number > 0,
    NextDigit is Number mod 10,
    RestNumber is Number // 10,
    sum_digits(RestNumber, RestSum),
    Sum is NextDigit + RestSum.

% Произведенице цифр (+Number, -Product)
prod_digits(0, 1) :- !.
prod_digits(Number, Product) :-
    Number > 0,
    NextDigit is Number mod 10,
    RestNumber is Number // 10,
    prod_digits(RestNumber, RestProduct),
    Product is NextDigit * RestProduct.

% gcd +A, +B, -GCD Наибольший общий делитель
gcd(A, 0, A) :- !.
gcd(A, B, GCD) :-
    Remainder is A mod B,
    gcd(B, Remainder, GCD).

% compire +A +B проверяет является ли числа А и В взаимно простыми
coprime(A, B) :-
    gcd(A, B, GCD),
    GCD =:= 1.
