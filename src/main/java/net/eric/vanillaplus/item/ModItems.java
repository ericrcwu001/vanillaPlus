package net.eric.vanillaplus.item;

import net.eric.vanillaplus.VanillaPlus;
import net.eric.vanillaplus.item.custom.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item MYTHRIL_INGOT = registerItem("mythril_ingot",
            new Item(new FabricItemSettings().group(ModItemGroup.AETHER)));
    public static final Item MYTHRIL_NUGGET = registerItem("mythril_nugget",
            new Item(new FabricItemSettings().group(ModItemGroup.AETHER)));
    public static final Item RAW_MYTHRIL = registerItem("raw_mythril",
            new Item(new FabricItemSettings().group(ModItemGroup.AETHER)));

    public static final Item MYTHRIL_SWORD = registerItem("mythril_sword",
            new ModSlownessSwordItem(ModToolMaterials.MYTHRIL, 1, 2f,
                    new FabricItemSettings().group(ModItemGroup.AETHER)));
    public static final Item MYTHRIL_AXE = registerItem("mythril_axe",
            new ModHasteAxeItem(ModToolMaterials.MYTHRIL, 3, 1f,
                    new FabricItemSettings().group(ModItemGroup.AETHER)));
    public static final Item MYTHRIL_HOE = registerItem("mythril_hoe",
            new ModSpeedHoeItem(ModToolMaterials.MYTHRIL, 0, 0f,
                    new FabricItemSettings().group(ModItemGroup.AETHER)));
    public static final Item MYTHRIL_SHOVEL = registerItem("mythril_shovel",
            new ModHasteShovelItem(ModToolMaterials.MYTHRIL, 0, 1f,
                    new FabricItemSettings().group(ModItemGroup.AETHER)));
    public static final Item MYTHRIL_PICKAXE = registerItem("mythril_pickaxe",
            new ModHastePickaxeItem(ModToolMaterials.MYTHRIL, 1, 1f,
                    new FabricItemSettings().group(ModItemGroup.AETHER)));

    public static final Item MYTHRIL_HELMET = registerItem("mythril_helmet",
            new ModArmorItem(ModArmorMaterials.MYTHRIL, EquipmentSlot.HEAD,
                    new FabricItemSettings().group(ModItemGroup.AETHER)));
    public static final Item MYTHRIL_CHESTPLATE = registerItem("mythril_chestplate",
            new ArmorItem(ModArmorMaterials.MYTHRIL, EquipmentSlot.CHEST,
                    new FabricItemSettings().group(ModItemGroup.AETHER)));
    public static final Item MYTHRIL_LEGGINGS = registerItem("mythril_leggings",
            new ArmorItem(ModArmorMaterials.MYTHRIL, EquipmentSlot.LEGS,
                    new FabricItemSettings().group(ModItemGroup.AETHER)));
    public static final Item MYTHRIL_BOOTS = registerItem("mythril_boots",
            new ArmorItem(ModArmorMaterials.MYTHRIL, EquipmentSlot.FEET,
                    new FabricItemSettings().group(ModItemGroup.AETHER)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(VanillaPlus.MOD_ID, name), item);
    }

    public static void registerModItems() {
        VanillaPlus.LOGGER.info("Registering Mod Items for " + VanillaPlus.MOD_ID);
    }
}
