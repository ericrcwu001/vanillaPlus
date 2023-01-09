package net.eric.cooking.item;

import net.eric.cooking.Cooking;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static final Item CUTTING_BOARD = registerItem("cutting_board",
            new Item(new FabricItemSettings().group(ItemGroup.FOOD)));
    public static final Item CHEFS_KNIFE = registerItem("chefs_knife",
            new Item(new FabricItemSettings().group(ItemGroup.FOOD)));
    public static final Item STEAK_AND_POTATOES = registerItem("steak_and_potatoes",
            new Item(new FabricItemSettings().group(ItemGroup.FOOD).food(ModFoodComponents.STEAK_AND_POTATOES)));
    public static final Item SURF_AND_TURF = registerItem("surf_and_turf",
            new Item(new FabricItemSettings().group(ItemGroup.FOOD).food(ModFoodComponents.SURF_AND_TURF)));
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Cooking.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Cooking.LOGGER.info("Registering Mod Items for " + Cooking.MOD_ID);
    }
}
