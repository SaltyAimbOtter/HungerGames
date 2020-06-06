package tk.shanebee.hg.loot;

import org.bukkit.block.Chest;
import org.bukkit.inventory.ItemStack;
import tk.shanebee.hg.HG;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;

public class Lootchest {

    String name;
    ArrayList<LootBundle> lootBundles;
    HashMap<Integer, ItemStack> chestContents = new HashMap<>();

    public void fill(Chest chest) {
        chestContents.putAll(generateChestContent());
        for (Integer i : chestContents.keySet()) {
            chest.getInventory().setItem(i, chestContents.get(i));
        }
    }

    public HashMap<Integer, ItemStack> generateChestContent() {
        ArrayList<ItemStack> allLootBundleItems = combineLootBundleItems();
        Random ranGen = new Random();
        int safetyInt = 0;

        for (ItemStack item : allLootBundleItems) {
            if (safetyInt == 27) {
                HG.getPlugin().getLogger().log(Level.SEVERE, "TOO MANY ITEMS IN LOOTCHEST '" + name + "'! Some Items were not generated!");
                return chestContents;
            }

            int slot = ranGen.nextInt(27);

            while (chestContents.containsValue(slot)) {
                slot = ranGen.nextInt(27);
            }
            chestContents.put(slot, item);

            safetyInt++;
        }
        return chestContents;
    }

    private ArrayList<ItemStack> combineLootBundleItems() {
        ArrayList<ItemStack> result = new ArrayList<>();

        for (LootBundle bundle : lootBundles) {
            Iterator<LootItem> i = bundle.getItems();
            while (i.hasNext()) {
                result.add(i.next().getRolledItemStack());
            }
        }
        return result;
    }
}
