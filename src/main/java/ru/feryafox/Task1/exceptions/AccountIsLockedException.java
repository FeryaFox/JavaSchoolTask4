package ru.feryafox.Task1.exceptions;

public class AccountIsLockedException extends TerminalException {
    private final long remainingLockTime;

    public AccountIsLockedException(long remainingLockTime) {
        super("Аккаунт заблокирован. Осталось ждать: " +
                (remainingLockTime / 1000) + " секунд");
        this.remainingLockTime = remainingLockTime;
    }

    public long getRemainingLockTime() {
        return remainingLockTime;
    }
}