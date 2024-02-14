%max(+X,+Y,+U,-Z)

max(X, Y, U, Z) :-
    TempMax is max(X, max(Y, U)),
    Z = TempMax.
%Факториал рекурсия вниз +N,-X

fact_down(0, 1).
fact_down(N, X) :-
    N > 0,
    N1 is N - 1,
    fact_down(N1, X1),
    X is N * X1.

%Рекурсия вверх +N,-X
fact_up(N, X) :- fact_up_helper(N, 1, X).

fact_up_helper(0, Acc, Acc).
fact_up_helper(N, Acc, X) :-
    N > 0,
    N1 is N - 1,
    Acc1 is N * Acc,
    fact_up_helper(N1, Acc1, X).
%Сумма цифр числа рекурсия вверх +Number,-Sum

sum_digits_up(0, 0).
sum_digits_up(Number, Sum) :-
    Number > 0,
    NextNumber is Number div 10,
    Digit is Number mod 10,
    sum_digits_up(NextNumber, PartialSum),
    Sum is PartialSum + Digit.

%%Сумма цифр числа рекурсия вниз +Number,-Sum

sum_digits_down(Number, Sum) :- sum_digits_down_helper(Number, 0, Sum).

sum_digits_down_helper(0, Acc, Acc).
sum_digits_down_helper(Number, Acc, Sum) :-
    Number > 0,
    NextNumber is Number div 10,
    Digit is Number mod 10,
    NewAcc is Acc + Digit,
    sum_digits_down_helper(NextNumber, NewAcc, Sum).

%Число свободное от квадратов +N

is_square_free(1).
is_square_free(N) :-
    N > 1,
    \+ (between(2, N, X), 0 is N mod X^2).

%Предикаты чтения списка с клавиатуры read_list -List и вывода списка на экран write_list +List

read_list(List) :-
    write('Enter elements, end with "end".: '), nl,
    read_line(List).

read_line([X|List]) :-
    read(X), X \= end,
    !, read_line(List).
read_line([]).

write_list([]).
write_list([Head|Tail]) :-
    write(Head), nl,
    write_list(Tail).

% сумма списка рекурсия вниз +List, ?Sum

sum_list_down(List, Sum) :- sum_list_down_helper(List, 0, Sum).

sum_list_down_helper([], Acc, Acc).
sum_list_down_helper([Head|Tail], Acc, Sum) :-
    NewAcc is Acc + Head,
    sum_list_down_helper(Tail, NewAcc, Sum).

%сумма списка рекурсия вверх +List, ?Sum

sum_list_up([], 0).
sum_list_up([Head|Tail], Sum) :-
    sum_list_up(Tail, TailSum),
    Sum is Head + TailSum.

%Удаление элементов, сумма цифр которых равна данной  +List,+Sum,-Result

remove_items_with_digit_sum(List, Sum, Result) :-
    exclude(has_digit_sum(Sum), List, Result).

has_digit_sum(Sum, Item) :-
    sum_digits_up(Item, ItemSum),
    ItemSum =:= Sum.
