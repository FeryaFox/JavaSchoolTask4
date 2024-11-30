package ru.feryafox.Task1;

import ru.feryafox.Task1.exceptions.AccountIsLockedException;
import ru.feryafox.Task1.exceptions.InvalidAmountException;
import ru.feryafox.Task1.exceptions.InvalidPinException;
import ru.feryafox.Task1.exceptions.TerminalException;
import ru.feryafox.Task1.interfaces.MessageDisplay;
import ru.feryafox.Task1.interfaces.PinValidator;
import ru.feryafox.Task1.interfaces.TerminalServer;

public class TerminalImpl {
    private final TerminalServer server;
    private final PinValidator pinValidator;
    private final MessageDisplay messageDisplay;

    private boolean isAuthorized = false;
    private String currentPin = "";

    public TerminalImpl(TerminalServer server,
                        PinValidator pinValidator,
                        MessageDisplay messageDisplay) {
        this.server = server;
        this.pinValidator = pinValidator;
        this.messageDisplay = messageDisplay;
    }

    public void enterPin(char input) {
        try {
            // Проверка, что вводится цифра
            if (!Character.isDigit(input)) {
                messageDisplay.showError("Можно вводить только цифры!");
                return;
            }

            // Добавляем цифру к текущему PIN
            currentPin += input;

            // Если PIN полный (4 цифры), проверяем его
            if (currentPin.length() == 4) {
                checkPin(currentPin);
                currentPin = ""; // Сбрасываем для следующего ввода
            }
        } catch (Exception e) {
            messageDisplay.showError(e.getMessage());
            currentPin = ""; // Сбрасываем PIN при ошибке
        }
    }

    private void checkPin(String pin) throws AccountIsLockedException, InvalidPinException {
        if (pinValidator.validatePin(pin)) {
            isAuthorized = true;
            messageDisplay.showMessage("PIN успешно введен!");
        }
    }

    public void checkBalance() {
        try {
            checkAuthorization();
            long balance = server.getBalance();
            messageDisplay.showMessage("Баланс: " + balance + " руб.");
        } catch (Exception e) {
            messageDisplay.showError(e.getMessage());
        }
    }

    public void withdraw(long amount) {
        try {
            checkAuthorization();
            validateAmount(amount);
            server.withdraw(amount);
            messageDisplay.showMessage("Снятие " + amount + " руб. выполнено.");
        } catch (Exception e) {
            messageDisplay.showError(e.getMessage());
        }
    }

    public void deposit(long amount) {
        try {
            checkAuthorization();
            validateAmount(amount);
            server.deposit(amount);
            messageDisplay.showMessage("Пополнение " + amount + " руб. выполнено.");
        } catch (Exception e) {
            messageDisplay.showError(e.getMessage());
        }
    }

    private void checkAuthorization() throws TerminalException {
        if (!isAuthorized) {
            throw new TerminalException("Для операции требуется авторизация!");
        }
    }

    private void validateAmount(long amount) throws InvalidAmountException {
        if (amount % 100 != 0) {
            throw new InvalidAmountException();
        }
    }
}