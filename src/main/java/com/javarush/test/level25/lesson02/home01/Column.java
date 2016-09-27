package com.javarush.test.level25.lesson02.home01;

import java.util.LinkedList;
import java.util.List;

public enum Column implements Columnable {
    Customer("Customer"),
    BankName("Bank Name"),
    AccountNumber("Account Number"),
    Amount("Available Amount");

    private String columnName;

    private static int[] realOrder;

    private Column(String columnName) {
        this.columnName = columnName;
    }

    /**
     * Задает новый порядок отображения колонок, который хранится в массиве realOrder.
     * realOrder[индекс в энуме] = порядок отображения; -1, если колонка не отображается.
     *
     * @param newOrder новая последовательность колонок, в которой они будут отображаться в таблице
     * @throws IllegalArgumentException при дубликате колонки
     */
    public static void configureColumns(Column... newOrder) {
        realOrder = new int[values().length];
        for (Column column : values()) {
            realOrder[column.ordinal()] = -1;
            boolean isFound = false;

            for (int i = 0; i < newOrder.length; i++) {
                if (column == newOrder[i]) {
                    if (isFound) {
                        throw new IllegalArgumentException("Column '" + column.columnName + "' is already configured.");
                    }
                    realOrder[column.ordinal()] = i;
                    isFound = true;
                }
            }
        }
    }

    /**
     * Вычисляет и возвращает список отображаемых колонок в сконфигурированом порядке (см. метод configureColumns)
     * Используется поле realOrder.
     *
     * @return список колонок
     */
    public static List<Column> getVisibleColumns() {
        List<Column> result = new LinkedList<>();
        int howMuch = 0;
        for (int i : realOrder)
        {
            if (i != -1)
                howMuch++;
        }

        int currentOrder = 0;
        int index = 0;
        for (int i = 0; i < howMuch; i++) {
            for (int i1 = 0; i1 < realOrder.length; i1++)
            {
                if (realOrder[i1] == currentOrder)
                {
                    index = i1;
                    currentOrder++;
                    break;
                }
            }
            result.add(Column.values()[index]);
        }


        return result;
    }

    @Override
    public String getColumnName()
    {
        return this.columnName;
    }

    @Override
    public boolean isShown()
    {
        if (realOrder[ordinal()] == -1)
            return false;
        else
            return true;
    }

    @Override
    public void hide()
    {
        if (!isShown())
            return;

        int oldOrder = realOrder[ordinal()];
        realOrder[ordinal()] = -1;
        for (int i = 0; i < realOrder.length; i++)
        {
            if (realOrder[i] > oldOrder)
                realOrder[i]--;
        }
    }
}
