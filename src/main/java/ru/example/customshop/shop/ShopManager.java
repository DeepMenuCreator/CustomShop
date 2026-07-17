package ru.example.customshop.shop;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import ru.example.customshop.CustomShopPlugin;
import ru.example.customshop.util.ItemBuilder;

import java.io.File;
import java.util.*;

public class ShopManager {

    private final CustomShopPlugin plugin;
    private final Map<ShopCategory, List<ShopItem>> items = new EnumMap<>(ShopCategory.class);

    public ShopManager(CustomShopPlugin plugin) {
        this.plugin = plugin;
        for (ShopCategory c : ShopCategory.values()) items.put(c, new ArrayList<>());
    }

    public void loadItems() {
        items.values().forEach(List::clear);

        File file = new File(plugin.getDataFolder(), "items.yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        ConfigurationSection root = cfg.getConfigurationSection("items");
        if (root == null) return;

        for (String key : root.getKeys(false)) {
            ConfigurationSection sec = root.getConfigurationSection(key);
            if (sec == null) continue;

            Material mat = Material.matchMaterial(sec.getString("material", "STONE"));
            if (mat == null) mat = Material.STONE;

            String name = sec.getString("name", key);
            List<String> lore = sec.getStringList("lore");
            int price = sec.getInt("price", 100);
            int customModelData = sec.getInt("custom-model-data", 0);
            String catName = sec.getString("category", "RAZNOE").toUpperCase();
            ShopCategory category;
            try { category = ShopCategory.valueOf(catName); }
            catch (Exception e) { category = ShopCategory.RAZNOE; }

            ItemBuilder builder = new ItemBuilder(mat)
                    .name(name)
                    .lore(lore);
            if (customModelData > 0) builder.customModelData(customModelData);
            builder.tag("shop_item_id", key);

            ItemStack stack = builder.build();

            items.get(category).add(new ShopItem(key, stack, category, price));
        }

        plugin.getLogger().info("Загружено товаров: " +
                items.values().stream().mapToInt(List::size).sum());
    }

    public List<ShopItem> getItems(ShopCategory category) {
        return items.getOrDefault(category, Collections.emptyList());
    }

    public Optional<ShopItem> findById(String id) {
        return items.values().stream()
                .flatMap(List::stream)
                .filter(i -> i.getId().equalsIgnoreCase(id))
                .findFirst();
    }
              }
                                  
