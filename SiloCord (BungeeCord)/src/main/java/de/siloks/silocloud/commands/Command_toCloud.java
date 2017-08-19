package de.siloks.silocloud.commands;

import de.siloks.silocloud.Client;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

/**
 * Erstellt von Lars am 19.08.2017.
 * Alle Rechte vorbehalten. Der Entwickler kann jederzeit
 * die Rechte an diesem Code entziehen!
 */
public class Command_toCloud extends Command {
    public Command_toCloud(String name) {
        super(name);
    }

    public void execute(CommandSender commandSender, String[] args) {

        if(args.length > 0) {
            String message = "";
            for(int i = 0; i < args.length; i++){
                message = message + args[i] + " ";
            }
            try {
                new Client("BungeeCord|[INFO] Server responsed with: " + message);
            } catch (Exception e) {}
        } else {
            commandSender.sendMessage(new TextComponent("§cPlease use /toCloud [Responsemessage]"));
        }
    }
}
