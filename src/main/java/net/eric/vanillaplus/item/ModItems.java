package net.eric.vanillaplus.item;

import net.eric.vanillaplus.VanillaPlus;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item MYTHRIL_INGOT = registerItem("mythril_ingot",
            new Item(new FabricItemSettings().group(ItemGroup.MISC)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(VanillaPlus.MOD_ID, name), item);
    }

    public static void registerModItems() {
        VanillaPlus.LOGGER.info("Registering Mod Items for " + VanillaPlus.MOD_ID);
    }
}
