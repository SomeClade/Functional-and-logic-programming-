man(voeneg).
man(ratibor).
man(boguslav).
man(velerad).
man(duhovlad).
man(svyatoslav).
man(dobrozhir).
man(bogomil).
man(zlatomir).

woman(goluba).
woman(lubomila).
woman(bratislava).
woman(veslava).
woman(zhdana).
woman(bozhedara).
woman(broneslava).
woman(veselina).
woman(zdislava).

parent(voeneg,ratibor).
parent(voeneg,bratislava).
parent(voeneg,velerad).
parent(voeneg,zhdana).

parent(goluba,ratibor).
parent(goluba,bratislava).
parent(goluba,velerad).
parent(goluba,zhdana).

parent(ratibor,svyatoslav).
parent(ratibor,dobrozhir).
parent(lubomila,svyatoslav).
parent(lubomila,dobrozhir).

parent(boguslav,bogomil).
parent(boguslav,bozhedara).
parent(bratislava,bogomil).
parent(bratislava,bozhedara).

parent(velerad,broneslava).
parent(velerad,veselina).
parent(veslava,broneslava).
parent(veslava,veselina).

parent(duhovlad,zdislava).
parent(duhovlad,zlatomir).
parent(zhdana,zdislava).
parent(zhdana,zlatomir).

% men()
men() :- man(X), print(X), nl, fail.

% women()
women() :- woman(X), print(X), nl, fail.

% children(+Parent)
children(X) :- parent(X, Y), print(Y), nl, fail.

% mother(+Child, -Mother)
mother(X, Y) :- woman(X), parent(X, Y).

% mother(-Mother)
mother(X) :- mother(Y, X), print(Y), nl, fail.

% sons(+Parent, -Son)
sons(X, Y) :- parent(X, Y), man(Y), print(Y), nl, fail.

% son(+Parent)
son(X) :- parent(X, Y), man(Y), print(Y), nl, fail.

% check_married(+PersonOne, +PersonTwo)
check_married(X, Y) :- parent(X, Z), parent(Y, Z), X \= Y.

% husband(+Wife, -Husband)
husband(X, Y) :- check_married(Y, X), man(Y).

% husband(+Wife)
husband(X) :- check_married(Y, X), man(Y), print(Y), nl, fail.