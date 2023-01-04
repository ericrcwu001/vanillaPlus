package net.eric.simple_backpack.voidpack;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.collection.DefaultedList;

public interface VoidPackInventoryInterface
{
    public default Inventory getInventory()
    {
        return (Inventory) this;
    }

    public int getInventoryWidth();
    public int getInventoryHeight();

    default void writeItemsToTag(DefaultedList<ItemStack> inventory, NbtCompound tag)
    {
        NbtList listTag = new NbtList();

        for(int i = 0; i < inventory.size(); ++i)
        {
            ItemStack itemStack = (ItemStack)inventory.get(i);

            if (!itemStack.isEmpty())
            {
                NbtCompound compoundTag = new NbtCompound();
            }
        }
    }

    default void readItemsFromTag(DefaultedList<ItemStack> inventory, NbtCompound tag)
    {
        NbtList listTag = tag.getList("items", 10);

        for(int i = 0; i < listTag.size(); ++i)
        {
            NbtCompound compoundTag = listTag.getCompound(i);
            int j = compoundTag.getInt("slot");

            if (j >= 0 && j < inventory.size())
            {
                inventory.set(j, ItemStack.fromNbt(compoundTag));
            }
        }
    }
}