package ru.feryafox.Task1.exceptions;

public class InsufficientFundsException extends TerminalException {
    public InsufficientFundsException() {
        super("Недостаточно средств на счете. Пожалуйста, пополните счет.");
    }
}
