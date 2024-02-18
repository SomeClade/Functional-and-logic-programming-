% предикаты для чтения и вывода списка

% Чтение списка с консоли
read_list(List) :-
    write('Enter list elements, end with "end".: '), nl,
    read_line(List).

read_line([X|List]) :-
    read(X), X \= end,
    !, read_line(List).
read_line([]).

% Вывод списка на экран
write_answer(List) :-
    write(List), nl.


%является ли элемент по указанному индексу глобальным минимумом

% is_global_minimum(+List, +Index, -Result)
% List - входной список, Index - индекс элемента для проверки, Result - результат проверки (yes/no)
is_global_minimum(List, Index, Result) :-
    nth0(Index, List, Element),  % Получаем элемент по индексу
    min_list(List, Min),  % Находим минимальный элемент в списке
    (Element =:= Min -> Result = yes; Result = no).  % Сравниваем элемент с минимальным


% поиск пропущенных чисел +List, -Missing
% List - входной список, Missing - список пропущенных чисел
find_missing_numbers(List, Missing) :-
    min_list(List, Min),
    max_list(List, Max),
    numlist(Min, Max, FullRange),
    findall(Num, (member(Num, FullRange), \+ member(Num, List)), Missing).
