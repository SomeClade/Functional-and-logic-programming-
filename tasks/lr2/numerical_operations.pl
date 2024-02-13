%max(+X,+Y,+U,-Z)

max(X, Y, U, Z) :-
    TempMax is max(X, max(Y, U)),
    Z = TempMax.
%Факториал рекурсия вниз +N,-X

fact_down(0, 1).
fact_down(N, X) :-
    N > 0,
    N1 is N - 1,
    fact_down(N1, X1),
    X is N * X1.

%Рекурсия вверх (+N,-X)
fact_up(N, X) :- fact_up_helper(N, 1, X).

fact_up_helper(0, Acc, Acc).
fact_up_helper(N, Acc, X) :-
    N > 0,
    N1 is N - 1,
    Acc1 is N * Acc,
    fact_up_helper(N1, Acc1, X).
