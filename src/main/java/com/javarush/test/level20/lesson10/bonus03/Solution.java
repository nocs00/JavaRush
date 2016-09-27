package com.javarush.test.level20.lesson10.bonus03;

import java.util.ArrayList;
import java.util.List;

/* Кроссворд
1. Дан двумерный массив, который содержит буквы английского алфавита в нижнем регистре.
2. Метод detectAllWords должен найти все слова из words в массиве crossword.
3. Элемент(startX, startY) должен соответствовать первой букве слова, элемент(endX, endY) - последней.
text - это само слово, располагается между начальным и конечным элементами
4. Все слова есть в массиве.
5. Слова могут быть расположены горизонтально, вертикально и по диагонали как в нормальном, так и в обратном порядке.
6. Метод main не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        List<Word> words = detectAllWords(crossword, "home", "same", "emoh", "emas", "fderlk", "klredf", "fulmp", "poeejj", "jjeeop",
                "pmluf", "kovhj", "jhvok", "lprr", "rrpl", "lprr", "o", "", null, "test", "eo", "oe");
        for (Word word : words)
            System.out.println(word);
    }

    public static boolean checkFirstLetter(char firstLetter, int x, int y, int[][] crossword, String word) {
        final List<String> tryWords = new ArrayList<>();

        for (int i = 0; i < 8; i++)
            tryWords.add("");

        for (int i = 0; i < word.length(); i++) {
            try { tryWords.set(0, tryWords.get(0) + (char)crossword[y][x + i]);
            } catch (Exception e) {}
            try { tryWords.set(1, tryWords.get(1) + (char)crossword[y][x - i]);
            } catch (Exception e) {}
            try { tryWords.set(2, tryWords.get(2) + (char)crossword[y - i][x]);
            } catch (Exception e) {}
            try {tryWords.set(3, tryWords.get(3) + (char)crossword[y + i][x]);
            } catch (Exception e) {}
            try {tryWords.set(4, tryWords.get(4) + (char)crossword[y + i][x + i]);
            } catch (Exception e) {}
            try {tryWords.set(5, tryWords.get(5) + (char)crossword[y - i][x + i]);
            } catch (Exception e) {}
            try {tryWords.set(6, tryWords.get(6) + (char)crossword[y + i][x - i]);
            } catch (Exception e) {}
            try {tryWords.set(7, tryWords.get(7) + (char)crossword[y - i][x - i]);
            } catch (Exception e) {}
        }

        if (tryWords.contains(word)) return true;
        else return false;
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> wordList = new ArrayList<>(words.length);
        for (String word : words) {
            if (word == null || word.length() == 0) continue;

            boolean found = false;
            Word currentWord = new Word(word);

            char firstLetter = word.charAt(0);
            for (int y = 0; y < crossword.length; y++)
                for (int x = 0; x < crossword[y].length; x++)
                {
                    if (firstLetter == crossword[y][x])
                    {
                        if (checkFirstLetter(firstLetter, x, y, crossword, word))
                        {
                            currentWord.setStartPoint(x, y);
                            found = true;
                            break;
                        }
                    }
                }

            if (!found) continue;

            final int x = currentWord.startX;
            final int y = currentWord.startY;
            final List<String> tryWords = new ArrayList<>();

            for (int i = 0; i < 8; i++)
                tryWords.add("");

            for (int i = 0; i < word.length(); i++) {
                try { tryWords.set(0, tryWords.get(0) + (char)crossword[y][x + i]);
                } catch (Exception e) {}
                try { tryWords.set(1, tryWords.get(1) + (char)crossword[y][x - i]);
                } catch (Exception e) {}
                try { tryWords.set(2, tryWords.get(2) + (char)crossword[y - i][x]);
                } catch (Exception e) {}
                try {tryWords.set(3, tryWords.get(3) + (char)crossword[y + i][x]);
                } catch (Exception e) {}
                try {tryWords.set(4, tryWords.get(4) + (char)crossword[y + i][x + i]);
                } catch (Exception e) {}
                try {tryWords.set(5, tryWords.get(5) + (char)crossword[y - i][x + i]);
                } catch (Exception e) {}
                try {tryWords.set(6, tryWords.get(6) + (char)crossword[y + i][x - i]);
                } catch (Exception e) {}
                try {tryWords.set(7, tryWords.get(7) + (char)crossword[y - i][x - i]);
                } catch (Exception e) {}
            }

            int endX = 0, endY = 0;
            for (int i = 0; i < tryWords.size(); i++)
            {
                if (tryWords.get(i).equals(word)) {
                    switch (i) {
                        case 0:
                            endX = x + (word.length() - 1);
                            endY = y;
                            break;
                        case 1:
                            endX = x - (word.length() - 1);
                            endY = y;
                            break;
                        case 2:
                            endX = x;
                            endY = y - (word.length() - 1);
                            break;
                        case 3:
                            endX = x;
                            endY = y + (word.length() - 1);
                            break;
                        case 4:
                            endX = x + (word.length() - 1);
                            endY = y + (word.length() - 1);
                            break;
                        case 5:
                            endX = x + (word.length() - 1);
                            endY = y - (word.length() - 1);
                            break;
                        case 6:
                            endX = x - (word.length() - 1);
                            endY = y + (word.length() - 1);
                            break;
                        case 7:
                            endX = x - (word.length() - 1);
                            endY = y - (word.length() - 1);
                            break;
                    }
                    currentWord.setEndPoint(endX, endY);
                    break;
                }
            }
            wordList.add(currentWord);

        }

        return wordList;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
