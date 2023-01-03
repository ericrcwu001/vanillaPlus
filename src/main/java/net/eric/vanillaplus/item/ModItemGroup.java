package net.eric.vanillaplus.item;

import net.eric.vanillaplus.VanillaPlus;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static final ItemGroup AETHER = FabricItemGroupBuilder.build(
            new Identifier(VanillaPlus.MOD_ID, "aether"),
            () -> new ItemStack(ModItems.MYTHRIL_INGOT));
}
