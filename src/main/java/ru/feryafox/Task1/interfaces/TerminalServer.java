package ru.feryafox.Task1.interfaces;

import ru.feryafox.Task1.exceptions.InsufficientFundsException;

public interface TerminalServer {
    long getBalance() throws Exception;
    void withdraw(long amount) throws InsufficientFundsException, Exception;
    void deposit(long amount) throws Exception;
}