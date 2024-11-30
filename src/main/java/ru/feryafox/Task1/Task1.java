package ru.feryafox.Task1;

import ru.feryafox.Task1.interfaces.MessageDisplay;
import ru.feryafox.Task1.interfaces.PinValidator;
import ru.feryafox.Task1.interfaces.TerminalServer;

public final class Task1 {
    public static void main(String[] args) {
        TerminalServer server = new SimpleTerminalServer();
        PinValidator pinValidator = new SimplePinValidator();
        MessageDisplay display = new ConsoleMessageDisplay();

        TerminalImpl terminal = new TerminalImpl(server, pinValidator, display);

        terminal.enterPin('1');
        terminal.enterPin('2');
        terminal.enterPin('3');
        terminal.enterPin('4');

        terminal.checkBalance();

        terminal.withdraw(500);
        terminal.checkBalance();
    }
}
