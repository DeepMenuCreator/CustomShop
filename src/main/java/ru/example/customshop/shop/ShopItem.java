package ru.example.customshop.shop;

import org.bukkit.inventory.ItemStack;

public class ShopItem {
    private final String id;
    private final ItemStack itemStack;
    private final ShopCategory category;
    private final int price; // цена в PlayerPoints

    public ShopItem(String id, ItemStack itemStack, ShopCategory category, int price) {
        this.id = id;
        this.itemStack = itemStack;
        this.category = category;
        this.price = price;
    }

    public String getId() { return id; }
    public ItemStack getItemStack() { return itemStack.clone(); }
    public ShopCategory getCategory() { return category; }
    public int getPrice() { return price; }
}
