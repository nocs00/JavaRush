package com.javarush.test.level30.lesson15.big01.client;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

/**
 * Created by pdudenkov on 29.07.2016.
 */
public class BotClient extends Client
{
    @Override
    protected SocketThread getSocketThread()
    {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSentTextFromConsole()
    {
        return false;
    }

    @Override
    protected String getUserName()
    {
        Random random = new Random();
        return "date_bot_"+random.nextInt(100);
    }



    public static void main(String[] args)
    {
        new BotClient().run();
    }

    public class BotSocketThread extends SocketThread {
        @Override
        protected void processIncomingMessage(String message)
        {
            super.processIncomingMessage(message);
            String[] args = message.split(": ");
            if (args.length != 2) return;
            String messagePattern = "Информация для " + args[0] + ": ";

            SimpleDateFormat format = null;
            String formatPattern = "";
            boolean messageShouldBeSent = true;
            switch (args[1]) {
                case "дата":
                    formatPattern = "d.MM.YYYY";
                    break;
                case "день":
                    formatPattern = "d";
                    break;
                case "месяц":
                    formatPattern = "MMMM";
                    break;
                case "год":
                    formatPattern = "YYYY";
                    break;
                case "время":
                    formatPattern = "H:mm:ss";
                    break;
                case "час":
                    formatPattern = "H";
                    break;
                case "минуты":
                    formatPattern = "m";
                    break;
                case "секунды":
                    formatPattern = "s";
                    break;
                default:
                    messageShouldBeSent = false;
            }
            format = new SimpleDateFormat(formatPattern);
            if (messageShouldBeSent) sendTextMessage(messagePattern + format.format(Calendar.getInstance().getTime()));
        }

        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException
        {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }
    }
}