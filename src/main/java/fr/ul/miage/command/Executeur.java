package fr.ul.miage.command;

public class Executeur extends Invocateur {

    public void register(String commandName, Commande command) {
        getCommandMap().put(commandName, command);
    }

    public void execute(String cmdName) {
        Commande command = getCommandMap().get(cmdName);
        if (command == null) {
            throw new IllegalStateException("no command registered for " + cmdName);
        }
        getHistory().add(command); // optional
        command.execute();
    }

    public void executeAll() {
        for (Commande command : getCommandMap().values()) {
            command.execute();
        }
    }
}
