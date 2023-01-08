package net.eric.trees;

import net.eric.trees.util.ModRegistries;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Trees implements ModInitializer {
    public static final String MOD_ID = "trees";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModRegistries.registerModStuffs();
    }
}
