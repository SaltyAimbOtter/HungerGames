package tk.shanebee.hg.loot;

import java.util.ArrayList;
import java.util.Iterator;

public class LootBundle {

    ArrayList<LootItem> items;
    int probability;

    public Iterator<LootItem> getItems() {
        return items.iterator();
    }
}
