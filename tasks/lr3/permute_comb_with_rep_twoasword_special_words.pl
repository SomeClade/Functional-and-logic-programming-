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

%������ ������: `generate_combinations_with_repetition(3, 2, ['a', 'b', 'c'], 'combinations.txt').`

% ��������� ���� ���������� ���� � ������ �� � ����
generate_double_letter_words(Filename) :-
open(Filename, write, Stream),
alphabet(Alphabet),
findall(Word, (combination(5, Alphabet, Word), valid_double_letter_word(Word)), Words),
write_list_to_stream(Words, Stream),
close(Stream).

% �������� ��� ��������, ��� ����� �������� ����� ���� �����, ������������� ��� ����
valid_double_letter_word(Word) :-
sort(Word, Sorted),
length(Sorted, 4), % ������ ���� 4 ���������� �������, ���� ���� ����������� ������
msort(Word, Msorted),
Word == Msorted.

% ��������� ���� ���������� �������� �����
combination(0, _, []) :- !.
combination(Len, Alphabet, [H|T]) :-
Len > 0,
member(H, Alphabet),
Len1 is Len - 1,
combination(Len1, Alphabet, T).

% ������ ������ ������� � �����
write_list_to_stream([], _).
write_list_to_stream([H|T], Stream) :-
atomic_list_concat(H, '', Atom), % ������������� ������ �������� � ������
write(Stream, Atom), write(Stream, '\n'),
write_list_to_stream(T, Stream).

% ������� ��� ��������� ����
alphabet(['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z']).

% ������ ������ generate_double_letter_words('double_letter_words.txt').
