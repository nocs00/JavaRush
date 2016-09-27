package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.command.CommandExecutor;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.Locale;

/**
 * Created by pdudenkov on 09.03.2016.
 */
public class CashMachine
{
    public static final String RESOURCE_PATH = "com.javarush.test.level26.lesson15.big01.resources.";
    public static final Locale[] supportedLocales = {
            new Locale.Builder().setLanguage("ru").setScript("Cyrl").build(),
            Locale.ENGLISH
    };

    public static void main(String[] args)
    {
        try
        {
            Locale.setDefault(supportedLocales[0]);
            CommandExecutor.execute(Operation.LOGIN);
            Operation operation = null;
            do
            {
                operation = ConsoleHelper.askOperation();
                CommandExecutor.execute(operation);
            }
            while (operation != Operation.EXIT);

        } catch (InterruptOperationException e) {
            ConsoleHelper.printExitMessage();
        }
    }
}
