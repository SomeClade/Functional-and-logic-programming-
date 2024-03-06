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

% Генерация всех комбинаций слов и запись их в файл
% generate_double_letter_words(+Filename)
generate_double_letter_words(Filename) :-
open(Filename, write, Stream),
alphabet(Alphabet),
findall(Word, (combination(5, Alphabet, Word), valid_double_letter_word(Word)), Words),
write_list_to_stream(Words, Stream),
close(Stream).

% Предикат для проверки, что слово содержит ровно одну букву, повторяющуюся два раза
% valid_double_letter_word(+Word)
valid_double_letter_word(Word) :-
sort(Word, Sorted),
length(Sorted, 4), % Должно быть 4 уникальных символа, если один повторяется дважды
msort(Word, Msorted),
Word == Msorted.

% Генерация всех комбинаций заданной длины
% combination(+Len, +Alphabet, -Word)
combination(0, _, []) :- !.
combination(Len, Alphabet, [H|T]) :-
Len > 0,
member(H, Alphabet),
Len1 is Len - 1,
combination(Len1, Alphabet, T).

% Запись списка списков в поток
% write_list_to_stream(+List, +Stream)

write_list_to_stream([], _).
write_list_to_stream([H|T], Stream) :-
atomic_list_concat(H, '', Atom), % Преобразовать список символов в строку
write(Stream, Atom), write(Stream, '\n'),
write_list_to_stream(T, Stream).

% Алфавит для генерации слов
alphabet(['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z']).

% Пример вызова generate_double_letter_words('double_letter_words.txt').

% Генерация всех специальных слов и запись их в файл
% generate_special_words(+Filename)
generate_special_words(Filename) :-
open(Filename, write, Stream),
alphabet(Alphabet),
findall(Word, generate_word(Alphabet, Word), Words),
write_list_to_stream(Words, Stream),
close(Stream).

% Генерация слова с двумя буквами, повторяющимися 2 и 3 раза
% generate_word(+Alphabet, -Word)
generate_word(Alphabet, Word) :-
select(D, Alphabet, AlphabetWithoutD), % Буква, которая будет повторяться два раза
select(T, AlphabetWithoutD, _), % Буква, которая будет повторяться три раза
D \= T, % Буквы различны
combination(2, Alphabet, Others), % Выбрать две уникальные буквы
all_different(Others), % Убедиться, что они различны
\+ member(D, Others), % Убедиться, что они отличны от D и T
\+ member(T, Others),
WordList = [D, D, T, T, T | Others], % Формирование списка букв слова
permutation(WordList, Word), % Генерация всех перестановок
sort([D, T | Others], [D, T | Others]). % Убедиться, что выбранные буквы в порядке для уникальности слова

% Запись списка списков в поток
% write_list_to_stream(+List, +Stream)
write_list_to_stream([], _).
write_list_to_stream([H|T], Stream) :-
atomic_list_concat(H, '', Atom),
write(Stream, Atom), write(Stream, '\n'),
write_list_to_stream(T, Stream).

% Алфавит для генерации слов
alphabet(['a', 'b', 'c', 'd', 'e', 'f', 'g']).

% Предикат для генерации комбинаций без повторений
% combination(+K, +L, -Combination)
combination(0, _, []).
combination(K, L, [H|T]) :-
K > 0,
select(H, L, R),
K1 is K - 1,
combination(K1, R, T).

% Проверка, что все элементы списка различны
% all_different(+List)
all_different([]).
all_different([H|T]) :-
\+ member(H, T),
all_different(T).

% Пример вызова generate_special_words('special_words.txt').
