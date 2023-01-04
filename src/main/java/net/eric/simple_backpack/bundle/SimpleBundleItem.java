package net.eric.simple_backpack.bundle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.util.NbtType;

import net.minecraft.client.item.BundleTooltipData;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.item.TooltipData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.BundleItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ClickType;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/*
    Adapted from Minecraft source
    class: BundleItem
 */

public class SimpleBundleItem extends Item
{
    private static final int ITEM_BAR_COLOR = MathHelper.packRgb(0.4F, 0.4F, 1.0F);
    private final int maxStorage;

    public SimpleBundleItem(Settings settings, int maxStorage)
    {
        super(settings);
        this.maxStorage = maxStorage;
    }

    @Override
    public boolean canBeNested()
    {
        return false;
    }

    @Override
    public boolean onStackClicked(ItemStack stack, Slot slot, ClickType clickType, PlayerEntity player)
    {
        if (clickType != ClickType.RIGHT)
        {
            return false;
        }
        else
        {
            ItemStack slotStack = slot.getStack();
            if (slotStack.isEmpty())
            {
                getTopStack(slotStack).ifPresent(removedStack -> addToBundle(slotStack, slot.insertStack(removedStack)));
            }
            else if (slotStack.getItem().canBeNested())
            {
                int i = (maxStorage - getBundleOccupancy(stack)) / getItemOccupancy(slotStack);
                addToBundle(stack, slot.takeStackRange(slotStack.getCount(), i, player));
            }

            return true;
        }
    }

