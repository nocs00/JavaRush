package com.javarush.test.level30.lesson15.big01.client;

import com.javarush.test.level30.lesson15.big01.Connection;
import com.javarush.test.level30.lesson15.big01.ConsoleHelper;
import com.javarush.test.level30.lesson15.big01.Message;
import com.javarush.test.level30.lesson15.big01.MessageType;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by pdudenkov on 27.07.2016.
 */
public class Client
{
    protected Connection connection;
    volatile private boolean clientConnected = false;

    protected String getServerAddress()
    {
        ConsoleHelper.writeMessage("Enter server address: ");
        return ConsoleHelper.readString();
    }

    protected int getServerPort()
    {
        ConsoleHelper.writeMessage("Enter port number: ");
        return ConsoleHelper.readInt();
    }

    protected String getUserName()
    {
        ConsoleHelper.writeMessage("Enter user name: ");
        return ConsoleHelper.readString();
    }

    protected boolean shouldSentTextFromConsole()
    {
        return true;
    }

    protected SocketThread getSocketThread()
    {
        return new SocketThread();
    }

    protected void sendTextMessage(String text)
    {
        try
        {
            connection.send(new Message(MessageType.TEXT, text));
        }
        catch (IOException e)
        {
            ConsoleHelper.writeMessage("ERROR: io exception occurred, disconnecting from server");
            clientConnected = false;
        }
    }

    public void run()
    {
        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();

        try
        {
            synchronized (this)
            {
                this.wait();
            }
        }
        catch (InterruptedException e)
        {
            ConsoleHelper.writeMessage("ERROR: error during connection to server, client shutdown");
            return;
        }

        if (clientConnected) {
            ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду 'exit'.");
            while (clientConnected)
            {
                String message = ConsoleHelper.readString();
                if (message.equals("exit")) return;
                if (shouldSentTextFromConsole()) sendTextMessage(message);
            }
        }
        else ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");
    }

    public static void main(String[] args)
    {
        new Client().run();
    }

    public class SocketThread extends Thread
    {
        protected void processIncomingMessage(String message)
        {
            ConsoleHelper.writeMessage(message);
        }

        protected void informAboutAddingNewUser(String userName)
        {
            processIncomingMessage("New user in the chat: " + userName);
        }

        protected void informAboutDeletingNewUser(String userName)
        {
            processIncomingMessage("User has left the chat: " + userName);
        }

        protected void notifyConnectionStatusChanged(boolean clientConnected)
        {
            synchronized (Client.this)
            {
                Client.this.clientConnected = clientConnected;
                Client.this.notify();
            }
        }

        protected void clientHandshake()
                throws IOException, ClassNotFoundException {
            while (true) {
                Message receivedMessage = connection.receive();
                switch (receivedMessage.getType()) {
                    case NAME_REQUEST:
                        String userName = getUserName();
                        connection.send(new Message(MessageType.USER_NAME, userName));
                        break;
                    case NAME_ACCEPTED:
                        notifyConnectionStatusChanged(true);
                        return;
                    default:
                        throw new IOException("Unexpected MessageType");
                }
            }
        }

        protected void clientMainLoop()
                throws IOException, ClassNotFoundException {
            while (true)
            {
                Message receivedMessage = connection.receive();
                switch (receivedMessage.getType())
                {
                    case TEXT:
                        processIncomingMessage(receivedMessage.getData());
                        break;
                    case USER_ADDED:
                        informAboutAddingNewUser(receivedMessage.getData());
                        break;
                    case USER_REMOVED:
                        informAboutDeletingNewUser(receivedMessage.getData());
                        break;
                    default:
                        throw new IOException("Unexpected MessageType");
                }
            }
        }

        @Override
        public void run()
        {
            try
            {
                String serverAddress = getServerAddress();
                int serverPort = getServerPort();
                Socket socket = new Socket(serverAddress, serverPort);
                connection = new Connection(socket);
                clientHandshake();
                clientMainLoop();
            } catch (IOException|ClassNotFoundException e) {
                notifyConnectionStatusChanged(false);
            }
        }
    }
}
