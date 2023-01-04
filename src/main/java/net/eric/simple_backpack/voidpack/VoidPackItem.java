package net.eric.simple_backpack.voidpack;

import net.eric.simple_backpack.Backpack;
import net.eric.simple_backpack.backpack.BasePack;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class VoidPackItem extends BasePack
{
	public VoidPackItem(Settings settings)
	{
		super(settings);
	}

    @Override
    public boolean canBeNested()
    {
        return false;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand)
    {
        user.setCurrentHand(hand);
        if(!world.isClient)
        {
            ContainerProviderRegistry.INSTANCE.openContainer(Backpack.VOID_PACK_IDENTIFIER, user, buf -> {
                buf.writeItemStack(user.getStackInHand(hand));
                buf.writeInt(hand == Hand.MAIN_HAND ? 0 : 1);
            });
        }

        return super.use(world, user, hand);
    }

    public static VoidPackInventory getInventory(ItemStack stack, Hand hand, PlayerEntity player)
    {
        if(!stack.hasNbt())
        {
            stack.setNbt(new NbtCompound());
        }

        if(!stack.getNbt().contains("void_pack"))
        {
            stack.getNbt().put("void_pack", new NbtCompound());
        }

        return new VoidPackInventory(stack.getNbt().getCompound("void_pack"), hand, player);
    }

	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
	{
	    tooltip.add(new TranslatableText("item.simple_backpack.void_pack").formatted(Formatting.YELLOW));
	}
}