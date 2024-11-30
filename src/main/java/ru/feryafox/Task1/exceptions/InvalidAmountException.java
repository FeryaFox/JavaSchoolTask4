package ru.feryafox.Task1.exceptions;

public class InvalidAmountException extends TerminalException {
    public InvalidAmountException() {
        super("Сумма должна быть кратна 100. Введите корректную сумму.");
    }
}