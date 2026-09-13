package mod.pushnyasha.rubymod.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import mod.pushnyasha.rubymod.items.MoltenRubyBucketItem;
import mod.pushnyasha.rubymod.init.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractFurnaceBlockEntity.class)
public abstract class FurnaceFuelRestrictionMixin {
    @Shadow protected NonNullList<ItemStack> items;
    @Shadow int litTime;
    @Shadow int litDuration;
    @Shadow int cookingProgress;
    @Shadow int cookingTotalTime;

    @Inject(method = "serverTick", at = @At("HEAD"), cancellable = true)
    private static void forceRubySmeltTick(Level level, BlockPos pos, BlockState state, AbstractFurnaceBlockEntity blockEntity, CallbackInfo ci) {
        FurnaceFuelRestrictionMixin mixin = (FurnaceFuelRestrictionMixin) (Object) blockEntity;
        ItemStack rawInput = mixin.items.get(0);
        ItemStack fuelStack = mixin.items.get(1);
        ItemStack resultStack = mixin.items.get(2);

        if (mixin.litTime <= 0 && rawInput.is(ModItems.RUBY_POWDER_ITEM) && fuelStack.is(Items.LAVA_BUCKET)) {
            boolean canStart = resultStack.isEmpty() || (resultStack.getItem() instanceof MoltenRubyBucketItem && MoltenRubyBucketItem.getFluidAmount(resultStack) < 64);
            
            if (canStart) {
                mixin.litTime = 20000;
                mixin.litDuration = 20000;
                mixin.items.set(1, new ItemStack(Items.BUCKET));
                blockEntity.setChanged();
            }
        }

        if (rawInput.is(ModItems.RUBY_POWDER_ITEM) && fuelStack.is(Items.BUCKET) && mixin.litTime > 0) {
            boolean canSmelt = resultStack.isEmpty() || (resultStack.getItem() instanceof MoltenRubyBucketItem && MoltenRubyBucketItem.getFluidAmount(resultStack) < 64);
            
            if (canSmelt) {
                mixin.cookingTotalTime = 100;
                mixin.cookingProgress += 2;
                
                if (mixin.cookingProgress >= mixin.cookingTotalTime) {
                    mixin.cookingProgress = 0;
                    mixin.executeRubyBucketBurn();
                    blockEntity.setChanged();
                }
                
                if (mixin.litTime > 0) {
                    mixin.litTime--;
                }
                
                ci.cancel();
                return;
            }
        }

        if (resultStack.getItem() instanceof MoltenRubyBucketItem) {
            int currentAmount = MoltenRubyBucketItem.getFluidAmount(resultStack);
            
            if (currentAmount > 0 && currentAmount < 64 && mixin.litTime <= 0) {
                if (rawInput.isEmpty()) {
                    mixin.items.set(0, new ItemStack(ModItems.RUBY_POWDER_ITEM, currentAmount));
                } else if (rawInput.is(ModItems.RUBY_POWDER_ITEM)) {
                    rawInput.grow(currentAmount);
                } else {
                    net.minecraft.world.Containers.dropItemStack(level, pos.getX(), pos.getY() + 1, pos.getZ(), new ItemStack(ModItems.RUBY_POWDER_ITEM, currentAmount));
                }
                
                mixin.items.set(1, new ItemStack(Items.LAVA_BUCKET));
                mixin.items.set(2, ItemStack.EMPTY);
                mixin.cookingProgress = 0;
                mixin.litTime = 0;
                mixin.litDuration = 0;
                blockEntity.setChanged();
            }
        }
    }

    private void executeRubyBucketBurn() {
        ItemStack rawInput = this.items.get(0);
        ItemStack fuelStack = this.items.get(1);
        ItemStack resultStack = this.items.get(2);
        boolean updated = false;

        if (resultStack.isEmpty()) {
            ItemStack newBucket = new ItemStack(ModItems.MOLTEN_RUBY_BUCKET_ITEM);
            MoltenRubyBucketItem.setFluidAmount(newBucket, 1);
            this.items.set(2, newBucket);
            updated = true;
        } else if (resultStack.getItem() instanceof MoltenRubyBucketItem) {
            int currentAmount = MoltenRubyBucketItem.getFluidAmount(resultStack);
            
            if (currentAmount < 64) {
                int nextAmount = currentAmount + 1;
                MoltenRubyBucketItem.setFluidAmount(resultStack, nextAmount);
                updated = true;
                
                if (nextAmount == 64) {
                    fuelStack.shrink(1);
                }
            }
        }
        
        if (updated) {
            rawInput.shrink(1);
        }
    }
}
