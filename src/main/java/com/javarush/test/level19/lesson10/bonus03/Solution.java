package com.javarush.test.level19.lesson10.bonus03;

/* Знакомство с тегами
Считайте с консоли имя файла, который имеет HTML-формат
Пример:
Info about Leela <span xml:lang="en" lang="en"><b><span>Turanga Leela
</span></b></span><span>Super</span><span>girl</span>
Первым параметром в метод main приходит тег. Например, "span"
Вывести на консоль все теги, которые соответствуют заданному тегу
Каждый тег на новой строке, порядок должен соответствовать порядку следования в файле
Количество пробелов, \n, \r не влияют на результат
Файл не содержит тег CDATA, для всех открывающих тегов имеется отдельный закрывающий тег, одиночных тегов нету
Тег может содержать вложенные теги
Пример вывода:
<span xml:lang="en" lang="en"><b><span>Turanga Leela</span></b></span>
<span>Turanga Leela</span>
<span>Super</span>
<span>girl</span>

Шаблон тега:
<tag>text1</tag>
<tag text2>text1</tag>
<tag
text2>text1</tag>

text1, text2 могут быть пустыми
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws Exception {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        final String fname = reader.readLine();
//        final String askTag = args[0];
        final String fname = "c:/users/pdudenkov/javarush/tagTest.txt";
        final String askTag = "span";

        final File file = new File(fname);
        final BufferedReader fileReader = new BufferedReader(new FileReader(file));

        List<String> tags = new ArrayList<>();
        String line = null;
        String nextTag = "";
        int open = 0;
        int closed = 0;
        closed += 0;
        while((line = fileReader.readLine()) != null) {
            int index = 0;
            while (line.contains(">")) {
                index = line.indexOf("<");
                int indexNext = line.indexOf("<", 2);
                nextTag += line.substring(index, indexNext);

                line = line.substring(index);
                index = 0;
                if (!line.substring(index, index+2).equals("</")) {
                    open++;
                } else
                    closed++;

                nextTag += line.substring(index, line.indexOf(">")+1);
                line = line.substring(line.indexOf(">")+1);
                index = line.indexOf("<");

                if (open == closed && open != 0 && closed != 0) {
                    open = 0;
                    closed = 0;
                    tags = extractTags(nextTag, tags);
                    nextTag = "";
                }
            }
            nextTag += line;
        }
        reader.close();
        fileReader.close();
    }

    public static List<String> extractTags(String tag, List<String> tagList) {
        if (tag.contains(">"))
            tagList.add(tag);
        int index = tag.indexOf(">")+1;
        int lastIndex = tag.lastIndexOf("<");
        tag = tag.substring(index, lastIndex);
        if (tag.contains("<")) {
            tagList = extractTags(tag, tagList);
        } else
            return tagList;

        return tagList;
    }
}
