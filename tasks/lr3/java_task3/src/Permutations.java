import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Permutations {
    public static void permute(List<Character> out, List<Character> in, FileWriter writer) throws IOException {
        if (in.isEmpty()) {
            for (char c : out) {
                writer.write(c);
            }
            writer.write('\n');
            return;
        }
        for (int i = 0; i < in.size(); i++) {
            List<Character> newIn = new ArrayList<>(in);
            List<Character> newOut = new ArrayList<>(out);
            newOut.add(newIn.remove(i));
            permute(newOut, newIn, writer);
        }
    }

    public static void main(String[] args) {
        try (FileWriter writer = new FileWriter("permutations.txt")) {
            permute(new ArrayList<>(), List.of('a', 'b', 'c'), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
