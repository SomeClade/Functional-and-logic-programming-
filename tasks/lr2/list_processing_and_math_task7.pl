% calculate_average_abs +List, -Average
% List - входной список чисел, Average - среднее арифметическое модулей элементов списка.
calculate_average_abs(List, Average) :-
    sum_abs(List, Sum, Count),
    Average is Sum / Count.

% sum_abs +List, -Sum, -Count
% Вспомогательный предикат для подсчета суммы модулей элементов и их количества.
sum_abs([], 0, 0).
sum_abs([H|T], Sum, Count) :-
    sum_abs(T, TailSum, TailCount),
    Sum is TailSum + abs(H),
    Count is TailCount + 1.

write_average_abs(Average) :-
    write('Среднее арифметическое модулей элементов: '), write(Average), nl.

% sum_in_range(+List, +A, +B, -Sum)
% List - входной список чисел, A и B - границы интервала, Sum - сумма элементов в этом интервале.
sum_in_range(List, A, B, Sum) :-
    include(between(A, B), List, FilteredList),
    sum_list(FilteredList, Sum).

% unique_elements(+List1, +List2, -UniqueList)
% List1 и List2 - входные списки, UniqueList - список уникальных элементов.
unique_elements(List1, List2, UniqueList) :-
    findall(X, (member(X, List1), \+ member(X, List2)), Unique1),
    findall(X, (member(X, List2), \+ member(X, List1)), Unique2),
    append(Unique1, Unique2, UniqueList).

