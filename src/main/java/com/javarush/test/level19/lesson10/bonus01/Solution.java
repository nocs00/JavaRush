package com.javarush.test.level19.lesson10.bonus01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Отслеживаем изменения
Считать в консоли 2 имени файла - file1, file2.
Файлы содержат строки, file2 является обновленной версией file1, часть строк совпадают.
Нужно создать объединенную версию строк, записать их в список lines
Операции ADDED и REMOVED не могут идти подряд, они всегда разделены SAME
Пример:
оригинальный   редактированный    общий
file1:         file2:             результат:(lines)

строка1        строка1            SAME строка1
строка2                           REMOVED строка2
строка3        строка3            SAME строка3
строка4                           REMOVED строка4
строка5        строка5            SAME строка5
строка0                           ADDED строка0
строка1        строка1            SAME строка1
строка2                           REMOVED строка2
строка3        строка3            SAME строка3
строка5                           ADDED строка5
строка4        строка4            SAME строка4
строка5                           REMOVED строка5
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] fname = new String[2];
        fname[0] = reader.readLine();
        fname[1] = reader.readLine();

        reader.close();
        BufferedReader original = new BufferedReader(new FileReader(fname[0]));
        BufferedReader changes = new BufferedReader(new FileReader(fname[1]));

        String lineOriginal = null;
        String lineOriginalNext = null;
        String lineChanges = null;
        String lineChangesNext = null;

        while (true) {
            if (lineOriginalNext != null)
                lineOriginal = lineOriginalNext;
            else
                lineOriginal = original.readLine();
            if (lineChangesNext != null)
                lineChanges = lineChangesNext;
            else
                lineChanges = changes.readLine();

            if (lineOriginal == null && lineChanges == null) break;
            if (lineOriginal == null) {
                lines.add(new LineItem(Type.ADDED, lineChanges));
                lineOriginalNext = null;
                lineChangesNext = null;
                continue;
            } else if (lineChanges == null) {
                lines.add(new LineItem(Type.REMOVED, lineOriginal));
                lineOriginalNext = null;
                lineChangesNext = null;
                continue;
            }


            if (lineChanges.equals(lineOriginal)) {
                lines.add(new LineItem(Type.SAME, lineOriginal));
                lineOriginalNext = null;
                lineChangesNext = null;
                continue;
            } else {
                lineOriginalNext = original.readLine();
                if (lineOriginalNext != null && lineOriginalNext.equals(lineChanges)) {
                    lines.add(new LineItem(Type.REMOVED, lineOriginal));
                    lines.add(new LineItem(Type.SAME, lineOriginalNext));
                    lineOriginalNext = null;
                    lineChangesNext = null;
                    continue;
                }
                lineChangesNext = changes.readLine();
                if (lineChangesNext != null && lineChangesNext.equals(lineOriginal)) {
                    lines.add(new LineItem(Type.ADDED, lineChanges));
                    lines.add(new LineItem(Type.SAME, lineChangesNext));
                    lineChangesNext = null;
                    continue;
                }
            }
        }
        original.close();
        changes.close();
    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
