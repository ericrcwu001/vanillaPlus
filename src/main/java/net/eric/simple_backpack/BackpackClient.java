package net.eric.simple_backpack;

import net.eric.simple_backpack.backpack.BackpackClientScreen;
import net.eric.simple_backpack.backpack.BackpackScreenHandler;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.TranslatableText;

@Environment(EnvType.CLIENT)
public class BackpackClient implements ClientModInitializer
{
     @Override
    public void onInitializeClient()
    {
        for (int i = 0; i < 16; ++i) {
            ScreenProviderRegistry.INSTANCE.<BackpackScreenHandler>registerFactory(Backpack.IDENTIFIERS[i],
                    (container -> new BackpackClientScreen(container, MinecraftClient.getInstance().player.getInventory(),
                            new TranslatableText("itemGroup.simple_backpack.simple_backpack_group"))));
        }
    }
}
