package me.ilucah.ahmaadsadventure.entity.model.inventory;

import me.ilucah.ahmaadsadventure.entity.model.InventoryHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Inventory {

    private List<InventoryItem> items;
    private InventoryHolder owner;

    private int rows = 4;

    public Inventory(InventoryHolder owner, List<InventoryItem> items) {
        this.items = items;
        this.owner = owner;
    }

    public Inventory(InventoryHolder owner, InventoryItem... items) {
        this(owner, Arrays.asList(items));
    }

    public Inventory(InventoryHolder owner) {
        this(owner, new ArrayList<>());
    }

    public List<InventoryItem> getItems() {
        return items;
    }

    public InventoryHolder getOwner() {
        return owner;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

}
