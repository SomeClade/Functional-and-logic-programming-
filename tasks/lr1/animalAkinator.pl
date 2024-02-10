main :-
    retractall(asked(_,_)),
    animal(Fa),
    !,
    nl,
    write('The problem is '), write(Fa), write(.), nl.
main :-
    nl,
    write('The problem cannot be recognized.'), nl.

fact(mamals,Answer) :-
    query('явл€етс€ ли животное млекопитающим?',Answer).

fact(bird,Answer) :-
    query('явл€етс€ ли животное птицей?',Answer).

fact(aquatic,Answer) :-
    query('∆ивет ли животное в воде?',Answer).

fact(predator,Answer) :-
    query('явл€етс€ ли животное хищником?',Answer).

fact(fur,Answer):-
    query('≈сть ли у животного шерсть?',Answer).

fact(domestic,Answer):-
    query('явл€етс€ ли животное домащним?',Answer).

fact(strip,Answer):-
	query('≈сть ли у животного полоски?',Answer).


animal(lion) :-
    fact(mamals,y),
    fact(predator,y),
    fact(fur,y),
    fact(aquatic,n),
    fact(bird,n),
    fact(domestic,n),
    fact(strip,n).

animal(tiger) :-
    fact(mamals,y),
    fact(predator,y),
    fact(fur,y),
    fact(aquatic,n),
    fact(bird,n),
    fact(strip,y),
    fact(domestic,n).

animal(whale) :-
    fact(mamals,y),
    fact(strip,n),
    fact(aquatic,y),
    fact(predator,y),
    fact(fur,n),
    fact(bird,n),
    fact(domestic,n).

animal(dolphin) :-
    fact(mamals,y),
    fact(aquatic,y),
    fact(predator,n),
    fact(fur,n),
    fact(strip,n),
    fact(bird,n),
    fact(domestic,n).

animal(cat):-
    fact(mamals,y),
    fact(strip,n),
    fact(predator,y),
    fact(fur,y),
    fact(aquatic,n),
    fact(bird,n),
    fact(domestic,y).


animal(cat_strip):-
    fact(mamals,y),
    fact(strip,y),
    fact(predator,y),
    fact(fur,y),
    fact(aquatic,n),
    fact(bird,n),
    fact(domestic,y).

animal(shark):-
    fact(aquatic,y),
    fact(predator,y),
    fact(mamals,n),
    fact(fur,n),
    fact(bird,n),
	fact(strip,n),
    fact(domestic,n).

animal(goldfish) :-
    fact(aquatic,y),
	fact(strip,n),
    fact(predator,n),
    fact(mamals,n),
    fact(fur,n),
    fact(bird,n),
    fact(domestic,y).

animal(parrot) :-
    fact(mamals,n),
    fact(bird,y),
	fact(strip,y),
	fact(strip,n),
    fact(aquatic,n),
    fact(predator,n),
    fact(fur,n),
    fact(domestic,y).

animal(penguin) :-
    fact(mamals,n),
    fact(bird,y),
	fact(strip,n),
    fact(aquatic,y),
    fact(predator,n),
    fact(fur,n),
    fact(domestic,n).

animal(cow) :-
    fact(mamals,y),
	fact(strip,n),
    fact(bird,n),
    fact(aquatic,n),
    fact(predator,n),
    fact(fur,y),
    fact(domestic,y).

animal(bear) :-
    fact(mamals,y),
    fact(bird,n),
    fact(aquatic,n),
    fact(predator,y),
    fact(fur,y),
	fact(strip,n),
    fact(domestic,n).

animal(wolf) :-
    fact(mamals,y),
    fact(bird,n),
    fact(aquatic,n),
    fact(predator,y),
    fact(fur,y),
    fact(strip,n),
    fact(domestic,n).

animal(horse) :-
    fact(mamals,y),
    fact(bird,n),
    fact(aquatic,n),
    fact(predator,n),
    fact(fur,y),
    fact(strip,n),
    fact(domestic,y).

animal(zebra) :-
    fact(mamals,y),
    fact(bird,n),
    fact(aquatic,n),
    fact(predator,n),
    fact(fur,y),
    fact(strip,y),
    fact(domestic,n).

animal(elephant) :-
    fact(mamals,y),
    fact(bird,n),
    fact(aquatic,n),
    fact(predator,n),
    fact(fur,n),
    fact(strip,n),
    fact(domestic,n).

animal(crocodile) :-
    fact(mamals,n),
    fact(bird,n),
    fact(strip,n),
    fact(aquatic,y),
    fact(predator,y),
    fact(fur,n),
    fact(domestic,n).

animal(eagle) :-
    fact(mamals,n),
    fact(strip,n),
    fact(bird,y),
    fact(aquatic,n),
    fact(predator,y),
    fact(fur,n),
    fact(domestic,n).

animal(snake) :-
    fact(mamals,n),
    fact(bird,n),
    fact(aquatic,n),
    fact(predator,y),
    fact(fur,n),
    fact(strip,n),
    fact(domestic,n).

query(Prompt,Answer) :-
    (   asked(Prompt, Reply) -> true
    ;   nl, write(Prompt), write(' (y/n)? '),
        read(X),(X = y -> Reply = y ; Reply = n),
	assert(asked(Prompt, Reply))
    ),
    Reply = Answer.










