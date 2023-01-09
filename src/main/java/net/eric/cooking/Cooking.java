package net.eric.cooking;

import net.eric.cooking.item.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Cooking implements ModInitializer {
    public static final String MOD_ID = "cooking";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    @Override
    public void onInitialize() {
        ModItems.registerModItems();
    }
}
