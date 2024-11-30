package ru.feryafox.Task1.interfaces;

import ru.feryafox.Task1.exceptions.AccountIsLockedException;
import ru.feryafox.Task1.exceptions.InvalidPinException;

public interface PinValidator {
    boolean validatePin(String pin) throws InvalidPinException, AccountIsLockedException;
    void resetPinAttempts();
}

