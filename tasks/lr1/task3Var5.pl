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

% проверяет, является ли X дедушкой Y. Дедушкой является мужчина, который является родителем родителя Y. 

grand_pa(X, Y) :- parent(X, Z), parent(Z, Y),man(X).

grand_pas(X) :- parent(Z, X),parent(Y, Z),man(Y),print(Y), nl, fail.

% проверяет, является ли X и Y дедушкой и внучкой или внучкой и дедушкой.

grand_pa_and_da(X, Y) :- grand_pa(X, Y); grand_pa(Y, X).

%  Предикаты для проверки тети и вывода всех теть

aunt(X, Y) :- parent(Z, Y),sister(X, Z).

sister(X, Y) :- parent(Z, X),parent(Z, Y),woman(X),X \= Y.

%Вывод всех теть X

aunts(X) :- parent(Z, X),sister(Y, Z),print(Y), nl, fail.


