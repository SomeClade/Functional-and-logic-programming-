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

