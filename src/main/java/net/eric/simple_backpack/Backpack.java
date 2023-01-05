package net.eric.simple_backpack;

import net.eric.simple_backpack.backpack.BackpackInventoryInterface;
import net.eric.simple_backpack.backpack.BackpackItem;
import net.eric.simple_backpack.backpack.BackpackScreenHandler;
import net.eric.simple_backpack.config.ModConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Backpack implements ModInitializer
{
    public static Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "simple_backpack";
    public static final String MOD_NAME = "SimpleBackpack";
    public static final ItemGroup SIMPLE_BACKPACK_GROUP = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "simple_backpack_group"), () -> new ItemStack(Backpack.WHITE_BACKPACK));

    public static final ModConfig CONFIG = AutoConfig.register(ModConfig.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new)).getConfig();

    public static final Identifier WHITE_BACKPACK_IDENTIFIER = new Identifier(MOD_ID, "white_backpack");
    public static final Identifier ORANGE_BACKPACK_IDENTIFIER = new Identifier(MOD_ID, "orange_backpack");
    public static final Identifier MAGENTA_BACKPACK_IDENTIFIER = new Identifier(MOD_ID, "magenta_backpack");
    public static final Identifier LIGHT_BLUE_BACKPACK_IDENTIFIER = new Identifier(MOD_ID, "light_blue_backpack");
    public static final Identifier YELLOW_BACKPACK_IDENTIFIER = new Identifier(MOD_ID, "yellow_backpack");
    public static final Identifier LIME_BACKPACK_IDENTIFIER = new Identifier(MOD_ID, "lime_backpack");
    public static final Identifier PINK_BACKPACK_IDENTIFIER = new Identifier(MOD_ID, "pink_backpack");
    public static final Identifier GRAY_BACKPACK_IDENTIFIER = new Identifier(MOD_ID, "gray_backpack");
    public static final Identifier LIGHT_GRAY_BACKPACK_IDENTIFIER = new Identifier(MOD_ID, "light_gray_backpack");
    public static final Identifier CYAN_BACKPACK_IDENTIFIER = new Identifier(MOD_ID, "cyan_backpack");
    public static final Identifier PURPLE_BACKPACK_IDENTIFIER = new Identifier(MOD_ID, "purple_backpack");
    public static final Identifier BLUE_BACKPACK_IDENTIFIER = new Identifier(MOD_ID, "blue_backpack");
    public static final Identifier BROWN_BACKPACK_IDENTIFIER = new Identifier(MOD_ID, "brown_backpack");
    public static final Identifier GREEN_BACKPACK_IDENTIFIER = new Identifier(MOD_ID, "green_backpack");
    public static final Identifier RED_BACKPACK_IDENTIFIER = new Identifier(MOD_ID, "red_backpack");
    public static final Identifier BLACK_BACKPACK_IDENTIFIER = new Identifier(MOD_ID, "black_backpack");
    public static final Identifier[] IDENTIFIERS = new Identifier[]{
            WHITE_BACKPACK_IDENTIFIER, ORANGE_BACKPACK_IDENTIFIER, MAGENTA_BACKPACK_IDENTIFIER,
            LIGHT_BLUE_BACKPACK_IDENTIFIER, YELLOW_BACKPACK_IDENTIFIER, LIME_BACKPACK_IDENTIFIER,
            PINK_BACKPACK_IDENTIFIER, GRAY_BACKPACK_IDENTIFIER, LIGHT_GRAY_BACKPACK_IDENTIFIER,
            CYAN_BACKPACK_IDENTIFIER, PURPLE_BACKPACK_IDENTIFIER, BLUE_BACKPACK_IDENTIFIER,
            BROWN_BACKPACK_IDENTIFIER, GREEN_BACKPACK_IDENTIFIER, RED_BACKPACK_IDENTIFIER,
            BLACK_BACKPACK_IDENTIFIER};

    public static final String[] TRANSLATION_KEYS = new String[16];
    public static final Item WHITE_BACKPACK = new BackpackItem(new Item.Settings().group(Backpack.SIMPLE_BACKPACK_GROUP).maxCount(1));
    public static final Item ORANGE_BACKPACK = new BackpackItem(new Item.Settings().group(Backpack.SIMPLE_BACKPACK_GROUP).maxCount(1));
    public static final Item MAGENTA_BACKPACK = new BackpackItem(new Item.Settings().group(Backpack.SIMPLE_BACKPACK_GROUP).maxCount(1));
    public static final Item LIGHT_BLUE_BACKPACK = new BackpackItem(new Item.Settings().group(Backpack.SIMPLE_BACKPACK_GROUP).maxCount(1));
    public static final Item YELLOW_BACKPACK = new BackpackItem(new Item.Settings().group(Backpack.SIMPLE_BACKPACK_GROUP).maxCount(1));
    public static final Item LIME_BACKPACK = new BackpackItem(new Item.Settings().group(Backpack.SIMPLE_BACKPACK_GROUP).maxCount(1));
    public static final Item PINK_BACKPACK = new BackpackItem(new Item.Settings().group(Backpack.SIMPLE_BACKPACK_GROUP).maxCount(1));
    public static final Item GRAY_BACKPACK = new BackpackItem(new Item.Settings().group(Backpack.SIMPLE_BACKPACK_GROUP).maxCount(1));
    public static final Item LIGHT_GRAY_BACKPACK = new BackpackItem(new Item.Settings().group(Backpack.SIMPLE_BACKPACK_GROUP).maxCount(1));

    public static final Item CYAN_BACKPACK = new BackpackItem(new Item.Settings().group(Backpack.SIMPLE_BACKPACK_GROUP).maxCount(1));

    public static final Item PURPLE_BACKPACK = new BackpackItem(new Item.Settings().group(Backpack.SIMPLE_BACKPACK_GROUP).maxCount(1));

    public static final Item BLUE_BACKPACK = new BackpackItem(new Item.Settings().group(Backpack.SIMPLE_BACKPACK_GROUP).maxCount(1));

    public static final Item BROWN_BACKPACK = new BackpackItem(new Item.Settings().group(Backpack.SIMPLE_BACKPACK_GROUP).maxCount(1));
    public static final Item GREEN_BACKPACK = new BackpackItem(new Item.Settings().group(Backpack.SIMPLE_BACKPACK_GROUP).maxCount(1));
    public static final Item RED_BACKPACK = new BackpackItem(new Item.Settings().group(Backpack.SIMPLE_BACKPACK_GROUP).maxCount(1));
    public static final Item BLACK_BACKPACK = new BackpackItem(new Item.Settings().group(Backpack.SIMPLE_BACKPACK_GROUP).maxCount(1));

    public static final Item[] BACKPACKS = new Item[]{
            WHITE_BACKPACK, ORANGE_BACKPACK, MAGENTA_BACKPACK, LIGHT_BLUE_BACKPACK, YELLOW_BACKPACK, LIME_BACKPACK,
            PINK_BACKPACK, GRAY_BACKPACK, LIGHT_GRAY_BACKPACK, CYAN_BACKPACK, PURPLE_BACKPACK, BLUE_BACKPACK,
            BROWN_BACKPACK, GREEN_BACKPACK, RED_BACKPACK, BLACK_BACKPACK};



    @Override
    public void onInitialize()
    {
        String[] colours = new String[]{"white", "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray",
                "light_gray", "cyan", "purple", "blue", "brown", "green", "red", "black"};
        for (int i = 0; i < 16; ++i) {
            TRANSLATION_KEYS[i] = "item.simple_backpack." + colours[i] + "_backpack";
        }

        for (Identifier TEMP: IDENTIFIERS) {
            ContainerProviderRegistry.INSTANCE.registerFactory(TEMP, ((syncId, identifier, player, buf) -> {
                final ItemStack stack = buf.readItemStack();
                final Hand hand = buf.readInt() == 0 ? Hand.MAIN_HAND : Hand.OFF_HAND;
                final BackpackInventoryInterface inventory = BackpackItem.getInventory(stack, hand, player);
                final String customTitle = buf.readString();

                return new BackpackScreenHandler(syncId, player.getInventory(), inventory.getInventory(), inventory.getInventoryWidth(), inventory.getInventoryHeight(), hand, customTitle);
            }));
        }

        if(CONFIG.GENERAL.enableBackpack)
        {
            for (int i = 0; i < 16; ++i) {
                Registry.register(Registry.ITEM, IDENTIFIERS[i], BACKPACKS[i]);
            }
        }
    }

    public static void log(Level level, String message)
    {
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }
}