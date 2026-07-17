package ru.example.customshop.shop;

public enum ShopCategory {
    ORUZHIE("Оружие"),
    BRONYA("Броня"),
    EDA("Еда"),
    BLOKI("Блоки"),
    ZELYA("Зелья"),
    INSTRUMENTY("Инструменты"),
    LOVUSHKI("Ловушки"),
    RAZNOE("Разное");

    private final String title;
    ShopCategory(String title) { this.title = title; }
    public String getTitle() { return title; }
}
