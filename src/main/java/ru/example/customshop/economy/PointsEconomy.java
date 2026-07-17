package ru.example.customshop.economy;

import dev.rosewood.playerpoints.api.PlayerPointsAPI;
import org.black_ixx.playerpoints.PlayerPoints;
import java.util.UUID;

public class PointsEconomy {

    private final PlayerPointsAPI api;

    public PointsEconomy() {
        this.api = PlayerPoints.getInstance().getAPI();
    }

    public int getBalance(UUID uuid) {
        return api.look(uuid);
    }

    public boolean has(UUID uuid, int amount) {
        return getBalance(uuid) >= amount;
    }

    public boolean take(UUID uuid, int amount) {
        if (!has(uuid, amount)) return false;
        return api.take(uuid, amount);
    }

    public void give(UUID uuid, int amount) {
        api.give(uuid, amount);
    }
}
