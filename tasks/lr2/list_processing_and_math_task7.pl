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
