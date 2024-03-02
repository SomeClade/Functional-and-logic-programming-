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
fail % ���������� backtracking ��� ������ ���� ������������
; true),
close(Stream).

%������ ������ generate_permutations([1, 2, 3], 'permutations.txt')
