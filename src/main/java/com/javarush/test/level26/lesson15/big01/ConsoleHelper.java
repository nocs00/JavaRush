package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ResourceBundle;

/**
 * Created by pdudenkov on 09.03.2016.
 */
public class ConsoleHelper
{
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"common_en");
    private static BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(new String(message.getBytes(Charset.forName("UTF-8")), Charset.defaultCharset()));
    }

    public static String readString() throws InterruptOperationException
    {

        String line = "";
        try
        {
            line = consoleReader.readLine();
            if (line.toUpperCase().equals("EXIT")) {
                throw new InterruptOperationException();
            }
        }
        catch (IOException e)
        {

        }

        return line;
    }

    public static void printExitMessage() {
        writeMessage(res.getString("the.end"));
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        String currencyCode = "";
        while (true)
        {
            writeMessage(res.getString("choose.currency.code"));
            currencyCode = readString();

            if (!currencyCode.isEmpty() && currencyCode.matches("^[a-zA-Z]{3}$")) {
                currencyCode = currencyCode.toUpperCase();
                return currencyCode;
            }
            writeMessage(res.getString("invalid.data"));
        }
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        String twoDigits = null;
        while (true)
        {
            writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
            twoDigits = readString();


            String[] pair = twoDigits.split(" ");
            if (pair.length == 2 && pair[0].matches("^[0-9]+$") && pair[1].matches("^[0-9]+$")) {
                try {
                    int n1 = Integer.parseInt(pair[0]);
                    int n2 = Integer.parseInt(pair[1]);

                    if (n1 != 1 && n1 != 2 && n1 != 5 && n1 != 10 && n1 != 50 && n1 != 100) {
                        ConsoleHelper.writeMessage(res.getString("invalid.denomination"));
                        continue;
                    }


                    if (n1 >= 0 && n2 >= 0)
                        return pair;
                } catch (NumberFormatException e) {

                }
            }
            writeMessage(res.getString("invalid.data"));
        }
    }

    public static Operation askOperation() throws InterruptOperationException {
        String operation = "";
        while (true) {
            writeMessage(res.getString("choose.operation"));
            writeMessage(String.format("1 - %s, 2 - %s, 3 - %s, 4 - %s",
                    res.getString("operation.INFO"),
                    res.getString("operation.DEPOSIT"),
                    res.getString("operation.WITHDRAW"),
                    res.getString("operation.EXIT")));

            operation = readString();
            try {
                Operation o = Operation.getAllowableOperationByOrdinal(Integer.parseInt(operation));
                return o;
            } catch (Exception e) {
                writeMessage(res.getString("invalid.data"));
            }
        }
    }
}