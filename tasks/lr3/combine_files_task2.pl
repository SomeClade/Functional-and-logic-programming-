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


% Пример вызова
% :- print_max_line_length('file.txt').
% :- print_lines_without_spaces('file.txt').
