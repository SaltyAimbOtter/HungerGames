package tk.shanebee.hg.loot;

import lombok.Setter;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class LootItem {
    @Setter
    private ItemStack item;
    @Setter
    private int minAmount;
    @Setter
    private int maxAmount;
    @Setter
    private int decreasedChance;

    /**
     * Copies the {@code LootItem} {@code ItemStack} and sets its amount to a value
     * that respects the minAmount and maxAmount settings.
     * @return {@code ItemStack} with amount.
     */
    public ItemStack getRolledItemStack() {
        ItemStack i = new ItemStack(item);
        i.setAmount(rollAmount());
        return i;
    }
    

    private int rollAmount() {
        Random ranGen = new Random();

        for (int i = 0; i <= maxAmount - minAmount; i++) {
            int newChance = 100 - decreasedChance * i;
            int rolledInt = ranGen.nextInt(101);

            if (rolledInt > newChance) {
               return minAmount + i;
            }
        }
        return maxAmount;
    }


}
