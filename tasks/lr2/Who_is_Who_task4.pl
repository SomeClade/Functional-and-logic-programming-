% Проверяем, включен ли элемент в список
in_list(A, B) :- member(B, A).

% Предикат для решения задачи с использованием перебора
solve :-
    Professions = [_, _, _, _],
    
    % Присваиваем каждому из четырех людей профессию
    in_list(Professions, [voronov, Profession1]),
    in_list(Professions, [pavlov, Profession2]),
    in_list(Professions, [levitskiy, Profession3]),
    in_list(Professions, [saharov, Profession4]),

    % Список профессий для перебора
    in_list(Professions, [_, tancer]),
    in_list(Professions, [_, hudozhnik]),
    in_list(Professions, [_, pevec]),
    in_list(Professions, [_, pisatel]),

    % Условия из задания
    % Воронов и Левицкий не могут быть певцом, так как были в зале
    Profession1 \= pevec,
    Profession3 \= pevec,

    % Павлов и писатель позировали художнику, значит, Павлов не художник
    Profession2 \= hudozhnik,

    % Писатель написал биографическую повесть о Сахарове, значит, Сахаров не писатель
    Profession4 \= pisatel,

    % Воронов не слышал о Левицком, следовательно, Воронов не писатель (так как писатель собирается написать о Воронове)
    Profession1 \= pisatel,

    % Павлов не может быть писателем, так как позировал художнику вместе с писателем
    Profession2 \= pisatel,

    % Выводим распределение профессий
    write('Voronov - '), write(Profession1), nl,
    write('Pavlov - '), write(Profession2), nl,
    write('Levitskiy - '), write(Profession3), nl,
    write('Saharov - '), write(Profession4), nl.

% Запускаем решение
:- solve.
