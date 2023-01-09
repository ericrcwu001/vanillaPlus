package net.eric.vanillaplus;

import net.eric.vanillaplus.block.ModBlocks;
import net.eric.vanillaplus.item.ModItems;
import net.eric.vanillaplus.world.dimension.ModDimensions;
import net.eric.vanillaplus.world.feature.ModConfiguredFeatures;
import net.eric.vanillaplus.world.gen.ModWorldGen;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VanillaPlus implements ModInitializer {
	public static final String MOD_ID = "vanillaplus";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModConfiguredFeatures.registerConfiguredFeatures();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModWorldGen.generateModWorldGen();

		ModDimensions.register();
	}
}
