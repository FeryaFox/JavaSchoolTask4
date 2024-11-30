package ru.feryafox.Task1;

import ru.feryafox.Task1.exceptions.AccountIsLockedException;
import ru.feryafox.Task1.exceptions.InvalidPinException;
import ru.feryafox.Task1.interfaces.PinValidator;

public class SimplePinValidator implements PinValidator {
    private static final String CORRECT_PIN = "1234";
    private int failedAttempts = 0;
    private long lockTimestamp = 0;

    @Override
    public boolean validatePin(String pin) throws InvalidPinException, AccountIsLockedException {
        // Проверка блокировки
        if (System.currentTimeMillis() - lockTimestamp < 10_000) {
            long remainingTime = 10_000 - (System.currentTimeMillis() - lockTimestamp);
            throw new AccountIsLockedException(remainingTime);
        }

        if (!CORRECT_PIN.equals(pin)) {
            failedAttempts++;

            if (failedAttempts >= 3) {
                lockTimestamp = System.currentTimeMillis();
                failedAttempts = 0;
                throw new AccountIsLockedException(10_000);
            }

            throw new InvalidPinException(3 - failedAttempts);
        }

        // Успешная валидация
        resetPinAttempts();
        return true;
    }

    @Override
    public void resetPinAttempts() {
        failedAttempts = 0;
    }
}

