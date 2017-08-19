package de.siloks.silocloud.main;

import de.siloks.silocloud.Client;
import de.siloks.silocloud.commands.Command_bungee;
import de.siloks.silocloud.commands.Command_toCloud;
import net.md_5.bungee.api.plugin.Plugin;

/**
 * Erstellt von Lars am 19.08.2017.
 * Alle Rechte vorbehalten. Der Entwickler kann jederzeit
 * die Rechte an diesem Code entziehen!
 */
public class SiloCord extends Plugin{


    @Override
    public void onEnable() {
        this.getProxy().getPluginManager().registerCommand(this, new Command_bungee("bungee"));
        this.getProxy().getPluginManager().registerCommand(this, new Command_bungee("cloud"));
        this.getProxy().getPluginManager().registerCommand(this, new Command_bungee("silocord"));
        this.getProxy().getPluginManager().registerCommand(this, new Command_toCloud("toCloud"));
        try {
            new Client("BungeeCord|BungeeCord started");
        } catch (Exception e) {}
    }

    @Override
    public void onDisable() {
        System.out.println("Disabling SiloCord...");
        try {
            new Client("BungeeCord|BungeeCord stopped");
        } catch (Exception e) {}
    }
}
