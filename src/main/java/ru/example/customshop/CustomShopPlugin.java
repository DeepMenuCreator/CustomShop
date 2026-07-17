package ru.example.customshop;

import org.bukkit.plugin.java.JavaPlugin;
import ru.example.customshop.commands.DonShopCommand;
import ru.example.customshop.commands.ShopCommand;
import ru.example.customshop.economy.DonateEconomy;
import ru.example.customshop.economy.PointsEconomy;
import ru.example.customshop.listeners.ShopGuiListener;
import ru.example.customshop.listeners.TrapListener;
import ru.example.customshop.market.ListingManager;
import ru.example.customshop.shop.ShopManager;

public class CustomShopPlugin extends JavaPlugin {

    private static CustomShopPlugin instance;

    private PointsEconomy pointsEconomy;
    private DonateEconomy donateEconomy;
    private ShopManager shopManager;
    private ListingManager listingManager;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        saveResource("items.yml", false);

        this.pointsEconomy = new PointsEconomy();
        this.donateEconomy = new DonateEconomy(this);
        this.shopManager = new ShopManager(this);
        this.listingManager = new ListingManager(this);

        shopManager.loadItems();

        getCommand("shop").setExecutor(new ShopCommand(this));
        getCommand("donshop").setExecutor(new DonShopCommand(this));

        getServer().getPluginManager().registerEvents(new ShopGuiListener(this), this);
        getServer().getPluginManager().registerEvents(new TrapListener(this), this);

        getLogger().info("CustomShop успешно запущен!");
    }

    @Override
    public void onDisable() {
        if (listingManager != null) listingManager.save();
        if (donateEconomy != null) donateEconomy.save();
    }

    public static CustomShopPlugin get() { return instance; }
    public PointsEconomy getPointsEconomy() { return pointsEconomy; }
    public DonateEconomy getDonateEconomy() { return donateEconomy; }
    public ShopManager getShopManager() { return shopManager; }
    public ListingManager getListingManager() { return listingManager; }
}
