package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.exception.NotEnoughMoneyException;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulator;
import com.javarush.test.level26.lesson15.big01.CurrencyManipulatorFactory;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.*;

/**
 * Created by pdudenkov on 09.03.2016.
 */
class WithdrawCommand implements Command
{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"withdraw_en");

    @Override
    public void execute() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);

        while(true) {
            ConsoleHelper.writeMessage(res.getString("specify.amount"));
            String sum = ConsoleHelper.readString();
            try
            {
                int sumI = Integer.parseInt(sum);
                if (sumI <= 0)
                    throw new NumberFormatException();

                if (manipulator.isAmountAvailable(sumI)) {
                    Map<Integer, Integer> withdrawMap = manipulator.withdrawAmount(sumI);
                    TreeSet<Integer> keys = new TreeSet<>(new Comparator<Integer>()
                    {
                        @Override
                        public int compare(Integer i1, Integer i2)
                        {
                            return i2.compareTo(i1);
                        }
                    });
                    for (Integer integer : withdrawMap.keySet())
                    {
                        keys.add(integer);
                    }

                    for (Integer key : keys)
                    {
                        ConsoleHelper.writeMessage(String.format("\t%d - %d", key, withdrawMap.get(key)));
                    }

                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"), sumI, currencyCode));
                    break;
                } else
                    ConsoleHelper.writeMessage(res.getString("not.enough.money"));
            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
            } catch (NotEnoughMoneyException|ConcurrentModificationException e) {
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
            }
        }
    }
}