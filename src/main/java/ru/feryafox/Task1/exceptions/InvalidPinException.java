package ru.feryafox.Task1.exceptions;

public class InvalidPinException extends TerminalException {
    public InvalidPinException(int remainingAttempts) {
        super("Неверный PIN-код. Осталось попыток: " + remainingAttempts + ". После 3 неудачных попыток аккаунт будет заблокирован.");
    }
}