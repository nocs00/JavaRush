package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;

import java.util.ResourceBundle;

/**
 * Created by pdudenkov on 09.03.2016.
 */
class InfoCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"info_en");

    @Override
    public void execute()
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        if (!CurrencyManipulatorFactory.hasMoney())
            ConsoleHelper.writeMessage(res.getString("no.money"));

        for (CurrencyManipulator manipulator : CurrencyManipulatorFactory.getAllCurrencyManipulators()) {
            if (manipulator.hasMoney())
                ConsoleHelper.writeMessage(String.format("%s - %d", manipulator.getCurrencyCode(), manipulator.getTotalAmount()));
            else
                ConsoleHelper.writeMessage(res.getString("no.money"));
        }
    }
}