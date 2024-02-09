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


men():- man(X), print(X), nl, fail.
women():- woman(X), print(X), nl, fail.
children(X):- parent(X,Y), print(Y), nl, fail.

mother(X,Y):- woman(X), parent(X,Y).
mother(X):- mother(Y,X), print(Y), nl, fail.

% Создадим вспомогательные предикаты

% Определяет, является ли X отцом Y
father(X, Y) :- man(X), parent(X, Y).

% Определяет, является ли X матерью Y
mother(X, Y) :- woman(X), parent(X, Y).

% Определяет братьев и сестер
sibling(X, Y) :- parent(Z, X), parent(Z, Y), X \= Y.

% Определяет сестер
sister(X, Y) :- woman(X), sibling(X, Y).

% Предикат, который проверяет, является ли X дедушкой Y
grand_pa(X, Y) :-parent(X, Z),parent(Z, Y),man(X).

% Предикат, который выводит всех дедушек X
grand_pas(X) :-parent(Z, X),father(Y, Z),print(Y), nl, fail.

% Предикат, который проверяет, являются ли X и Y дедушкой и внучкой или внучкой и дедушкой
grand_pa_and_da(X, Y) :-(grand_pa(X, Y) ; grand_pa(Y, X)).