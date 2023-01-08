package net.eric.trees;

import net.eric.trees.block.ModBlocks;
import net.eric.trees.item.ModItems;
import net.eric.trees.util.ModRegistries;
import net.eric.trees.world.feature.ModConfiguredFeatures;
import net.eric.trees.world.feature.gen.ModWorldGen;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Trees implements ModInitializer {
    public static final String MOD_ID = "trees";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModConfiguredFeatures.registerConfiguredFeatures();

        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        ModRegistries.registerModStuffs();

        ModWorldGen.generateModWorldGen();
    }
}
