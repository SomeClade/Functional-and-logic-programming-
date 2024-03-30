% Определение вершин, рёбер и клики графа
vertex(a).
vertex(b).
vertex(c).
vertex(d).
vertex(e).

edge(a, b).
edge(b, c).
edge(c, a).

% Клика в графе
clique([a, b, c]).

% Генерация всех подмножеств списка
subsets([], []).
subsets([E|Tail], [E|NTail]) :-
    subsets(Tail, NTail).
subsets([_|Tail], NTail) :-
    subsets(Tail, NTail).

% Проверка, является ли набор вершин покрытием алгебраического дополнения
is_cover(Set) :-
    findall((X, Y), complement_edge(X, Y), Edges),
    forall(member((X, Y), Edges), (member(X, Set); member(Y, Set))).

% Определение рёбер алгебраического дополнения
complement_edge(X, Y) :-
    vertex(X),
    vertex(Y),
    X \= Y,
    \+ edge(X, Y),
    \+ edge(Y, X).

% Сортировка наборов по размеру и возвращение списка отсортированных наборов
sort_sets(Sets, SortedSets) :-
    map_list_to_pairs(length, Sets, Pairs),
    sort(1, @>=, Pairs, SortedPairs),
    pairs_values(SortedPairs, SortedSets).

% Нахождение наименьшего покрытия алгебраического дополнения
min_cover(MinSet) :-
    findall(V, vertex(V), Vertices),
    findall(Set, (subsets(Vertices, Set), is_cover(Set)), Sets),
    sort_sets(Sets, SortedSets),
    SortedSets = [MinSet|_].  % Исправлено для корректного вывода

% Пример использования
example :-
    min_cover(Cover),
    write('Minimum vertex cover of the algebraic complement: '), write(Cover), nl.
