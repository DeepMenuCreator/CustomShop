package ru.example.customshop.economy;

import org.bukkit.configuration.file.YamlConfiguration;
import ru.example.customshop.CustomShopPlugin;

import java.io.File;
import java.util.UUID;

public class DonateEconomy {

    private final File file;
    private final YamlConfiguration data;

    public DonateEconomy(CustomShopPlugin plugin) {
        this.file = new File(plugin.getDataFolder(), "donate_data.yml");
        if (!file.exists()) {
            try { file.getParentFile().mkdirs(); file.createNewFile(); }
            catch (Exception e) { e.printStackTrace(); }
        }
        this.data = YamlConfiguration.loadConfiguration(file);
    }

    public double getBalance(UUID uuid) {
        return data.getDouble(uuid.toString(), 0);
    }

    public boolean has(UUID uuid, double amount) {
        return getBalance(uuid) >= amount;
    }

    public void add(UUID uuid, double amount) {
        data.set(uuid.toString(), getBalance(uuid) + amount);
    }

    public boolean take(UUID uuid, double amount) {
        if (!has(uuid, amount)) return false;
        data.set(uuid.toString(), getBalance(uuid) - amount);
        return true;
    }

    public void save() {
        try { data.save(file); } catch (Exception e) { e.printStackTrace(); }
    }
}
