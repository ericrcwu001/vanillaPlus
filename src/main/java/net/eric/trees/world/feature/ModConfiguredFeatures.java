package net.eric.trees.world.feature;

import net.eric.trees.Trees;
import net.eric.trees.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.foliage.SpruceFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.List;

public class ModConfiguredFeatures {
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> JACARANDA_TREE =
            ConfiguredFeatures.register("jacaranda_tree", Feature.TREE, new TreeFeatureConfig.Builder(
                    BlockStateProvider.of(ModBlocks.JACARANDA_LOG),
                    new StraightTrunkPlacer(5, 3, 1),
                    BlockStateProvider.of(ModBlocks.JACARANDA_LEAVES),
                    new SpruceFoliagePlacer(UniformIntProvider.create(2, 4),
                            UniformIntProvider.create(0, 2), UniformIntProvider.create(1, 2)),
                    new TwoLayersFeatureSize(2, 0, 2)).ignoreVines().build());

    public static final RegistryEntry<PlacedFeature> JACARANDA_CHECKED =
            PlacedFeatures.register("jacaranda_checked", JACARANDA_TREE,
                    PlacedFeatures.wouldSurvive(ModBlocks.JACARANDA_SAPLING));

    public static final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> JACARANDA_SPAWN =
            ConfiguredFeatures.register("jacaranda_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfig(List.of(new RandomFeatureEntry(JACARANDA_CHECKED, 0.5f)),
                            JACARANDA_CHECKED));

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> CHERRY_TREE =
            ConfiguredFeatures.register("cherry_tree", Feature.TREE, new TreeFeatureConfig.Builder(
                    BlockStateProvider.of(ModBlocks.CHERRY_LOG),
                    new StraightTrunkPlacer(5, 3, 1),
                    BlockStateProvider.of(ModBlocks.CHERRY_LEAVES),
                    new SpruceFoliagePlacer(UniformIntProvider.create(2, 4),
                            UniformIntProvider.create(0, 2), UniformIntProvider.create(1, 2)),
                    new TwoLayersFeatureSize(2, 0, 2)).ignoreVines().build());

    public static final RegistryEntry<PlacedFeature> CHERRY_CHECKED =
            PlacedFeatures.register("cherry_checked", CHERRY_TREE,
                    PlacedFeatures.wouldSurvive(ModBlocks.CHERRY_SAPLING));

    public static final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> CHERRY_SPAWN =
            ConfiguredFeatures.register("cherry_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfig(List.of(new RandomFeatureEntry(CHERRY_CHECKED, 0.5f)),
                            CHERRY_CHECKED));

    public static void registerConfiguredFeatures() {
        System.out.println("Registering ModConfiguredFeatures for " + Trees.MOD_ID);
    }
}
