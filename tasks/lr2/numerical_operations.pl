%max(+X,+Y,+U,-Z)

max(X, Y, U, Z) :-
    TempMax is max(X, max(Y, U)),
    Z = TempMax.
