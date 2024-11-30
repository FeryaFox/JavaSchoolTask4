package ru.feryafox.Task1;

import ru.feryafox.Task1.exceptions.InsufficientFundsException;
import ru.feryafox.Task1.interfaces.TerminalServer;

public class SimpleTerminalServer implements TerminalServer {
    private long balance = 10_000; // Начальный баланс

    @Override
    public long getBalance() {
        return balance;
    }

    @Override
    public void withdraw(long amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException();
        }
        balance -= amount;
    }

    @Override
    public void deposit(long amount) {
        balance += amount;
    }
}

