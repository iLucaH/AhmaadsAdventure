package me.ilucah.ahmaadsadventure.gfx.objects;

import me.ilucah.ahmaadsadventure.entity.model.InventoryHolder;
import me.ilucah.ahmaadsadventure.entity.model.inventory.InventoryItem;
import me.ilucah.ahmaadsadventure.handler.Handler;

import java.awt.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class InventoryPanel {

    public static float ITEM_SIZE = 70;

    private InventoryHolder owner;
    private List<InventoryItem> items;
    private int x, y, rows;
    private int width, height;

    private Rectangle generalBounding;
    private ConcurrentHashMap<InventoryItem, Rectangle> itemBounding;

    private boolean isOpen = false;

    public InventoryPanel(InventoryHolder owner, List<InventoryItem> items, int xLoc, int yLoc, int rows) {
        this.owner = owner;
        this.items = items;
        this.x = xLoc;
        this.y = yLoc;
        this.rows = rows;
        this.width = (int) ITEM_SIZE * 5 + (5 * 10); // gap of 10px between item
        this.height = (int) ITEM_SIZE * rows + (rows * 10); // gap of 10px between item

        this.generalBounding = new Rectangle(x, y, width, height);
        itemBounding = new ConcurrentHashMap<>();
        for (int i = 0; i < items.size(); i++) {
            InventoryItem item = items.get(i);
            int row = i + 1 < 5 ? 1 : i + 1 < 10 ? 2 : i + 1 < 15 ? 3 : i + 1 < 20 ? 4 : 5;

        }
    }

    public void paint(Graphics g) {
        if (isOpen == false)
            return;
        for (int i = 0; i < items.size(); i++) {
            InventoryItem item = items.get(i);
            g.drawString(item.getName(), x, y);
        }
    }

    public void tick(Handler handler) {
        if (isOpen == false)
            return;
    }
}
