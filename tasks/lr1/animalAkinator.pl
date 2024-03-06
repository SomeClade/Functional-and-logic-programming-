:- dynamic asked/2.

% Животные и их характеристики
animal(lion, [mammal-yes, aquatic-no, predator-yes, fur-yes, domestic-no, stripes-no]).
animal(tiger, [mammal-yes, aquatic-no, predator-yes, fur-yes, domestic-no, stripes-yes]).
animal(whale, [mammal-yes, aquatic-yes, predator-no, fur-no, domestic-no, stripes-no]).
animal(dolphin, [mammal-yes, aquatic-yes, predator-yes, fur-no, domestic-no, stripes-no]).
animal(cat, [mammal-yes, aquatic-no, predator-yes, fur-yes, domestic-yes, stripes-no]).
animal(goldfish, [mammal-no, aquatic-yes, predator-no, fur-no, domestic-yes, stripes-no]).
animal(shark, [mammal-no, aquatic-yes, predator-yes, fur-no, domestic-no, stripes-no]).

% Вопросы для определения животного
question(mammal, 'Является ли животное млекопитающим?').
question(aquatic, 'Живет ли животное в воде?').
question(predator, 'Является ли животное хищником?').
question(fur, 'Есть ли у животного шерсть?').
question(domestic, 'Является ли животное домашним?').
question(stripes, 'Есть ли у животного полосы?').

% Главный цикл
main :-
    retractall(asked(_,_)),
    findall(A, animal(A, _), Animals),
    identify(Animals, Result),
    respond(Result),
    clear_memory.

% Идентификация животного
% identify(+Animals, -Result)
identify([Animal], Animal) :- !.  % Если осталось одно животное
identify(_, unknown) :-           % Если не осталось животных
    not(can_ask_more), !.

identify(Animals, Result) :-
    select_question(Animals, Question),
    ask(Question, Reply),
    update_animals(Animals, Question, Reply, UpdatedAnimals),
    identify(UpdatedAnimals, Result).

% select_question(+Animals, -Question)
select_question(Animals, Question) :-
    question(Fact, Q),
    not(asked(Fact, _)),
    is_relevant(Fact, Animals),
    Question = question(Fact, Q),
    !.

% is_relevant(+Fact, +Animals)
is_relevant(Fact, Animals) :-
    findall(Val, (member(A, Animals), animal(A, Traits), member(Fact-Val, Traits)), Vals),
    list_to_set(Vals, UniqueVals),
    length(UniqueVals, Length),
    Length > 1.

% ask(+Question, -Reply)
ask(question(Fact, Text), Reply) :-
    (   asked(Fact, Reply) -> true
    ;   nl, write(Text), write(' (y/n)? '),
        read(Reply),
        assert(asked(Fact, Reply))
    ).

% update_animals(+Animals, +Question, +Reply, -UpdatedAnimals)
update_animals(Animals, question(Fact, _), Reply, UpdatedAnimals) :-
    include(is_match(Fact, Reply), Animals, UpdatedAnimals).


% is_match(+Fact, +Reply, +Animal)
is_match(Fact, Reply, Animal) :-
    animal(Animal, Traits),
    member(Fact-Val, Traits),
    match_reply(Reply, Val).

match_reply(y, yes).
match_reply(n, no).

% respond(+Result)
respond(unknown) :-
    write('Не могу определить животное на основе данных ответов.'), nl.


respond(Animal) :-
    write('Думаю, это '), write(Animal), write('.'), nl.

can_ask_more :-
    question(Fact, _),
    not(asked(Fact, _)),
    !.

clear_memory :-
    retractall(asked(_, _)).

% Запустить главный цикл
start :-
    main.
