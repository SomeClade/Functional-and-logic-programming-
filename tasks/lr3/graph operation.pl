% Определение вершин и рёбер графа
vertex(a).
vertex(b).
vertex(c).


edge(a, b).
edge(b, c).
edge(c, a).


% Генерация всех подмножеств списка
subsets([], []).
subsets([E|Tail], [E|NTail]) :-
    subsets(Tail, NTail).
subsets([_|Tail], NTail) :-
    subsets(Tail, NTail).

% Определение рёбер алгебраического дополнения
complement_edge(X, Y) :-
    vertex(X),
    vertex(Y),
    X \= Y,
    \+ edge(X, Y),
    \+ edge(Y, X).

% Проверка, является ли набор вершин покрытием алгебраического дополнения
is_cover(Set) :-
    findall((X, Y), complement_edge(X, Y), Edges),
    forall(member((X, Y), Edges), (member(X, Set); member(Y, Set))).

% Нахождение наименьшего покрытия алгебраического дополнения
min_cover(MinSet) :-
    findall(V, vertex(V), Vertices),
    findall(Set, (subsets(Vertices, Set), is_cover(Set)), Sets),
    min_length(Sets, MinSet).

% Находит список с минимальной длиной
min_length([F|Rest], MinSet) :-
    min_length(Rest, F, MinSet).

% Вспомогательная функция для min_length
min_length([], Min, Min).
min_length([Set|Rest], CurrentMin, Min) :-
    length(Set, LenSet),
    length(CurrentMin, LenCurrentMin),
    (LenSet < LenCurrentMin -> NewMin = Set; NewMin = CurrentMin),
    min_length(Rest, NewMin, Min).

% Пример использования
example :-
    min_cover(Cover),
    write('Minimum vertex cover of the algebraic complement: '), write(Cover), nl.