    @Override
    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player,
                             StackReference cursorStackReference)
    {
        if (clickType == ClickType.RIGHT && slot.canTakePartial(player))
        {
            if (otherStack.isEmpty())
            {
                getTopStack(stack).ifPresent(cursorStackReference::set);
            }
            else
            {
                otherStack.decrement(addToBundle(stack, otherStack));
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand)
    {
        ItemStack stack = user.getStackInHand(hand);
        return dumpBundle(stack, user) ? TypedActionResult.success(stack, world.isClient()) : TypedActionResult.fail(stack);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public boolean isItemBarVisible(ItemStack stack)
    {
        return getBundleOccupancy(stack) > 0;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public int getItemBarStep(ItemStack stack)
    {
        return Math.min(13 * getBundleOccupancy(stack) / maxStorage, 13);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public int getItemBarColor(ItemStack stack)
    {
        return ITEM_BAR_COLOR;
    }

    private int addToBundle(ItemStack bundle, ItemStack stack)
    {
        if (!stack.isEmpty() && stack.getItem().canBeNested())
        {
            NbtCompound tag = bundle.getOrCreateNbt();
            if (!tag.contains("Items"))
            {
                tag.put("Items", new NbtList());
            }

            NbtList items = tag.getList("Items", 10);
            BundleInventory inv = new BundleInventory(bundle, items);
            int remainder = stack.getCount() - inv.addStack(stack).getCount();
            tag.put("Items", inv.getTags());
            return remainder;
        }
        else
        {
            return 0;
        }
    }

    private int getItemOccupancy(ItemStack stack)
    {
        if (stack.getItem() instanceof BundleItem)
        {
            return 4 + getBundledStacks(stack).mapToInt((itemStack) -> getItemOccupancy(itemStack) * itemStack.getCount()).sum();
        }
        else if (stack.getItem() instanceof SimpleBundleItem)
        {
            return 4 + getBundleOccupancy(stack);
        }
        return 64 / stack.getMaxCount();
    }

    private int getBundleOccupancy(ItemStack stack)
    {
        return new BundleInventory(stack).count;
    }

    private static Optional<ItemStack> getTopStack(ItemStack itemStack)
    {
        NbtCompound tag = itemStack.getOrCreateNbt();
        if (!tag.contains("Items"))
        {
            return Optional.empty();
        }
        else
        {
            NbtList items = tag.getList("Items", 10);
            if (items.isEmpty())
            {
                return Optional.empty();
            }
            else
            {
                NbtCompound item = items.getCompound(0);
                ItemStack stack = ItemStack.fromNbt(item);
                items.remove(0);
                return Optional.of(stack);
            }
        }
    }

    private static boolean dumpBundle(ItemStack itemStack, PlayerEntity playerEntity)
    {
        NbtCompound tag = itemStack.getOrCreateNbt();
        if (!tag.contains("Items"))
        {
            return false;
        }
        else
        {
            if(playerEntity.isSneaking())  // Safety check
            {
                if (playerEntity instanceof ServerPlayerEntity)
                {
                    NbtList items = tag.getList("Items", 10);

                    for(int i = 0; i < items.size(); ++i)
                    {
                        NbtCompound item = items.getCompound(i);
                        ItemStack stack = ItemStack.fromNbt(item);
                        playerEntity.dropItem(stack, true);
                    }
                }

                itemStack.removeSubNbt("Items");
                return true;
            }
        }

        return false;
    }

    private static Stream<ItemStack> getBundledStacks(ItemStack stack)
    {
        NbtCompound compoundTag = stack.getNbt();
        if (compoundTag == null)
        {
            return Stream.empty();
        }
        else
        {
            NbtList listTag = compoundTag.getList("Items", 10);
            return listTag.stream().map(NbtCompound.class::cast).map(ItemStack::fromNbt);
        }
    }

    @Override
    @Environment(EnvType.CLIENT)
    public Optional<TooltipData> getTooltipData(ItemStack stack)
    {
        DefaultedList<ItemStack> defaultedList = DefaultedList.of();
        getBundledStacks(stack).forEach(defaultedList::add);
        return Optional.of(new BundleTooltipData(defaultedList, (int) (((float) getBundleOccupancy(stack) / (float) maxStorage) * 64)));
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context)
    {
        tooltip.add((new TranslatableText("item.minecraft.bundle.fullness", getBundleOccupancy(stack), maxStorage)).formatted(Formatting.GRAY));
        tooltip.add((new TranslatableText("item.simple_backpack.simple_bundle.tip1").formatted(Formatting.YELLOW)));
    }



    /*

        Class to define bundle inventory object

     */
    private class BundleInventory implements Inventory
    {
        private final ItemStack bundle;
        private final List<ItemStack> stacks = DefaultedList.of();

        public int count;

        private BundleInventory(ItemStack bundle)
        {
            this.bundle = bundle;
            if (bundle.hasNbt() && bundle.getNbt().contains("Items"))
            {
                this.readNbt(bundle.getNbt().getList("Items", NbtType.COMPOUND));
            }
        }

        private BundleInventory(ItemStack bundle, NbtList nbtList)
        {
            this.bundle = bundle;
            this.readNbt(nbtList);
        }

        public ItemStack addStack(ItemStack stack)
        {
            ItemStack insertStack = stack.copy();
            int itemOccupancy = getItemOccupancy(stack);
            int insertCount = Math.min(stack.getCount(), (maxStorage - count) / itemOccupancy);
            if (insertCount == 0) return insertStack;
            int remainder = insertStack.getCount() - insertCount;
            insertStack.setCount(insertCount);
            this.addToExistingSlot(insertStack);
            if (insertStack.isEmpty())
            {
                ItemStack ret = stack.copy();
                ret.setCount(remainder);
                return ret;
            }
            else
            {
                this.addToNewSlot(insertStack);
                if (insertStack.isEmpty())
                {
                    ItemStack ret = stack.copy();
                    ret.setCount(remainder);
                    return ret;
                }
                else
                {
                    insertStack.increment(remainder);
                    return insertStack;
                }
            }
        }

        private void addToExistingSlot(ItemStack stack)
        {
            for(int i = 0; i < this.size(); ++i)
            {
                ItemStack itemStack = this.getStack(i);
                if (ItemStack.canCombine(itemStack, stack))
                {
                    this.transfer(stack, itemStack);
                    if (stack.isEmpty())
                    {
                        return;
                    }
                }
            }

        }

        public void readNbt(NbtList tags)
        {
            for(int i = tags.size(); i >= 0; --i)
            {
                ItemStack itemStack = ItemStack.fromNbt(tags.getCompound(i));
                if (!itemStack.isEmpty())
                {
                    this.addStack(itemStack);
                }
            }
            this.markDirty();
        }

        public NbtList getTags()
        {
            NbtList listTag = new NbtList();

            for(int i = 0; i < this.size(); ++i)
            {
                ItemStack itemStack = this.getStack(i);
                if (!itemStack.isEmpty())
                {
                    listTag.add(itemStack.writeNbt(new NbtCompound()));
                }
            }

            return listTag;
        }

        private void transfer(ItemStack source, ItemStack target)
        {
            int i = Math.min(this.getMaxCountPerStack(), target.getMaxCount());
            int j = Math.min(source.getCount(), i - target.getCount());
            if (j > 0)
            {
                target.increment(j);
                source.decrement(j);
                this.markDirty();
            }

        }

        private void addToNewSlot(ItemStack stack)
        {
            this.stacks.add(0, stack.copy());
            stack.setCount(0);
        }

        @Override
        public int size()
        {
            return stacks.size();
        }

        @Override
        public boolean isEmpty()
        {
            for (ItemStack stack : stacks)
            {
                if (!stack.isEmpty()) return false;
            }

            return true;
        }

        @Override
        public ItemStack getStack(int slot)
        {
            return slot >= 0 && slot < this.stacks.size() ? this.stacks.get(slot) : ItemStack.EMPTY;
        }

        @Override
        public ItemStack removeStack(int slot, int amount)
        {
            ItemStack itemStack = Inventories.splitStack(this.stacks, slot, amount);
            if (!itemStack.isEmpty())
            {
                this.markDirty();
            }

            return itemStack;
        }

        @Override
        public ItemStack removeStack(int slot)
        {
            ItemStack itemStack = this.stacks.get(slot);
            if (itemStack.isEmpty())
            {
                return ItemStack.EMPTY;
            }
            else
            {
                this.stacks.set(slot, ItemStack.EMPTY);
                return itemStack;
            }
        }

        @Override
        public void setStack(int slot, ItemStack stack)
        {
            if (slot < stacks.size())
            {
                this.stacks.set(slot, stack);
            }
            else
            {
                this.stacks.add(stack);
            }

            if (!stack.isEmpty() && stack.getCount() > this.getMaxCountPerStack())
            {
                stack.setCount(this.getMaxCountPerStack());
            }
        }

        @Override
        public void markDirty()
        {
            updateCount();
        }

        @Override
        public boolean canPlayerUse(PlayerEntity player)
        {
            return true;
        }

        @Override
        public void clear()
        {
            this.stacks.clear();
            this.markDirty();
        }

        private void updateCount()
        {
            count = 0;
            for (ItemStack stack : stacks)
            {
                count += getItemOccupancy(stack) * stack.getCount();
            }
        }
    }
}