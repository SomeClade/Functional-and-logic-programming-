% Чтение строк из файла
read_lines_from_file(FileName, Lines) :-
    setup_call_cleanup(
        open(FileName, read, Stream),
        read_stream_to_lines(Stream, Lines),
        close(Stream)
    ).

read_stream_to_lines(Stream, Lines) :-
    read_line_to_string(Stream, Line),
    (   Line == end_of_file
    ->  Lines = []
    ;   Lines = [Line|Tail],
        read_stream_to_lines(Stream, Tail)
    ).

% Запись списка строк в файл
write_lines_to_file(FileName, Lines) :-
    setup_call_cleanup(
        open(FileName, write, Stream),
        write_lines(Stream, Lines),
        close(Stream)
    ).

write_lines(_, []).
write_lines(Stream, [Line|Tail]) :-
    writeln(Stream, Line),
    write_lines(Stream, Tail).

% Задача 1: Длина наибольшей строки
max_line_length(Lines, MaxLength) :-
    maplist(string_length, Lines, Lengths),
    max_list(Lengths, MaxLength).

% Задача 2: Количество строк без пробелов
lines_without_spaces(Lines, Count) :-
    include(\+sub_string(_, _, _, _, " "), Lines, FilteredLines),
    length(FilteredLines, Count).

% Вспомогательные предикаты для вывода
print_max_line_length(FileName) :-
    read_lines_from_file(FileName, Lines),
    max_line_length(Lines, MaxLength),
    format('The longest line length is: ~w~n', [MaxLength]).

print_lines_without_spaces(FileName) :-
    read_lines_from_file(FileName, Lines),
    lines_without_spaces(Lines, Count),
    format('Lines without spaces: ~w~n', [Count]).

% Считаем количество 'A' в строке
count_a_in_string(Str, Count) :-
    sub_string(Str, _, 1, _, 'A'),
    sub_string(Str, 1, _, 0, SubStr),
    count_a_in_string(SubStr, TailCount),
    Count is TailCount + 1.
count_a_in_string(_, 0).

% Находим среднее количество 'A' в списке строк
average_a_in_lines(Lines, Average) :-
    maplist(count_a_in_string, Lines, Counts),
    sum_list(Counts, Total),
    length(Lines, NumLines),
    Average is Total / NumLines.

% Выводим строки с количеством 'A' выше среднего
print_lines_with_more_a_than_average(FileName) :-
    read_lines_from_file(FileName, Lines),
    average_a_in_lines(Lines, Average),
    include(lambda(Line, (count_a_in_string(Line, Count), Count > Average)), Lines, FilteredLines),
    maplist(writeln, FilteredLines).



% Пример вызова
% :- print_max_line_length('file.txt').
% :- print_lines_without_spaces('file.txt').
% :- print_lines_with_more_a_than_average('file.txt').
