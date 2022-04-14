package me.ilucah.ahmaadsadventure.entity.model.inventory;

public class InventoryItem {

    private String name;
    private int amount;
    private final int id;

    public InventoryItem(int id, int amount, String name) {
        this.id = id;
        this.amount = amount;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
