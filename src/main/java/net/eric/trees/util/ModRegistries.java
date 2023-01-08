package net.eric.trees.util;

import net.eric.trees.block.ModBlocks;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;

public class ModRegistries {
    public static void registerModStuffs() {
        registerFlammableBlocks();
        registerStrippables();
    }
    private static void registerFlammableBlocks() {
        FlammableBlockRegistry instance = FlammableBlockRegistry.getDefaultInstance();

        instance.add(ModBlocks.JACARANDA_LOG, 5, 5);
        instance.add(ModBlocks.JACARANDA_PLANKS, 5, 20);
        instance.add(ModBlocks.JACARANDA_WOOD, 5, 5);
        instance.add(ModBlocks.STRIPPED_JACARANDA_LOG, 5, 5);
        instance.add(ModBlocks.STRIPPED_JACARANDA_WOOD, 5, 5);
        instance.add(ModBlocks.JACARANDA_LEAVES, 30, 60);

        instance.add(ModBlocks.CHERRY_LOG, 5, 5);
        instance.add(ModBlocks.CHERRY_PLANKS, 5, 20);
        instance.add(ModBlocks.CHERRY_WOOD, 5, 5);
        instance.add(ModBlocks.STRIPPED_CHERRY_LOG, 5, 5);
        instance.add(ModBlocks.STRIPPED_CHERRY_WOOD, 5, 5);
        instance.add(ModBlocks.CHERRY_LEAVES, 30, 60);

    }

    private static void registerStrippables() {
        StrippableBlockRegistry.register(ModBlocks.JACARANDA_LOG, ModBlocks.STRIPPED_JACARANDA_LOG);
        StrippableBlockRegistry.register(ModBlocks.JACARANDA_WOOD, ModBlocks.STRIPPED_JACARANDA_WOOD);

        StrippableBlockRegistry.register(ModBlocks.CHERRY_LOG, ModBlocks.STRIPPED_CHERRY_LOG);
        StrippableBlockRegistry.register(ModBlocks.CHERRY_WOOD, ModBlocks.STRIPPED_CHERRY_WOOD);
    }
}
