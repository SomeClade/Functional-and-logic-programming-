% Define surnames and professions
surname(Surname) :-
    member(Surname, [kuznetsov, tokarev, slesarev, rezchikov]).

profession(Profession) :-
    member(Profession, [blacksmith, turner, locksmith, carver]).

% Проверяет, что все элементы списка уникальны
all_different([]).
all_different([H|T]) :-
    \+ member(H, T),
    all_different(T).

% генерация всех вариантов
generate_arrangement(Arrangement) :-
    profession(ProfA), profession(ProfB), profession(ProfC), profession(ProfD),
    all_different([ProfA, ProfB, ProfC, ProfD]),
    surname(SurA), surname(SurB), surname(SurC), surname(SurD),
    all_different([SurA, SurB, SurC, SurD]),
    Arrangement = [sits(SurA, ProfA), sits(SurB, ProfB), sits(SurC, ProfC), sits(SurD, ProfD)].

% Check conditions of the puzzle
check_conditions(Arrangement) :-
    % The locksmith sits opposite Kuznetsov
    opposite(Arrangement, sits(kuznetsov, _), sits(_, locksmith)),
    % The carver sits opposite Rezchikov
    opposite(Arrangement, sits(rezchikov, _), sits(_, carver)),
    % The turner sits to the right of Slesarev
    right_left(Arrangement, sits(slesarev, _), sits(_, turner)),
    % Professions do not match with surnames
    \+ member(sits(kuznetsov, blacksmith), Arrangement),
    \+ member(sits(tokarev, turner), Arrangement),
    \+ member(sits(slesarev, locksmith), Arrangement),
    \+ member(sits(rezchikov, carver), Arrangement).

% Helper predicates for positioning
opposite(Arrangement, A, B) :-
    Arrangement = [A, _, B, _];
    Arrangement = [_, A, _, B];
    Arrangement = [B, _, A, _];
    Arrangement = [_, B, _, A].

right_left(Arrangement, Right, Left) :-
    Arrangement = [Right, Left, _, _];
    Arrangement = [_, Right, Left, _];
    Arrangement = [_, _, Right, Left];
    Arrangement = [Left, _, _, Right].


left_of_blacksmith(Arrangement, LeftOfBlacksmith) :-
    % Находим индекс кузнеца в расстановке
    nth0(IndexBlacksmith, Arrangement, sits(_, blacksmith)),
    % Количество участников
    length(Arrangement, Length),
    % Вычисляем индекс сидящего слева (предыдущий в списке, с учетом круговой расстановки)
    LeftIndex is (IndexBlacksmith - 1 + Length) mod Length,
    % Получаем фамилию сидящего слева от кузнеца
    nth0(LeftIndex, Arrangement, sits(LeftOfBlacksmith, _)).

% Решаем задачу
solve :-
    generate_arrangement(Arrangement),
    check_conditions(Arrangement),
    write('Расстановка: '), write(Arrangement), nl,
    left_of_blacksmith(Arrangement, LeftOfBlacksmith),
    write('Слева от кузнеца сидит: '), write(LeftOfBlacksmith), nl,
    fail.
