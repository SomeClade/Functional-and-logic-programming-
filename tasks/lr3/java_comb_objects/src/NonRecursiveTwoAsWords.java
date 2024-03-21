import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class NonRecursiveTwoAsWords {
    // Определение класса для хранения состояния каждого "вызова"
    private static class State {
        int n;
        String current;
        int countA;

        State(int n, String current, int countA) {
            this.n = n;
            this.current = current;
            this.countA = countA;
        }
    }

    public static void generateWords(int n, FileWriter writer) throws IOException {
        // Использование стека для имитации стека вызовов рекурсии
        Stack<State> stack = new Stack<>();
        stack.push(new State(n, "", 0));

        while (!stack.isEmpty()) {
            State state = stack.pop();

            if (state.current.length() == n) {
                if (state.countA == 2) {
                    writer.write(state.current);
                    writer.write('\n');
                }
                continue;
            }

            if (state.countA < 2) {
                stack.push(new State(n, state.current + 'a', state.countA + 1));
            }

            for (char c = 'b'; c <= 'z'; c++) {
                if (state.current.indexOf(c) == -1) { // Обеспечиваем отсутствие повторений
                    stack.push(new State(n, state.current + c, state.countA));
                }
            }
        }
    }

    public static void main(String[] args) {
        try (FileWriter writer = new FileWriter("nonrecursivetwo_as_words.txt")) {
            generateWords(5, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
