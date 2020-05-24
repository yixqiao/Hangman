package me.yixqiao.hangman;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Main {
    static ArrayList<String> words;

    public static void main(String[] args) {
        loadWords();
        guessWord("pineapple");
    }

    private static void guessWord(String correctWord) {
        Game game = new Game(correctWord);
        ArrayList<String> candidates = new ArrayList<>();
        String word = game.word.toString();
        int[] freq = new int[26];

        for (String curW : words) {
            if (curW.length() == correctWord.length() && Pattern.matches(word, curW)) {
                candidates.add(curW);
                for(int c=0; c<curW.length(); c++){
                    freq[curW.charAt(c) - 97]++;
                }
            }
        }

        // for(int i=0; i<26; i++){
        //     System.out.printf("%s: %d\n", (char)(i+97), freq[i]);
        // }
    }

    private static void loadWords() {
        words = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("words/words.txt")));
            String line;
            while ((line = br.readLine()) != null && line.length() != 0) {
                words.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.printf("Read %d words.", words.size());
    }

}
