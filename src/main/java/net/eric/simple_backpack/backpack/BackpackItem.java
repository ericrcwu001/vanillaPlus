package net.eric.simple_backpack.backpack;

import net.eric.simple_backpack.Backpack;
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

public class BackpackItem extends BasePack
{
    public BackpackItem(Settings settings)
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
            for (int i = 0; i < 16; ++i) {
                ContainerProviderRegistry.INSTANCE.openContainer(Backpack.IDENTIFIERS[i], user, buf -> {
                    ItemStack stack = user.getStackInHand(hand);
                    buf.writeItemStack(stack);
                    buf.writeInt(hand == Hand.MAIN_HAND ? 0 : 1);
                    buf.writeString(stack.getName().asString());
                });
            }

        }

        return super.use(world, user, hand);
    }

    public static BackpackInventory getInventory(ItemStack stack, Hand hand, PlayerEntity player)
    {
        if(!stack.hasNbt())
        {
            stack.setNbt(new NbtCompound());
        }

        if(!stack.getNbt().contains("backpack"))
        {
            stack.getNbt().put("backpack", new NbtCompound());
        }

        return new BackpackInventory(stack.getNbt().getCompound("backpack"), hand, player);
    }

	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
	{
        tooltip.add(new TranslatableText("item.simple_backpack.backpack.tooltip").formatted(Formatting.YELLOW));
	}
}