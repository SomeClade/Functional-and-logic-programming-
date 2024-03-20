% Основной предикат для записи подходящих слов в файл
generate_and_write_valid_words(FileName) :-
    open(FileName, write, Stream),
    findall(Word, generate_valid_word(Word), Words),
    maplist(write_line(Stream), Words),
    close(Stream).

% Предикат для записи слова в файл
write_line(Stream, Word) :-
    write(Stream, Word), write(Stream, '\n').

% Генерация валидного слова
generate_valid_word(Word) :-
    % Список возможных символов
    PossibleChars = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k'],
    % Генерация слова из 7 букв
    length(Chars, 7),
    maplist(select_char(PossibleChars), Chars),
    % Преобразование списка символов в строку
    atom_chars(Word, Chars),
    % Проверка условий слова
    valid_word(Word).

% Выбор символа из списка
select_char(PossibleChars, Char) :-
    random_member(Char, PossibleChars).

% Предикат для проверки слова
% +Word - слово для проверки
valid_word(Word) :-
    % Преобразовать строку в список символов
    string_chars(Word, Chars),
    % Убедиться, что слово состоит из 7 букв
    length(Chars, 7),
    % Найти букву, которая повторяется два раза
    find_letter_repeats(Chars, Letter1, 2),
    % Найти другую букву, которая повторяется три раза
    find_letter_repeats(Chars, Letter2, 3),
    % Убедиться, что выбранные буквы не совпадают
    Letter1 \= Letter2,
    % Удалить найденные буквы из списка
    delete(Chars, Letter1, TempChars1),
    delete(TempChars1, Letter2, TempChars2),
    % Убедиться, что оставшиеся буквы уникальны
    all_unique(TempChars2).

% Предикат для нахождения буквы, которая повторяется заданное количество раз
find_letter_repeats(Chars, Letter, Count) :-
    member(Letter, Chars),
    count_occurrences(Chars, Letter, Count).

% Предикат для подсчета количества вхождений элемента в список
count_occurrences([], _, 0).
count_occurrences([X|T], X, Count) :-
    count_occurrences(T, X, RestCount),
    Count is RestCount + 1.
count_occurrences([Y|T], X, Count) :-
    X \= Y,
    count_occurrences(T, X, Count).

% Предикат для проверки, что все элементы списка уникальны
all_unique([]).
all_unique([H|T]) :-
    \+ member(H, T),
    all_unique(T).

% Предикат для записи слова в файл и вывода его в консоль
write_line(Stream, Word) :-
    % Вывод слова в консоль
    write(Word), nl,
    % Запись слова в файл
    write(Stream, Word), write(Stream, '\n').



% Запуск генерации слов и запись их в файл
% ?- generate_and_write_valid_words('valid_words.txt').
