% max_digit_down(+Number, -MaxDigit)
max_digit_down(Number, MaxDigit) :- max_digit_down(Number, 0, MaxDigit).

% max_digit_down(+Number, +CurrentMax, -MaxDigit)
% Вспомогательный предикат с аккумулятором( то есть для сохранения промежуточных результатов)
max_digit_down(0, CurrentMax, CurrentMax) :- !.
max_digit_down(Number, CurrentMax, MaxDigit) :-
    Number > 0,
    NextDigit is Number mod 10,
    NextNumber is Number // 10,
    NewMax is max(CurrentMax, NextDigit),
    max_digit_down(NextNumber, NewMax, MaxDigit).


% max_digit_up(+Number, -MaxDigit)
max_digit_up(Number, MaxDigit) :- max_digit_up(Number, 0, MaxDigit).

% max_digit_up(+Number, +Acc, -MaxDigit)
% Acc - аккумулятор, хранит текущее максимальное значение.
max_digit_up(0, Acc, Acc).
max_digit_up(Number, Acc, MaxDigit) :-
    Number > 0,
    NextDigit is Number mod 10,
    NewAcc is max(Acc, NextDigit),
    NextNumber is Number // 10,
    max_digit_up(NextNumber, NewAcc, MaxDigit).


% min_odd_digit_down +Number, -MinOddDigit
% Number - число, MinOddDigit - минимальная нечетная цифра числа.
min_odd_digit_down(Number, MinOddDigit) :- min_odd_digit_down(Number, -1, MinOddDigit).

% Вспомогательный предикат с аккумулятором
min_odd_digit_down(0, CurrentMin, CurrentMin) :- CurrentMin >= 0, !.
min_odd_digit_down(0, _, -1) :- !.  % Если не найдено нечетных цифр
min_odd_digit_down(Number, CurrentMin, MinOddDigit) :-
    Number > 0,
    NextDigit is Number mod 10,
    NextNumber is Number // 10,
    (NextDigit mod 2 =:= 1 -> NewMin = min(CurrentMin, NextDigit); NewMin = CurrentMin),
    min_odd_digit_down(NextNumber, NewMin, MinOddDigit).

% min_odd_digit_up +Number, -MinOddDigit
% Number - число, из которого нужно найти минимальную нечетную цифру.
% MinOddDigit - минимальная нечетная цифра числа.
min_odd_digit_up(Number, MinOddDigit) :- 
    min_odd_digit_up_helper(Number, -1, MinOddDigit).  % Инициализация аккумулятора значением -1.

% min_odd_digit_up_helper(+Number, +Acc, -MinOddDigit) - вспомогательный предикат с аккумулятором.
% Number - текущее число для обработки.
% Acc - аккумулятор, хранящий текущую минимальную нечетную цифру.
% MinOddDigit - результат, минимальная нечетная цифра числа.
min_odd_digit_up_helper(0, Acc, Acc) :- Acc >= 0, !.  % Базовый случай, если найдена хотя бы одна нечетная цифра.
min_odd_digit_up_helper(0, Acc, -1) :- Acc = -1, !.  % Базовый случай, если не найдено нечетных цифр.
min_odd_digit_up_helper(Number, Acc, MinOddDigit) :-
    Number > 0,
    NextDigit is Number mod 10,
    NextNumber is Number // 10,
    (NextDigit mod 2 =:= 1 ->  % Если цифра нечетная,
        (Acc = -1 -> NewAcc = NextDigit; NewAcc = min(Acc, NextDigit))  % и аккумулятор не инициализирован, обновляем его.
    ;   NewAcc = Acc),  % Иначе сохраняем текущее значение аккумулятора.
    min_odd_digit_up_helper(NextNumber, NewAcc, MinOddDigit).

% gcd_down +A, +B, -GCD (алгоритм Эвклида)
% A, B - числа, для которых нужно найти НОД, GCD - наибольший общий делитель.
gcd_down(A, 0, A) :- !.
gcd_down(A, B, GCD) :-
    B > 0,
    Remainder is A mod B,
    gcd_down(B, Remainder, GCD).


% НОД рекурсия вверх  gcd_up +A, +B, -GCD
% Инициализирующий предикат, который просто запускает рекурсивный процесс
gcd_up(A, B, GCD) :-
    gcd_up_helper(A, B, 1, GCD).

% Вспомогательный предикат с "аккумулятором", который здесь используется для передачи текущего НОД "вверх" по рекурсии
% A и B - числа, для которых ищем НОД
% CurrentGCD - текущий НОД, который изначально устанавливается в 1
% GCD - итоговый НОД, который будет возвращен
gcd_up_helper(0, B, CurrentGCD, GCD) :-
    !, GCD is max(CurrentGCD, B).
gcd_up_helper(A, 0, CurrentGCD, GCD) :-
    !, GCD is max(CurrentGCD, A).
gcd_up_helper(A, B, CurrentGCD, GCD) :-
    A >= B,
    A1 is A - B,
    gcd_up_helper(A1, B, CurrentGCD, GCD).
gcd_up_helper(A, B, CurrentGCD, GCD) :-
    B > A,
    gcd_up_helper(A, B - A, CurrentGCD, GCD).
