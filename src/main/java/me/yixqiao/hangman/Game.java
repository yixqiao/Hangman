package me.yixqiao.hangman;

public class Game {
    public StringBuilder word;
    public String actualWord;
    public int wrong = 0;
    public boolean[] guessed = new boolean[26];

    public Game(int wLen) {
        word = new StringBuilder();
        for (int i = 0; i < wLen; i++)
            word.append(".");

    }

    public Game(String actualWord) {
        word = new StringBuilder();
        this.actualWord = actualWord.toLowerCase();
        for (int i = 0; i < actualWord.length(); i++)
            word.append(".");
    }

    public int guess(char c) {
        assert actualWord.length() == word.length();
        int changed = 0;
        for (int i = 0; i < word.length(); i++) {
            if (actualWord.charAt(i) == c) {
                word.setCharAt(i, c);
                changed++;
            }
        }
        if (changed == 0)
            wrong++;
        guessed[c-97] = true;
        return changed;
    }

    public boolean isOver(){
        for(int i=0; i<word.length(); i++){
            if(word.charAt(i)=='.')
                return false;
        }
        return true;
    }
}
