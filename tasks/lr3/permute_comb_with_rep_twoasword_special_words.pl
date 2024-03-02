%permute(+List, -Permutations)
permute([], []).
permute(L, [H|T]) :-
select(H, L, Rest),
permute(Rest, T).

% generate_permutations(+List, +Filename)
generate_permutations(List, Filename) :-
open(Filename, write, Stream),
( permute(List, Perm),
write(Stream, Perm), write(Stream, '\n'),
fail % Используем backtracking для поиска всех перестановок
; true),
close(Stream).

%пример вызова generate_permutations([1, 2, 3], 'permutations.txt')


% combination_with_repetition(+N, +K, +Elements, -Combination)
combination_with_repetition(_, 0, _, []).
combination_with_repetition(N, K, Elements, [H|T]) :-
K > 0,
nth1(Index, Elements, H), Index =< N,
K1 is K - 1,
combination_with_repetition(N, K1, Elements, T).

% generate_combinations_with_repetition(+N, +K, +Elements, +Filename)
generate_combinations_with_repetition(N, K, Elements, Filename) :-
open(Filename, write, Stream),
( combination_with_repetition(N, K, Elements, Comb),
write(Stream, Comb), write(Stream, '\n'),
fail
; true
),
close(Stream).

%Пример вызова: `generate_combinations_with_repetition(3, 2, ['a', 'b', 'c'], 'combinations.txt').`
