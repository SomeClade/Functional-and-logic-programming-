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

%без создания вспомогательных предикатов

grand_pas(X) :- parent(Z, X),parent(Y, Z),man(Y),print(Y), nl, fail.


% РїСЂРѕРІРµСЂСЏРµС‚, СЏРІР»СЏРµС‚СЃСЏ Р»Рё X Рё Y РґРµРґСѓС€РєРѕР№ Рё
% РІРЅСѓС‡РєР%ѕР№ РёР»Рё РІРЅСѓС‡РєРѕР№ Рё РґРµРґСѓС€РєРѕР№.

aunt(X, Y) :- parent(Z, Y),sister(X, Z).

sister(X, Y) :- parent(Z, X),parent(Z, Y),woman(X),X \= Y.

%Р’С‹РІРѕРґ РІСЃРµС… С‚РµС‚СЊ X

aunts(X) :- parent(Z, X),sister(Y, Z),print(Y), nl, fail.


% Предикат, определяющий дедушку
grand_pa(X, Y) :- man(X), parent(X, Z), parent(Z, Y).

% Предикат, определяющий внучку или внука
grand_child(Y, X) :- parent(Z, Y), parent(X, Z), (man(Y); woman(Y)).

% Итоговый предикат, проверяющий отношения дедушка и внучки или внучки и дедушки
grand_pa_and_da(X, Y) :-
    (grand_pa(X, Y), woman(Y));
    (grand_pa(Y, X), woman(X)).


% Создадим вспомогательные предикаты
% Определяет, является ли X отцом Y
father(X, Y) :- man(X), parent(X, Y).

% Определяет, является ли X матерью Y
mother(X, Y) :- woman(X), parent(X, Y).
% sibling(+X, +Y)
% Определяет братьев и сестер
sibling(X, Y) :- parent(Z, X), parent(Z, Y), X \= Y.

% sister(+X, -Y)
% Определяет сестер
sister(X, Y) :- woman(X), sibling(X, Y).

% grand_pas(+X)
% Предикат, который выводит всех дедушек X
grand_pas(X) :- parent(Z, X), parent(Y, Z), man(Y), print(Y), nl, fail.

% grand_pa(+X, -Y)
% Предикат, определяющий дедушку
grand_pa(X, Y) :- man(X), parent(X, Z), parent(Z, Y).

% grand_child(-Y, +X)
% Предикат, определяющий внучку или внука
grand_child(Y, X) :- parent(Z, Y), parent(X, Z).

% grand_pa_and_da(+X, -Y) или grand_pa_and_da(-X, +Y)
% Итоговый предикат, проверяющий отношения дедушка и внучки или внучки и дедушки
grand_pa_and_da(X, Y) :-
    (grand_pa(X, Y), woman(Y));
    (grand_pa(Y, X), woman(X)).

% aunt(+X, -Y)
% Предикат, который проверяет, является ли X тетей Y
aunt(X, Y) :- parent(Z, Y), sister(X, Z).

% aunts(+X)
% Предикат, который выводит всех тетей X
aunts(X) :- parent(Z, X), sister(Y, Z), print(Y), nl, fail.