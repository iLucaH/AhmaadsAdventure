package me.ilucah.ahmaadsadventure.entity.model.inventory;

import me.ilucah.ahmaadsadventure.entity.implementation.Player;
import me.ilucah.ahmaadsadventure.gfx.objects.InventoryPanel;

public class InventoryFactory {

    public static InventoryPanel openInventory(Player player, Inventory inventory) {
        return new InventoryPanel(player, inventory.getItems(), 100, 100, inventory.getRows());
    }

    private Inventory inventory;
    private Player player;

    private InventoryFactory(Inventory inv, Player p) {
        this.inventory = inv;
        this.player = p;
    }

    private InventoryFactory build() {

        return this;
    }
}
