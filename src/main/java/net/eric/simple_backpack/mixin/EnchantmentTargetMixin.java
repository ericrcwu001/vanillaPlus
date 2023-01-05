package net.eric.simple_backpack.mixin;

import net.eric.simple_backpack.backpack.BasePack;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// BREAKABLE enchantment target
@Mixin(targets = "net/minecraft/enchantment/EnchantmentTarget$2")
public class EnchantmentTargetMixin
{
//    @Inject(method = "Lnet/minecraft/enchantment/EnchantmentTarget$2;isAcceptableItem(Lnet/minecraft/item/Item;)Z", at = @At("HEAD"), cancellable = true)
//    private void backpackIsAcceptableItem(Item item, CallbackInfoReturnable<Boolean> cir)
//    {
//        if (item instanceof BasePack ||
//                item instanceof SimpleBundleItem)
//        {
//            cir.setReturnValue(true);
//        }
//    }
}