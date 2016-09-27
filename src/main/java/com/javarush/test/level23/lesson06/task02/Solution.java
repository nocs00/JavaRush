package com.javarush.test.level23.lesson06.task02;

/* Рефакторинг
Отрефакторите класс Solution: вынесите все константы в public вложенный(nested) класс Constants.
Запретите наследоваться от Constants.
*/
public class Solution {

    public class ServerNotAccessibleException extends Exception {
        public ServerNotAccessibleException() {
            super(Constants.SERVER);
        }

        public ServerNotAccessibleException(Throwable cause) {
            super(Constants.SERVER, cause);
        }
    }

    public class UnauthorizedUserException extends Exception {
        public UnauthorizedUserException() {
            super(Constants.UNATH);
        }

        public UnauthorizedUserException(Throwable cause) {
            super(Constants.UNATH, cause);
        }
    }

    public class BannedUserException extends Exception {
        public BannedUserException() {
            super(Constants.BAN);
        }

        public BannedUserException(Throwable cause) {
            super(Constants.BAN, cause);
        }
    }

    public class RestrictionException extends Exception {
        public RestrictionException() {
            super(Constants.RESTR);
        }

        public RestrictionException(Throwable cause) {
            super(Constants.RESTR, cause);
        }
    }

    public static final class Constants {
        public final static String SERVER = "Server is not accessible for now.";
        public final static String UNATH = "User is not authorized.";
        public final static String BAN = "User is banned.";
        public final static String RESTR = "Access is denied.";
    }
}
