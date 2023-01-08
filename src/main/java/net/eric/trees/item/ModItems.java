package net.eric.trees.item;

import net.eric.trees.Trees;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Trees.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Trees.LOGGER.info("Registering Mod Items for " + Trees.MOD_ID);
    }
}
