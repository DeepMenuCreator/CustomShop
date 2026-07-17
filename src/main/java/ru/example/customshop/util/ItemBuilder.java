package ru.example.customshop.util;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import ru.example.customshop.CustomShopPlugin;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {

    private final ItemStack stack;
    private final ItemMeta meta;

    public ItemBuilder(Material material) {
        this.stack = new ItemStack(material);
        this.meta = stack.getItemMeta();
    }

    public ItemBuilder name(String name) {
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        return this;
    }

    public ItemBuilder lore(List<String> lore) {
        List<String> colored = new ArrayList<>();
        for (String l : lore) colored.add(ChatColor.translateAlternateColorCodes('&', l));
        meta.setLore(colored);
        return this;
    }

    public ItemBuilder customModelData(int id) {
        meta.setCustomModelData(id);
        return this;
    }

    public ItemBuilder tag(String key, String value) {
        NamespacedKey nk = new NamespacedKey(CustomShopPlugin.get(), key);
        meta.getPersistentDataContainer().set(nk, PersistentDataType.STRING, value);
        return this;
    }

    public ItemStack build() {
        stack.setItemMeta(meta);
        return stack;
    }
}
