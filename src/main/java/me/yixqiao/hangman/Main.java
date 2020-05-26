package me.yixqiao.hangman;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    static ArrayList<String> words;
    static ArrayList<String> candidates;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        loadWords();
        System.out.print("Word: ");
        guessWord(sc.next());
        sc.close();
    }

    private static void guessWord(String correctWord) {
        Game game = new Game(correctWord);
        while (!game.isOver()) {
            char c = pickLetter(game);
            game.guess(c);
            System.out.printf("%s: %s\n", c, game.word.toString().replaceAll("\\.", "_"));
        }
        System.out.println("\n\n\n\n--------------------------------------------------------------------------------");
        System.out.printf("%d incorrect %s. \n", game.wrong, (game.wrong == 1 ? "guess" : "guesses"));
    }

    private static char pickLetter(Game game) {
        candidates = new ArrayList<>();
        String word = game.word.toString();
        int[] freq = new int[26];

        for (String curW : words) {
            if (curW.length() == game.word.length() && Pattern.matches(word, curW)) {
                candidates.add(curW);
                // boolean[] appears = new boolean[26];
                for (int c = 0; c < curW.length(); c++) {
                    freq[curW.charAt(c) - 97]++;
                    // appears[curW.charAt(c) - 97] = true;
                }
                // for (int i = 0; i < 26; i++) {
                //     if (appears[i])
                //         freq[i]++;
                // }
            }
        }


        int count = 12;
        double interval = Math.max(1.0, (double) candidates.size() / (count - 1));
        System.out.print("\n\n\nCandidates:  ");
        for (double i = 0; i < candidates.size(); i += interval) {
            System.out.print(candidates.get((int) i) + "  ");
        }
        System.out.printf("(%d)", candidates.size());
        System.out.println("\n");


        int maxI = -1, maxV = -1;
        for (int i = 0; i < 26; i++) {
            if (freq[i] > maxV && !game.guessed[i]) {
                maxV = freq[i];
                maxI = i;
            }
        }
        return (char) (maxI + 97);
    }

    private static void loadWords() {
        System.out.println("\nBegin loading...");
        words = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("words/words_alpha.txt")));
            String line;
            while ((line = br.readLine()) != null && line.length() != 0) {
                words.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.printf("Read %d words.\n\n", words.size());
    }

}
