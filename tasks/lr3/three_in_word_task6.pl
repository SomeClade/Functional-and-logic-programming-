% Предикат для создания всех слов длины 8 из алфавита {a,b,c,d,e,f}, состоящих ровно из трех различных букв.
% generate_three_letter_words(+Alphabet, +FileName)
generate_three_letter_words(Alphabet, FileName) :-
    open(FileName, write, Stream), 
    findall(Word, (length(Word, 8),
                   maplist(member_of_alphabet(Alphabet), Word),  
                   three_different_letters(Word)), 
            Words),
    maplist(write_word(Stream), Words),
    close(Stream).

% Вспомогательный предикат для проверки, что символ принадлежит алфавиту.
% member_of_alphabet(+Alphabet, ?Char)
member_of_alphabet(Alphabet, Char) :-
    member(Char, Alphabet).

% Предикат для проверки, что в слове ровно три различные буквы.
% three_different_letters(+Word)
three_different_letters(Word) :-
    sort(Word, UniqueLetters),
    length(UniqueLetters, 3).

% Предикат для записи слова в файл.
% write_word(+Stream, +Word)
write_word(Stream, Word) :-
    atom_chars(WordAtom, Word),  % Преобразуем список символов в атом
    write(Stream, WordAtom),     % Пишем атом в файл
    write(Stream, '\n').         % Новая строка после каждого слова

% Пример вызова: generate_three_letter_words([a,b,c,d,e,f], 'output.txt').
