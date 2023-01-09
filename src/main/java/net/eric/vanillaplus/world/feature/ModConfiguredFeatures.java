package net.eric.vanillaplus.world.feature;

import net.eric.vanillaplus.VanillaPlus;
import net.eric.vanillaplus.block.ModBlocks;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;

import java.util.List;

public class ModConfiguredFeatures {
    public static final List<OreFeatureConfig.Target> OVERWORLD_MYTHRIL_ORES = List.of(
            OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES,
                    ModBlocks.MYTHRIL_ORE.getDefaultState()),
            OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES,
                    ModBlocks.DEEPSLATE_MYTHRIL_ORE.getDefaultState()));

    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> MYTHRIL_ORE =
            ConfiguredFeatures.register("mythril_ore",Feature.ORE,
                    new OreFeatureConfig(OVERWORLD_MYTHRIL_ORES, 9));



    public static void registerConfiguredFeatures() {
        System.out.println("Registering ModConfiguredFeatures for " + VanillaPlus.MOD_ID);
    }
}
