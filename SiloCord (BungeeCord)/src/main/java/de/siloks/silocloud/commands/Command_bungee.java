package de.siloks.silocloud.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

/**
 * Erstellt von Lars am 19.08.2017.
 * Alle Rechte vorbehalten. Der Entwickler kann jederzeit
 * die Rechte an diesem Code entziehen!
 */
public class Command_bungee extends Command{
    public Command_bungee(String name) {
        super(name);
    }

    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage(new TextComponent("§9This Server is running SiloCord [BungeeCord edit] version SiloCloud-proxy:1.0 by Siloks"));
    }
}
