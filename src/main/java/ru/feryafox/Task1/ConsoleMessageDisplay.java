package ru.feryafox.Task1;

import ru.feryafox.Task1.interfaces.MessageDisplay;

public class ConsoleMessageDisplay implements MessageDisplay {
    @Override
    public void showMessage(String message) {
        System.out.println("Сообщение: " + message);
    }

    @Override
    public void showError(String error) {
        System.err.println("Ошибка: " + error);
    }
}