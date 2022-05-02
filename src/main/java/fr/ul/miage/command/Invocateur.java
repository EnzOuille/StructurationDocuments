package fr.ul.miage.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Invocateur {
    private final HashMap<String, Commande> commandMap = new HashMap<>();
    private final List<Commande> history = new ArrayList<Commande>();

    public List<Commande> getHistory() {
        return history;
    }

    public HashMap<String, Commande> getCommandMap() {
        return commandMap;
    }
}
