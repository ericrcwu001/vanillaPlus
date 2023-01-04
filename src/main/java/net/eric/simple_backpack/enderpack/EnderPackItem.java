package net.eric.simple_backpack.enderpack;

import net.eric.simple_backpack.backpack.BasePack;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class EnderPackItem extends BasePack
{
	public EnderPackItem(Item.Settings settings)
	{
		super(settings);
	}

	public static final TranslatableText TITLE = new TranslatableText("container.simple_backpack.ender_pack");

	@Override
	public boolean canBeNested()
	{
		return false;
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
	{
		ItemStack itemStack = player.getStackInHand(hand);
		player.setCurrentHand(hand);
		EnderChestInventory enderChest = player.getEnderChestInventory();

		if (enderChest != null)
		{
			if (!world.isClient)
			{
	            player.openHandledScreen(new SimpleNamedScreenHandlerFactory((i, playerInventory, playerEntity) -> {
	               return GenericContainerScreenHandler.createGeneric9x3(i, playerInventory, enderChest);
	            }, TITLE));
			}
		}

		return TypedActionResult.success(itemStack);
	}

	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext)
	{
	    tooltip.add(new TranslatableText("item.simple_backpack.ender_pack").formatted(Formatting.YELLOW));
	}
}