package mod.pushnyasha.rubymod.items;

import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;

public class MoltenRubyBucketItem extends Item{
    public static final int MAX_PROGRESS = 64;
    public MoltenRubyBucketItem(Properties properties) {
        super(properties.rarity(net.minecraft.world.item.Rarity.COMMON).stacksTo(1));
    }
    @Override
    public boolean isBarVisible(ItemStack stack) {
        return getFluidAmount(stack) < MAX_PROGRESS;
    }
    @Override
    public int getBarWidth(ItemStack stack) {
        float fluedAmountRatio = (float) getFluidAmount(stack) / MAX_PROGRESS;
        return Math.round(fluedAmountRatio * 13.0f);
    }
    @Override
    public int getBarColor(ItemStack stack) {
        return 0xBC2600;
    }
    
    public static int getFluidAmount(ItemStack stack) {
        CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
        if (customData != null) {
            CompoundTag tag = customData.copyTag();
            if (tag.contains("FluidAmount")) {
                return tag.getInt("FluidAmount");
            }
        }
        return 0;
    }
    public static void setFluidAmount(ItemStack stack, int amount) {
        int clampedAmount = Mth.clamp(amount, 0, MAX_PROGRESS);
        stack.update(DataComponents.CUSTOM_DATA, CustomData.EMPTY, customData -> {
            CompoundTag tag = customData.copyTag();
            tag.putInt("FluidAmount", clampedAmount);
            return CustomData.of(tag);
        });
    }
}