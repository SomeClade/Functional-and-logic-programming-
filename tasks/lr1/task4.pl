main :-
    retractall(asked(_,_)),
    fault(Problem),
    !,
    nl,
    write('The problem is '), write(Problem), write(.), nl.
main :-
    nl,
    write('The problem cannot be recognized.'), nl.

problem(disc_format) :-
    query('Does the computer show error cannot format').

problem(boot_failure) :-
    query('Does the computer show boot failure').

problem(bad_sector) :-
    query('Does the computer show bad sector error').

problem(cannot_read) :-
    query('Does the computer show cannot read from specified device').

problem(long_beep) :-
    query('Is there a long beep during bootup').

problem(short_beep) :-
    query('Is there a short beep during bootup').

problem(two_long_beeps) :-
    query('Are there two long beeps during bootup').

problem(two_short_beeps) :-
    query('Are there two short beeps during bootup').

problem(blank_display) :-
    query('Is there a blank display during bootup').

problem(repeating_short_beeps) :-
    query('Are there repeating short beeps during bootup').

problem(continuous_beeps) :-
    query('Is there a continuous beep during bootup').

problem(no_beep) :-
    query('Is there a beep during bootup').

problem(not_printing) :-
    query('Is there a problem with printing').

problem(missing_dots) :-
    query('Is there a missing character during printing').

problem(non_uniform_printing) :-
    query('Is there uniform printing').

problem(spread_ink) :-
    query('Is there spreading of ink during printing').

problem(paper_jam) :-
    query('Is there a paper jam during printing').

problem(out_of_paper) :-
    query('Is there out-of- paper error during printing').

fault(power_supply) :-
    problem(repeating_short_beeps),
    problem(continuous_beeps),
    problem(blank_display),
    problem(no_beep).

fault(display_adapter) :-
    problem(long_beep),
    problem(two_short_beeps),
    problem(blank_display),
    problem(no_beep).

fault(motherboard) :-
    problem(long_beep),
    problem(short_beep).

fault(hard_disc) :-
    problem(two_short_beeps),
    problem(blank_display).

fault(booting_problem) :-
    problem(bad_sector),
    problem(boot_failure).

fault(floppy_disk_unusable) :-
    problem(bad_sector),
    problem(cannot_read),
    problem(disc_format).

fault(printer_head) :-
    problem(not_printing),
    problem(missing_dots),
    problem(nonuniform_printing).

fault(ribbon) :-
    problem(not_printing),
    problem(missing_dots),
    problem(spread_ink).

fault(paper) :-
    problem(not_printing),
    problem(paper_jam),
    problem(out_of_paper).

%Добавление ошибок,без добавлени¤ новых вопросов

fault(operating_system_error) :-
    problem(boot_failure),
    not(problem(disc_format)),
    not(problem(cannot_read)),
    not(problem(bad_sector)).

fault(cable_connection) :-
    problem(no_beep),
    problem(blank_display),
    not(problem(long_beep)),
    not(problem(continuous_beeps)).
	
	% Добавление новых вопросов и новых ошибок
	
	% Добавление уникальных симптомов для новых проблем
fault(cooling_system) :-
    problem(overheating),
    not(problem(long_beep)),
    not(problem(blank_display)).

fault(memory_module) :-
    problem(memory_failure),
    not(problem(boot_failure)),
    not(problem(bad_sector)).

fault(graphics_card) :-
    problem(graphics_card_failure),
    not(problem(repeating_short_beeps)),
    not(problem(continuous_beeps)).

fault(operating_system) :-
    problem(os_corruption),
    not(problem(cannot_read)),
    not(problem(disc_format)).

fault(computer_virus) :-
    problem(virus_infection),
    not(problem(no_beep)),
    not(problem(not_printing)).

% Вопросы для дополнительной диагностики
problem(virus_infection):-
	query('Computer acting suspiciously, possible virus infection?').
problem(overheating):-
	query('The computer is overheating?').
problem(memory_failure):-
	query('Memory errors occur on the computer?').
problem(graphics_card_failure):-
	query('Problems with graphics or video?').
problem(os_corruption):-
	query('The computer reports damage to the operating system?').

query(Prompt) :-
    (   asked(Prompt, Reply) -> true
    ;   nl, write(Prompt), write(' (y/n)? '),
        read(X),(X = y -> Reply = y ; Reply = n),
	assert(asked(Prompt, Reply))
    ),
    Reply = y.
