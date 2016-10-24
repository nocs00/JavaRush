package com.javarush.test.level38.lesson06.home01;

public class ExceptionFactory {
    public static Throwable getException(Enum msg) {
        if (msg != null) {
            String message = msg.name();
            message = message.toLowerCase().replace("_", " ");
            message = message.substring(0, 1).toUpperCase() + message.substring(1);

            if (msg instanceof ExceptionApplicationMessage) return new Exception(message);
            else
            if (msg instanceof ExceptionUserMessage) return new RuntimeException(message);
            else
            if (msg instanceof ExceptionDBMessage) return new Error(message);
        }
        return new IllegalArgumentException();
    }
}
