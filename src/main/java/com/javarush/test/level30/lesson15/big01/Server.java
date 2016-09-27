package com.javarush.test.level30.lesson15.big01;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by pdudenkov on 25.07.2016.
 */
public class Server
{
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void sendBroadcastMessage(Message message) {
        for (Map.Entry<String, Connection> entry : connectionMap.entrySet())
        {
            String clientName = entry.getKey();
            Connection clientConnection = entry.getValue();
            try
            {
                clientConnection.send(message);
            }
            catch (IOException e)
            {
                ConsoleHelper.writeMessage("Failed sending message to {" + clientName + "}");
            }
        }
    }

    public static void main(String[] args)
    {
        ConsoleHelper.writeMessage("Enter port number for chat-server:");
        int port = ConsoleHelper.readInt();
        ServerSocket serverSocket = null;
        try
        {
            serverSocket = new ServerSocket(port);
            ConsoleHelper.writeMessage("Server started at port: " + port);
            while (true)
            {
                Socket socket = serverSocket.accept();
                Handler handler = new Handler(socket);
                handler.start();
            }
        } catch (IOException e) {
            if (serverSocket != null) {
                try
                {
                    serverSocket.close();
                }
                catch (IOException e1)
                {
                    ConsoleHelper.writeMessage(e1.toString());
                }
            }
        }
    }

    private static class Handler extends Thread {
        private Socket socket;

        public Handler(Socket socket)
        {
            this.socket = socket;
        }

        private String serverHandshake(Connection connection)
                throws IOException, ClassNotFoundException
        {
            while (true)
            {
                Message nameRequest = new Message(MessageType.NAME_REQUEST);
                connection.send(nameRequest);

                Message receivedResponse = connection.receive();
                if (!receivedResponse.getType().equals(MessageType.USER_NAME)) continue;
                String userName = receivedResponse.getData();
                if (userName == null || userName.isEmpty() || connectionMap.containsKey(userName)) continue;
                connectionMap.put(userName, connection);
                Message confirmationMessage = new Message(MessageType.NAME_ACCEPTED);
                connection.send(confirmationMessage);
                return userName;
            }
        }

        private void sendListOfUsers(Connection connection, String userName)
                throws IOException {
            for (String name : connectionMap.keySet())
            {
                if (name.equals(userName)) continue;
                Message message = new Message(MessageType.USER_ADDED, name);
                connection.send(message);
            }
        }

        private void serverMainLoop(Connection connection, String userName)
                throws IOException, ClassNotFoundException {
            while (true)
            {
                Message incomingMessage = connection.receive();
                switch (incomingMessage.getType()) {
                    case TEXT:
                        sendBroadcastMessage(new Message(MessageType.TEXT,
                                String.format("%s: %s", userName, incomingMessage.getData())));
                        break;
                    default:
                        ConsoleHelper.writeMessage("ERROR: received message is not a text!");
                }
            }
        }

        @Override
        public void run() {
            ConsoleHelper.writeMessage("INFO: established connection with server with remote address: " + socket.getRemoteSocketAddress());
            Connection connection = null;
            String userName = null;
            try
            {
                connection = new Connection(socket);
                userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                sendListOfUsers(connection, userName);
                serverMainLoop(connection, userName);
            } catch (IOException|ClassNotFoundException e) {
                try
                {
                    if (connection != null) connection.close();
                }
                catch (IOException e1) {}
                ConsoleHelper.writeMessage("ERROR: exception during data exchange with remote address");
            }
            if (userName != null) connectionMap.remove(userName);
            sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
            ConsoleHelper.writeMessage("INFO: connection with remote address is closed");
        }
    }
}

/*
Он должен:
11.9.	После того как все исключения обработаны, если п.11.3 отработал и возвратил
нам имя, мы должны удалить запись для этого имени из connectionMap и разослать
всем остальным участникам сообщение с типом USER_REMOVED и сохраненным
именем.
11.10.	Последнее, что нужно сделать в методе run() – вывести сообщение,
информирующее что соединение с удаленным адресом закрыто.
Наш сервер полностью готов. Попробуй его запустить.
 */