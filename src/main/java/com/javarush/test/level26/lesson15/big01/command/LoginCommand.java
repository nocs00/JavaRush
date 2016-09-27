package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.CashMachine;
import com.javarush.test.level26.lesson15.big01.ConsoleHelper;
import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.util.ResourceBundle;

/**
 * Created by pdudenkov on 11.03.2016.
 */
public class LoginCommand implements Command
{
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH+"login");

    @Override
    public void execute() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("before"));
        String checkCard = null;
        String checkPin = null;

        while (true) {
            ConsoleHelper.writeMessage(res.getString("specify.data"));
            checkCard = ConsoleHelper.readString();
            if (!checkCard.matches("^[0-9]{12}$"))
            {
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                continue;
            }

            checkPin = ConsoleHelper.readString();
            if (!checkPin.matches("^[0-9]{4}$"))
            {
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                continue;
            }

            if (validCreditCards.containsKey(checkCard) &&
                    validCreditCards.getString(checkCard).equals(checkPin)) {
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"), checkCard));
                break;
            } else {
                ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), checkCard));
                ConsoleHelper.writeMessage("try.again.or.exit");
            }
        }
    }
}