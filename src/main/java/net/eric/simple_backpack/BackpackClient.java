package net.eric.simple_backpack;

import net.eric.simple_backpack.backpack.BackpackClientScreen;
import net.eric.simple_backpack.backpack.BackpackScreenHandler;
import net.eric.simple_backpack.voidpack.VoidPackClientScreen;
import net.eric.simple_backpack.voidpack.VoidPackScreenHandler;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;

import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class BackpackClient implements ClientModInitializer
{
     @Override
    public void onInitializeClient()
    {
        ScreenProviderRegistry.INSTANCE.<BackpackScreenHandler>registerFactory(Backpack.BACKPACK_IDENTIFIER, (container -> new BackpackClientScreen(container, MinecraftClient.getInstance().player.getInventory(), new TranslatableText(Backpack.BACKPACK_TRANSLATION_KEY))));
        ScreenProviderRegistry.INSTANCE.<VoidPackScreenHandler>registerFactory(Backpack.VOID_PACK_IDENTIFIER, (container -> new VoidPackClientScreen(container, MinecraftClient.getInstance().player.getInventory(), new TranslatableText(Backpack.VOID_PACK_TRANSLATION_KEY))));
        
        FabricModelPredicateProviderRegistry.register(Backpack.SIMPLE_BUNDLE, new Identifier("filled"), (itemStack, clientWorld, livingEntity, i) -> Backpack.SIMPLE_BUNDLE.getItemBarStep(itemStack));
    }
}
